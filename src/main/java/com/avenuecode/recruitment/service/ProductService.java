package com.avenuecode.recruitment.service;

import com.avenuecode.recruitment.dao.ProductDAO;
import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductView> list(boolean loadSubProducts, boolean loadImages){
        List<Product> products = null;
        products = productDAO.listAll(loadSubProducts, loadImages);
        if(products == null || products.isEmpty()){
            throw new NotFoundException();
        }
        if(loadSubProducts){
            if(loadImages){
                return getFullView(products);
            }else{
                return getSubProductsView(products);
            }
        }else if(loadImages){
            return getImagesView(products);
        }

        return getMinView(products);
    }

    public Product find(Long productId, boolean loadSubProducts, boolean loadImages) {
        Product product = productDAO.findById(productId,loadSubProducts,loadImages);
        if(product == null){
            throw new NotFoundException();
        }
        return product;
    }

    public Product update(Product product) {
        Product productResult = productDAO.findById(product.getId(),true, true);
        if(product == null){
            throw new NotFoundException();
        }
        return productDAO.update(product);
    }

    public void delete(Long product){
        productDAO.delete(product);
    }

    private List<ProductView> getMinView(List<Product> products) {
        List<ProductView> productsView = new ArrayList<ProductView>();
        for (Product product:products) {
            productsView.add(new MinView(product));
        }
        return productsView.stream().distinct().collect(Collectors.toList());
    }

    private List<ProductView> getSubProductsView(List<Product> products) {
        List<ProductView> productsView = new ArrayList<ProductView>();
        for (Product product:products) {
            productsView.add(new SubProductsView(product));
        }
        return productsView.stream().distinct().collect(Collectors.toList());
    }

    private List<ProductView> getImagesView(List<Product> products) {
        List<ProductView> productsView = new ArrayList<ProductView>();
        for (Product product:products) {
            productsView.add(new ImagesView(product));
        }
        return productsView.stream().distinct().collect(Collectors.toList());
    }
    private List<ProductView> getFullView(List<Product> products) {
        List<ProductView> productsView = new ArrayList<ProductView>();
        for (Product product:products) {
            productsView.add(new FullView(product));
        }
        return productsView.stream().distinct().collect(Collectors.toList());
    }

    public List<ProductView> listSubProduts(Long productId, boolean loadSubProducts, boolean loadImages) {
        List<Product> products = null;
        products = productDAO.listAllSubProducts(productId, loadSubProducts, loadImages);
        if(products == null || products.isEmpty()){
            throw new NotFoundException();
        }
        if(loadSubProducts){
            if(loadImages){
                return getFullView(products);
            }else{
                return getSubProductsView(products);
            }
        }else if(loadImages){
            return getImagesView(products);
        }

        return getMinView(products);
    }
}
