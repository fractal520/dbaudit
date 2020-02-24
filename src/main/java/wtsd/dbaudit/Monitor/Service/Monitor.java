package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.Result.CardSaleAnomalyResult;
import wtsd.dbaudit.Monitor.Result.LotteryAnomalyResult;
import wtsd.dbaudit.Monitor.Result.LotteryCashingResult;
import wtsd.dbaudit.Monitor.Result.RechargeAnomalyResult;
import wtsd.dbaudit.Monitor.Utils.EntityUtils;
import wtsd.dbaudit.Monitor.Utils.LotteryCashingCompanyMap;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;
import wtsd.dbaudit.Monitor.dao.mps.TbBizTransferRecordRepository;
import wtsd.dbaudit.Monitor.dao.tpc.LotteryCashingRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizCardSaleRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizLotteryRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizRechargeRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Monitor {
    @Autowired
    private TbBizRechargeRepository tbBizRechargeRepository;
    @Autowired
    private TbBizCardSaleRepository tbBizCardSaleRepository;
    @Autowired
    private TbBizLotteryRepository tbBizLotteryRepository;
    @Autowired
    private TbBizTransferRecordRepository tbBizTransferRecordRepository;
    @Autowired
    private LotteryCashingRepository lotteryCashingRepository;
    @Autowired
    private TransCheck transCheck;
    @Autowired
    private TransferCheck transferCheck;
    @Autowired LotteryCashingCheck lotteryCashingCheck;

    private List<Object[]> rechargelist,cardsalelist,lotterylist,lotterycashinglist;
    private Integer transfer_td,transfer_ytd;

    public void monitorProbPay(){
        CheckTime checkTime = new CheckTime();
        Date endtime = checkTime.getEndtime();
        Date begintime = checkTime.getBegintime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("本次检查的时间区间为:" + sdf.format(begintime) + '至' +sdf.format(endtime));

        rechargelist = tbBizRechargeRepository.RechargeAnomaly(begintime,endtime);
        List<RechargeAnomalyResult> rechargeAnomalyResultsList = EntityUtils.castEntity(rechargelist, RechargeAnomalyResult.class, new RechargeAnomalyResult());

        cardsalelist = tbBizCardSaleRepository.CardSaleAnomaly(begintime,endtime);
        List<CardSaleAnomalyResult> cardSaleAnomalyResultsList = EntityUtils.castEntity(cardsalelist, CardSaleAnomalyResult.class, new CardSaleAnomalyResult());

        lotterylist = tbBizLotteryRepository.LotteryAnomaly(begintime,endtime);
        List<LotteryAnomalyResult> lotteryAnomalyResultsList = EntityUtils.castEntity(lotterylist, LotteryAnomalyResult.class, new LotteryAnomalyResult());

        transCheck.check(TransTypeEnums.RECHARGE, rechargeAnomalyResultsList);
        transCheck.check(TransTypeEnums.CARDSALE, cardSaleAnomalyResultsList);
        transCheck.check(TransTypeEnums.LOTTERY, lotteryAnomalyResultsList);

    }

    public void monitorProbTransfer(){
        Date check_time,today_begintime,today_endtime,yesterday_begintime,yesterday_endtime;
        String time_interval;

        Calendar d1= Calendar.getInstance();
        check_time=d1.getTime();

        d1.add(Calendar.MINUTE,-1);  //减1分钟，查询上一分钟所在的时间区间的记录。
        d1.set(d1.get(d1.YEAR),d1.get(d1.MONTH),d1.get(d1.DATE),d1.get(d1.HOUR_OF_DAY),0,0);
        d1.set(Calendar.MILLISECOND,0);

        today_begintime = d1.getTime();
        d1.add(Calendar.HOUR_OF_DAY,1);
        today_endtime = d1.getTime();

        Calendar d2=d1;
        d2.add(Calendar.DATE,-1);
        yesterday_endtime  = d2.getTime();
        d2.add(Calendar.HOUR_OF_DAY,-1);
        yesterday_begintime = d2.getTime();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("本次转账交易检查的时间区间为:" + sdf1.format(today_begintime) + '至' + sdf1.format(today_endtime) +
                 "对比的时间区间为:" + sdf1.format(yesterday_begintime) + '至' + sdf1.format(yesterday_endtime));

        transfer_td = Integer.parseInt(tbBizTransferRecordRepository.TransferAmountLotteryCashing(
                today_begintime,today_endtime).toString());
        transfer_ytd = Integer.parseInt(tbBizTransferRecordRepository.TransferAmountLotteryCashing(
                yesterday_begintime,yesterday_endtime).toString());

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        time_interval = sdf2.format(today_begintime) + " - " + sdf2.format(today_endtime);

        transferCheck.check(check_time,time_interval,transfer_ytd,transfer_td);

    }

    public void monitorLotteryCashing(){
        lotterycashinglist = lotteryCashingRepository.LotteryCashingAccept(
                new ArrayList<>(LotteryCashingCompanyMap.companyMap.keySet()));
        List<LotteryCashingResult> lotteryCashingResultsList = EntityUtils.castEntity(lotterycashinglist, LotteryCashingResult.class, new LotteryCashingResult());
        lotteryCashingCheck.check(TransTypeEnums.LOTTERYCASHING,lotteryCashingResultsList);
    }

}
