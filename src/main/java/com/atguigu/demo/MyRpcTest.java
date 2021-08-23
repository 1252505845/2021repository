package com.atguigu.demo;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/23 0023 - 20:33
 * @Description: com.atguigu.demo
 * @version: 1.0
 */


public class MyRpcTest {

    //模拟客户端
  public  void get(){
      Car car =proxyGet(Car.class);//动态代理实现
      car.ooxx("sasas");

      Fly fly =proxyGet(Fly.class);//动态代理实现
      fly.xxoo("sasas");


  }

    public  static  <T>T proxyGet(Class<T> interfaceInfo){

        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?> []methodInfo ={interfaceInfo};

        return (T)Proxy.newProxyInstance(loader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {

                //如何设计我们的consumer对于provider的调用过程

                //1，调用 服务，方法，参数  ==》 封装成message  [content]

                String name = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();

                MyContent content=new MyContent();

                content.setArgs(args);
                content.setName(name);
                content.setMethodName(methodName);
                content.setParameterTypes(parameterTypes);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(content);
                byte[] msgBody = out.toByteArray();

                //2，requestID+message  ，本地要缓存
                //协议：【header<>】【msgBody】
               // Myheader header = createHeader(msgBody);




                //3，连接池：：取得连接


                //4，发送--> 走IO  out -->走Netty（event 驱动）



                //5，？，如果从IO ，未来回来了，怎么将代码执行到这里
                //（睡眠/回调，如何让线程停下来？你还能让他继续。。。）




                return null;
            }
        });

    }

}







//test之外
class Myheader implements Serializable{
    //通信上的协议
    /*
    1，ooxx值
    2，UUID:requestID
    3，DATA_LEN

     */
    int flag;  //32bit可以设置很多信息。。。
    long requestID;
    long dataLen;


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public long getDataLen() {
        return dataLen;
    }

    public void setDataLen(long dataLen) {
        this.dataLen = dataLen;
    }
}


class MyContent implements Serializable {

    String name;
    String methodName;
    Class<?>[] parameterTypes;
    Object[] args;
    String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}



interface Car{
    public String ooxx(String msg);
}
interface Fly{
    void xxoo(String msg);
}


