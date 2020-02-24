package wtsd.dbaudit.Monitor.db.oms;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "tb_inf_station")
@Data
public class TbInfStation {
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="STATIOINID")
    private String statioinid;
    @Column(name="NAME")
    private String name;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="COMPANYID")
    private String companyid;
    @Column(name="CHANELCODE")
    private String chanelcode;
    @Column(name="STATIONCODE")
    private String stationcode;
    @Column(name="STATIONTYPE")
    private String stationtype;
    @Column(name="MANAGER")
    private String manager;
    @Column(name="MANAGERTEL")
    private String managertel;
    @Column(name="TELEPHONE")
    private String telephone;
    @Column(name="EMAIL")
    private String email;
    @Column(name="WORKTIME")
    private String worktime;
    @Column(name="BUS")
    private String bus;
    @Column(name="BUSSTATION")
    private String busstation;
    @Column(name="FLAGBUILD")
    private String flagbuild;
    @Column(name="VALID")
    private String valid;
    @Column(name="LASTONLINE")
    private Date lastonline;
    @Column(name="FACTORYNO")
    private String factoryno;
    @Column(name="VERSION")
    private String version;
    @Column(name="CARDUPDATETIME")
    private Date cardupdatetime;
    @Column(name="MONEYUPDATETIME")
    private Date moneyupdatetime;
    @Column(name="MONEYBOX")
    private String moneybox;
    @Column(name="BEFORELIMIT")
    private Integer beforeLimit;
    @Column(name="DLLVERSION")
    private String dllVersion;
    @Column(name="START_WORK_TIME")
    private String startWorkTime;
    @Column(name="END_WORK_TIME")
    private String endWorkTime;
    @Column(name="SHOULD_MONITOR")
    private Integer shouldMoniter;
    @Column(name="LINESID")
    private String linesId;
    @Column(name="group_id")
    private BigInteger group_id;
    @Column(name="grade")
    private String grade;
    @Column(name="visit_time")
    private String visitTime;
    @Column(name="visit_num")
    private Integer visitNum;
    @Column(name="deposit_code")
    private String depositCode;
    @Column(name="stock_days")
    private Double stockDays;
    @Column(name="min_stock")
    private Integer minStock;	//最少配卡数
    @Column(name="x")
    private Double x;
    @Column(name="y")
    private Double y;
    @Column(name="stock_min")
    private Integer stockMin;	//最小库存数
    @Column(name="status")
    private String status;  //站点状态  1：未启用  2：验收中  3：运营中
    @Column(name="env")
    private String env;  //站点环境   1：内部   2：外部
    @Column(name="city_name")
    private String cityName;	//城市名称
    @Column(name="linestation_name")
    private String linestationName;	//线路站点名称
    @Column(name="cycle")
    private Integer cycle;	//拜访周期，单位：周
    @Column(name="start_operation_time")
    private Date startOperationTime; 	//设备开始运营时间
    @Column(name="stock_limit")
    private Integer stockLimit;	//卡道库存小于该值时，配卡计算时必须配卡

}
