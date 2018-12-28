package com.example.alex.compareXml.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "excluded")
@Component
public class CodeConfiguration {

    private List<String> code260;
    private List<String> code10615;
    private List<String> code77290;

}
