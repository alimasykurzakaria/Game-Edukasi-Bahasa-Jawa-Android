package com.games.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class UserActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvUserInfo, tvDndResult, tvQuizResult;
    private MaterialButton btnLogout;
    private ImageView ivGender;

    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_USER_ID = "id_user";      // Harus sama dengan LoginActivity & MainActivity
    private static final String KEY_GENDER = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Inisialisasi view
        tvUserInfo = findViewById(R.id.tvUserInfo);
        tvDndResult = findViewById(R.id.tvDndResult);
        tvQuizResult = findViewById(R.id.tvQuizResult);
        btnLogout = findViewById(R.id.btnlogout);
        ivGender = findViewById(R.id.ivgender);

        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        long userIdLong = sharedPreferences.getLong(KEY_USER_ID, -1L);
        int userId = (int) userIdLong;

        String gender = sharedPreferences.getString(KEY_GENDER, "");
        //Toast.makeText(this, "Gender terdeteksi : " + gender, Toast.LENGTH_SHORT).show();
        if ("Laki-laki".equals(gender)) {
            ivGender.setImageResource(R.drawable.cowok);
        } else if ("Perempuan".equals(gender)) {
            ivGender.setImageResource(R.drawable.cewek);
        } else {
            ivGender.setImageResource(R.drawable.kosong);  // default image
        }

        db = AppDatabase.getInstance(getApplicationContext());

        new Thread(() -> {
            User user = db.userDao().getUserById(userId);
            DragAndDrop dndResult = db.draganddropDao().getHighestScore();  // perubahan di sini
            Quiz quizResult = db.quizDao().getHighestScore();               // perubahan di sini


            if (user != null) {
                dndResult = db.draganddropDao().getHighestScore();
                quizResult = db.quizDao().getHighestScore();
            }

            final DragAndDrop finalDndResult = dndResult;
            final Quiz finalQuizResult = quizResult;

            runOnUiThread(() -> {
                if (user != null) {
                    tvUserInfo.setText("Nama: " + user.getNamaUser() +
                            "\nUmur: " + user.getUmur() +
                            "\nKelas: " + user.getKelas());
                } else {
                    tvUserInfo.setText("User tidak ditemukan");
                }

                if (finalDndResult != null) {
                    tvDndResult.setText("Skor DND: " + finalDndResult.getSkor() +
                            ", Benar: " + finalDndResult.getBenar() +
                            ", Salah: " + finalDndResult.getSalah());
                } else {
                    tvDndResult.setText("Belum ada data Drag and Drop");
                }

                if (finalQuizResult != null) {
                    tvQuizResult.setText("Skor Quiz: " + finalQuizResult.getSkor() +
                            ", Benar: " + finalQuizResult.getBenar() +
                            ", Salah: " + finalQuizResult.getSalah());
                } else {
                    tvQuizResult.setText("Belum ada data Quiz");
                }
            });
        }).start();

        btnLogout.setOnClickListener(v -> new Thread(() -> {
            // Logout: Hapus SharedPreferences, tapi **jangan hapus user di database** kecuali memang ingin hapus permanen
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            runOnUiThread(() -> {
                Toast.makeText(UserActivity.this, "Berhasil logout", Toast.LENGTH_SHORT).show();
                Intent intentLogout = new Intent(UserActivity.this, LoginActivity.class);
                intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                finish();
            });
        }).start());
    }
}
