package wtsd.dbaudit.Monitor.dao.tpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizLottery;

import java.util.Date;
import java.util.List;

@Repository
public interface TbBizLotteryRepository extends JpaRepository<TbBizLottery,String> {
    TbBizLottery findByTranseq(String transeq);

    @Query(value = "select l.TRANSEQ,l.STATIONID,l.cardtypeno,l.quantity,l.amount,l.inserttime,l.STATUS,t.price " +
            "from tpc.tb_biz_lottery l ,oms.tb_inf_card_type t " +
            "where inserttime >= ?1 and inserttime < ?2 " +
            "and l.cardtypeno = t.typeno and l.amount <> l.quantity*t.price and status='00'",
            nativeQuery = true)
    List<Object[]> LotteryAnomaly(Date begintime, Date endtime);
}
