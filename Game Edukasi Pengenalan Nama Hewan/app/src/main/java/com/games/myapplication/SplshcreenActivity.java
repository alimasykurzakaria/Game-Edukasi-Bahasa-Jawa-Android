package com.games.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplshcreenActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_prefs"; // Nama file preferences
    private static final String KEY_IS_REGISTERED = "is_registered"; // Key untuk status pendaftaran

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splshcreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Memeriksa apakah pengguna sudah melakukan registrasi sebelumnya
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isRegistered = sharedPreferences.getBoolean(KEY_IS_REGISTERED, false); // Default false jika belum ada data

        // Menunda perpindahan activity selama 3 detik
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            if (isRegistered) {
                // Jika sudah terdaftar, langsung menuju MainActivity
                intent = new Intent(SplshcreenActivity.this, MainActivity.class);
            } else {
                // Jika belum terdaftar, menuju LoginActivity
                intent = new Intent(SplshcreenActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish(); // Menutup SplashScreenActivity
        }, 3000); // Waktu delay 3 detik
    }
}
