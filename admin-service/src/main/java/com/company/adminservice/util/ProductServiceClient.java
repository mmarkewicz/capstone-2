package com.company.adminservice.util;

import com.company.adminservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    Product fetchProductById(@PathVariable int productId);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> fetchAllProducts();

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    Product addNewProduct(@RequestBody Product product);

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    void updateProduct(@RequestBody Product product);

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    void deleteProduct(@PathVariable int productId);

}
