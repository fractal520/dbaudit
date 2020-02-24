package wtsd.dbaudit.Monitor.Result;

import java.util.Date;

public class CardSaleAnomalyResult {
    private String transeq;
    private String stationid;
    private String cardtypeno;
    private Integer money;
    private Date transtime;
    private String status;
    private Integer price;

    public CardSaleAnomalyResult(String transeq, String stationid, String cardtypeno, Integer money,
                                  Date transtime, String status,Integer price){
        this.transeq = transeq;
        this.stationid = stationid;
        this.cardtypeno = cardtypeno;
        this.money = money;
        this.transtime = transtime;
        this.status = status;
        this.price = price;
    }

    public CardSaleAnomalyResult(){
    }

    @Override
    public String toString(){
        return "异常售卡:[transeq="+ transeq +",money="+ money +
                ",price="+ price+",transtime="+transtime+"]";
    }

    public String gettranseq(){
        return this.transeq;
    }

    public String getstationid(){
        return this.stationid;
    }
    public Integer getmoney(){
        return this.money;
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
