package wtsd.dbaudit.Monitor.Service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.Result.TransferAnomalyResult;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;
import wtsd.dbaudit.Monitor.Utils.WindThdTransfer;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class TransferCheck {

    @Autowired
    TransAnomalyDeal transAnomalyDeal;
    @Autowired
    WindThdTransfer windThdTransfer;

    public void check(Date check_time, String time_interval, Integer transfer_ytd, Integer transfer_td){

        TransferAnomalyResult transferAnomalyResult = new TransferAnomalyResult(
                check_time,time_interval,transfer_ytd,transfer_td);
        List<TransferAnomalyResult> transferList = Lists.newArrayList(transferAnomalyResult);
        int thd_value;
        float thd_percent;
        if (transfer_ytd == 0 ) {
            thd_value = windThdTransfer.getValue(0);
            thd_percent = windThdTransfer.getPercent(0);
        }
        else if (transfer_ytd > 0 && transfer_ytd<100*100) {
            thd_value = windThdTransfer.getValue(1);
            thd_percent = windThdTransfer.getPercent(1);
        }
        else if (transfer_ytd >= 100*100 && transfer_ytd<1000*100) {
            thd_value = windThdTransfer.getValue(2);
            thd_percent = windThdTransfer.getPercent(2);
        }
        else if (transfer_ytd >= 1000*100 && transfer_ytd<10000*100) {
            thd_value = windThdTransfer.getValue(3);
            thd_percent = windThdTransfer.getPercent(3);
        }
        else {
            thd_value = windThdTransfer.getValue(4);
            thd_percent = windThdTransfer.getPercent(4);
        }

        if ( transfer_ytd == 0 ) {
            log.info("昨日同时段无转账交易");
            log.info("今日同时段转账交易金额为:" + transfer_td.toString());
            //log.info("转账交易告警金额阀值为:" + thd_value);
            if (transfer_td >= thd_value) {
                log.info("转账交易异常，今日同时段转账交易金额超过阀值:" + thd_value);
                log.info("查看详情url:" + transferAnomalyResult.getAlarm_url());
                transAnomalyDeal.deal(TransTypeEnums.TRANSFER, transferList);
            }
        }
        else {
            log.info("昨日同时段转账交易金额为:" + transfer_ytd.toString());
            log.info("今日同时段转账交易金额为:" + transfer_td.toString());
            //log.info("转账交易告警金额阀值为:" + thd_value);
            Float percent = ((float) (transfer_td-transfer_ytd))/transfer_ytd;
            log.info("偏差率为:" + percent.toString());
            //log.info("转账交易告警偏差率阀值为:" + thd_percent);
            if( transfer_td >= thd_value && percent >= thd_percent ){
                log.info("转账交易异常，今日同时段转账交易金额超过阀值:" + thd_value +
                         "和偏差率超过阀值:" + thd_percent);
                log.info("查看详情url:" + transferAnomalyResult.getAlarm_url());
                transAnomalyDeal.deal(TransTypeEnums.TRANSFER, transferList);
            }
        }

    }

}
