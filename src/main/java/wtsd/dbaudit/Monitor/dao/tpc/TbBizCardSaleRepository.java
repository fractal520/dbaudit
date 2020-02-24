package wtsd.dbaudit.Monitor.dao.tpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizCardSale;

import java.util.Date;
import java.util.List;

@Repository
public interface TbBizCardSaleRepository extends JpaRepository<TbBizCardSale,String> {
    TbBizCardSale findByTranseq(String transeq);

    @Query(value = "select s.TRANSEQ,s.STATIONID,s.cardtypeno,s.price money,s.inserttime,s.STATUS,t.price " +
            "from tpc.tb_biz_card_sale s ,oms.tb_inf_card_type t " +
            "where inserttime >= ?1 and inserttime < ?2 " +
            "and s.cardtypeno = t.typeno and s.price <> t.price and status='00'",
            nativeQuery = true)
    List<Object[]> CardSaleAnomaly(Date begintime, Date endtime);
}
