package com.games.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayActivity extends AppCompatActivity {

    private Button btnQuiz, btnDnd;
    private ImageButton imgBtnQuiz, imgBtnDnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi tombol dan image button
        btnQuiz = findViewById(R.id.btnquiz);
        btnDnd = findViewById(R.id.btndraganddrop);
        imgBtnQuiz = findViewById(R.id.imgbtnquiz);
        imgBtnDnd = findViewById(R.id.imgbtndnd);

        // Aksi Ketika Tombol Quiz atau imagebutton diklik
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke Activity Quiz
                Intent intent = new Intent(PlayActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        imgBtnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke Activity Quiz
                Intent intent = new Intent(PlayActivity.this, QuizActivity.class);
                startActivity(intent);
                }
        });

        btnDnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke Activity Drag and Drop
                Intent intent = new Intent(PlayActivity.this, DndActivity.class);
                startActivity(intent);
            }
        });

        imgBtnDnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke Activity Drag and Drop
                Intent intent = new Intent(PlayActivity.this, DndActivity.class);
                startActivity(intent);
            }
        });
    }
}