package com.test.betapersei.model;

public class CreateBloomResponse {
  private String url;
  private Bloom bloom;
  
  public CreateBloomResponse(String url, Bloom bloom) {
    this.url = url;
    this.bloom = bloom;
  }

  public String getUrl() {
    return url;
  }

  public Bloom getBloom() {
    return bloom;
  }
}
