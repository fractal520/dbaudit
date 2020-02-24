package wtsd.dbaudit.Monitor.Utils;

import java.util.Calendar;

public class WTimeUtil {
    private Calendar d;

    public WTimeUtil(){
        d = Calendar.getInstance();
        d.set(d.get(d.YEAR),d.get(d.MONTH),d.get(d.DATE),d.get(d.HOUR_OF_DAY),d.get(d.MINUTE),0);
        d.set(Calendar.MILLISECOND,0);
    }

    public Calendar getMinutePrecision() {
        return d;
    }

}
