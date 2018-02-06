package com.avenuecode.recruitment.dao.impl;

import com.avenuecode.recruitment.dao.ProductDAO;
import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;
import org.hibernate.Hibernate;
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
public class ProductDaoImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product add(Product product) {

        inicializeImagesAndSubProductsRecursive(product);

        em.persist(product);
        return product;
    }

    private void inicializeImagesAndSubProductsRecursive(Product product) {
        if(product.getImages()!=null && !product.getImages().isEmpty()){
            for (ProductImage image:product.getImages()) {
                image.setProduct(product);
            }
        }

        if(product.getSubProducts()!=null && !product.getSubProducts().isEmpty()){
            for (Product subProduct:product.getSubProducts()) {
                subProduct.setProductParent(product);
                inicializeImagesAndSubProductsRecursive(subProduct);
            }
        }
    }

    @Override
    public List<Product> listAll(boolean loadSubProducts, boolean loadImages) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT p FROM Product p");
        if(loadSubProducts){
            query.append("\n left join p.subProducts sp");
        }
        if(loadImages){
            query.append("\nleft join p.images");
        }
        query.append("\n where p.id > 0");

        return inicializarListas(em.createQuery(query.toString()).getResultList());
    }

    @Override
    public Product findById(Long productId, boolean loadSubProducts, boolean loadImages) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT p FROM Product p");
        if(loadSubProducts){
            query.append("\n left join p.subProducts sp");
        }
        if(loadImages){
            query.append("\nleft join p.images");
        }
        query.append("\n where p.id = ").append(productId);

        return inicializarListas((Product) em.createQuery(query.toString()).getSingleResult());
    }

    @Override
    public Product update(Product product) {
        Product productManaged = em.find(Product.class, product.getId());
        if(productManaged == null){
            throw new NotFoundException("This product does not exist.");
        }
        if(product.getProductParent() != null){
            Product productParentManaged = em.find(Product.class, product.getProductParent().getId());
            if(productParentManaged == null){
                throw new NotFoundException("The product parent does not exist.");
            }
            productManaged.setProductParent(productParentManaged);
        }
        inicializarListas(productManaged);
        productManaged.setName(product.getName());
        productManaged.setSubProducts(product.getSubProducts());
        productManaged.setImages(product.getImages());
        em.merge(productManaged);
        em.flush();
        return inicializarListas(productManaged);
    }

    @Override
    public void delete(Long productId) {
        Product productManaged = em.find(Product.class, productId);
        if(productManaged==null){
            throw new NotFoundException();
        }
        em.remove(productManaged);
    }

    @Override
    public List<Product> listAllSubProducts(Long productId, boolean loadSubProducts, boolean loadImages) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT p FROM Product p join p.productParent pp");
        if(loadSubProducts){
            query.append("\n left join p.subProducts sp");
        }
        if(loadImages){
            query.append("\nleft join p.images");
        }
        query.append("\n where pp.id = ").append(productId);

        return inicializarListas(em.createQuery(query.toString()).getResultList());    }

    private List<Product> inicializarListas(List<Product> products) {
        if(products != null){
            for (Product product:products) {
                inicializarListas(product);
            }
        }
        return products;
    }

    private Product inicializarListas(Product product) {
        if(product != null){
            Hibernate.initialize(product.getSubProducts());
            Hibernate.initialize(product.getImages());
            inicializarListas(product.getSubProducts());
        }
        return product;
    }
}
