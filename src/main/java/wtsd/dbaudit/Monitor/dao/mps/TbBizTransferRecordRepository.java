package wtsd.dbaudit.Monitor.dao.mps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.mps.TbBizTransferRecord;

import java.util.Date;

@Repository
public interface TbBizTransferRecordRepository extends JpaRepository<TbBizTransferRecord,String> {

    @Query(value = "SELECT COALESCE(SUM(TRANSFERAMOUNT),0) AS TRANSFERAMOUNT " +
            "FROM mps.tb_biz_transfer_records " +
            "WHERE `TRANSFERTIME` >= ?1 AND `TRANSFERTIME` < ?2 and STATUS = '00' " ,
            nativeQuery = true)
    Object TransferAmount(Date begintime, Date endtime);

    @Query(value = "SELECT COALESCE(SUM(TRANSFERAMOUNT),0) AS TRANSFERAMOUNT " +
            "FROM mps.tb_biz_transfer_records " +
            "WHERE `TRANSFERTIME` >= ?1 AND `TRANSFERTIME` < ?2 and TRANSTYPE='11' and STATUS = '00' " ,
            nativeQuery = true)
    Object TransferAmountLotteryCashing(Date begintime, Date endtime);
}


