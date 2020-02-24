package wtsd.dbaudit.Monitor.Result;

import java.util.Date;

public class LotteryAnomalyResult {

    private String transeq;
    private String stationid;
    private String cardtypeno;
    private Integer quantity;
    private Integer amount;
    private Date transtime;
    private String status;
    private Integer price;

    public LotteryAnomalyResult(String transeq, String stationid, String cardtypeno, Integer quantity,
                                 Integer amount, Date transtime, String status,Integer price){
        this.transeq = transeq;
        this.stationid = stationid;
        this.cardtypeno = cardtypeno;
        this.quantity = quantity;
        this.amount = amount;
        this.transtime = transtime;
        this.status = status;
        this.price = price;
    }

    public LotteryAnomalyResult(){
    }

    @Override
    public String toString(){
        return "异常充值:[transeq="+ transeq +",amount="+ amount +",quantity="+ quantity +
                ",price="+ price +",transtime="+transtime+"]";
    }

    public String gettranseq(){
        return this.transeq;
    }

    public String getstationid(){
        return this.stationid;
    }

    public Integer getquantity() {
        return  this.quantity;
    }

    public Integer getamount(){
        return this.amount;
    }

    public Integer getprice(){
        return this.price;
    }

    public Date gettranstime(){
        return this.transtime;
    }

    public String getstatus(){
        return this.status;
    }

}
