package com.example.jetty_jersey.ws.requests;

public class MapRequest {

    private String name;
    private Integer privacy;
//    private InputStream image;

    public MapRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

//    public InputStream getImage() {
//        return image;
//    }
//
//    public void setImage(InputStream image) {
//        this.image = image;
//    }

    @Override
    public String toString() {
        return "name: " + name +
                "\nprivacy: " + privacy;
//                "image: " + image.toString();
    }
}
