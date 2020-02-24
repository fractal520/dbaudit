package wtsd.dbaudit.Monitor.db.tpc;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_biz_lottery_cashing")
public class LotteryCashing implements Serializable {


    private static final long serialVersionUID = -118581414586008239L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "company_id")
    private String companyId;//城市编号

    @Column(name = "lottery_type")
    private String lotteryType;//彩种：00，福彩，01，体彩

    @Column(name = "qr_code")
    private String qrCode;//彩票兑奖条码字符串

    @Column(name = "openid")
    private String openid;//用户支付宝或微信号的openId'

    //支付宝ID
    @Column(name = "alipayid")
    private String alipayId;

    @Column(name = "create_time")
    private Date createTime;//创建时间(用户提交申请的时间)

    @Column(name = "winning_status")
    private String winningStatus;//中奖状态： 00 已中奖 01 未中奖，02已受理

    @Column(name = "amount")
    private Integer amount;//兑奖金额'

    @Column(name = "apply_time")
    private Date applyTime;//兑奖申请时间（客服确认的时间）

    @Column(name = "cashing_status")
    private String cashingStatus;//兑奖状态： 00 已兑奖 01 未兑奖，02未审核，03兑换中，04兑换失败

    @Column(name = "operation_person")
    private String operationPerson;//操作人

    @Column(name = "verity_person")
    private String verityPerson;//审核人

    @Column(name = "verity_time")
    private String verityTime;//审核时间

    @Column(name = "transfer_time")
    private Date transferTime;//转账时间

    @Column(name = "transfer_seq")
    private String transferSeq;//转账流水号

    //来源
    @Column(name = "cashing_source")
    private String cashingSource;

    //票号
    @Column(name = "ticket_number")
    private String ticketNumber;

    //兑奖类型 01-半自动兑奖 02-全自动兑奖
    @Column(name = "cashing_type")
    @ColumnDefault("01")
    private String cashingType;

    //全自动兑奖上传的图片地址
    @Column(name = "img_url")
    @ColumnDefault("")
    private String imgUrl;

    //用户昵称
    @Column(name = "nickname")
    private String nickname;
    @Transient
    private String message;
    @Transient
    private String deviceId;

    @Column(name = "apply_source")
    private String applySource;

    @Column(name = "factory_no")
    private String factoryNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWinningStatus() {
        return winningStatus;
    }

    public void setWinningStatus(String winningStatus) {
        this.winningStatus = winningStatus;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getCashingStatus() {
        return cashingStatus;
    }

    public void setCashingStatus(String cashingStatus) {
        this.cashingStatus = cashingStatus;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }

    public String getVerityPerson() {
        return verityPerson;
    }

    public void setVerityPerson(String verityPerson) {
        this.verityPerson = verityPerson;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferSeq() {
        return transferSeq;
    }

    public void setTransferSeq(String transferSeq) {
        this.transferSeq = transferSeq;
    }

    public String getVerityTime() {
        return verityTime;
    }

    public void setVerityTime(String verityTime) {
        this.verityTime = verityTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCashingSource() {
        return cashingSource;
    }

    public void setCashingSource(String cashingSource) {
        this.cashingSource = cashingSource;
    }

    public String getCashingType() {
        return cashingType;
    }

    public void setCashingType(String cashingType) {
        this.cashingType = cashingType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
/*
    public LotteryCashing() {
        cashingType = CashingTypeEnum.MANUAL.getStatus();
        imgUrl = "";
        applySource="";
        factoryNo="";
    }
*/
    public String getApplySource() {
        return applySource;
    }

    public void setApplySource(String applySource) {
        this.applySource = applySource;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LotteryCashing(String companyId, String lotteryType, String qrCode, String openid, String alipayId, Date createTime, String winningStatus, Integer amount, Date applyTime, String cashingStatus, String operationPerson, String verityPerson, String verityTime, Date transferTime, String transferSeq, String cashingSource, String ticketNumber, String cashingType, String imgUrl, String nickname, String applySource, String factoryNo) {
        this.companyId = companyId;
        this.lotteryType = lotteryType;
        this.qrCode = qrCode;
        this.openid = openid;
        this.alipayId = alipayId;
        this.createTime = createTime;
        this.winningStatus = winningStatus;
        this.amount = amount;
        this.applyTime = applyTime;
        this.cashingStatus = cashingStatus;
        this.operationPerson = operationPerson;
        this.verityPerson = verityPerson;
        this.verityTime = verityTime;
        this.transferTime = transferTime;
        this.transferSeq = transferSeq;
        this.cashingSource = cashingSource;
        this.ticketNumber = ticketNumber;
        this.cashingType = cashingType;
        this.imgUrl = imgUrl;
        this.nickname = nickname;
        this.applySource = applySource;
        this.factoryNo = factoryNo;
    }

    @Override
    public String toString() {
        return "LotteryCashing{" +
                "id=" + id +
                ", companyId='" + companyId + '\'' +
                ", lotteryType='" + lotteryType + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", openid='" + openid + '\'' +
                ", alipayId='" + alipayId + '\'' +
                ", createTime=" + createTime +
                ", winningStatus='" + winningStatus + '\'' +
                ", amount=" + amount +
                ", applyTime=" + applyTime +
                ", cashingStatus='" + cashingStatus + '\'' +
                ", operationPerson='" + operationPerson + '\'' +
                ", verityPerson='" + verityPerson + '\'' +
                ", verityTime='" + verityTime + '\'' +
                ", transferTime=" + transferTime +
                ", transferSeq=" + transferSeq +
                ", cashingSource='" + cashingSource + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", cashingType='" + cashingType + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", applySource='" + applySource + '\'' +
                ", factoryNo='" + factoryNo + '\'' +
                '}';
    }
}
