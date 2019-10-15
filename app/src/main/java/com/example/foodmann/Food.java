package com.example.foodmann;

public class Food {
    private String name;
    private String image;
    private String description;
    private String price;
    private String discount;
    private String menu;
    private String number;

    public Food() {
    }

    public Food(String name, String image, String description, String price, String discount, String menu,String number) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.menu = menu;
        this.number=number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName(){
    return name;
}
public void setName(){
    name=name;
}
    public String getImage(){
        return image;
    }
    public void setImage(){
        image=image;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(){
        description=description;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(){
        price=price;
    }
    public String getDiscount(){
        return discount;
    }
    public void setDiscount(){
        discount=discount;
    }
    public String getMenu(){
        return menu;
    }
    public void setMenu(){
        menu=menu;
    }
}
