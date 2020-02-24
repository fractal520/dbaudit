package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;

import java.util.List;

@Component
@Slf4j
public class TransCheck {
    @Autowired
    private TransAnomalyDeal transAnomalyDeal;

    public <T> void check(TransTypeEnums transType, List<T> transResultsList){
        T trans;

        if (transResultsList.isEmpty()){
            log.info("未查询到异常" + transType.getName());
        }
        else {
            log.info("发现"+transResultsList.size()+"笔疑似异常"+transType.getName());
            transResultsList.removeIf(e->SpecialTransCheck.specialcheck(transType, e));
            if (transResultsList.isEmpty()){
                log.info("未发现异常" + transType.getName());
            }
            else {
                transAnomalyDeal.deal(transType,transResultsList);
            }
        }
    }

}
