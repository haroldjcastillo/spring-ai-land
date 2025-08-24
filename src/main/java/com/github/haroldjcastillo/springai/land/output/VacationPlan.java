package com.github.haroldjcastillo.springai.land.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {

  private final ChatClient chatClient;

  public VacationPlan(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  @GetMapping("/vacation/unstructured")
  public String unstructured() {
    return chatClient
        .prompt()
        .user("What's a good vacation plan while I'm in Montreal CA for 4 days?")
        .call()
        .content();
  }

  @GetMapping("/vacation/structured")
  public Itinerary structured() {
    return chatClient
        .prompt()
        .user("What's a good vacation plan while I'm in Montreal CA for 4 days?")
        .call()
        .entity(Itinerary.class);
  }
}
