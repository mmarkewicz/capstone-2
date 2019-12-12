package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoImplTest {

    @Autowired
    ProductDao productDao;

    private Product product1;

    @Before
    public void setup() throws Exception {
        clearDatabase();
        product1 = setupTestObject();
    }

    public void clearDatabase() {
        List<Product> productList = productDao.getAllProducts();

        for(Product p : productList){
            productDao.deleteProduct(p.getProductId());
        }
    }

    public Product setupTestObject() {

        Product product1 = new Product();
        product1.setProductName("Iphone-5");
        product1.setProductDescription("Smart Phone");
        product1.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product1.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));

        return product1;
    }

    @Test
    public void shouldAddGetDeleteCustomer(){

        product1 = productDao.addProduct(product1);

        Product product2 = productDao.getProductById(product1.getProductId());

        assertEquals(product2, product1);

        productDao.deleteProduct(product1.getProductId());

        assertNull(productDao.getProductById(product1.getProductId()));
    }

    @Test
    public void shouldGetAllCustomers(){

        product1 = productDao.addProduct(product1);

        Product product2 = new Product();
        product2.setProductName("Iphone-1");
        product2.setProductDescription("First Generation Smart Phone");
        product2.setListPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        product2.setUnitCost(new BigDecimal(25.99).setScale(2,RoundingMode.HALF_UP));;

        product2 = productDao.addProduct(product2);

        List<Product> productList = productDao.getAllProducts();

        assertEquals(2, productList.size());

    }

    @Test
    public void shouldUpdateConsole(){

        product1 = productDao.addProduct(product1);

        product1.setProductName("Iphone-4");
        product1.setProductDescription("Smart Phone");
        product1.setListPrice(new BigDecimal(79.99).setScale(2, RoundingMode.HALF_UP));
        product1.setUnitCost(new BigDecimal(42.49).setScale(2, RoundingMode.HALF_UP));

        productDao.updateProduct(product1);

        Product product2 = productDao.getProductById(product1.getProductId());

        assertEquals(product2, product1);
    }

}
