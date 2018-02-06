package com.avenuecode.recruitment.views;

import com.avenuecode.recruitment.entities.Product;

public class MinView implements ProductView {

    private Long id;
    private String name;

    public MinView(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinView minView = (MinView) o;
        if (id == minView.id && name == minView.name){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((id == null) ? 0 : id.hashCode());
        return result;    }
}
