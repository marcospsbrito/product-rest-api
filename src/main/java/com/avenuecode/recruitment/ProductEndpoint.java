package com.avenuecode.recruitment;

import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.service.ProductService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by marcos on 2/5/18.
 */
@Component
@Path("/product")
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @GET
    public String message() {
        return "Hello! List our products here search for code.";
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") Long productId){
        return new Product();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Product addProduct(Product product){
        return productService.add(product);
    }

}
