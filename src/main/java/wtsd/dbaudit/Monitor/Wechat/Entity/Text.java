package wtsd.dbaudit.Monitor.Wechat.Entity;

import lombok.Data;

@Data
public class Text {
    //是  消息内容，最长不超过2048个字节
    private String content;
}