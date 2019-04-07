package pl.confitura.jelatyna;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("conference")
public class ConferenceConfigurationProperties {

    private C4PConfiguration c4p = new C4PConfiguration();

    @Data
    public static class C4PConfiguration {
        private boolean enabled = false;
    }
}
