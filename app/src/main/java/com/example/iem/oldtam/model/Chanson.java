package com.example.iem.oldtam.model;


import com.google.gson.annotations.SerializedName;

public class Chanson {
    @SerializedName("id")
    private String id;
    @SerializedName("artiste")
    private String artiste;
    @SerializedName("titre")
    private String titre;
    @SerializedName("album")
    private String album;
    @SerializedName("paroles")
    private String paroles;
    private boolean isChecked;

    public Chanson(String id, String artiste, String titre, String album, String paroles) {

        this.id = id;
        this.artiste = artiste;
        this.titre = titre;
        this.album = album;
        this.paroles = paroles;
        this.isChecked = false;
    }

    @Override
    public String toString() {
        return "Chanson{" +
                "id='" + id + '\'' +
                ", artiste='" + artiste + '\'' +
                ", titre='" + titre + '\'' +
                ", album='" + album + '\'' +
                ", paroles='" + paroles + '\'' +
                '}';
    }

    public Chanson(){
        //do nothing
    }

    public boolean isChecked(){
        return this.isChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getParoles() {
        return paroles;
    }

    public void setParoles(String paroles) {
        this.paroles = paroles;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
