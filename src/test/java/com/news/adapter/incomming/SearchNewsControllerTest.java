package com.news.adapter.incomming;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import com.news.adapter.incoming.SearchNewsController;
import com.news.adapter.model.MostRelevantNewsResponse;
import com.news.adapter.model.SearchNewsResponse;
import com.news.adapter.model.TopTenNewsRequest;
import com.news.port.RequestCommandProcessor;
import com.news.port.command.Command;
import com.news.port.command.TopTenCommand;

@ExtendWith(MockitoExtension.class)
public class SearchNewsControllerTest {

  @Mock
  private RequestCommandProcessor<MostRelevantNewsResponse, Command> processorMostRelevant;

  @Mock
  private RequestCommandProcessor<SearchNewsResponse, TopTenCommand> processorSearch;

  @InjectMocks
  private SearchNewsController searchNewsController;

  @Test
  public void whenExecuteTopTenNewsEndpoint_then_returnTopTenArticles() {
    TopTenNewsRequest request = new TopTenNewsRequest("", "", "", "", "");

    assertThat(searchNewsController.topTenNewsEndpoint(request).getStatusCode())
        .isEqualTo(HttpStatus.OK);
  }

  @Test
  public void whenExecuteMostRelevantNewsEndpoint_then_returnMostRelevantArticles() {

    assertThat(searchNewsController.mostRelevantNewsEndpoint().getStatusCode())
        .isEqualTo(HttpStatus.OK);
  }
}
