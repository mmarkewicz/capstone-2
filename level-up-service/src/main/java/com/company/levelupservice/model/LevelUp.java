package com.company.levelupservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUp {

    private int level_up_id;
    private int customer_id;
    private int points;
    private LocalDate member_date;

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
        if (!(o instanceof LevelUp)) return false;
        LevelUp levelUp = (LevelUp) o;
        return level_up_id == levelUp.level_up_id &&
                customer_id == levelUp.customer_id &&
                points == levelUp.points &&
                Objects.equals(member_date, levelUp.member_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level_up_id, customer_id, points, member_date);
    }

    @Override
    public String toString() {
        return "LevelUp{" +
                "level_up_id=" + level_up_id +
                ", customer_id=" + customer_id +
                ", points=" + points +
                ", member_date=" + member_date +
                '}';
    }
}
