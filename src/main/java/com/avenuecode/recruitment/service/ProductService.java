package com.avenuecode.recruitment.service;

import com.avenuecode.recruitment.dao.ProductDAO;
import com.avenuecode.recruitment.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marcos on 2/5/18.
 */

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public Product add(Product product){
        return productDAO.add(product);
    }

}
