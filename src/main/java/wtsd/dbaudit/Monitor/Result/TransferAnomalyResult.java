package wtsd.dbaudit.Monitor.Result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import wtsd.dbaudit.DbauditApplication;
import wtsd.dbaudit.Monitor.Utils.WindAlarmUrl;

import java.util.Date;

@Data
@Slf4j
public class TransferAnomalyResult {
    private Date check_time;
    private String time_interval;
    private Integer transfer_ytd;
    private Integer transfer_td;
    private Float percent;
    private String alarm_url;


    public TransferAnomalyResult(Date check_time, String time_interval, Integer transfer_ytd,
                                 Integer transfer_td){
        this.check_time = check_time;
        this.time_interval = time_interval;
        this.transfer_ytd = transfer_ytd;
        this.transfer_td = transfer_td;
        WindAlarmUrl windAlarmUrl = DbauditApplication.getApplicationContext().getBean(WindAlarmUrl.class);
        this.alarm_url = windAlarmUrl.transfer;
    }

    public float getPercent(){
        percent = ((float) (transfer_td-transfer_ytd))/transfer_ytd;
        return (float)(Math.round(percent*100))/100;
    }
/*
    public String getAlarm_url(){
        log.info("url is :" + configurationDbaudit.transfer);
        return alarm_url = configurationDbaudit.transfer;
    }
*/
    @Override
    public String toString(){
        return "转账异常:[时间区间="+ time_interval +",昨日转账金额="+ transfer_ytd +
                ",今日转账金额="+ transfer_td + "]";
    }

}
