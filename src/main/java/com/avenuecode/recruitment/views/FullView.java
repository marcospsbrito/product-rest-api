package com.avenuecode.recruitment.views;

import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;

import java.util.List;

public class FullView extends MinView{

    private List<Product> subProducts;
    private List<ProductImage> productImages;

    public FullView(Product product) {
        super(product);
        this.productImages = product.getImages();
        this.subProducts = product.getSubProducts();
    }

    public List<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }


}