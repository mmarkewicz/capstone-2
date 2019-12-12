package com.company.productservice.controller;


import com.company.productservice.ProductService;
import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductServiceController {

    @Autowired
    ProductService productService;

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

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
    }


}
