package com.test.betapersei.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.betapersei.model.CreateBloomResponse;
import com.test.betapersei.service.BloomService;

@RestController
@RequestMapping(BloomController.BASE_PATH)
public class BloomController {
  public static final String BASE_PATH = "/bloom";

  @Autowired
  private BloomService service;

  @PutMapping
  public CreateBloomResponse storeFilter(@RequestParam String url) throws MalformedURLException, IOException {

    return service.storeFilter(url);
  }

  @GetMapping
  public CreateBloomResponse queryFilter(@RequestParam String url, @RequestParam(name = "contains") String word)
      throws MalformedURLException, IOException {

    return service.queryFilter(url, word);
  }

}
