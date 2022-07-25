package com.news.core.exception;

import java.time.ZonedDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class ErrorResponse {

  private String type;
  
  private String code;
  
  private String details;
  
  private String location;
  
  private String modeInfo;
  
  private String uuid;
  
  private ZonedDateTime timestamp;
}
