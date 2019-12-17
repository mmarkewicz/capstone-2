package com.company.adminservice.service;

import com.company.adminservice.model.*;
import com.company.adminservice.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceLayer {

    @Autowired
    private final ProductServiceClient productServiceClient;

    @Autowired
    private final CustomerServiceClient customerServiceClient;

    @Autowired
    private final LevelUpClient levelUpClient;

    @Autowired
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    private final InvoiceServiceClient invoiceServiceClient;

    public AdminServiceLayer(ProductServiceClient productServiceClient, CustomerServiceClient customerServiceClient, LevelUpClient levelUpClient, InventoryServiceClient inventoryServiceClient, InvoiceServiceClient invoiceServiceClient) {
        this.productServiceClient = productServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.levelUpClient = levelUpClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.invoiceServiceClient = invoiceServiceClient;
    }

    // Product service endpoints

    public ProductViewModel fetchProductById(int productId) {

        Product product = productServiceClient.fetchProductById(productId);
        ProductViewModel pvm = new ProductViewModel();

        pvm = productViewModelBuilder(product);

        return pvm;
    }

    public List<ProductViewModel> fetchAllProducts(){

        List<Product> productList = productServiceClient.fetchAllProducts();

        List<ProductViewModel> pvmList = new ArrayList<>();

        for(Product p : productList){
            ProductViewModel pvm = productViewModelBuilder(p);
            pvmList.add(pvm);
        }
        return pvmList;
    }

    public ProductViewModel addNewProduct(ProductViewModel productViewModel){

        Product product = new Product();
        product.setProductName(productViewModel.getProductName());
        product.setProductDescription(productViewModel.getProductDescription());
        product.setListPrice(productViewModel.getListPrice());
        product.setUnitCost(productViewModel.getUnitCost());

        product = productServiceClient.addNewProduct(product);

        productViewModel.setProductId(product.getProductId());

        return productViewModel;

    }

    public void updateProduct(ProductViewModel productViewModel){

        Product product = new Product();
        product.setProductName(productViewModel.getProductName());
        product.setProductDescription(productViewModel.getProductDescription());
        product.setListPrice(productViewModel.getListPrice());
        product.setUnitCost(productViewModel.getUnitCost());
        product.setProductId(productViewModel.getProductId());

        productServiceClient.updateProduct(product);
    }

    public void deleteProduct(int productId){
        productServiceClient.deleteProduct(productId);
    }


    //Helper method to build productViewModel from Customer
    public ProductViewModel productViewModelBuilder(Product product){
        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setProductId(product.getProductId());
        productViewModel.setProductName(product.getProductName());
        productViewModel.setProductDescription(product.getProductDescription());
        productViewModel.setListPrice(product.getListPrice());
        productViewModel.setUnitCost(product.getUnitCost());

        return productViewModel;
    }

    // Customer service Endpoints

    public CustomerViewModel fetchCustomerById(int customerId) {

        Customer customer = customerServiceClient.fetchCustomerById(customerId);
        CustomerViewModel cvm = customerViewModelBuilder(customer);

        return cvm;

    }

    public List<CustomerViewModel> fetchAllCustomers(){

        List<Customer> customerList = customerServiceClient.fetchAllCustomers();

        List<CustomerViewModel> cvmList = new ArrayList<>();

        for(Customer c : customerList){
            CustomerViewModel cvm = customerViewModelBuilder(c);
            cvmList.add(cvm);
        }
        return cvmList;
    }

    public CustomerViewModel addNewCustomer(CustomerViewModel customerViewModel){

        Customer customer = new Customer();
        customer.setFirstName(customerViewModel.getFirstName());
        customer.setLastName(customerViewModel.getLastName());
        customer.setCity(customerViewModel.getCity());
        customer.setStreet(customerViewModel.getStreet());
        customer.setZip(customerViewModel.getZip());
        customer.setEmail(customerViewModel.getEmail());
        customer.setPhone(customerViewModel.getPhone());

        customer = customerServiceClient.addNewCustomer(customer);

        customerViewModel.setCustomerId(customer.getCustomerId());

        return customerViewModel;

    }

    public void updateCustomer(CustomerViewModel customerViewModel){

        Customer customer = new Customer();
        customer.setFirstName(customerViewModel.getFirstName());
        customer.setLastName(customerViewModel.getLastName());
        customer.setCity(customerViewModel.getCity());
        customer.setStreet(customerViewModel.getStreet());
        customer.setZip(customerViewModel.getZip());
        customer.setEmail(customerViewModel.getCity());
        customer.setPhone(customerViewModel.getPhone());
        customer.setCustomerId(customerViewModel.getCustomerId());

        customerServiceClient.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId){
        customerServiceClient.deleteCustomer(customerId);
    }


    //Helper method to build CustomerViewModel from Customer
    public CustomerViewModel customerViewModelBuilder(Customer customer){
        CustomerViewModel cvm = new CustomerViewModel();
        cvm.setCustomerId(customer.getCustomerId());
        cvm.setFirstName(customer.getFirstName());
        cvm.setLastName(customer.getLastName());
        cvm.setCity(customer.getCity());
        cvm.setStreet(customer.getStreet());
        cvm.setZip(customer.getZip());
        cvm.setEmail(customer.getEmail());
        cvm.setPhone(customer.getPhone());

        return cvm;
    }


    // LevelUp services

    public LevelUpViewModel fetchLevelUpById(int levelUpId) {

        LevelUp levelUp = levelUpClient.getLevelUp(levelUpId);
        LevelUpViewModel lvm = new LevelUpViewModel();

        lvm = levelUpViewModelBuilder(levelUp);

        return lvm;
    }

    public List<LevelUpViewModel> fetchAllLevelUps(){

        List<LevelUp> levelUpList = levelUpClient.getAllLevelUps();

        List<LevelUpViewModel> lvmList = new ArrayList<>();

        for(LevelUp l : levelUpList){
            LevelUpViewModel lvm = levelUpViewModelBuilder(l);
            lvmList.add(lvm);
        }
        return lvmList;
    }

    public LevelUpViewModel addNewLeveup(LevelUpViewModel levelUpViewModel){

        LevelUp levelUp = new LevelUp();
        levelUp.setCustomer_id(levelUpViewModel.getCustomer_id());
        levelUp.setMember_date(levelUpViewModel.getMember_date());
        levelUp.setPoints(levelUpViewModel.getPoints());

        levelUp = levelUpClient.postLevelUp(levelUp);

        levelUpViewModel.setLevel_up_id(levelUp.getLevel_up_id());

        return levelUpViewModel;

    }

    public void updateLevelUp(LevelUpViewModel levelUpViewModel){

        LevelUp levelUp = new LevelUp();
        levelUp.setCustomer_id(levelUpViewModel.getCustomer_id());
        levelUp.setMember_date(levelUpViewModel.getMember_date());
        levelUp.setPoints(levelUpViewModel.getPoints());
        levelUp.setLevel_up_id(levelUpViewModel.getLevel_up_id());

        levelUpClient.putLevelUp(levelUp);
    }

    public void deleteLevelUp(int levelUpId){
        levelUpClient.deleteLevelUp(levelUpId);
    }

    //Helper method to build CustomerViewModel from Customer
    public LevelUpViewModel levelUpViewModelBuilder(LevelUp levelUp){
        LevelUpViewModel lvm = new LevelUpViewModel();
        lvm.setLevel_up_id(levelUp.getLevel_up_id());
        lvm.setCustomer_id(levelUp.getCustomer_id());
        lvm.setMember_date(levelUp.getMember_date());
        lvm.setPoints(levelUp.getPoints());

        return lvm;
    }


    // Inventory service

    public InventoryViewModel fetchInventoryById(int id){
        Inventory inventory = inventoryServiceClient.getInventoryById(id);
        InventoryViewModel ivm = new InventoryViewModel();
        ivm = inventoryViewModelBuilder(inventory);

        return ivm;

    }

    public List<InventoryViewModel> fetchAllInventories(){
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList = inventoryServiceClient.getAllInventories();

        List<InventoryViewModel> ivmList = new ArrayList<>();

        for (Inventory i : inventoryList){
            InventoryViewModel ivm = new InventoryViewModel();
            ivm = inventoryViewModelBuilder(i);
            ivmList.add(ivm);
        }

        return ivmList;
    }

    public InventoryViewModel addNewInventory(InventoryViewModel ivm){
        Inventory inventory = new Inventory();
        inventory.setProductId(ivm.getProductId());
        inventory.setQuantity(ivm.getQuantity());

        inventory = inventoryServiceClient.postInventory(inventory);

        ivm.setId(inventory.getId());

        return ivm;
    }

    public void updateInventory(InventoryViewModel ivm){
        Inventory inventory = new Inventory();
        inventory.setProductId(ivm.getProductId());
        inventory.setQuantity(ivm.getQuantity());
        inventory.setId(ivm.getId());

        inventoryServiceClient.updateInventory(inventory);
    }

    public void deleteInventory(int id){
        inventoryServiceClient.deleteInventoryById(id);
    }

    //Helper method to build inventoryViewModel from inventory
    public InventoryViewModel inventoryViewModelBuilder(Inventory inventory){
        InventoryViewModel ivm = new InventoryViewModel();
        ivm.setId(inventory.getId());
        ivm.setProductId(inventory.getProductId());
        ivm.setQuantity(inventory.getQuantity());

        return ivm;
    }

    // Invoice service

    public InvoiceViewModel addNewInvoice(InvoiceViewModel invoiceViewModel){
        invoiceViewModel = invoiceServiceClient.postInvoice(invoiceViewModel);

        return invoiceViewModel;
    }

    public List<InvoiceViewModel> fetchAllInvoices(){

        return invoiceServiceClient.getAllInvoices();
    }

    public InvoiceViewModel fetchInvoiceById(int id){
        return invoiceServiceClient.getInvoiceById(id);
    }

    public List<InvoiceViewModel> fetchInvoicesByCustomerId(int customerId){
        return invoiceServiceClient.getInvoicesByCustomerId(customerId);
    }

}
