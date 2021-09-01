package com.atguigu.testJsonAndXml;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlJsonUtils {

    /**
     * JSON(数组)字符串转换成XML字符串
     */
    public static String jsonToxml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<o>", "")
                .replace("</o>", "")
                .replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }
    /**
     * xml 转 json
     */
    public static String xmlTojson(String xmlString) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xmlString);

            return jsonObject.toString(10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}