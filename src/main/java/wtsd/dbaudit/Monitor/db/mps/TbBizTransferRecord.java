package wtsd.dbaudit.Monitor.db.mps;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_biz_transfer_records")
public class TbBizTransferRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "TRANSFERSEQ")
    private String transferSeq;

    @Column(name = "OUTTRANSEQ")
    private String outTranseq;

    @Column(name = "OUTPAYTRANSEQ")
    private String outPayTranseq;

    @Column(name = "TRANSFERAMOUNT")
    private int transferAmount;

    @Column(name = "TRANSFERTYPE")
    private String transferType;

    @Column(name = "TRANSTYPE")
    private String transType;

    @Column(name = "APPLYTIME")
    private Date applyTime;

    @Column(name = "TRANSFERTIME")
    private Date transferTime;

    @Column(name = "INSERTTIME")
    private Date insertTime;

    @Column(name = "UPDATETIME")
    private Date updateTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TRANSFERNO")
    private String transferNo;

    @Column(name = "PARTNERID")
    private String partnerId;

    @Column(name = "STOREID")
    private String storeId;

    @Column(name = "WINDOWID")
    private String windowId;

    @Column(name = "DEVICENO")
    private String deviceNo;

    @Column(name = "CASHIERID")
    private String cashierId;

    @Column(name = "PAYACCOUNTNO")
    private String payAccountNo;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "ACCOUNTNAME")
    private String accountName;

    @Column(name = "BANKCODE")
    private String bankCode;

    @Column(name = "OPENID")
    private String openid;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "MAC")
    private String mac;

    @Column(name = "BIZTYPE")
    private String bizType;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "ERRORCODE")
    private String errorCode;

    @Column(name = "ERRORMSG")
    private String errorMsg;
}
