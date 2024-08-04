package forms.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {
    private String address;

    private Integer port;

    private Boolean tsl;

    private String login;

    private String password;
}
