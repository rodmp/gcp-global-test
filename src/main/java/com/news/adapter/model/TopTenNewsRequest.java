package com.news.adapter.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@Schema
public class TopTenNewsRequest {

  @NotNull
  @NotEmpty
  private String title;
  
  @NotNull
  @NotEmpty
  private String content;
  
  @NotNull
  @NotEmpty
  @DateTimeFormat(pattern = "yyyy-mm-ddThh:mm:ssZ")
  private String from;
  
  @NotNull
  @NotEmpty
  @DateTimeFormat(pattern = "yyyy-mm-ddThh:mm:ssZ")
  private String to;
  
  @Pattern(regexp="^(DESC|ASC)$",message="invalid code")
  private String orderBy;
}
