package com.games.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import android.view.WindowManager;
import android.util.TypedValue;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnImageUser, btnStudy, btnPlay, btnClose;

    private AppDatabase db;

    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_USER_ID = "id_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Mengatur padding agar tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(getApplicationContext());


        // Tombol keluar
        ImageButton btnClose = findViewById(R.id.btnclose);
        btnClose.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog)
                    .setTitle("Konfirmasi")
                    .setMessage("Yakin anda ingin keluar ?")
                    .setPositiveButton("YA", (dialogInterface, i) -> {
                        finishAffinity();
                        Toast.makeText(MainActivity.this, "Anda telah keluar dari aplikasi", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Tidak", (dialogInterface, i) -> dialogInterface.dismiss())
                    .setCancelable(false)
                    .create();

            dialog.setOnShowListener(d -> {
                if (dialog.getWindow() != null) {
                    // Ubah ukuran dialog
                    int widthInPx = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics());
                    int heightInPx = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 190, getResources().getDisplayMetrics());

                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    params.width = widthInPx;
                    params.height = heightInPx;
                    dialog.getWindow().setAttributes(params);

                    // Pastikan background putih (jika style tidak diterapkan oleh sistem)
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
                }
            });

            dialog.show();
        });



        // Tombol Study
        ImageButton btnStudy = findViewById(R.id.btnstudy);
        btnStudy.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StudyActivity.class));
        });

        // Tombol Play
        ImageButton btnPlay = findViewById(R.id.btnplay);
        btnPlay.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PlayActivity.class));
        });

        // Tombol User
        ImageButton btnImageUser = findViewById(R.id.btnuser);
        btnImageUser.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            long userIdLong = sharedPreferences.getLong("user_id", -1L);
            int userId = (int) userIdLong;
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

    }
}
