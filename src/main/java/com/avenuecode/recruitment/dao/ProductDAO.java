package com.avenuecode.recruitment.dao;

import com.avenuecode.recruitment.entities.Product;

import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */
public interface ProductDAO {
    Product add(Product product);
    List<Product> listAll(boolean listSubProducts, boolean listImages);
    Product findById(Long productId, boolean loadSubProducts, boolean loadImages);
    Product update(Product product);
    void delete(Long productId);
    List<Product> listAllSubProducts(Long productId, boolean loadSubProducts, boolean loadImages);
}
