package zxy.weixin.qyh.utils;

import zxy.weixin.qyh.domain.receive.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;

/**
 * 微信接收的数据转换器
 */
public class WeixinXmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(WeixinXmlUtil.class);

    public static BaseReceiveObject xmlToObject(String xml) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("receiveStringToObject ParserConfigurationException source:({}), error:", xml, e);
            return null;
        }
        StringReader sr = new StringReader(xml);
        InputSource is = new InputSource(sr);
        Document document;
        try {
            document = db.parse(is);
        } catch (Exception e) {
            logger.error("receiveStringToObject parse source:({}), error:", xml, e);
            return null;
        }

        Element root = document.getDocumentElement();
        String msgType = getTextFromDocument(root, "MsgType");
        BaseReceiveObject baseReceiveObject;
        if (BaseEvent.MSG_TYPE.equals(msgType)) {
            baseReceiveObject = documentToEvent(root);
        } else {
            baseReceiveObject = documentToMessage(root, msgType);
        }

        if (baseReceiveObject != null) {
            baseReceiveObject.setFromUserName(getTextFromDocument(root, "FromUserName"));
            baseReceiveObject.setToUserName(getTextFromDocument(root, "ToUserName"));
            baseReceiveObject.setMsgType(msgType);
            baseReceiveObject.setCreateTime(NumberUtils.toInt(getTextFromDocument(root, "CreateTime")));
            baseReceiveObject.setAgentID(NumberUtils.toInt(getTextFromDocument(root, "AgentID")));
        }

        return baseReceiveObject;
    }

    private static BaseMessage documentToMessage(Element root, String msgType) {
        if (root == null || StringUtils.isBlank(msgType)) {
            return null;
        }

        BaseMessage message = null;
        switch (msgType) {
            case MessageText.MSG_TYPE:
                MessageText messageText = new MessageText();
                messageText.setContent(getTextFromDocument(root, "Content"));
                message = messageText;
                break;

            case MessageImage.MSG_TYPE:
                MessageImage messageImage = new MessageImage();
                messageImage.setPicUrl(getTextFromDocument(root, "PicUrl"));
                messageImage.setMediaId(getTextFromDocument(root, "MediaId"));
                message = messageImage;
                break;
        }

        if (message != null) {
            message.setMsgId(NumberUtils.toLong(getTextFromDocument(root, "MsgId")));
        }
        return message;
    }

    private static BaseEvent documentToEvent(Element root) {
        if (root == null) {
            return null;
        }

        String event = getTextFromDocument(root, "Event");
        if (StringUtils.isBlank(event)) {
            return null;
        }

        BaseEvent baseEvent = null;
        switch (event) {
            case EventEnterAgent.EVENT:
                EventEnterAgent enterAgent = new EventEnterAgent();
                baseEvent = enterAgent;
                break;

            case EventMenuMessage.EVENT:
                EventMenuMessage eventMenuMessage = new EventMenuMessage();
                baseEvent = eventMenuMessage;
                break;

            case EventMenuWeb.EVENT:
                EventMenuWeb eventMenuWeb = new EventMenuWeb();
                baseEvent = eventMenuWeb;
                break;

            case EventLocation.EVENT:
                EventLocation location = new EventLocation();
                location.setLatitude(NumberUtils.toDouble(getTextFromDocument(root, "Latitude")));
                location.setLongitude(NumberUtils.toDouble(getTextFromDocument(root, "Longitude")));
                location.setPrecision(NumberUtils.toDouble(getTextFromDocument(root, "Precision")));
                baseEvent = location;
                break;
        }

		if (null != baseEvent) {
			baseEvent.setEvent(event);
			baseEvent.setEventKey(getTextFromDocument(root, "EventKey"));
		}
        return baseEvent;
    }

    private static String getTextFromDocument(Element root, String key) {
        if (root == null || StringUtils.isBlank(key)) {
            return null;
        }
        NodeList nodeList = root.getElementsByTagName(key);
        if (nodeList == null) {
            return null;
        }
        if (nodeList.getLength() < 1) {
            return null;
        }
        return nodeList.item(0).getTextContent();
    }

    public static String generateMessageText(String corpId, String userid, String content) {
        String format = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[%s]]></Content>" +
                "</xml>";
        return String.format(format, userid, corpId, System.currentTimeMillis() / 1000, content);
    }

}
