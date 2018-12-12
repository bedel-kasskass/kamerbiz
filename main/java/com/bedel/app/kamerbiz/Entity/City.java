package com.bedel.app.kamerbiz.Entity;

import java.io.Serializable;

public class City implements Serializable {
    public String id;
    public String name;

    public City() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
