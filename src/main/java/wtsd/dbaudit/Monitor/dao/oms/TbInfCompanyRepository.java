package wtsd.dbaudit.Monitor.dao.oms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wtsd.dbaudit.Monitor.db.oms.TbInfCompany;

import java.util.List;

@Repository
public interface TbInfCompanyRepository extends JpaRepository<TbInfCompany,Integer> {
    List<TbInfCompany> findByNameLike(String name);

    @Query(value = "select COMPANYID from tb_inf_company where name like ?1 ",
            nativeQuery = true)
    List<Object[]> findhangzCompanyIDs(String name);
}
