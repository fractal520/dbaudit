package wtsd.dbaudit.Monitor.Service;

import java.util.Calendar;
import java.util.Date;

public class CheckTime {
    private Date begintime,endtime;

    public CheckTime(){
        Calendar d = Calendar.getInstance();
        d.set(d.get(d.YEAR),d.get(d.MONTH),d.get(d.DATE),d.get(d.HOUR_OF_DAY),d.get(d.MINUTE),0);
        d.set(Calendar.MILLISECOND,0);
        endtime = d.getTime();
        d.add(Calendar.MINUTE,-1);
        begintime = d.getTime();
    }

    public Date getBegintime() {
        return begintime;
    }
    public Date getEndtime() {
        return endtime;
    }

}
