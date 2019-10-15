package com.example.foodmann;

public class category {
    private String name;
    private String image;
    private String owner;
    private String stars;
    private String address;
    private String latitude;
    private String longitude;

public category(){

}
public category(String name,String image,String owner,String stars,String address,String latitude,String longitude){
    this.name=name;
    this.image=image;
    this.owner=owner;
    this.stars=stars;
    this.address=address;
    this.latitude=latitude;
    this.longitude=longitude;

}

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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


    public void setImage(String image) {
        this.image = image;
    }
}
