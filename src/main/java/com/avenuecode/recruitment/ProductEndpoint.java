package com.avenuecode.recruitment;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Service;

/**
 * Created by marcos on 2/5/18.
 */
@Component
@Path("/product")
public class ProductEndpoint {

    @GET
    public String message() {
        return "Hello! List our products here search for code.";
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") Long productId){
        return new Product(productId,"new product");
    }

}
