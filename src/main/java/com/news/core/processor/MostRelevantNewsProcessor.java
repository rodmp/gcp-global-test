package com.news.core.processor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.news.adapter.model.Article;
import com.news.adapter.model.CountryArticle;
import com.news.adapter.model.MostRelevantNewsResponse;
import com.news.adapter.model.SearchNews;
import com.news.adapter.outgoing.GNewsClient;
import com.news.core.configuration.ApiEnvironmentConfig;
import com.news.port.RequestCommandProcessor;
import com.news.port.command.Command;

@Service("mostRelevantProcessor")
public class MostRelevantNewsProcessor
    implements RequestCommandProcessor<MostRelevantNewsResponse, Command> {

  private final GNewsClient feignClient;

  private final ApiEnvironmentConfig properties;

  public MostRelevantNewsProcessor(final GNewsClient feignClient,
      final ApiEnvironmentConfig properties) {
    this.feignClient = feignClient;
    this.properties = properties;
  }

  @Override
  public MostRelevantNewsResponse execute(Command command) {

    List<CountryArticle> articles = properties.getCountries().stream()
        .map(country -> mapArticleByCountry(country)).collect(Collectors.toList());

    return MostRelevantNewsResponse.builder().articles(articles).build();
  }

  private CountryArticle mapArticleByCountry(String country) {

    Optional<SearchNews> searchOpt = Optional
        .ofNullable(feignClient.getTopHeadLineByLanguague(properties.getTokenApi(), country));

    if (!searchOpt.isPresent()) {
      return CountryArticle.builder().country(country).build();
    }

    Article article = searchOpt.get().getArticles().stream()
        .sorted(Comparator.comparing(Article::getPublishedAt).reversed()).findFirst().get();


    return CountryArticle.builder().country(country).article(article).build();
  }

}
