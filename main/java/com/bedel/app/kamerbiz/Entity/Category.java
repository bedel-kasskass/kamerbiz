package com.bedel.app.kamerbiz.Entity;



import android.support.annotation.NonNull;

import com.yalantis.filter.model.FilterModel;

import java.io.Serializable;
import java.util.ArrayList;


public class Category implements FilterModel,Serializable {
    private String text;
    private int color;
    public String parent;
    private int level;


    public Category() {

    }

    public Category(String text, int color, String parent, int level) {
        this.text = text;
        this.color = color;
        this.parent = parent;
        this.level = level;
    }

    public Category(String text, int color) {
        this.text = text;
        this.color = color;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (getColor() != category.getColor()) return false;
        return getText().equals(category.getText());

    }

    @Override
    public int hashCode() {
        int result = getText().hashCode();
        result = 31 * result + getColor();
        return result;
    }

}
