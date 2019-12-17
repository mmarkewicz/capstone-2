package com.company.adminservice.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InventoryViewModel {

    private int id;
    @NotNull(message = "Please supply a product id")
    private int productId;
    @NotNull(message = "Please supply a quantity")
    private int quantity;

    public InventoryViewModel() {
    }

    public InventoryViewModel(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public InventoryViewModel(int id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryViewModel that = (InventoryViewModel) o;
        return getId() == that.getId() &&
                getProductId() == that.getProductId() &&
                getQuantity() == that.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getQuantity());
    }

    @Override
    public String toString() {
        return "InventoryViewModel{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
