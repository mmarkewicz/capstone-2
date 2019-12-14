package com.company.retailapiservice.service;

import com.company.retailapiservice.feign.*;
import com.company.retailapiservice.model.*;
import com.company.retailapiservice.util.message.LevelUpEntry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class RetailAPIService {

    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "levelup.list.add.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    InvoiceServiceFeign invoiceFeign;

    @Autowired
    ProductServiceFeign productFeign;

    @Autowired
    LevelUpServiceFeign levelUpFeign;

    @Autowired
    InventoryServiceFeign inventoryFeign;

    @Autowired
    CustomerServiceFeign customerFeign;

    public InvoiceViewModelWithPoints addInvoice(InvoiceViewModel invoiceViewModel) throws Exception {

        // order must contain a valid customer
        try {
            customerFeign.fetchCustomerById(invoiceViewModel.getCustomerId());
        } catch (Exception e) {
            throw new Exception("That is not a valid customer ID");
        }

        // order must contain valid products
        try {
            invoiceViewModel.getInvoiceItems().stream()
                    .forEach(invoiceItem -> inventoryFeign.getInventoryById(invoiceItem.getInventory_id()));
        } catch (Exception e) {
            throw new Exception("That is not a valid product ID");
        }

        AtomicBoolean isQuantityValid = new AtomicBoolean(true);

        // order quantity must be greater than 0 and less than or equal to the number of items in each inventory
        invoiceViewModel.getInvoiceItems().stream()
                .forEach(invoiceItem -> {
                    Inventory inventory = inventoryFeign.getInventoryById(invoiceItem.getInventory_id());

                    if (inventory.getQuantity() <= 0 || invoiceItem.getQuantity() > inventory.getQuantity()) {
                        isQuantityValid.set(false);
                    }
                });

        if (isQuantityValid.equals(false)) {
            throw new Exception("The quantity requested is not available");
        }

        // get total price of invoice (sum of all unit prices in invoice items)
        // quantity * unit price of each inventoryitem
        List<InvoiceItem> invoiceItemList = invoiceViewModel.getInvoiceItems();
        List<BigDecimal> priceList = invoiceItemList.stream()
                .map(invoiceItem -> invoiceItem.getUnitPrice().multiply(new BigDecimal(invoiceItem.getQuantity())))
                .collect(Collectors.toList());
        BigDecimal total = new BigDecimal(0);

        for (BigDecimal price : priceList) {
            total = total.add(price);
        }

        // (total price % 50) * 10 is total number of level up points that will be added
        int points = total.divide(new BigDecimal(50)).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal(10)).setScale(2, RoundingMode.DOWN).toBigInteger().intValue();

        invoiceViewModel = invoiceFeign.postInvoice(invoiceViewModel);

        InvoiceViewModelWithPoints invoiceViewModelWithPoints = new InvoiceViewModelWithPoints();
        invoiceViewModelWithPoints.setId(invoiceViewModel.getId());
        invoiceViewModelWithPoints.setCustomerId(invoiceViewModel.getCustomerId());
        invoiceViewModelWithPoints.setInvoiceItems(invoiceViewModel.getInvoiceItems());
        invoiceViewModelWithPoints.setPurchaseDate(invoiceViewModel.getPurchaseDate());
        invoiceViewModelWithPoints.setLevelUpPoints(points);
        invoiceViewModelWithPoints.setTotal(total);

        // decrease number of products if successful order is placed
        invoiceViewModel.getInvoiceItems().stream()
                .forEach(invoiceItem -> {
                    int numProducts = invoiceItem.getQuantity();
                    Inventory inventory = inventoryFeign.getInventoryById(invoiceItem.getInventory_id());
                    inventory.setQuantity(inventory.getQuantity() - numProducts);
                    inventoryFeign.updateInventory(inventory);
                });

        // TODO: send level up points to db via queue
        LevelUpEntry msg = new LevelUpEntry();
        msg.setCustomerId(invoiceViewModelWithPoints.getCustomerId());
        msg.setPoints(points);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);

        return invoiceViewModelWithPoints;
    }

    public InvoiceViewModel getInvoiceById(int id) {
        return invoiceFeign.getInvoiceById(id);
    }

    public List<InvoiceViewModel> getAllInvoices() {
        return invoiceFeign.getAllInvoices();
    }

    public List<InvoiceViewModel> getInvoicesByCustomerId(int id) {
        return invoiceFeign.getInvoicesByCustomerId(id);
    }

    public List<Product> getProductsInInventory() {
        return productFeign.fetchAllProducts();
    }

    public Product getProductById(int id) {
        return productFeign.fetchProductById(id);
    }

    public List<Product> getProductsByInvoiceId(int id) {

        // get all invoice items
        List<InvoiceItem> invoiceItems = invoiceFeign.getInvoiceById(id).getInvoiceItems();

        // get all inventory ids from invoice items
        List<Integer> inventoryIds = invoiceItems.stream()
                .map(invoiceItem -> invoiceItem.getInventory_id())
                .collect(Collectors.toList());

        // get all inventories
        List<Inventory> inventoryList = inventoryIds.stream()
                .map(integer -> inventoryFeign.getInventoryById(integer)).collect(Collectors.toList());

        // use product id from inventories to get all products
        List<Product> productList = inventoryList.stream()
                .map(inventory -> productFeign.fetchProductById(inventory.getProductId()))
                .collect(Collectors.toList());

        return productList;
    }

    public int getLevelUpPointsByCustomerId(int id) {
        List<LevelUp> levelUps = levelUpFeign.getAllLevelUps().stream().filter(levelUp -> levelUp.getCustomer_id() == id).collect(Collectors.toList());
        return levelUps.get(0).getPoints();
    }
}
