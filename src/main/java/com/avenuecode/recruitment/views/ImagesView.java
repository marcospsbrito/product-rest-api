package com.avenuecode.recruitment.views;

import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;

import java.util.List;

public class ImagesView extends MinView{

    private List<ProductImage> productImages;

    public ImagesView(Product product) {
        super(product);
        this.productImages = product.getImages();
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
