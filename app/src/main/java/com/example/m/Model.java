package com.example.m;
public class Model {
    String name;
    int image;
    String text2;
    String Count;
    String id ;


    public Model(String name, int image,String text2 ) {
        this.name = name;
        this.image = image;
        this.text2=text2;

    }
    public Model(String name, int image,String text2,String Count ) {
        this.name = name;
        this.image = image;
        this.text2=text2;
        this.Count=Count;

    }

    public Model(String id ,String name, int image,String text2,String Count ) {
        this.name = name;
        this.image = image;
        this.text2=text2;
        this.Count=Count;
        this.id=id;

    }


    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

