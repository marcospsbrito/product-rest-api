package com.avenuecode.recruitment.dao.impl;

import com.avenuecode.recruitment.dao.ProductImageDAO;
import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */

@Repository
@Transactional
public class ProductImageDaoImpl implements ProductImageDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public ProductImage add(ProductImage productImage) {
        em.persist(productImage);
        return productImage;
    }

    @Override
    public List<ProductImage> listAll(Long productId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT pi FROM ProductImage pi join pi.product p");
        query.append("\n where p.id = ").append(productId);

        return em.createQuery(query.toString()).getResultList();
    }

    @Override
    public ProductImage findById(Long productId, Long productImageId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT pi FROM ProductImage pi join pi.product p");
        query.append("\n where p.id = ").append(productId);
        query.append("\n and pi.id = ").append(productImageId);

        return (ProductImage) em.createQuery(query.toString()).getSingleResult();
    }

    @Override
    public ProductImage update(Long productId, ProductImage productImage) {
        Product productManaged = em.find(Product.class, productId);
        if(productManaged == null){
            throw new NotFoundException("This product does not exist.");
        }
        ProductImage productImageManaged = findById(productId,productImage.getId());
        if(productImageManaged == null){
            throw new NotFoundException("This productImage does not exist.");
        }
        productImageManaged.setType(productImage.getType());
        productImageManaged.setProduct(productManaged);
        em.merge(productImageManaged);
        em.flush();
        return productImageManaged;
    }

    @Override
    public void delete(Long productId, Long productImageId) {
        ProductImage productImageManaged = findById(productId,productImageId);
        if(productImageManaged ==null){
            throw new NotFoundException();
        }
        em.remove(productImageManaged);
    }
}
