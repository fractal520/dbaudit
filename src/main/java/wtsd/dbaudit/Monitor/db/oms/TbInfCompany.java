package wtsd.dbaudit.Monitor.db.oms;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tb_inf_company")
@Data
public class TbInfCompany {
    @Id
    @Column(name="ID")
    private Integer id;
    private String companyid;
    private String name;
    private String phone;
    private String addr;
    private String email;
    private Date addtime;
    private Integer canadjustmoney;
    private Integer ratedmoney;
    private String  citycardno;
    private String legalperson;
    private String buslicense;
    private String explains;
    private Integer transferaccount;
    private Integer warntransfer;
    private String synchronous;
    private String wechaturl;
    private String aliurl;
    private String brokerage;
    @Column(name="report_show_flag")
    private String reportShowFlag;
    @Column(name="region_id")
    private String regionId;
    private String account;
    @Column(name="stock_days")
    private Double stockDays;
    @Column(name="receive_card_time")
    private String receiveCardTime;
    @Column(name="min_stock")
    private Integer minStock;	//最少配卡数
    @Column(name="credit_type")
    private String creditType;
    @Column(name="stock_min")
    private Integer stockMin;	//最小库存数
    @Column(name="collect_money")
    private Integer collectMoney; //钞箱金额超过该值，生成扎帐任务
    @Column(name="patrol_times")
    private Integer patrolTimes;
    @Column(name="stock_limit")
    private Integer stockLimit;	//卡道库存小于该值时，配卡计算时必须配卡
    @Column(name="refund_card_num")
    private Integer refundCardNum;//设备退卡数超过该值，生成回收卡片任务
}
