package com.news.adapter.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

  private String title;
  
  private String description;
  
  private String content;
  
  private String image;
  
  private LocalDateTime publishedAt;
  
  private Source source;
}
