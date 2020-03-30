package com.smartystore.core.products.service;

import com.smartystore.core.common.service.BaseService;
import com.smartystore.core.products.domain.Product;
import com.smartystore.core.products.repository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Service
@EqualsAndHashCode(callSuper = true)
public class ProductService extends BaseService<Product> {
   private final ProductRepository repository;
}
