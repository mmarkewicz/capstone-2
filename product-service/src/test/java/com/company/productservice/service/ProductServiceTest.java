package com.company.productservice.service;

import com.company.productservice.ProductService;
import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductDao productDao;

    @Autowired
    ProductService productService;

    private Product product;

    @Before
    public void setUp(){
        setUpDaoMock();

        product = new Product();
        product.setProductName("Iphone-5");
        product.setProductDescription("Smart Phone");
        product.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldCreateAndFindProduct(){
        product = productService.createProduct(product);
        assertEquals(product, productService.findProductById(product.getProductId()));
    }

    @Test
    public void shouldFindAllProducts(){
        List<Product> actualList = productService.findAllProducts();
        assertEquals(2, actualList.size());
    }


    public void setUpDaoMock(){

        Product product1 = new Product();
        product1.setProductName("Iphone-5");
        product1.setProductDescription("Smart Phone");
        product1.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product1.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));
        product1.setProductId(1);

        Product product2 = new Product();
        product2.setProductName("Iphone-5");
        product2.setProductDescription("Smart Phone");
        product2.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product2.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));

        doReturn(product1).when(productDao).addProduct(product2);
        doReturn(product1).when(productDao).getProductById(1);

        Product product3 = new Product();
        product3.setProductName("Iphone-4");
        product3.setProductDescription("Smart Phone");
        product3.setListPrice(new BigDecimal(69.99).setScale(2, RoundingMode.HALF_UP));
        product3.setUnitCost(new BigDecimal(35.99).setScale(2, RoundingMode.HALF_UP));
        product3.setProductId(2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product3);

        doReturn(productList).when(productDao).getAllProducts();

    }
}
