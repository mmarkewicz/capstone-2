package com.company.adminservice.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {

    private int level_up_id;
    @NotNull(message = "Please supply a customer id")
    private int customer_id;
    @NotNull(message = "Please supply the number of points")
    private int points;
    @NotNull(message = "Please supply a date")
    private LocalDate member_date;

    public LevelUpViewModel() {
    }

    public LevelUpViewModel(int level_up_id, int customer_id, int points, LocalDate member_date) {
        this.level_up_id = level_up_id;
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
    }

    public LevelUpViewModel(int customer_id, int points, LocalDate member_date) {
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
    }

    public int getLevel_up_id() {
        return level_up_id;
    }

    public void setLevel_up_id(int level_up_id) {
        this.level_up_id = level_up_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMember_date() {
        return member_date;
    }

    public void setMember_date(LocalDate member_date) {
        this.member_date = member_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpViewModel that = (LevelUpViewModel) o;
        return getLevel_up_id() == that.getLevel_up_id() &&
                getCustomer_id() == that.getCustomer_id() &&
                getPoints() == that.getPoints() &&
                Objects.equals(getMember_date(), that.getMember_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevel_up_id(), getCustomer_id(), getPoints(), getMember_date());
    }

    @Override
    public String toString() {
        return "LevelUpViewModel{" +
                "level_up_id=" + level_up_id +
                ", customer_id=" + customer_id +
                ", points=" + points +
                ", member_date=" + member_date +
                '}';
    }
}
