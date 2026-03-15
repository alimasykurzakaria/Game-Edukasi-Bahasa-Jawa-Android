package com.games.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id_user;

    @ColumnInfo(name = "nama_user")
    private String namaUser;

    private int umur;
    private int kelas;
    private String gender; // <-- tambahkan ini

    @ColumnInfo(name = "id_dnd")
    private int idDnd;

    @ColumnInfo(name = "id_quiz")
    private int idQuiz;

    // Getter dan Setter
    public int getId_user() { return id_user; }
    public void setId_user(int id_user) { this.id_user = id_user; }

    public String getNamaUser() { return namaUser; }
    public void setNamaUser(String namaUser) { this.namaUser = namaUser; }

    public int getUmur() { return umur; }
    public void setUmur(int umur) { this.umur = umur; }

    public int getKelas() { return kelas; }
    public void setKelas(int kelas) { this.kelas = kelas; }

    public String getGender() { return gender; }  // <-- Getter
    public void setGender(String gender) { this.gender = gender; } // <-- Setter

    public int getIdDnd() { return idDnd; }
    public void setIdDnd(int idDnd) { this.idDnd = idDnd; }

    public int getIdQuiz() { return idQuiz; }
    public void setIdQuiz(int idQuiz) { this.idQuiz = idQuiz; }
}







