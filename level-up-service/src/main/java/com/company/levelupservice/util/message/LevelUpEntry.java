package com.company.levelupservice.util.message;

import java.util.Objects;

public class LevelUpEntry {

    private int points;
    private int customerId;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LevelUpEntry)) return false;
        LevelUpEntry that = (LevelUpEntry) o;
        return points == that.points &&
                customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, customerId);
    }

    @Override
    public String toString() {
        return "LevelUpEntry{" +
                "points=" + points +
                ", customerId=" + customerId +
                '}';
    }
}
