package com.github.haroldjcastillo.springai.land.multimodal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

  private final ChatClient chatClient;
  private final Resource sampleImage;

  public ImageDetection(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
    sampleImage = new ClassPathResource("/images/unsplash_img.png");
  }

  @GetMapping("/image-to-text")
  public String imageToText() {
    return chatClient
        .prompt()
        .user(
            u ->
                u.text("Can you please explain what you see in the following image?")
                    .media(MimeTypeUtils.IMAGE_PNG, sampleImage))
        .call()
        .content();
  }
}
