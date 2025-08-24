package com.github.haroldjcastillo.springai.land.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {

  private final ChatClient chatClient;

  public DateTimeController(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  @GetMapping("/tools")
  public String dateTime() {
    return chatClient
        .prompt()
        .user("What is tomorrow's date?")
        .tools(new DateTimeTools())
        .call()
        .content();
  }
}
