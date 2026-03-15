package com.games.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView etGender;
    private EditText etNama, etUmur, etKelas;
    private Button btnRegister;
    private TextView tvNama, tvUmur, tvKelas, tvJudul;
    private static final String PREFS_NAME = "user_prefs"; // Nama file preferences
    private static final String KEY_IS_REGISTERED = "is_registered"; // Key untuk status pendaftaran
    private static final String KEY_USER_ID = "id_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        etNama = findViewById(R.id.et_name);
        etUmur = findViewById(R.id.et_age);
        etKelas = findViewById(R.id.et_class);
        btnRegister = findViewById(R.id.btn_register);
        tvNama = findViewById(R.id.tvnama);
        tvUmur = findViewById(R.id.tvumur);
        tvKelas = findViewById(R.id.tvkelas);
        tvJudul = findViewById(R.id.tvjudul);
        etGender = findViewById(R.id.et_gender);

        // Gender options setup
        String[] genderOptions = {"Laki-laki", "Perempuan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, genderOptions);
        etGender.setAdapter(adapter);
        etGender.setOnClickListener(v -> etGender.showDropDown());

        btnRegister.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String umurString = etUmur.getText().toString();
            String kelas = etKelas.getText().toString();
            String gender = etGender.getText().toString();

            // Validate user input
            if (nama.isEmpty() || umurString.isEmpty() || kelas.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int umurInt = Integer.parseInt(umurString);
                    // Create user object
                    User user = new User();
                    user.setNamaUser(nama);
                    user.setUmur(umurInt);
                    user.setKelas(Integer.parseInt(kelas)); // Assuming kelas is an integer
                    user.setGender(gender); // Save gender

                    // Save user to database in background thread
                    AppDatabase db = AppDatabase.getInstance(this);
                    UserDao userDao = db.userDao();

                    new Thread(() -> {
                        long userId = userDao.insert(user); // Insert user and get generated ID

                        // Save user info in SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(KEY_IS_REGISTERED, true);
                        editor.putLong(KEY_USER_ID, userId); // Save user ID as long
                        editor.putString("gender", gender);
                        editor.apply();

                        runOnUiThread(() -> {
                            Toast.makeText(this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                            // Move to MainActivity or UserActivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();  // Close LoginActivity
                        });
                    }).start();

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Usia dan Kelas harus berupa angka", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
