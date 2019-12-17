package com.company.retailapiservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModelWithPoints {

    private int id;
    private int customerId;
    private LocalDate purchaseDate;
    private List<InvoiceItem> invoiceItems;
    private int levelUpPoints;
    private BigDecimal total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public int getLevelUpPoints() {
        return levelUpPoints;
    }

    public void setLevelUpPoints(int levelUpPoints) {
        this.levelUpPoints = levelUpPoints;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceViewModelWithPoints)) return false;
        InvoiceViewModelWithPoints that = (InvoiceViewModelWithPoints) o;
        return id == that.id &&
                customerId == that.customerId &&
                levelUpPoints == that.levelUpPoints &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(invoiceItems, that.invoiceItems) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, purchaseDate, invoiceItems, levelUpPoints, total);
    }

    @Override
    public String toString() {
        return "InvoiceViewModelWithPoints{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", invoiceItems=" + invoiceItems +
                ", levelUpPoints=" + levelUpPoints +
                ", total=" + total +
                '}';
    }
}
