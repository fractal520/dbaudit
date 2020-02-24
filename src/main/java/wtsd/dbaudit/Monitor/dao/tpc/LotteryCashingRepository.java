package wtsd.dbaudit.Monitor.dao.tpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.tpc.LotteryCashing;

import java.util.List;

@Repository
public interface LotteryCashingRepository extends JpaRepository<LotteryCashing,String> {

    @Query(value = "select t.company_id,t.num,t.id,c.create_time,c.ticket_number,c.winning_status from ( " +
            "select company_id,min(id) as id,count(*) as num" +
            "  from tb_biz_lottery_cashing" +
            " where winning_status='02' and company_id in ?1" +
            " group by company_id) t,tpc.tb_biz_lottery_cashing c" +
            " where t.id=c.id" +
            " order by id",
            nativeQuery = true)
    List<Object[]> LotteryCashingAccept(List<String> company);
}
