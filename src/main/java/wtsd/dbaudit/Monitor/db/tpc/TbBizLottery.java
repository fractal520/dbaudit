package wtsd.dbaudit.Monitor.db.tpc;

import javax.persistence.*;
import java.util.Date;

/**
 * 购彩记录表(记录自助设备的购彩信息)
 * @author Alian
 *
 */
@Entity
@Table(name="tb_biz_lottery")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = TbBizLottery.EG_DEFAULT,
                attributeNodes = {
                        @NamedAttributeNode(value = "pay")
                }
        )
})
public class TbBizLottery implements java.io.Serializable {

    public static final String EG_DEFAULT="TbBizLottery.default";

    private static final long serialVersionUID = 1L;
    private String transeq;		//支付流水
    private String employeeid;	//操作员编号(目前未用到)
    private String cityno;	    //城市编号
    private String stationid;	//站点编号
    private String roadno;		//卡道编号
    private String cardtypeno;	//卡种编号
    private Integer price;		//单价，单位分
    private Integer quantity;	//购买数量
    private Integer amount;		//总金额，单位分
    private Date inserttime;	//记录插入时间
    private Date updatetime;	//记录更新时间
    private String outtranseq;	//外部流水号(设备流水)
    private String status;		//售卡状态,00：成功，01：失败，02：售卡中
    private String errorcode;	//错误编码
    private String payway;		//支付方式：00：现金支付，01:，微信支付，03：支付宝，07：闪付，10：订单，20：建行龙支付
    private String source;		//交易来源,01：自助售卡机,02：交通卡小管家,03:站点
    private String outcardstatus;//发卡器出卡码
    private String recyclestatus;//回收状态, 00:已回收, 01:未回收
    private Integer successCount; //成功出票数量;
    private Date suretime;	 //入账时间
    private TbBizPay pay;

    @Id
    @Column(name="transeq", unique=true, nullable=false)
    public String getTranseq() {
        return this.transeq;
    }

    public void setTranseq(String transeq) {
        this.transeq = transeq;
    }

    @Column(name="employeeid", length=9)
    public String getEmployeeid() {
        return this.employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    @Column(name="cityno")
    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    @Column(name="stationid")
    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    @Column(name="roadno", length=9)
    public String getRoadno() {
        return this.roadno;
    }

    public void setRoadno(String roadno) {
        this.roadno = roadno;
    }

    @Column(name="cardtypeno", length=9)
    public String getCardtypeno() {
        return this.cardtypeno;
    }

    public void setCardtypeno(String cardtypeno) {
        this.cardtypeno = cardtypeno;
    }

    @Column(name="price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name="quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name="amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="inserttime",  length=19)
    public Date getInserttime() {
        return this.inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatetime",  length=19)
    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Column(name="status", length=3)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="outtranseq", length=9)
    public String getOuttranseq() {
        return this.outtranseq;
    }

    public void setOuttranseq(String outtranseq) {
        this.outtranseq = outtranseq;
    }

    @Column(name="errorcode")
    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    @Column(name="payway")
    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    @Column(name="source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name="outcardstatus")
    public String getOutcardstatus() {
        return outcardstatus;
    }

    public void setOutcardstatus(String outcardstatus) {
        this.outcardstatus = outcardstatus;
    }

    @Column(name="recyclestatus")
    public String getRecyclestatus() {
        return recyclestatus;
    }

    public void setRecyclestatus(String recyclestatus) {
        this.recyclestatus = recyclestatus;
    }

    @Column(name="success_count")
    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    @Column(name="suretime")
    public Date getSuretime() {
        return suretime;
    }

    public void setSuretime(Date suretime) {
        this.suretime = suretime;
    }

    @OneToOne
    @JoinColumn(name="transeq",referencedColumnName="transeq")
    public TbBizPay getPay() {
        return pay;
    }

    public void setPay(TbBizPay pay) {
        this.pay = pay;
    }
}
