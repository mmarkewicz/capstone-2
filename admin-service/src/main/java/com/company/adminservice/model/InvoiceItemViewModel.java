package com.company.adminservice.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItemViewModel {

    private int id;
    @NotNull(message = "You must proide an invoice id")
    private int invoiceId;
    @NotNull(message = "You must provide an inventory id")
    private int inventory_id;
    @NotNull(message = "You must provide a quantity")
    private int quantity;
    @NotNull(message = "You must provide a unit price")
    private BigDecimal unitPrice;

    public InvoiceItemViewModel() {
    }

    public InvoiceItemViewModel(@NotNull(message = "You must proide an invoice id") int invoiceId, @NotNull(message = "You must provide an inventory id") int inventory_id, @NotNull(message = "You must provide a quantity") int quantity, @NotNull(message = "You must provide a unit price") BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.inventory_id = inventory_id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceItemViewModel(int id, @NotNull(message = "You must proide an invoice id") int invoiceId, @NotNull(message = "You must provide an inventory id") int inventory_id, @NotNull(message = "You must provide a quantity") int quantity, @NotNull(message = "You must provide a unit price") BigDecimal unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.inventory_id = inventory_id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return getId() == that.getId() &&
                getInvoiceId() == that.getInvoiceId() &&
                getInventory_id() == that.getInventory_id() &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getUnitPrice(), that.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInvoiceId(), getInventory_id(), getQuantity(), getUnitPrice());
    }

    @Override
    public String toString() {
        return "InvoiceItemViewModel{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", inventory_id=" + inventory_id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

}
