package com.test.betapersei.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "url", "word", "mightContain", "bloom" })
public class GetBloomResponse extends CreateBloomResponse {
  private String word;

  @JsonProperty("might_contain")
  private boolean mightContain;

  public GetBloomResponse(String url, Bloom bloom, String word, boolean mightContain) {
    super(url, bloom);
    this.word = word;
    this.mightContain = mightContain;
  }

  public String getWord() {
    return word;
  }

  public boolean isMightContain() {
    return mightContain;
  }
}
