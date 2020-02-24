package wtsd.dbaudit.Monitor.Wechat.Exception;

import lombok.Data;
import wtsd.dbaudit.Monitor.Wechat.Utils.EnumsUtils;

@Data
public class BizException extends RuntimeException {

    private int code;
    private String msg;
    private EnumsUtils enumsUtils;

    public BizException(EnumsUtils enumsUtils) {
        this.enumsUtils = enumsUtils;
    }

    public BizException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}