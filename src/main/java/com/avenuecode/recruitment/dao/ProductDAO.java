package com.avenuecode.recruitment.dao;

import com.avenuecode.recruitment.entities.Product;

import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */
public interface ProductDAO {
    Product add(Product product);
    List<Product> listAll();
    List<Product> listAllEager();
}
