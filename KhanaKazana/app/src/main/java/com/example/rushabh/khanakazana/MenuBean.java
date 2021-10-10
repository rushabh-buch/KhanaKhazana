package com.example.rushabh.khanakazana;

public class MenuBean {

    private String dish_name;
    private Integer dish_price;
    private int quantity=0;

    public MenuBean(String dish_name, Integer dish_price, int quantity) {
        this.dish_name = dish_name;
        this.dish_price = dish_price;
        this.quantity = quantity;
    }


    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getDish_price() {
        return dish_price;
    }

    public void setDish_price(Integer dish_price) {
        this.dish_price = dish_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
