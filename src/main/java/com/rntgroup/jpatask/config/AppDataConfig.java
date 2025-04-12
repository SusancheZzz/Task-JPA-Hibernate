package com.rntgroup.jpatask.config;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "testdata")
public class AppDataConfig {

  @Positive
  private int userCount;
  @Positive
  private int artistCount;
  @Positive
  private int albumCount;
  @Positive
  private int purchaseCount;
  @Positive
  private int songCount;

}
