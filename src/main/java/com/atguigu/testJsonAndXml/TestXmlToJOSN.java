package com.atguigu.testJsonAndXml;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/3 0003 - 14:45
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class TestXmlToJOSN {

    public static void main(String[] args)throws Exception {
        //将本地xml文件转化成json
       // String jsonStr = xmlToJson();
        //将json转换成xml
   String xml="<message>\n" +
           "<head>\n" +
           "\t<code>1009430255</code>\n" +
           "\t<stageId>go2Leq1wj5y8vuA5w7Azw</stageId>\n" +
           "\t<gradeId>26vYkWDVjhivNno6Kbz7ZM</gradeId>\n" +
           "\t<courseStageId>PcjbvAQ8h9KfZ8q6UZcw</courseStageId>\n" +
           "</head>\n" +
           "<body>\n" +
           "\t<app_head>\n" +
           "\t\t<name>ttt</name>\n" +
           "\t\t<id>WtGG1FhQSmqIQhKU8pMg</id>\n" +
           "\t</app_head>\n" +
           "\t<tett>8766</tett>\n" +
           "</body>\n" +
           "</message>";
        String json = xmlToJson(xml);
        System.out.println(json);


        String jsonStr2="{\n" +
                "  \"message\": {\n" +
                "    \"Sys_Head\": {\n" +
                "      \"SOURCE_TYPE\": \"namYYY\",\n" +
                "      \"BRANCH_ID\": \"UUU\"\n" +
                "    },\n" +
                "    \"Body\": { \"yyDs\": \"sass\" },\n" +
                "    \"userName\": \"\",\n" +
                "    \"userAge\": \"12\"\n" +
                "  }\n" +
                "}";
//        String jsonStr2="{\n" +
//                "        \"head\": {\n" +
//                "            \"code\": \"1009430255\", \n" +
//                "            \"stageId\": \"go2Leq1wj5y8vuA5w7Azw\", \n" +
//                "            \"gradeId\": \"26vYkWDVjhivNno6Kbz7ZM\", \n" +
//                "            \"courseStageId\": \"PcjbvAQ8h9KfZ8q6UZcw\"\n" +
//                "        }, \n" +
//                "        \"body\": {\n" +
//                "            \"app_head\": {\n" +
//                "                \"name\": \"张三\", \n" +
//                "                \"id\": \"WtGG1FhQSmqIQhKU8pMg\"\n" +
//                "            }, \n" +
//                "            \"tett\": \"8766\"\n" +
//                "        }\n" +
//                "    \n" +
//                "}";

        String xmlStr2 = XmlJsonUtils.jsonTo2Xml(jsonStr2);
        System.out.println(xmlStr2);

        String xmlToJson = xmlToJson(xmlStr2);
        System.out.println(xmlToJson);
        //String xmlStr = jsonToXml(jsonStr);
        //将json按照响应格式写入score2.xml
       // writeXmlToFile(xmlStr);

    }

    /**
     * xml转化为 JOSN
     * @return
     * @throws Exception
     */
    public static String xmlToJson(String xml) throws Exception{
        //使用DOM4j
//        SAXReader saxReader = new SAXReader();
//        //读取文件
//        Document read = saxReader.read("C:Users\\long\\Desktop\\httpAndDubbo\\students.xml");
//        //使用json的xml转json方法
        JSONObject jsonObject = XML.toJSONObject(xml);
        //设置缩进转为字符串
       // System.out.println(jsonObject.toString(3));
        return jsonObject.toString(3);
    }

    /**
     * XML写入到文件里
     * @param xmlStr
     * @throws Exception
     */
    public static void writeXmlToFile(String xmlStr) throws Exception{
        //将xmlstr转为文件形式
        Document document = DocumentHelper.parseText(xmlStr);
        //设置输出的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //构建输出流
        //C:\Users\long\Desktop\httpAndDubbo\students.xml
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("C:Users\\long\\Desktop\\httpAndDubbo\\students2.xml"), format);
        //不要转义字符
        xmlWriter.setEscapeText(false);
        //写入
        xmlWriter.write(document);
        //关闭流
        xmlWriter.close();
    }


    /**
     *  json转换成xml
     */
    public static String jsonToXml(String json){
        //输入流
        StringReader input = new StringReader(json);
        //输出流
        StringWriter output = new StringWriter();
        //构建配置文件
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
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
        if (output.toString().length() >= 38) {
//            System.out.println(output.toString().substring(39));
            return output.toString().substring(39);
        }
//        System.out.println(output);
        return output.toString();
    }
}
