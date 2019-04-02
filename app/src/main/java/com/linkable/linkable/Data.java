package com.linkable.linkable;

public class Data {
    private String title;
    private String autor;
    private String description;
    private String location;
    private String imagesource;
    private int node;
    private int sellnum;

    public Data(String title,String autor,String description,String location,String imagesource,int node,int sellnum){
        this.title=title;this.autor=autor;this.description=description;this.imagesource=imagesource;this.location=location;
        this.node=node;this.sellnum=sellnum;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescription() {
        return description;
    }

    public String getImagesource() {
        return imagesource;
    }

    public String getLocation() {
        return location;
    }

    public int getNode() {
        return node;
    }
    public int getSellnum() {
        return sellnum;
    }
}