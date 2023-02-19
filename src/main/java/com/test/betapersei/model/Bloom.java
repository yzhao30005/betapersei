package com.test.betapersei.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bloom {
  @JsonProperty("estimated_word_count")
  private long estimatedWordCount;

  @JsonProperty("target_word_count")
  private long targetWordCount;

  @JsonProperty("expected_false_positive_prob")
  private BigDecimal expectedFalsePositiveProb;

  @JsonProperty("target_false_positive_prob")
  private BigDecimal targetFalsePositiveProb;

  public Bloom(long estimatedWordCount, long targetWordCount, BigDecimal expectedFalsePositiveProb,
      BigDecimal targetFalsePositiveProb) {
    this.estimatedWordCount = estimatedWordCount;
    this.targetWordCount = targetWordCount;
    this.expectedFalsePositiveProb = expectedFalsePositiveProb;
    this.targetFalsePositiveProb = targetFalsePositiveProb;
  }

  public long getEstimatedWordCount() {
    return estimatedWordCount;
  }

  public long getTargetWordCount() {
    return targetWordCount;
  }

  public BigDecimal getExpectedFalsePositiveProb() {
    return expectedFalsePositiveProb;
  }

  public BigDecimal getTargetFalsePositiveProb() {
    return targetFalsePositiveProb;
  }
}
