package com.smartystore.core.products.api;

import com.smartystore.core.products.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//TODO: custom annotation https://stackoverflow.com/questions/28006501/ EX: @V1ApiOwnerController
@RequestMapping("owner/products")
@RestController
public class ProductController {
  private ProductService productService;

  @GetMapping("/{id}")
  public ProductModel getProduct(@PathVariable("id") Long profileId) {
    productService.findById(profileId);
    return ProductModel.fromEntity(productService.findById(profileId));
  }
}
