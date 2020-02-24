package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.Result.RechargeAnomalyResult;
import wtsd.dbaudit.Monitor.Result.TbInfStationResult;
import wtsd.dbaudit.Monitor.Utils.TransTypeEnums;

import java.util.List;

@Component
@Slf4j
public class SpecialTransCheck {
    private static TbInfStationResult tbInfStationResult;

    @Autowired
    public SpecialTransCheck(TbInfStationResult tbInfStationResult){
        SpecialTransCheck.tbInfStationResult = tbInfStationResult;
    }

    public static <T>  boolean specialcheck(TransTypeEnums transType, T trans){
        List<String> hangzStationIDs,shenyStationIDs,fuzStationIDs,suzStationIDs;
        hangzStationIDs = tbInfStationResult.getStationIDs("%杭州%");
        shenyStationIDs = tbInfStationResult.getStationIDs("%沈阳%");
        fuzStationIDs = tbInfStationResult.getStationIDs("%福州%");
        suzStationIDs = tbInfStationResult.getStationIDs("%苏州%");

        if (transType.getType() == "Recharge"){
            RechargeAnomalyResult rechargeAnomalyResult = (RechargeAnomalyResult) trans;
            log.info("疑似异常充值:"+ rechargeAnomalyResult);
            if (hangzStationIDs.contains(rechargeAnomalyResult.getstationid())){
                if (rechargeAnomalyResult.getbalance()+rechargeAnomalyResult.getamount()*2 == rechargeAnomalyResult.getafterbalance()){
                    log.info("排除异常充值;"+rechargeAnomalyResult +"为杭州优惠充值。");
                    return true;
                }
            }
            else if (shenyStationIDs.contains(rechargeAnomalyResult.getstationid())) {
                if (rechargeAnomalyResult.getbalance()+135 == rechargeAnomalyResult.getafterbalance()){
                    log.info("排除异常充值;"+rechargeAnomalyResult +"为沈阳月票充值。");
                    return true;
                }
            }
            else if (fuzStationIDs.contains(rechargeAnomalyResult.getstationid())) {
                if (rechargeAnomalyResult.getbalance() == rechargeAnomalyResult.getafterbalance()){
                    log.info("排除异常充值;"+rechargeAnomalyResult +"为福州月票充值。");
                    return true;
                }
            }
            else if (suzStationIDs.contains(rechargeAnomalyResult.getstationid())) {
                if (rechargeAnomalyResult.getbalance() == rechargeAnomalyResult.getafterbalance()){
                    log.info("排除异常充值;"+rechargeAnomalyResult +"为苏州月票充值。");
                    return true;
                }
            }
        }
        return false;
    }
}
