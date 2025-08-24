package com.github.haroldjcastillo.springai.land.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

  private final ChatClient chatClient;

  public AcmeBankController(ChatClient.Builder chatClientBuilder) {
    chatClient = chatClientBuilder.build();
  }

  @GetMapping("/chat")
  public String chat(@RequestParam String message) {
    final var systemInstructions =
        """
        You are a customer service assistant for Acme Bank.
        You can ONLY discuss:
        - Account balance and transactions
        - Branch locations and hours
        - General banking services
        If asked about anything else, respond: "I can only help with banking-related questions." (NOT more than that)
        """;
    return chatClient.prompt().user(message).system(systemInstructions).call().content();
  }
}
