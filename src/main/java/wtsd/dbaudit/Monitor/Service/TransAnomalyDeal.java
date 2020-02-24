package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.MyMail.EmailService;
import wtsd.dbaudit.Monitor.Result.LotteryCashingResult;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;
import wtsd.dbaudit.Monitor.Utils.WTimeUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TransAnomalyDeal {
    @Autowired
    private EmailService emailService;
    @Autowired
    private WeiXinMessageSend weiXinMessageSend;

    @Value("${mail.toMail.addr}")
    private String toMailAddr;
    @Value("${wind.threshold.transfer.AlarmInterval}")
    private int transferAlarmInterval;
    @Value("${wind.threshold.lotteryCashing.AlarmInterval}")
    private  int lotteryCashingAlarmInterval;

    @Value("${wind.alarm.weiXinAddr.scss}")
    private String scssWeiXinAddr;
    @Value("${wind.alarm.weiXinAddr.lottery}")
    private String lotteryWeiXinAddr;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar ThisAlarmTime,transferLastAlarmTime;
    private Map<String, Calendar> lotteryCashingLastAlarmTime;

    public TransAnomalyDeal(){
        lotteryCashingLastAlarmTime = new HashMap<>();
        lotteryCashingLastAlarmTime.put("10000037", null);
        lotteryCashingLastAlarmTime.put("10000038", null);
        lotteryCashingLastAlarmTime.put("10000041", null);
        lotteryCashingLastAlarmTime.put("10000043", null);
        lotteryCashingLastAlarmTime.put("10000044", null);
    }

    public <T> void deal (TransTypeEnums transType, List<T> transResultsList){
        log.info(transResultsList.stream().map(e->"发现"+e).collect(Collectors.joining("\n")));
        if (transType == TransTypeEnums.TRANSFER){
            log.info("处理转账异常告警信息。");
            transferAlarm(transType,transResultsList);
        }
        else if (transType == TransTypeEnums.LOTTERYCASHING){
            log.info("处理彩票兑奖异常告警信息。");
            lotteryCashingAlarm(transType,transResultsList);
        }
        else {
            log.info("处理异常交易告警信息。");
            sendAlarm(transType,transResultsList);
        }
    }

    public <T> void transferAlarm(TransTypeEnums transType, List<T> transResultsList){
        ThisAlarmTime = new WTimeUtil().getMinutePrecision();
        log.info("transferThisAlarmTime:" + sdf.format(ThisAlarmTime.getTime()));
        if (transferLastAlarmTime != null){
            log.info("transferLastAlarmTime:" + sdf.format(transferLastAlarmTime.getTime()));
            long IntervalMinute = (ThisAlarmTime.getTimeInMillis() - transferLastAlarmTime.getTimeInMillis())/(1000 * 60);
            if ( IntervalMinute < transferAlarmInterval) {
                log.info(transType.getName()+"上次告警消息发送时间为：" + sdf.format(transferLastAlarmTime.getTime()) +
                        "，未超过告警消息发送间隔，本次不发送告警消息。");
            }
            else {
                transferLastAlarmTime.setTime(ThisAlarmTime.getTime());
                sendAlarm(transType,transResultsList);
            }
        }
        else {
            transferLastAlarmTime = Calendar.getInstance();
            transferLastAlarmTime.setTime(ThisAlarmTime.getTime());
            sendAlarm(transType,transResultsList);
        }
    }

    public <T> void lotteryCashingAlarm(TransTypeEnums transType, List<T> transResultsList){
        ThisAlarmTime = new WTimeUtil().getMinutePrecision();
        log.info("lotteryCashingThisAlarmTime:" + sdf.format(ThisAlarmTime.getTime()));
        Iterator<T> it = transResultsList.iterator();
        while (it.hasNext()){
            LotteryCashingResult trans = (LotteryCashingResult) it.next();
            String companyID= trans.getCompanyId();
            Calendar companyLastAlarmTime = lotteryCashingLastAlarmTime.get(companyID);
            if (companyLastAlarmTime != null){
                log.info(transType.getName() + trans.getcompanyName(companyID) + "lotteryCashingLastAlarmTime"
                        + ":" + sdf.format(companyLastAlarmTime.getTime()));
                long IntervalMinute = (ThisAlarmTime.getTimeInMillis() - companyLastAlarmTime.getTimeInMillis())/(1000 * 60);
                if ( IntervalMinute < lotteryCashingAlarmInterval) {
                    log.info(transType.getName() + trans.getcompanyName(companyID) + "上次告警消息发送时间为："
                            + sdf.format(companyLastAlarmTime.getTime()) +"，未超过告警消息发送间隔，本次不发送告警消息。");
                    it.remove();
                }
                else {
                    companyLastAlarmTime.setTime(ThisAlarmTime.getTime());
                }
            }
            else {
                companyLastAlarmTime = Calendar.getInstance();
                companyLastAlarmTime.setTime(ThisAlarmTime.getTime());
                lotteryCashingLastAlarmTime.put(companyID,companyLastAlarmTime);
            }
        }
        if (!transResultsList.isEmpty()){
            sendAlarm(transType,transResultsList);
        }
    }

    public <T> void sendAlarm(TransTypeEnums transType,List<T> transResultsList){
        String[] mailto =  toMailAddr.split(",");
        String[] lotteryWeiXin = lotteryWeiXinAddr.split(",");
        String[] scssWeiXin = scssWeiXinAddr.split(",");

        if (transType == TransTypeEnums.LOTTERYCASHING ||
                transType == TransTypeEnums.TRANSFER ||
                transType == TransTypeEnums.LOTTERY ){
            emailService.sendHtmlEmail(mailto,"异常交易",transResultsList,
                    transType.getType()+"EmailMessage.ftl");
            weiXinMessageSend.send(lotteryWeiXin[0], Integer.parseInt(lotteryWeiXin[1]),lotteryWeiXin[2],
                    transResultsList,transType.getType()+"WeiXinMessage.ftl");
        }
        else {
            emailService.sendHtmlEmail(mailto,"异常交易",transResultsList,
                    transType.getType()+"EmailMessage.ftl");
            weiXinMessageSend.send(scssWeiXin[0], Integer.parseInt(scssWeiXin[1]),scssWeiXin[2],
                    transResultsList,transType.getType()+"WeiXinMessage.ftl");
        }
    }

}
