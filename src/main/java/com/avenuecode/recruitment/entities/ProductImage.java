package com.avenuecode.recruitment.entities;

import javax.persistence.*;

/**
 * Created by marcos on 2/5/18.
 */

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    private String imageSrc;

    public ProductImage(Product product, String imageSrc) {
        this.product = product;
        this.imageSrc = imageSrc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
