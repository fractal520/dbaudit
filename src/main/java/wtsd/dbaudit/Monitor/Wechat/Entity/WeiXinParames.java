package wtsd.dbaudit.Monitor.Wechat.Entity;

import lombok.Data;

@Data
public class WeiXinParames {

    //企业ID
    private String corpId;
    //应用密钥
    private String agentSecret;

    //企业应用的id，整型。可在应用的设置页面查看
    //private  int agentId;


}