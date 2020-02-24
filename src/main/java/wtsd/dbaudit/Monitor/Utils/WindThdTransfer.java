package wtsd.dbaudit.Monitor.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wind.threshold.transfer")
@Data
public class WindThdTransfer {
    private String value;
    private String percent;

    public int getValue(int level){
        return Integer.parseInt(value.split(",")[level])*100;
    }

    public float getPercent(int level){
        return Float.parseFloat(percent.split(",")[level]);
    }
}
