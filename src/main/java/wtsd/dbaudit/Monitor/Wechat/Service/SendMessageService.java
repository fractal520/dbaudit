package wtsd.dbaudit.Monitor.Wechat.Service;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtsd.dbaudit.Monitor.Wechat.Exception.BizException;
import wtsd.dbaudit.Monitor.Wechat.Entity.AccessToken;
import wtsd.dbaudit.Monitor.Wechat.Entity.BaseMessage;
import wtsd.dbaudit.Monitor.Wechat.Entity.WeiXinParames;
import wtsd.dbaudit.Monitor.Wechat.Utils.EnumsUtils;
import wtsd.dbaudit.Monitor.Wechat.Utils.WeiXinUtil;

/**
 * @desc : 发送消息
 * @author:
 * @date : 2018-8-17 上午10:06:23
 */
public class SendMessageService {

    private static Logger log = LoggerFactory.getLogger(SendMessageService.class);

    private static final String SENDMESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";


    /**
     * @param accessToken
     * @param message     void
     * @desc ：0.公共方法：发送消息
     */
    public String sendMessage(String accessToken, BaseMessage message, WeiXinParames weiXinParames, int cycleSize) {
        //重试5次，超过五次直接失败
        cycleSize++;
        if(cycleSize > 5){
            throw new BizException(EnumsUtils.SENDMESSAGE_FAILED);
        }
        //存储不合法的userid
        String invaliduser = "";
        //1.获取json字符串：将message对象转换为json字符串
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);      //使用gson.toJson(user)即可将user对象顺序转成json
        log.info("请求发送消息的jsonTextMessage:" +  jsonMessage);

        //2.获取请求的url
        String sendMessageUrl = SENDMESSAGE_URL + accessToken;
        //3.调用接口，发送消息
        JSONObject jsonObject = WeiXinUtil.httpRequest(sendMessageUrl, "POST", jsonMessage);
        log.info("发送消息的返回jsonObject:" + jsonObject);

        //4.错误消息处理
        if (null != jsonObject) {
            int errcode = jsonObject.getInt("errcode");
            if (0 == errcode) {
                //批量发送推送消息时，可能存在部分用户发送消息成功，部分用户userid不合法的情侣
                if(!"".equals(jsonObject.getString("invaliduser"))){
                    log.warn("发送企业微信成功，部分用户userId不合法，不合法的userId = " + jsonObject.getString("invaliduser"));
                    invaliduser = jsonObject.getString("invaliduser");
                }else {
                    log.info("发送企业微信消息成功" + message);
                }
            }else {
                //如果是由于token失效,则替换老的token，重新调用
                if(40014 == errcode || 41001 == errcode || 42001 == errcode){
                    String key = weiXinParames.getCorpId() + "_" + weiXinParames.getAgentSecret();
                    //重新获取token之前,首先删除过期的token
                    WeiXinUtil.tokenMap.remove(key);
                    //再重新获取新token
                    AccessToken newToken = WeiXinUtil.getAccessToken(weiXinParames.getCorpId(), weiXinParames.getAgentSecret());
                    //重新发送消息
                    sendMessage(newToken.getToken(), message, weiXinParames, cycleSize);
                    //替换过期的token，所以先锁tokenMap不让别人用
                    synchronized (WeiXinUtil.tokenMap){
                        //替换新的token
                        WeiXinUtil.tokenMap.put(key, newToken);
                    }
                }else {
                    log.error("发送企业微信消息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                    throw new BizException(jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                }
            }
        }else {
            log.info("请求发送企业微信消息返回为空，requestUrl =" + sendMessageUrl);
            throw new BizException(EnumsUtils.SENDMESSAGE_FAILED);
        }
        return invaliduser;
    }
}