package com.news.adapter.model;

import java.util.List;
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
public class SearchNewsResponse {

  private List<Article> articles;
}
