package com.news.adapter.outgoing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.news.adapter.model.SearchNews;

/**
 * Client class
 * 
 * @author demo.
 *
 */
@FeignClient(name = "gnews", url = "https://gnews.io/api/v4")
public interface GNewsClient {

  /**
   * Client feign mapping.
   * 
   * @param
   * @return
   */
  @GetMapping(path = "/top-headlines")
  public SearchNews getTopHeadLineByLanguague(@RequestParam("token") String token,
      @RequestParam("lang") String languague);

  /**
   * Client feign mapping.
   * 
   * @param
   * @return
   */
  @GetMapping(path = "/search")
  public SearchNews searchNews(@RequestParam("q") String value, @RequestParam("token") String token,
      @RequestParam("lang") String language, @RequestParam("from") String from,
      @RequestParam("to") String to, @RequestParam("in") String in);
}
