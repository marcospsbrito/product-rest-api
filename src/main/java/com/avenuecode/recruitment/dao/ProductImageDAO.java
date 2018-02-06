package com.avenuecode.recruitment.dao;

import com.avenuecode.recruitment.entities.ProductImage;

import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */
public interface ProductImageDAO {
    ProductImage add(ProductImage productImage);
    List<ProductImage> listAll(Long productId);
    ProductImage findById(Long productId, Long productImageId);
    ProductImage update(Long productId, ProductImage productImage);
    void delete(Long productId, Long productImageId);
}
