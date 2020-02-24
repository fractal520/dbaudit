package wtsd.dbaudit.Monitor.db.tpc;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tb_biz_pay")
@Data
public class TbBizPay {
    @Id
    @Column(name="PAYTRANSEQ")
    private String paytranseq;
    @Column(name="OUTTRANSEQ")
    private String outtranseq;
    @Column(name="TRANSEQ")
    private String transeq;
    @Column(name="CARDID")
    private String cardid;
    @Column(name="CITYNO")
    private String cityno;
    @Column(name="AMOUNT")
    private int amount;
    @Column(name="PAYTIME")
    private Date paytime;
    @Column(name="STATIONID")
    private String stationid;
    @Column(name="REFUNDTIME")
    private Date refundtime;
    @Column(name="PAYTYPE")
    private String paytype;
    @Column(name="PAYNO")
    private String payno;
    @Column(name="REFUNDNO")
    private String refundno;
    @Column(name="SOURCE")
    private String source;
    @Column(name="STATUS")
    private String status;
    @Column()
    private Timestamp updatetime;
    @Column(length = 10)
    private String moneystatus;
    @Column(length = 2)
    private String transtype;
    @Column(length = 2)
    private String lockflag;
    @Column(length = 32)
    private String openid;
    @Column(length = 50)
    private String specialfield;

}