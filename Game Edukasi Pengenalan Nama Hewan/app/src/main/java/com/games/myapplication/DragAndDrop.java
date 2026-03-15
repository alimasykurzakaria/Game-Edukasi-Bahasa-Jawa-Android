package com.games.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Query;


@Entity(tableName = "draganddrop")
public class DragAndDrop {
    @PrimaryKey(autoGenerate = true)
    private int id_dnd;

    @ColumnInfo(name = "id_user")
    private int id_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @ColumnInfo(name = "skor")
    private int skor;

    @ColumnInfo(name = "salah")
    private int salah;

    @ColumnInfo(name = "benar")
    private int benar;

    //getter dan setter
    public int getId_dnd() {
        return id_dnd;
    }

    public void setId_dnd(int id_dnd) {
        this.id_dnd = id_dnd;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public int getSalah() {
        return salah;
    }

    public void setSalah(int salah) {
        this.salah = salah;
    }

    public int getBenar() {
        return benar;
    }

    public void setBenar(int benar) {
        this.benar = benar;
    }

}
