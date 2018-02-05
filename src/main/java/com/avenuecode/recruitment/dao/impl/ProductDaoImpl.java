package com.avenuecode.recruitment.dao.impl;

import com.avenuecode.recruitment.dao.ProductDAO;
import com.avenuecode.recruitment.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
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
       em.persist(product);
       return product;
    }

    @Override
    public List<Product> listAll() {
        CriteriaQuery<Product> criteriaQuery = em.getCriteriaBuilder().createQuery(Product.class);
        @SuppressWarnings("unused")
        Root<Product> root = criteriaQuery.from(Product.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Product> listAllEager() {
        CriteriaQuery<Product> criteriaQuery = em.getCriteriaBuilder().createQuery(Product.class);
        @SuppressWarnings("unused")
        Root<Product> root = criteriaQuery.from(Product.class);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
