package com.bedel.app.kamerbiz.Entity;

import java.io.Serializable;
import java.util.List;

public class Enterprise implements Serializable {
    private String name;
    private String djname;
    private String logo;
    private long like;
    private long dislike;
    private String date;
    public boolean testlike=false;
    private String description;
    private String nbreemploye;
    private String chiffredaffaire;
    private String phonenumberfirst;
    private String phonenumbersecondaire;
    private String RCCM;
    private String NIUV;
    private List<Comment> comments;
    private List<Service> services;
    private List<Category> categories;

    public Enterprise() {
    }

    public Enterprise(String name, String djname, String logo, long like, long dislike, String date, String description, String nbreemploye, String chiffredaffaire, String phonenumberfirst, String phonenumbersecondaire, String RCCM, String NIUV, List<Comment> comments, List<Service> services, List<Category> categories) {
        this.name = name;
        this.djname = djname;
        this.logo = logo;
        this.like = like;
        this.dislike = dislike;
        this.date = date;
        this.description = description;
        this.nbreemploye = nbreemploye;
        this.chiffredaffaire = chiffredaffaire;
        this.phonenumberfirst = phonenumberfirst;
        this.phonenumbersecondaire = phonenumbersecondaire;
        this.RCCM = RCCM;
        this.NIUV = NIUV;
        this.comments = comments;
        this.services = services;
        this.categories = categories;
    }

    public Enterprise(String name, String djname, String logo, String date, String description, List<Category> categories) {
        this.name = name;
        this.djname = djname;
        this.logo = logo;
        this.date = date;
        this.description = description;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDjname() {
        return djname;
    }

    public void setDjname(String djname) {
        this.djname = djname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getDislike() {
        return dislike;
    }

    public void setDislike(long dislike) {
        this.dislike = dislike;
    }

    public String getNbreemploye() {
        return nbreemploye;
    }

    public void setNbreemploye(String nbreemploye) {
        this.nbreemploye = nbreemploye;
    }

    public String getChiffredaffaire() {
        return chiffredaffaire;
    }

    public void setChiffredaffaire(String chiffredaffaire) {
        this.chiffredaffaire = chiffredaffaire;
    }

    public String getPhonenumberfirst() {
        return phonenumberfirst;
    }

    public void setPhonenumberfirst(String phonenumberfirst) {
        this.phonenumberfirst = phonenumberfirst;
    }

    public String getPhonenumbersecondaire() {
        return phonenumbersecondaire;
    }

    public void setPhonenumbersecondaire(String phonenumbersecondaire) {
        this.phonenumbersecondaire = phonenumbersecondaire;
    }

    public String getRCCM() {
        return RCCM;
    }

    public void setRCCM(String RCCM) {
        this.RCCM = RCCM;
    }

    public String getNIUV() {
        return NIUV;
    }

    public void setNIUV(String NIUV) {
        this.NIUV = NIUV;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean hasTag(String string) {
        for (Category category : categories) {
            if (category.getText().equals(string)) {
                return true;
            }
        }

        return false;
    }

}
