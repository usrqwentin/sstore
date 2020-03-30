package com.smartystore.core.products.repository;

import com.smartystore.core.common.repository.BaseRepository;
import com.smartystore.core.products.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
