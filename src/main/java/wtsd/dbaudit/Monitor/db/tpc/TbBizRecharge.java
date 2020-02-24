package wtsd.dbaudit.Monitor.db.tpc;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created by wangjianan on 2017/5/2 0002.
 */
@Entity
@Table(name = "tb_biz_recharge")
@Data
public class TbBizRecharge {
    public static final String EG_DEFAULT="TbBizRechargeDetail.default";

 /*   @ManyToOne
    @JoinColumn(name="paytranseq",referencedColumnName="paytranseq")
    @NotFound(action= NotFoundAction.IGNORE)
    private TbBizPay pay;*/

    @Column
    private String paytranseq;

    @Id
    private String transeq;


    @Column(name = "outtranseq", nullable = false)
    private String outtranseq;


    @Column(name = "cardid")
    private String cardid;

    @Column(name = "POSID")
    private String posid;

    @Column(name = "stationid")
    private String stationid;


    @Column(name = "balance")
    private Integer balance;

    @Column(name = "afterbalance")
    private Integer afterbalance;

    @Column(name = "CARDTRANNO")
    private String cardtranno;


    @Column(name = "payway")
    private String payway;



    @Column(name = "source")
    private String source;


    @Column(name = "updatetime")
    private String updatetime;

    @Column(name = "suretime")
    private String suretime;

    @Column(name = "status")
    private String status;

    @Column(name = "transtime")
    private Date transtime;//充值时间

    @Column(name = "inserttime")
    private String inserttime;

    @Column(name = "remlimit")
    private Integer remlimit;

    @Column(name = "locallimit")
    private Integer locallimit;



    @Column(name = "suretype")
    private String suretype;

    @Column(name = "ERRORCODE")
    private String errorcode;

    @Column(name = "transcount")
    private String transcount;

    @Column(name = "BEFORELIMIT")
    private Integer beforelimit;



    @Column(name = "SAMID")
    private String samid;


    @Column(name = "CARDVERSION")
    private String cardversion;

    @Column(name = "OPERATORNO")
    private String operatorno;

    @Column(name = "CENTERTRANS")
    private String centertrans;

    @Column(name = "TRANSID")
    private String transid;

    @Column(name = "UNITID")
    private String unitid;

    @Column(name = "CARDNUM")
    private String cardnum;

    @Column(name = "TAC")
    private String tac;

    @Column(name = "amont")
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

 /*   public TbBizPay getPay() {
        return pay;
    }

    public void setPay(TbBizPay pay) {
        this.pay = pay;
    }*/

    public String getTranseq() {
        return transeq;
    }

    public void setTranseq(String transeq) {
        this.transeq = transeq;
    }

    public String getOuttranseq() {
        return outtranseq;
    }

    public void setOuttranseq(String outtranseq) {
        this.outtranseq = outtranseq;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAfterbalance() {
        return afterbalance;
    }

    public void setAfterbalance(Integer afterbalance) {
        this.afterbalance = afterbalance;
    }

    public String getCardtranno() {
        return cardtranno;
    }

    public void setCardtranno(String cardtranno) {
        this.cardtranno = cardtranno;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    public String getSuretime() {
        return suretime;
    }

    public void setSuretime(String suretime) {
        this.suretime = suretime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTranstime() {
        return transtime;
    }

    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getRemlimit() {
        return remlimit;
    }

    public void setRemlimit(Integer remlimit) {
        this.remlimit = remlimit;
    }

    public Integer getLocallimit() {
        return locallimit;
    }

    public void setLocallimit(Integer locallimit) {
        this.locallimit = locallimit;
    }

    public String getSuretype() {
        return suretype;
    }

    public void setSuretype(String suretype) {
        this.suretype = suretype;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getTranscount() {
        return transcount;
    }

    public void setTranscount(String transcount) {
        this.transcount = transcount;
    }

    public Integer getBeforelimit() {
        return beforelimit;
    }

    public void setBeforelimit(Integer beforelimit) {
        this.beforelimit = beforelimit;
    }

    public String getSamid() {
        return samid;
    }

    public void setSamid(String samid) {
        this.samid = samid;
    }

    public String getCardversion() {
        return cardversion;
    }

    public void setCardversion(String cardversion) {
        this.cardversion = cardversion;
    }

    public String getOperatorno() {
        return operatorno;
    }

    public void setOperatorno(String operatorno) {
        this.operatorno = operatorno;
    }

    public String getCentertrans() {
        return centertrans;
    }

    public void setCentertrans(String centertrans) {
        this.centertrans = centertrans;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }
}
