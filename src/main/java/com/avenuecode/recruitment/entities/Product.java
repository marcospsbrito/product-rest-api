package com.avenuecode.recruitment.entities;


import javax.persistence.*;
import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "product", targetEntity = ProductImage.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "productParent", targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> subProducts;

    @ManyToOne
    private Product productParent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public Product getProductParent() {
        return productParent;
    }

    public void setProductParent(Product productParent) {
        this.productParent = productParent;
    }
}
