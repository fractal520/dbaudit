package wtsd.dbaudit.Monitor.Result;

import lombok.Data;
import wtsd.dbaudit.Monitor.Utils.LotteryCashingCompanyMap;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

@Data
public class LotteryCashingResult {
    private String companyId;
    private BigInteger num;
    private Integer id;
    private Date createTime;
    private String ticketNumber;
    private String winningStatus;

    public LotteryCashingResult(String company_id,BigInteger num,Integer id,Date create_time,
                                String ticket_number,String winning_status){

        this.companyId=company_id;
        this.num=num;
        this.id=id;
        this.createTime=create_time;
        this.ticketNumber=ticket_number;
        this.winningStatus=winning_status;
    }

    public LotteryCashingResult(){
    }

    public String getcompanyName(String companyId){
        return LotteryCashingCompanyMap.companyMap.get(companyId);
    }

    public long getTimeDelay(){
        Calendar currentTime = Calendar.getInstance();
        Calendar createT = Calendar.getInstance();
        createT.setTime(createTime);
        long IntervalMinute = (currentTime.getTimeInMillis() - createT.getTimeInMillis())/(1000 * 60);
        return IntervalMinute;
    }

    @Override
    public String toString(){
        return "兑奖异常:[companyId="+ companyId +",num="+ num +",id="+ id + ",createTime="+ createTime+
                ",ticketNumber="+ ticketNumber +",winningStatus="+winningStatus+"]";
    }

}
