package com.github.haroldjcastillo.springai.land.evals;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  private final ChatClient chatClient;

  public ReviewService(ChatClient.Builder builder) {
    this.chatClient =
        builder.defaultOptions(OllamaOptions.builder().temperature(0.1d).build()).build();
  }

  public Sentiment classifySentiment(String review) {
    String systemPrompt =
        """
        Classify the sentiment of the following text as POSITIVE, NEGATIVE, or NEUTRAL. \
        Your response must be only one of these three words.
        e.g. "POSITIVE" or "NEGATIVE" or "NEUTRAL" (With quotes)
        Do not return any JSON or structured data. Just a single word.
        """;

    return chatClient.prompt().system(systemPrompt).user(review).call().entity(Sentiment.class);
  }
}
