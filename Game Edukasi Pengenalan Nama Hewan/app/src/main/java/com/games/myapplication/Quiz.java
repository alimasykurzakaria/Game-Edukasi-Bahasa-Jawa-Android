package com.games.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Query;


@Entity(tableName = "quiz")
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    private int id_quiz;

    private int skor;
    private int salah;
    private int benar;

    // Getter dan Setter
    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
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
