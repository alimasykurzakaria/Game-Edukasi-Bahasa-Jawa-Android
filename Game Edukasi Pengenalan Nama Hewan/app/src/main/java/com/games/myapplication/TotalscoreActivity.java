package com.games.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TotalscoreActivity extends AppCompatActivity {

    private TextView tvTopSkor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalscore);

        tvTopSkor = findViewById(R.id.tvnotopskor); // ✅ pastikan ID di layout benar
        TextView tvFinalScore = findViewById(R.id.tvFinalScore);
        TextView tvCorrectAnswers = findViewById(R.id.tvCorrectAnswers);
        TextView tvIncorrectAnswers = findViewById(R.id.tvIncorrectAnswers);
        ImageView ivStars = findViewById(R.id.ivStars);
        Button btnmenuutama = findViewById(R.id.btnmenuutama);

        int score = getIntent().getIntExtra("SCORE", 0);
        int correctAnswers = getIntent().getIntExtra("JAWABAN_BENAR", 0);
        int incorrectAnswers = getIntent().getIntExtra("JAWABAN_SALAH", 0);
        String source = getIntent().getStringExtra("SOURCE"); // quiz atau dnd

        // Tampilkan skor
        tvFinalScore.setText(score + "/100");
        tvCorrectAnswers.setText(correctAnswers + "/20");
        tvIncorrectAnswers.setText(incorrectAnswers + "/20");

        // Tampilkan bintang
        if (score >= 80) {
            ivStars.setImageResource(R.drawable.three_stars);
        } else if (score >= 55) {
            ivStars.setImageResource(R.drawable.two_stars);
        } else if (score >= 25) {
            ivStars.setImageResource(R.drawable.one_star);
        } else {
            ivStars.setImageResource(R.drawable.kosong);
        }

        // Tampilkan top skor dari database
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());

            String topScoreText = "Belum ada top skor";

            if ("quiz".equals(source)) {
                Quiz topQuiz = db.quizDao().getHighestScore();
                if (topQuiz != null) {
                    topScoreText = topQuiz.getSkor() + "/100";
                }
            } else if ("dnd".equals(source)) {
                DragAndDrop topDnd = db.draganddropDao().getHighestScore();
                if (topDnd != null) {
                    topScoreText = + topDnd.getSkor() + "/100";
                }
            }

            String finalTopScoreText = topScoreText;
            runOnUiThread(() -> tvTopSkor.setText(finalTopScoreText));
        }).start();

        btnmenuutama.setOnClickListener(v -> {
            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());

                if ("quiz".equals(source)) {
                    Quiz quiz = new Quiz();
                    quiz.setSkor(score);
                    quiz.setBenar(correctAnswers);
                    quiz.setSalah(incorrectAnswers);
                    long idQuiz = db.quizDao().insertQuiz(quiz);

                    User user = db.userDao().getUserById(1); // Ganti dengan ID user dari SharedPreferences kalau perlu
                    user.setIdQuiz((int) idQuiz);
                    db.userDao().update(user);
                } else if ("dnd".equals(source)) {
                    DragAndDrop dnd = new DragAndDrop();
                    dnd.setSkor(score);
                    dnd.setBenar(correctAnswers);
                    dnd.setSalah(incorrectAnswers);
                    long idDnd = db.draganddropDao().insertDnd(dnd);

                    User user = db.userDao().getUserById(1); // Ganti dengan ID user dari SharedPreferences kalau perlu
                    user.setIdDnd((int) idDnd);
                    db.userDao().update(user);
                }

                runOnUiThread(() -> {
                    Intent intent = new Intent(TotalscoreActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }).start();
        });
    }
}
