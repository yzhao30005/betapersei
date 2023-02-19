package com.test.betapersei.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.test.betapersei.model.Bloom;
import com.test.betapersei.model.CreateBloomResponse;
import com.test.betapersei.model.GetBloomResponse;

@Service
public class BloomService {
  private Map<String, BloomFilter> bloomFilters = new HashMap<>();

  private final static double EXPECTED_PROB = 0.01;
  private final static long EXPECTED_INSERTIONS = 1000000;

  public CreateBloomResponse storeFilter(String url) throws IOException {
    try (InputStream in = (new URL(url)).openStream()) {
      var content = new String(in.readAllBytes(), StandardCharsets.UTF_8);

      var p = Pattern.compile("[a-zA-Z]+");
      var matcher = p.matcher(content);

      var bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), EXPECTED_INSERTIONS, EXPECTED_PROB);

      while (matcher.find()) {
        bloomFilter.put(matcher.group());
      }

      bloomFilters.put(url.toString(), bloomFilter);
      var bloom = new Bloom(bloomFilter.approximateElementCount(), EXPECTED_INSERTIONS,
          BigDecimal.valueOf(bloomFilter.expectedFpp()).setScale(2, BigDecimal.ROUND_HALF_UP),
          BigDecimal.valueOf(EXPECTED_PROB));
      return new CreateBloomResponse(url.toString(), bloom);
    }
  }

  public GetBloomResponse queryFilter(String url, String word) {
    var bloomFilter = bloomFilters.get(url);

    if (bloomFilter == null)
      return null;

    var mightContain = bloomFilter.mightContain(word);
    var bloom = new Bloom(bloomFilter.approximateElementCount(), EXPECTED_INSERTIONS,
        BigDecimal.valueOf(bloomFilter.expectedFpp()).setScale(2, BigDecimal.ROUND_HALF_UP),
        BigDecimal.valueOf(EXPECTED_PROB));
    return new GetBloomResponse(url, bloom, word, mightContain);
  }
}
