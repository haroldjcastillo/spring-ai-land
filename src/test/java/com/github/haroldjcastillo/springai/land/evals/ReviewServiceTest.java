package com.github.haroldjcastillo.springai.land.evals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewServiceTest {

  @Autowired ReviewService reviewService;

  @Test
  void testPositiveSentiment() {
    String positiveReview = "This is a great movie, it was amazing!";
    Sentiment sentiment = reviewService.classifySentiment(positiveReview);
    assertEquals(Sentiment.POSITIVE, sentiment, "The sentiment should be classified as POSITIVE.");
  }

  @Test
  void testNegativeSentiment() {
    String negativeReview =
        "This is the worst experience I've ever had. The product is terrible and broke immediately.";
    Sentiment result = reviewService.classifySentiment(negativeReview);
    assertEquals(Sentiment.NEGATIVE, result, "The sentiment should be classified as NEGATIVE.");
  }

  @Test
  void testNeutralSentiment() {
    String neutralReview = "The product is okay. It does what it says but nothing more.";
    Sentiment result = reviewService.classifySentiment(neutralReview);
    assertEquals(Sentiment.NEUTRAL, result, "The sentiment should be classified as NEUTRAL.");
  }
}
