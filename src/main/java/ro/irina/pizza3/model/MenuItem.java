package ro.irina.pizza3.model;

import java.io.Serializable;
import java.util.Arrays;

public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String category;
    private String name;
    private String[] topping;
    private double price;
    private Integer rank;

    public MenuItem(){

    }

    public MenuItem(Integer id, String category, String name, String[] topping, double price, Integer rank) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.topping = topping;
        this.price = price;
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTopping() {
        return topping;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", topping=" + Arrays.toString(topping) +
                ", price=" + price +
                ", rank=" + rank +
                '}';
    }
}
