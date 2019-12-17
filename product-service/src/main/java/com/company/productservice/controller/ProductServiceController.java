package com.company.productservice.controller;


import com.company.productservice.service.ProductService;
import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "products")
public class ProductServiceController {

    @Autowired
    ProductService productService;


    @Cacheable
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product fetchProductById(@PathVariable int productId){
        return productService.findProductById(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> fetchAllProducts(){
        return productService.findAllProducts();
    }

    @CachePut(key = "#result.getProductId()")
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @CacheEvict(key = "#product.getProductId()")
    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @CacheEvict
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }


}
