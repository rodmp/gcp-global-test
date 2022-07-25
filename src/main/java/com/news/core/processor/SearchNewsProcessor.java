package com.news.core.processor;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.news.adapter.model.Article;
import com.news.adapter.model.SearchNews;
import com.news.adapter.model.SearchNewsResponse;
import com.news.adapter.model.TopTenNewsRequest;
import com.news.adapter.outgoing.GNewsClient;
import com.news.core.configuration.ApiEnvironmentConfig;
import com.news.core.exception.model.DataNotFoundException;
import com.news.port.RequestCommandProcessor;
import com.news.port.command.TopTenCommand;

@Service("topTenProcessor")
public class SearchNewsProcessor
    implements RequestCommandProcessor<SearchNewsResponse, TopTenCommand> {

  private final GNewsClient feignClient;

  private final ApiEnvironmentConfig properties;

  public SearchNewsProcessor(final GNewsClient feignClient, final ApiEnvironmentConfig properties) {
    this.feignClient = feignClient;
    this.properties = properties;
  }

  @Override
  public SearchNewsResponse execute(TopTenCommand request) {

    TopTenNewsRequest topTenRequest = request.getRequest();

    Optional<SearchNews> searchOpt =
        Optional.ofNullable(feignClient.searchNews(topTenRequest.getContent(),
            properties.getTokenApi(), properties.getLanguage(), topTenRequest.getFrom(),
            topTenRequest.getTo(), topTenRequest.getTitle()));

    SearchNews searchNews = searchOpt.orElseThrow(() -> new DataNotFoundException());

    if (!topTenRequest.getOrderBy().isEmpty()) {

      Comparator<Article> comparing = null;


      if (topTenRequest.getOrderBy().equalsIgnoreCase("desc")) {
        comparing = Comparator.comparing(Article::getPublishedAt).reversed();
      } else if (topTenRequest.getOrderBy().equalsIgnoreCase("asc")) {
        comparing = Comparator.comparing(Article::getPublishedAt);
      }

      return SearchNewsResponse.builder()
          .articles(
              searchNews.getArticles().stream().sorted(comparing).collect(Collectors.toList()))
          .build();
    }

    return SearchNewsResponse.builder().articles(searchNews.getArticles()).build();
  }
}
