package wtsd.dbaudit.Monitor.dao.tpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizRecharge;

import java.util.Date;
import java.util.List;

@Repository
public interface TbBizRechargeRepository extends JpaRepository<TbBizRecharge,String> {
    TbBizRecharge findByTranseq(String transeq);

    /*    @Query(value = "select new wtsd.dbaudit.Monitor.Result.RechargeAnomalyResult(t.paytranseq,t.transeq,t.cardid,t.stationid,t.amount,t.balance,t.afterbalance,t.transtime,t.status) " +
            "from TbBizRecharge t where t.transtime between ?1 and ?2 " +
            "and t.amount+t.balance <> t.afterbalance and t.status='00'")
    List<RechargeAnomalyResult> RechargeAnomaly(Date begintime, Date endtime);*/

    @Query(value = "select t.paytranseq,t.transeq,t.cardid,t.stationid,t.amont,t.balance,t.afterbalance,t.transtime,t.status " +
            "from tpc.tb_biz_recharge t where t.transtime >= ?1 and t.transtime < ?2 " +
            "and t.amont+t.balance <> t.afterbalance and t.status='00' ",nativeQuery = true)
    List<Object[]> RechargeAnomaly(Date begintime, Date endtime);
}
