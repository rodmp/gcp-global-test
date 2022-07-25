package com.news.adapter.incoming;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.news.adapter.model.MostRelevantNewsResponse;
import com.news.adapter.model.SearchNewsResponse;
import com.news.adapter.model.TopTenNewsRequest;
import com.news.core.exception.ErrorResponse;
import com.news.port.RequestCommandProcessor;
import com.news.port.command.Command;
import com.news.port.command.TopTenCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Controll class.
 * 
 * @author demo.
 *
 */
@Slf4j
@RequestMapping()
@RestController
public class SearchNewsController {

  private final RequestCommandProcessor<MostRelevantNewsResponse, Command> processorMostRelevant;

  private final RequestCommandProcessor<SearchNewsResponse, TopTenCommand> processorSearch;

  public SearchNewsController(
      @Qualifier("mostRelevantProcessor") final RequestCommandProcessor<MostRelevantNewsResponse, Command> processorMostRelevant,
      @Qualifier("topTenProcessor") final RequestCommandProcessor<SearchNewsResponse, TopTenCommand> processorSearch) {
    this.processorMostRelevant = processorMostRelevant;
    this.processorSearch = processorSearch;
  }

  @Operation(tags = {"SERVICE TOP TEN ARTICLES"}, description = "SEARCH TOP TEN ARTICLES")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = SearchNewsResponse.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))})})


  @PostMapping("/top/ten/articles")
  public ResponseEntity<SearchNewsResponse> topTenNewsEndpoint(
      @Valid @RequestBody TopTenNewsRequest topTenNewsRequest) {

    log.info("New request top ten search {} ", topTenNewsRequest);
    
    return new ResponseEntity<>(
        processorSearch.execute(new TopTenCommand(topTenNewsRequest, UUID.randomUUID().toString())),
        HttpStatus.OK);
  }

  @Operation(tags = {"SERVICE MOST RELEVANT NEWS"}, description = "SEARCH MOST RELEVANT NEWS")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = SearchNewsResponse.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))})})

  @GetMapping("/most/relevant")
  public ResponseEntity<MostRelevantNewsResponse> mostRelevantNewsEndpoint() {

    log.info("New request most relevant search");
    
    return new ResponseEntity<>(
        processorMostRelevant.execute(new Command(UUID.randomUUID().toString())), HttpStatus.OK);
  }
}
