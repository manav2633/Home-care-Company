package com.example.homeservices;

public class worker_model {

    String name;
    String field;
    String rating;
    String price;
    String image;

    public worker_model(String name, String field, String rating, String price, String image) {
        this.name = name;
        this.field = field;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public worker_model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
