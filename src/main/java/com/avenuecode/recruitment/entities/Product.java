package com.avenuecode.recruitment.entities;


import com.avenuecode.recruitment.views.ProductView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */

@Entity
@Table(name = "product")
public class Product implements ProductView{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "product", targetEntity = ProductImage.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @JsonManagedReference
    @OneToMany(mappedBy = "productParent", targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> subProducts;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_product_fk")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
