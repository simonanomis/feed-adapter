package programmer.pesonal.feedadapter.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "feedadapter")
@Data
public class SpringBootConfig {
    private static String fixedDelay;
    private String url;

    public static String getFixedDelay() {
        return fixedDelay;
    }

    public static void setFixedDelay(String fixedDelay) {
        SpringBootConfig.fixedDelay = fixedDelay;
    }

    public String getUrl() {
        return url;
    }

    public SpringBootConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    //    @Value("${fixedDelay.in.milliseconds}")
//    private String fixedDelay;
}
