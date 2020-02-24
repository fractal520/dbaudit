package wtsd.dbaudit.Monitor.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LotteryCashingCompanyMap {
    public static final Map<String, String> companyMap;
    static
    {
        companyMap = new HashMap<>();
        companyMap.put("10000037", "陕西省体彩");
        companyMap.put("10000038", "北京市体彩");
        companyMap.put("10000041", "武汉市体彩");
        companyMap.put("10000043", "合肥市体彩");
        companyMap.put("10000044", "青岛市体彩");

    }
}
