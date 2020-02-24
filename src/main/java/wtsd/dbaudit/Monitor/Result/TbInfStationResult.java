package wtsd.dbaudit.Monitor.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.dao.oms.TbInfCompanyRepository;
import wtsd.dbaudit.Monitor.dao.oms.TbInfStationRepository;
import wtsd.dbaudit.Monitor.db.oms.TbInfCompany;
import wtsd.dbaudit.Monitor.db.oms.TbInfStation;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TbInfStationResult {
    @Autowired
    private TbInfCompanyRepository tbInfCompanyRepository;
    @Autowired
    private TbInfStationRepository tbInfStationRepository;

    private List<String> CompanyIdList,StationIdList;
    private List<TbInfCompany> TbInfCompanyList;
    private List<TbInfStation> TbInfStationList;

    public List<String> getStationIDs(String cityName) {
        TbInfCompanyList = tbInfCompanyRepository.findByNameLike(cityName);
        CompanyIdList =  TbInfCompanyList.stream().map(
                tbInfCompany -> tbInfCompany.getCompanyid()).collect(Collectors.toList());
        log.debug(cityName+"企业ID为:" + CompanyIdList.stream().collect(Collectors.joining(",")));

        TbInfStationList = tbInfStationRepository.findByCompanyidIn(
                (CompanyIdList.toArray(new String[CompanyIdList.size()])));
        StationIdList = TbInfStationList.stream().map(tbInfStation -> tbInfStation.getStatioinid()).collect(Collectors.toList());
        log.debug(cityName+"站点数为:" + StationIdList.size());

        return StationIdList;
    }
}
