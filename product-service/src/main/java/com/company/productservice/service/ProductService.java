package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findProductById(int productId){
        return productDao.getProductById(productId);
    }

    public List<Product> findAllProducts(){
        return  productDao.getAllProducts();
    }

    public Product createProduct(Product product){
        if (product == null) {
            throw new IllegalArgumentException("You must enter a product");
        }

        return productDao.addProduct(product);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

    public void deleteProduct(int productId){
        productDao.deleteProduct(productId);
    }

}
