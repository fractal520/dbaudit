package wtsd.dbaudit.Monitor.Wechat.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtsd.dbaudit.Monitor.Wechat.Entity.RequestMessage;
import wtsd.dbaudit.Monitor.Wechat.Entity.ResultJson;
import wtsd.dbaudit.Monitor.Wechat.Entity.TextMessage;
import wtsd.dbaudit.Monitor.Wechat.Entity.WeiXinParames;
import wtsd.dbaudit.Monitor.Wechat.Exception.BizException;
import wtsd.dbaudit.Monitor.Wechat.Service.SendMessageService;

/**
 * 微信发送消息工具类
 */
public class SendWeiXinUtils {

    //发送企业微信消息的service
    private final static SendMessageService SMS = new SendMessageService();
    //企业微信发送请求接口的连接符
    private final static String SYMBOL = "|";

    private static Logger log = LoggerFactory.getLogger(SendWeiXinUtils.class);


    /**
     * 发送企业微信消息
     *
     * @param message
     */
    public static ResultJson sendWeiXinMessage(RequestMessage message, WeiXinParames weiXinParames) {
        log.info("发送企业微信消息开始，message={},params ={}", message, weiXinParames);
        ResultJson resultJson = null;
        TextMessage textMessage = null;
        try {
            //1、校验BaseMessage和weiXinParames
            CheckParamsUtil.checkBaseMessage(message);
            CheckParamsUtil.checkWeiXinParames(weiXinParames);
            //RequestMessage转换BaseMessage
            textMessage = convertTextMessage(message);
            //1.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
            String accessToken = WeiXinUtil.getAccessToken(weiXinParames.getCorpId(), weiXinParames.getAgentSecret()).getToken();
            //发送消息
            SMS.sendMessage(accessToken, textMessage, weiXinParames, 1);
            resultJson = new ResultJson(EnumsUtils.SUCCESS);
            log.info("发送企业微信消息成功，response = " + resultJson);
        } catch (BizException e) {
            log.warn("发送企业微信消息失败，code ={},msg ={}", e.getEnumsUtils().getCode(), e.getEnumsUtils().getMsg());
            resultJson = new ResultJson(e.getEnumsUtils().getCode(), e.getEnumsUtils().getMsg());
        }
        return resultJson;
    }

    /**
     * RequestMessage转换BaseMessage
     *
     * @param message
     * @return
     */
    public static TextMessage convertTextMessage(RequestMessage message) {
        TextMessage textMessage = new TextMessage();
        //转换成员列表
        textMessage.setTouser(StringUtils.connectBySymbol(message.getTouser(), SYMBOL));
        //转换部门列表
        textMessage.setToparty(StringUtils.connectBySymbol(message.getToparty(), SYMBOL));
        //转换标签列表
        textMessage.setTotag(StringUtils.connectBySymbol(message.getTotag(), SYMBOL));
        textMessage.setMsgtype(message.getMsgtype());
        textMessage.setAgentid(message.getAgentid());
        textMessage.setText(message.getText());
        textMessage.setSafe(message.getSafe());
        return textMessage;
    }
}