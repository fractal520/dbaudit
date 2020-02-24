package wtsd.dbaudit.Monitor.dao.oms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.oms.TbInfStation;

import java.util.List;

@Repository
public interface TbInfStationRepository extends JpaRepository<TbInfStation,Integer> {
        List<TbInfStation> findByCompanyid(String companyid);
        List<TbInfStation> findByCompanyidIn(String companyids[]);
}
