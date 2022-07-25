package com.news.port.command;

import com.news.adapter.model.TopTenNewsRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopTenCommand extends Command {

  private TopTenNewsRequest request;
  
  public TopTenCommand(TopTenNewsRequest request, String requestId) {
    super(requestId);
    this.request = request;
  }

}
