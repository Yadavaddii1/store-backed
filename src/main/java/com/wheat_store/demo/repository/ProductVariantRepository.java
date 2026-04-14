package com.wheat_store.demo.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wheat_store.demo.model.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, UUID> {
}
