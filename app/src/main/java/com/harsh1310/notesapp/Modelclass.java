package com.harsh1310.notesapp;

import java.util.Date;

public class Modelclass {
    private String title,content,date,color;

public Modelclass()
{

}
    public Modelclass(String title, String content,String date,String color) {
        this.title = title;
        this.content = content;
        this.date=date;
        this.color=color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getcontent() {
        return content;
    }

    public void setContent(String content) {
        this.content =content;
    }
    public String getdate() {
        return date;
    }

    public void setDate(String date) {
        this.date =date;
    }
    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color =color;
    }


}
