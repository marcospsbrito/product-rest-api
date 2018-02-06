package com.avenuecode.recruitment.views;

import com.avenuecode.recruitment.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class SubProductsView extends MinView {

    private List<SubProductsView> subProducts;

    public SubProductsView(Product product) {
        super(product);
        this.subProducts = makeSubProductView(product.getSubProducts());
    }

    private List<SubProductsView> makeSubProductView(List<Product> subProducts) {
        List<SubProductsView> views = new ArrayList<SubProductsView>();
        if(subProducts!=null){
            for (Product product:subProducts) {
                views.add(new SubProductsView(product));
            }
        }
        return views;
    }

    public List<SubProductsView> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<SubProductsView> subProducts) {
        this.subProducts = subProducts;
    }
}
