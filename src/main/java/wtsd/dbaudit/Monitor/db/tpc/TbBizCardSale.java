package wtsd.dbaudit.Monitor.db.tpc;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_biz_card_sale")
@Data
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = TbBizCardSale.EG_DEFAULT,
                attributeNodes = {
                        @NamedAttributeNode(value = "pay")
                }
        )
})
public class TbBizCardSale {
    public static final String EG_DEFAULT="TbBizCardSale.default";

    private static final long serialVersionUID = 200446379535221388L;
    @Id
    private String transeq;
    @Column(name = "stationid")
    private String stationid;

    @Column(name = "employeeid")
    private String employeeid;
    @Column(name = "roadno")
    private String roadno;//卡道
    @Column(name = "cardtypeno")
    private String cardtypeno;//卡种
    @Column(name = "status")
    private String status;

    @Column(name = "recyclestatus")
    private String recyclestatus;//回收状态

    @Column(name = "outcardstatus")
    private String outcardstatus;//发卡状态码

    @Column(name = "price")
    private Integer price;

    @Column(name="inserttime")
    private String inserttime;
    @Column(name="suretime")
    private String suretime;
    @Column(name="payway")
    private String payway;



    @Column(name = "outtranseq")
    private String outtranseq;
    @Column(name = "printstatus")
    private String printstatus;//凭条
    @OneToOne
    @JoinColumn(name="transeq")
    private TbBizPay pay;

    public String getTranseq() {
        return transeq;
    }

    public void setTranseq(String transeq) {
        this.transeq = transeq;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getRoadno() {
        return roadno;
    }

    public void setRoadno(String roadno) {
        this.roadno = roadno;
    }

    public String getCardtypeno() {
        return cardtypeno;
    }

    public void setCardtypeno(String cardtypeno) {
        this.cardtypeno = cardtypeno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecyclestatus() {
        return recyclestatus;
    }

    public void setRecyclestatus(String recyclestatus) {
        this.recyclestatus = recyclestatus;
    }

    public String getOutcardstatus() {
        return outcardstatus;
    }

    public void setOutcardstatus(String outcardstatus) {
        this.outcardstatus = outcardstatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public String getSuretime() {
        return suretime;
    }

    public void setSuretime(String suretime) {
        this.suretime = suretime;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getOuttranseq() {
        return outtranseq;
    }

    public void setOuttranseq(String outtranseq) {
        this.outtranseq = outtranseq;
    }

    public String getPrintstatus() {
        return printstatus;
    }

    public void setPrintstatus(String printstatus) {
        this.printstatus = printstatus;
    }

    public TbBizPay getPay() {
        return pay;
    }

    public void setPay(TbBizPay pay) {
        this.pay = pay;
    }

}
