package com.atguigu.httpAndDubbo;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 18:55
 * @Description: com.atguigu.httpAndDubbo
 * @version: 1.0
 */


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DubboToHttpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(DubboToHttpServlet.class);

    private ApplicationConfig application = new ApplicationConfig("dubbo-to-http");
    private Map<String, GenericService> serviceCache = new HashMap<String, GenericService>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("servlet start");

        // 1. 解析请求体
        logger.info("parsing request body");
        JSONObject requestJson = JSON.parseObject(IOUtils.read(new InputStreamReader(request.getInputStream())));
        logger.info("parsed request body. body json: {}", requestJson);

        // 2. 获取泛化服务接口
        logger.info("fetching generic service");
        GenericService service = this.fetchGenericService(requestJson);
        logger.info("fetched generic service. service: {}", service);

        // 3. 组装调用参数
        String method = requestJson.getString("method");
        String[] parameterTypes = this.toArray(requestJson.getJSONArray("paramTypes"));
        Object[] args = requestJson.getJSONArray("paramValues").toArray(new Object[] {});

        // 4. 调用接口
        logger.info("invoking remote service");
        String result = JSON.toJSONString(service.$invoke(method, parameterTypes, args));
        logger.info("invoked remote service. return: {}", result);

        // 5. 返回
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(result).flush();
        out.close();

        logger.info("servlet end");
    }

//    @Override
//    public void destroy() {
//        RegistryConfig.destroyAll();
//    }

    // 获取泛化服务接口. 如有缓存, 从缓存取
    private GenericService fetchGenericService(JSONObject requestJson) {
        String serviceInterface = requestJson.getString("interface");
        String serviceGroup = requestJson.getString("group");
        String serviceVersion = requestJson.getString("version");

        String serviceCacheKey = serviceInterface + serviceGroup + serviceVersion;
        GenericService service = serviceCache.get(serviceCacheKey);
        if (service != null) {
            logger.info("fetched generic service from cache");
            return service;
        }

        logger.info("initing generic service");
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(application);
        reference.setInterface(serviceInterface);
        reference.setGroup(serviceGroup);
        reference.setVersion(serviceVersion);
        reference.setGeneric(true);
        service = reference.get();
        serviceCache.put(serviceCacheKey, service);

        return service;
    }

    // List<Object> -> String[]
    private String[] toArray(List<Object> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).toString();
        }
        return array;
    }
}