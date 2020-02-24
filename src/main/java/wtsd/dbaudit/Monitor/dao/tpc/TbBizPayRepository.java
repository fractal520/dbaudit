package wtsd.dbaudit.Monitor.dao.tpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizPay;


@Repository
public interface TbBizPayRepository extends JpaRepository<TbBizPay, String> {
    TbBizPay findByPaytranseq(String paytranseq);
    TbBizPay findByCardid(String cardid);

/*    @Query(value = "select PAYTRANSEQ,TRANSEQ,CARDID,CITYNO,AMOUNT,PAYTIME,STATIONID,PAYTYPE,STATUS " +
            "from tb_biz_pay where PAYTIME between date_add(now(), interval - 5 minute) and now() and AMOUNT<10000 order by PAYTRANSEQ",
            nativeQuery = true)
    List<Object[]> findProbPay();*/


}

