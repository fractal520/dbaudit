package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.Result.LotteryCashingResult;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class LotteryCashingCheck {

    @Autowired
    TransAnomalyDeal transAnomalyDeal;
    @Value("${wind.threshold.lotteryCashing.timeDelay}")
    private int timeDelay;

    private Calendar createTime,currentTime;

    public void check(TransTypeEnums transType, List<LotteryCashingResult> lotteryCashingResultsList){
        LotteryCashingResult trans;
        if (lotteryCashingResultsList.isEmpty()){
            log.info("未查询到延迟兑奖记录");
        }
        else {
            log.info("查询到"+lotteryCashingResultsList.size()+ "个发行方可能存在延迟兑奖记录。");
            Iterator<LotteryCashingResult> it = lotteryCashingResultsList.iterator();
            while (it.hasNext()){
                trans = it.next();
                if ( !istimedelay(trans)){
                    it.remove();
                    log.info(trans.getcompanyName(trans.getCompanyId()) +"的延迟兑奖记录未超过阀值。");
                }
            }
            if (lotteryCashingResultsList.isEmpty()){
                log.info("未发现延迟兑奖记录");
            }
            else {
                transAnomalyDeal.deal(transType,lotteryCashingResultsList);
            }

        }
    }

    private boolean istimedelay(LotteryCashingResult trans){
        long IntervalMinute = trans.getTimeDelay();
        if (IntervalMinute > timeDelay){
            return true;
        }
        else {
            return false;
        }

    }
}
