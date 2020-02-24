package wtsd.dbaudit.Monitor.Wechat.Entity;

import lombok.Data;

import java.util.List;

@Data
public class RequestMessage {
    // 否 成员ID列表
    private List<String> touser;
    // 否 部门ID列表
    private List<String> toparty;
    // 否 标签ID列表
    private List<String> totag;
    // 是 消息类型
    private String msgtype;
    // 是 企业应用的id，整型。可在应用的设置页面查看
    private Integer agentid;
    //文本
    private Text text;
    //否     表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
}