package wtsd.dbaudit.Monitor.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wind.alarm.url")
@Data
public class WindAlarmUrl {
    public String transfer;
}
