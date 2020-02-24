package wtsd.dbaudit.Monitor.Result;

import java.util.Date;

public class RechargeAnomalyResult {
    private String paytranseq;
    private String transeq;
    private String cardid;
    private String stationid;
    private Integer amount;
    private Integer balance;
    private Integer afterbalance;
    private Date transtime;
    private String status;

    public RechargeAnomalyResult(String paytranseq, String transeq, String cardid, String stationid, Integer amont,
                                 Integer balance , Integer afterbalance, Date transtime, String status){
        this.paytranseq = paytranseq;
        this.transeq = transeq;
        this.cardid = cardid;
        this.stationid = stationid;
        this.amount = amont;
        this.balance = balance;
        this.afterbalance = afterbalance;
        this.transtime = transtime;
        this.status = status;
    }

    public RechargeAnomalyResult(){
    }

    @Override
    public String toString(){
        return "异常充值:[transeq="+ transeq +",amount="+ amount +",balance="+ balance +
                ",afterbalance="+ afterbalance+",transtime="+transtime+"]";
    }

    public String getpaytranseq(){
        return this.paytranseq;
    }
    public String gettranseq(){
        return this.transeq;
    }
    public String getcardid(){
        return this.cardid;
    }
    public String getstationid(){
        return this.stationid;
    }
    public Integer getamount(){
        return this.amount;
    }
    public Integer getbalance(){
        return this.balance;
    }
    public Integer getafterbalance(){
        return this.afterbalance;
    }
    public Date gettranstime(){
        return this.transtime;
    }
    public String getstatus(){
        return this.status;
    }
}
