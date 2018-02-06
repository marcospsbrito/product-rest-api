package com.avenuecode.recruitment;

import com.avenuecode.recruitment.entities.Product;
import com.avenuecode.recruitment.entities.ProductImage;
import com.avenuecode.recruitment.service.ProductImageService;
import com.avenuecode.recruitment.service.ProductService;
import com.avenuecode.recruitment.views.ProductView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by marcos on 2/5/18.
 */
@Component
@Path("/product")
public class ProductEndpoint {

    @Inject
    private ProductService productService;


    @Inject
    private ProductImageService productImageService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductView> getAllProducts(@QueryParam("loadSubProduct") boolean loadSubProducts,
                                            @QueryParam("loadImages") boolean loadImages) {
        return productService.list(loadSubProducts,loadImages);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") Long productId,
                              @QueryParam("loadSubProduct") boolean loadSubProducts,
                              @QueryParam("loadImages") boolean loadImages) {
        return productService.find(productId, loadSubProducts, loadImages);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Product addProduct(Product product){
        return productService.add(product);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Product updateProduct(@PathParam("productId") Long productId, Product product){
        return productService.update(product);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    @Path("/{productId}")
    public String deleteProduct(@PathParam("productId") Long productId) {
        if (productId == null) {
            throw new NotFoundException();
        }
        productService.delete(productId);
        return "Product " + productId + " deleted";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/sub-product")
    public Product addSubProduct(@PathParam("productId") Long productId, Product product){
        Product productParent = productService.find(productId,false,false);
        product.setProductParent(productParent);
        return productService.add(product);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/sub-product")
    public List<ProductView> getAllSubProducts(@PathParam("productId") Long productId,
                                                @QueryParam("loadSubProduct") boolean loadSubProducts,
                                                @QueryParam("loadImages") boolean loadImages) {
        return productService.listSubProduts(productId,loadSubProducts,loadImages);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/image")
    public List<ProductImage> getAllProductImages(@PathParam("productId") Long productId) {
        return productImageService.list(productId);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/image/{productImageId}")
    public ProductImage getProductImage(@PathParam("productId") Long productId,
                                   @PathParam("productImageId") Long productImageId) {
        return productImageService.find(productId, productImageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/image")
    public ProductImage addProductImage(@PathParam("productId") Long productId, ProductImage productImage){
        return productImageService.add(productId, productImage);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{productId}/image")
    public ProductImage updateProductImage(@PathParam("productId") Long productId, ProductImage productImage){
        return productImageService.update(productId, productImage);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    @Path("/{productId}/image/{productImageId}")
    public String deleteProduct(@PathParam("productId") Long productId,
                                @PathParam("productImageId") Long productImageId) {
        if (productId == null || productImageId == null) {
            throw new NotFoundException();
        }
        productImageService.delete(productId, productImageId);
        return "Product Image " + productImageId + " deleted";
    }
}
