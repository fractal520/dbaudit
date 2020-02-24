package wtsd.dbaudit.Monitor.Wechat.Utils;


import org.springframework.util.StringUtils;
import wtsd.dbaudit.Monitor.Wechat.Exception.BizException;
import wtsd.dbaudit.Monitor.Wechat.Entity.RequestMessage;
import wtsd.dbaudit.Monitor.Wechat.Entity.WeiXinParames;

public class CheckParamsUtil {

    /**
     * 校验基本参数BaseMessage
     *
     * @param message
     */
    public static void checkBaseMessage(RequestMessage message) {
        //baseMessage不能为空
        if (null == message) {
            throw new BizException(EnumsUtils.BASEMESSAGE_ISEMPTY);
        }
        if (StringUtils.isEmpty(message.getMsgtype())) {
            throw new BizException(EnumsUtils.MSGTYPE_ISEMPTY);
        }
        if (null == message.getAgentid()) {
            throw new BizException(EnumsUtils.AGENTID_ISEMPTY);
        }
    }


    /**
     * 校验基本参数WeiXinParames
     *
     * @param weiXinParames
     */
    public static void checkWeiXinParames(WeiXinParames weiXinParames) {
        if (null == weiXinParames) {
            throw new BizException(EnumsUtils.WEIXINPARAMES_ISEMPTY);
        }
        if (StringUtils.isEmpty(weiXinParames.getCorpId())) {
            throw new BizException(EnumsUtils.CORPID_ISEMPTY);
        }
        if (StringUtils.isEmpty(weiXinParames.getAgentSecret())) {
            throw new BizException(EnumsUtils.AGENTSECRET_ISEMPTY);
        }
    }

}