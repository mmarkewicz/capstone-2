package com.company.adminservice.controller;

import com.company.adminservice.model.*;
import com.company.adminservice.service.AdminServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminServiceLayer adminServiceLayer;


    // Product service endpoints

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductViewModel createProduct(@RequestBody ProductViewModel product) {
        return adminServiceLayer.addNewProduct(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProductById(@PathVariable int id) {
        return adminServiceLayer.fetchProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProducts() {
        return adminServiceLayer.fetchAllProducts();
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody ProductViewModel product) {
         adminServiceLayer.updateProduct(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        adminServiceLayer.deleteProduct(id);
    }

    // Customer service endpoints

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerViewModel createCustomer(@RequestBody CustomerViewModel customer){
        return adminServiceLayer.addNewCustomer(customer);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomerViewModel getCustomerById(@PathVariable int customerId){
        return adminServiceLayer.fetchCustomerById(customerId);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomers(){
        return adminServiceLayer.fetchAllCustomers();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody CustomerViewModel customer){
        adminServiceLayer.updateCustomer(customer);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int customerId){
        adminServiceLayer.deleteCustomer(customerId);
    }


    //Level-up service endpoints

    @PostMapping("/levelups")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel createLevelUp(@RequestBody LevelUpViewModel levelUp){
        return adminServiceLayer.addNewLeveup(levelUp);
    }

    @GetMapping("/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUp(@PathVariable int id){
        return adminServiceLayer.fetchLevelUpById(id);
    }

    @GetMapping("/levelups")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUps(){
        return adminServiceLayer.fetchAllLevelUps();
    }

    @PutMapping("/levelups")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody LevelUpViewModel levelUp){
        adminServiceLayer.updateLevelUp(levelUp);
    }

    @DeleteMapping("/levelup/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int id){
        adminServiceLayer.deleteLevelUp(id);
    }


    // Inventory servic endpoints

    @PostMapping("/inventories")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel createInventory(@RequestBody InventoryViewModel inventory){
        return adminServiceLayer.addNewInventory(inventory);
    }

    @GetMapping("/inventories")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventories(){
        return adminServiceLayer.fetchAllInventories();
    }

    @GetMapping("/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewModel getInventoryById(@PathVariable int id){
        return adminServiceLayer.fetchInventoryById(id);
    }

    @PutMapping("/inventories")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInventory(@RequestBody InventoryViewModel inventory){
        adminServiceLayer.updateInventory(inventory);
    }

    @DeleteMapping("/inventory/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventoryById(@PathVariable int id){
        adminServiceLayer.deleteInventory(id);
    }


    // Invoice service endpoints

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return adminServiceLayer.addNewInvoice(invoiceViewModel);
    }

    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices(){
        return adminServiceLayer.fetchAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id){
        return adminServiceLayer.fetchInvoiceById(id);
    }

    @GetMapping("/invoices/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId){
        return adminServiceLayer.fetchInvoicesByCustomerId(customerId);
    }



}
