package com.avenuecode.recruitment.service;

import com.avenuecode.recruitment.dao.ProductDAO;
import com.avenuecode.recruitment.dao.ProductImageDAO;
import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */

@Service
public class ProductImageService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductImageDAO productImageDAO;


    public ProductImage add(Long productId, ProductImage productImage){
        Product productManaged = getProductManaged(productId);

        productImage.setProduct(productManaged);
        return productImageDAO.add(productImage);
    }

    public List<ProductImage> list(Long productId){
        Product productManaged = getProductManaged(productId);
        List<ProductImage> productImages = null;
        productImages = productImageDAO.listAll(productId);
        if(productImages == null || productImages.isEmpty()){
            throw new NotFoundException();
        }
        return productImages;
    }

    public ProductImage find(Long productId, Long productImageId) {
        Product productManaged = getProductManaged(productId);
        ProductImage productImage = productImageDAO.findById(productId, productImageId);
        if(productImage == null){
            throw new NotFoundException();
        }
        return productImage;
    }

    public ProductImage update(Long productId, ProductImage productImage) {
        getProductManaged(productId);
        return productImageDAO.update(productId, productImage);
    }


    public void delete(Long productId, Long productImageId){
        getProductManaged(productId);
        productImageDAO.delete(productId, productImageId);
    }

    private Product getProductManaged(Long productId) {
        Product productManaged = productDAO.findById(productId,false, false);
        if(productManaged == null){
            throw new NotFoundException("The product does not exist!");
        }
        return productManaged;
    }

}
