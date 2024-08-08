package radxdatahub.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "query")
@Getter
@Setter
public class QueryConfiguration {

    private Map<String, Float> queryFields;

    private List<String> aggregationFields;

    private Map<String, String> sortingFields;

}
