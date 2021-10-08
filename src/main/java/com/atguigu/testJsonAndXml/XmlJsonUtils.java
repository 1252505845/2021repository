package com.atguigu.testJsonAndXml;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlJsonUtils {

    /**
     * JSON(数组)字符串转换成XML字符串
     */
    public static String jsonToxml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<e>", "")
                .replace("</e>", "")
                .replace("<o>", "")
                .replace("</o>", "")
                .replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }


    /**
     * json转换成xml
     */
    public static String jsonTo2Xml(String json){
        //输入流
        StringReader input = new StringReader(json);
        //输出流
        StringWriter output = new StringWriter();
        //构建配置文件
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false)
                .repairingNamespaces(false).build();
        try {
            //xml事件读
            //  This is the top level interface for parsing XML Events.  It provides
            //  the ability to peek at the next event and returns configuration
            //  information through the property interface.
            // 这是最解析XML事件最顶层的接口，它提供了查看下一个事件并通过属性界面返回配置信息的功能。
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            //这是编写XML文档的顶级界面。
            //验证XML的形式不需要此接口的实例。
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            //创建一个实例使用默认的缩进和换行
            writer = new PrettyXMLEventWriter(writer);
            //添加整个流到输出流，调用next方法，知道hasnext返回false
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //移除头部标签
//        if (output.toString().length() >= 38) {
//            System.out.println(output.toString().substring(39));
//            return output.toString().substring(39);
//        }
        System.out.println("output---"+output);
        System.out.println("end");
        return output.toString();
    }

    /**
     * xml 转 json
     */
    public static String xmlTojson(String xmlString) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xmlString);

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}