package com.games.myapplication;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import android.os.CountDownTimer;
import android.os.Handler;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



import java.util.Random;

public class DndActivity extends AppCompatActivity {

    private ImageView ivhewan;
    private ImageButton btn1, btn2, btn3;
    private TextView tvscore, tvtopscore, tvnourut;
    private int score = 0;
    private int questionIndex = 0;
    private MediaPlayer mediaplayerbenar, mediaplayersalah;
    private int jawabanBenar = 0;
    private int jawabanSalah = 0;
    private CountDownTimer countDownTimer;
    private AppDatabase db;

    private int[] questions = {
            R.drawable.aardvark_img,
            R.drawable.aguti_img,
            R.drawable.alpaka_img,
            R.drawable.angrang_img,
            R.drawable.angsa_img,
            R.drawable.anjing_img,
            R.drawable.anoa_img,
            R.drawable.antelop_img,
            R.drawable.armadilo_img,
            R.drawable.ayam_img,
            R.drawable.ayam_hutan_img,
            R.drawable.aye_aye_img,
            R.drawable.babi_img,
            R.drawable.babi_hutan_img,
            R.drawable.babi_rusa_img,
            R.drawable.badak_img,
            R.drawable.banteng_img,
            R.drawable.belalang_img,
            R.drawable.belatung_img,
            R.drawable.beluga_img,
            R.drawable.belut_img,
            R.drawable.belut_listrik_img,
            R.drawable.beruang_img,
            R.drawable.beruang_madu_img,
            R.drawable.beruk_img,
            R.drawable.biawak_img,
            R.drawable.binturong_img,
            R.drawable.buaya_img,
            R.drawable.burung_img,
            R.drawable.burung_albatros_img,
            R.drawable.burung_apus_img,
            R.drawable.burung_bangau_img,
            R.drawable.burung_bangau_putih_img,
            R.drawable.burung_bubut_img,
            R.drawable.burung_beo_img,
            R.drawable.burung_cendet_img,
            R.drawable.burung_cendrawasih_img,
            R.drawable.burung_cikrak_img,
            R.drawable.burung_dedalu_img,
            R.drawable.burung_derkuku_img,
            R.drawable.burung_elang_img,
            R.drawable.burung_falkon_img,
            R.drawable.burung_gagak_img,
            R.drawable.burung_gagak_hutan_img,
            R.drawable.burung_jalak_img,
            R.drawable.burung_kakaktua_img,
            R.drawable.burung_kalkun_img,
            R.drawable.burung_kenari_img,
            R.drawable.burung_kolibri_img,
            R.drawable.burung_kutilang_img,
            R.drawable.burung_merak_img,
            R.drawable.burung_merpati_img,
            R.drawable.burung_nandu_img,
            R.drawable.burung_nuri_img,
            R.drawable.burung_pelikan_img,
            R.drawable.burung_perkutut_img,
            R.drawable.burung_puyuh_img,
            R.drawable.burung_rajawali_img,
            R.drawable.burung_rangkong_img,
            R.drawable.burung_tengkek_img,
            R.drawable.burung_walet_img,
            R.drawable.burung_flamingo_img,
            R.drawable.cacing_img,
            R.drawable.capung_img,
            R.drawable.cicak_img,
            R.drawable.cecurut_img,
            R.drawable.cheetah_img,
            R.drawable.cumi_cumi_img,
            R.drawable.domba_img,
            R.drawable.dugong_img,
            R.drawable.ermine_img,
            R.drawable.ferret_img,
            R.drawable.gajah_img,
            R.drawable.gaur_img,
            R.drawable.geko_img,
            R.drawable.gibon_img,
            R.drawable.galagos_img,
            R.drawable.gorila_img,
            R.drawable.gundi_img,
            R.drawable.gurita_img,
            R.drawable.hamster_img,
            R.drawable.harimau_img,
            R.drawable.harimau_sumatra_img,
            R.drawable.hiena_img,
            R.drawable.iguana_img,
            R.drawable.ikan_img,
            R.drawable.ikan_arwana_img,
            R.drawable.ikan_bandeng_img,
            R.drawable.ikan_bawal_img,
            R.drawable.ikan_baronang_img,
            R.drawable.ikan_belanak_img,
            R.drawable.ikan_betok_img,
            R.drawable.ikan_buntal_img,
            R.drawable.ikan_cakalang_img,
            R.drawable.ikan_cupang_img,
            R.drawable.ikan_dorado_img,
            R.drawable.ikan_dottyback_img,
            R.drawable.ikan_ekor_kuning_img,
            R.drawable.ikan_gabus_img,
            R.drawable.ikan_gobi_img,
            R.drawable.ikan_gurame_img,
            R.drawable.ikan_hiu_img,
            R.drawable.ikan_kakap_img,
            R.drawable.ikan_kembung_img,
            R.drawable.ikan_kerapu_img,
            R.drawable.ikan_kuwe_img,
            R.drawable.ikan_lamprey_img,
            R.drawable.ikan_lais_img,
            R.drawable.ikan_lele_img,
            R.drawable.ikan_mas_img,
            R.drawable.ikan_manta_img,
            R.drawable.ikan_mola_mola_img,
            R.drawable.ikan_mujair_img,
            R.drawable.ikan_nila_img,
            R.drawable.ikan_pari_img,
            R.drawable.ikan_piranha_img,
            R.drawable.ikan_pipih_img,
            R.drawable.ikan_tenggiri_img,
            R.drawable.ikan_teri_img,
            R.drawable.ikan_terbang_img,
            R.drawable.ikan_tongkol_img,
            R.drawable.ikan_tuna_img,
            R.drawable.ikan_wader_img,
            R.drawable.itik_img,
            R.drawable.jaguar_img,
            R.drawable.jangkrik_img,
            R.drawable.jerapah_img,
            R.drawable.kaki_seribu_img,
            R.drawable.kalajengking_img,
            R.drawable.kambing_img,
            R.drawable.kancil_img,
            R.drawable.katak_img,
            R.drawable.kecoa_img,
            R.drawable.keledai_img,
            R.drawable.kelelawar_img,
            R.drawable.kelinci_img,
            R.drawable.kelomang_img,
            R.drawable.kerang_img,
            R.drawable.kerbau_img,
            R.drawable.kepiting_img,
            R.drawable.kijang_img,
            R.drawable.koala_img,
            R.drawable.komodo_img,
            R.drawable.kuda_img,
            R.drawable.kuda_laut_img,
            R.drawable.kucing_img,
            R.drawable.kucing_hutan_img,
            R.drawable.kumbang_img,
            R.drawable.kungkang_img,
            R.drawable.kunang_kunang_img,
            R.drawable.kura_kura_img,
            R.drawable.kuskus_img,
            R.drawable.kangguru_img,
            R.drawable.kutu_img,
            R.drawable.laba_laba_img,
            R.drawable.labi_labi_img,
            R.drawable.lalat_img,
            R.drawable.landak_img,
            R.drawable.laron_img,
            R.drawable.lebah_img,
            R.drawable.lemur_img,
            R.drawable.lingsang_img,
            R.drawable.lintah_img,
            R.drawable.lobster_img,
            R.drawable.lumba_lumba_img,
            R.drawable.lutung_img,
            R.drawable.luwak_img,
            R.drawable.luwe_img,
            R.drawable.macan_tutul_img,
            R.drawable.mantis_img,
            R.drawable.marmut_img,
            R.drawable.monyet_img,
            R.drawable.musang_img,
            R.drawable.nyamuk_img,
            R.drawable.kalong_img,
            R.drawable.orangutan_img,
            R.drawable.owa_img,
            R.drawable.panda_img,
            R.drawable.paus_img,
            R.drawable.penguin_img,
            R.drawable.penyu_img,
            R.drawable.platipus_img,
            R.drawable.quokka_img,
            R.drawable.rakun_img,
            R.drawable.rayap_img,
            R.drawable.remis_img,
            R.drawable.rusa_img,
            R.drawable.sapi_img,
            R.drawable.semut_img,
            R.drawable.serigala_img,
            R.drawable.siamang_img,
            R.drawable.sidat_img,
            R.drawable.sigung_img,
            R.drawable.siput_img,
            R.drawable.singa_img,
            R.drawable.sotong_img,
            R.drawable.tapir_img,
            R.drawable.tikus_img,
            R.drawable.tokek_img,
            R.drawable.trenggiling_img,
            R.drawable.tupai_img,
            R.drawable.tupai_tanah_img,
            R.drawable.tuntong_img,
            R.drawable.udang_img,
            R.drawable.ular_img,
            R.drawable.ular_sanca_img,
            R.drawable.ulat_img,
            R.drawable.unta_img,
            R.drawable.viper_img,
            R.drawable.walabi_img,
            R.drawable.walrus_img,
            R.drawable.yak_img,
            R.drawable.zebra_img,
            R.drawable.zebu_img
    };

    private int[] answers = {
            R.drawable.aardvark_indo,
            R.drawable.aguti_indo,
            R.drawable.alpaka_indo,
            R.drawable.angrang_indo,
            R.drawable.angsa_indo,
            R.drawable.anjing_indo,
            R.drawable.anoa_indo,
            R.drawable.antelop_indo,
            R.drawable.armadilo_indo,
            R.drawable.ayam_indo,
            R.drawable.ayam_hutan_indo,
            R.drawable.aye_aye_indo,
            R.drawable.babi_indo,
            R.drawable.babi_hutan_indo,
            R.drawable.babi_rusa_indo,
            R.drawable.badak_indo,
            R.drawable.banteng_indo,
            R.drawable.belalang_indo,
            R.drawable.belatung_indo,
            R.drawable.beluga_indo,
            R.drawable.belut_indo,
            R.drawable.belut_listrik_indo,
            R.drawable.beruang_indo,
            R.drawable.beruang_madu_indo,
            R.drawable.beruk_indo,
            R.drawable.biawak_indo,
            R.drawable.binturong_indo,
            R.drawable.buaya_indo,
            R.drawable.burung_indo,
            R.drawable.burung_albatros_indo,
            R.drawable.burung_apus_indo,
            R.drawable.burung_bangau_indo,
            R.drawable.burung_bangau_putih_indo,
            R.drawable.burung_bubut_indo,
            R.drawable.burung_beo_indo,
            R.drawable.burung_cendet_indo,
            R.drawable.burung_cendrawasih_indo,
            R.drawable.burung_cikrak_indo,
            R.drawable.burung_dedalu_indo,
            R.drawable.burung_derkuku_indo,
            R.drawable.burung_elang_indo,
            R.drawable.burung_falkon_indo,
            R.drawable.burung_gagak_indo,
            R.drawable.burung_gagak_hutan_indo,
            R.drawable.burung_jalak_indo,
            R.drawable.burung_kakaktua_indo,
            R.drawable.burung_kalkun_indo,
            R.drawable.burung_kenari_indo,
            R.drawable.burung_kolibri_indo,
            R.drawable.burung_kutilang_indo,
            R.drawable.burung_merak_indo,
            R.drawable.burung_merpati_indo,
            R.drawable.burung_nandu_indo,
            R.drawable.burung_nuri_indo,
            R.drawable.burung_pelikan_indo,
            R.drawable.burung_perkutut_indo,
            R.drawable.burung_puyuh_indo,
            R.drawable.burung_rajawali_indo,
            R.drawable.burung_rangkong_indo,
            R.drawable.burung_tengkek_indo,
            R.drawable.burung_walet_indo,
            R.drawable.burung_flamingo_indo,
            R.drawable.cacing_indo,
            R.drawable.capung_indo,
            R.drawable.cicak_indo,
            R.drawable.cecurut_indo,
            R.drawable.cheetah_indo,
            R.drawable.cumi_cumi_indo,
            R.drawable.domba_indo,
            R.drawable.dugong_indo,
            R.drawable.ermine_indo,
            R.drawable.ferret_indo,
            R.drawable.gajah_indo,
            R.drawable.gaur_indo,
            R.drawable.geko_indo,
            R.drawable.gibon_indo,
            R.drawable.galagos_indo,
            R.drawable.gorila_indo,
            R.drawable.gundi_indo,
            R.drawable.gurita_indo,
            R.drawable.hamster_indo,
            R.drawable.harimau_indo,
            R.drawable.harimau_sumatra_indo,
            R.drawable.hiena_indo,
            R.drawable.iguana_indo,
            R.drawable.ikan_indo,
            R.drawable.ikan_arwana_indo,
            R.drawable.ikan_bandeng_indo,
            R.drawable.ikan_bawal_indo,
            R.drawable.ikan_baronang_indo,
            R.drawable.ikan_belanak_indo,
            R.drawable.ikan_betok_indo,
            R.drawable.ikan_buntal_indo,
            R.drawable.ikan_cakalang_indo,
            R.drawable.ikan_cupang_indo,
            R.drawable.ikan_dorado_indo,
            R.drawable.ikan_dottyback_indo,
            R.drawable.ikan_ekor_kuning_indo,
            R.drawable.ikan_gabus_indo,
            R.drawable.ikan_gobi_indo,
            R.drawable.ikan_gurame_indo,
            R.drawable.ikan_hiu_indo,
            R.drawable.ikan_kakap_indo,
            R.drawable.ikan_kembung_indo,
            R.drawable.ikan_kerapu_indo,
            R.drawable.ikan_kuwe_indo,
            R.drawable.ikan_lamprey_indo,
            R.drawable.ikan_lais_indo,
            R.drawable.ikan_lele_indo,
            R.drawable.ikan_mas_indo,
            R.drawable.ikan_manta_indo,
            R.drawable.ikan_mola_mola_indo,
            R.drawable.ikan_mujair_indo,
            R.drawable.ikan_nila_indo,
            R.drawable.ikan_pari_indo,
            R.drawable.ikan_piranha_indo,
            R.drawable.ikan_pipih_indo,
            R.drawable.ikan_tenggiri_indo,
            R.drawable.ikan_teri_indo,
            R.drawable.ikan_terbang_indo,
            R.drawable.ikan_tongkol_indo,
            R.drawable.ikan_tuna_indo,
            R.drawable.ikan_wader_indo,
            R.drawable.itik_indo,
            R.drawable.jaguar_indo,
            R.drawable.jangkrik_indo,
            R.drawable.jerapah_indo,
            R.drawable.kaki_seribu_indo,
            R.drawable.kalajengking_indo,
            R.drawable.kambing_indo,
            R.drawable.kancil_indo,
            R.drawable.katak_indo,
            R.drawable.kecoa_indo,
            R.drawable.keledai_indo,
            R.drawable.kelelawar_indo,
            R.drawable.kelinci_indo,
            R.drawable.kelomang_indo,
            R.drawable.kerang_indo,
            R.drawable.kerbau_indo,
            R.drawable.kepiting_indo,
            R.drawable.kijang_indo,
            R.drawable.koala_indo,
            R.drawable.komodo_indo,
            R.drawable.kuda_indo,
            R.drawable.kuda_laut_indo,
            R.drawable.kucing_indo,
            R.drawable.kucing_hutan_indo,
            R.drawable.kumbang_indo,
            R.drawable.kungkang_indo,
            R.drawable.kunang_kunang_indo,
            R.drawable.kura_kura_indo,
            R.drawable.kuskus_indo,
            R.drawable.kangguru_indo,
            R.drawable.kutu_indo,
            R.drawable.laba_laba_indo,
            R.drawable.labi_labi_indo,
            R.drawable.lalat_indo,
            R.drawable.landak_indo,
            R.drawable.laron_indo,
            R.drawable.lebah_indo,
            R.drawable.lemur_indo,
            R.drawable.lingsang_indo,
            R.drawable.lintah_indo,
            R.drawable.lobster_indo,
            R.drawable.lumba_lumba_indo,
            R.drawable.lutung_indo,
            R.drawable.luwak_indo,
            R.drawable.luwe_indo,
            R.drawable.macan_tutul_indo,
            R.drawable.mantis_indo,
            R.drawable.marmut_indo,
            R.drawable.monyet_indo,
            R.drawable.musang_indo,
            R.drawable.nyamuk_indo,
            R.drawable.kalong_indo,
            R.drawable.orangutan_indo,
            R.drawable.owa_indo,
            R.drawable.panda_indo,
            R.drawable.paus_indo,
            R.drawable.penguin_indo,
            R.drawable.penyu_indo,
            R.drawable.platipus_indo,
            R.drawable.quokka_indo,
            R.drawable.rakun_indo,
            R.drawable.rayap_indo,
            R.drawable.remis_indo,
            R.drawable.rusa_indo,
            R.drawable.sapi_indo,
            R.drawable.semut_indo,
            R.drawable.serigala_indo,
            R.drawable.siamang_indo,
            R.drawable.sidat_indo,
            R.drawable.sigung_indo,
            R.drawable.siput_indo,
            R.drawable.singa_indo,
            R.drawable.sotong_indo,
            R.drawable.tapir_indo,
            R.drawable.tikus_indo,
            R.drawable.tokek_indo,
            R.drawable.trenggiling_indo,
            R.drawable.tupai_indo,
            R.drawable.tupai_tanah_indo,
            R.drawable.tuntong_indo,
            R.drawable.udang_indo,
            R.drawable.ular_indo,
            R.drawable.ular_sanca_indo,
            R.drawable.ulat_indo,
            R.drawable.unta_indo,
            R.drawable.viper_indo,
            R.drawable.walabi_indo,
            R.drawable.walrus_indo,
            R.drawable.yak_indo,
            R.drawable.zebra_indo,
            R.drawable.zebu_indo

    };

    private long timerDuration = 7000; // 7 detik untuk menjawab soal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dnd);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivhewan = findViewById(R.id.ivhewan);
        btn1 = findViewById(R.id.imageButton1);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.imageButton3);
        tvscore = findViewById(R.id.tvscore_value);
        tvnourut = findViewById(R.id.tvnourut);
        tvtopscore = findViewById(R.id.tvtopscore);

        mediaplayerbenar = MediaPlayer.create(this, R.raw.jwbbenar);
        mediaplayersalah = MediaPlayer.create(this, R.raw.jwbsalah);

        db = AppDatabase.getInstance(getApplicationContext());
        // Load top score from the database
        new Thread(() -> {
            String topScoreText = "Belum ada top skor";
            DragAndDrop topDnd = db.draganddropDao().getHighestScore();
            if (topDnd != null) {
                topScoreText = topDnd.getSkor() + "/100";
            }

            // Update UI with the top score
            String finalTopScoreText = topScoreText;
            runOnUiThread(() -> tvtopscore.setText(finalTopScoreText));
        }).start();

        // Randomize questions and answers using Fisher-Yates
        shuffleQuestionsAndAnswers();

        loadNextQuestion();

        ivhewan.setOnDragListener(dragListener);

        btn1.setOnLongClickListener(longClickListener);
        btn2.setOnLongClickListener(longClickListener);
        btn3.setOnLongClickListener(longClickListener);
    }

    // Fisher-Yates Shuffle to shuffle the questions and answers
    private void shuffleQuestionsAndAnswers() {
        List<Integer> questionList = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            questionList.add(i);
        }

        // Fisher-Yates shuffle algorithm
        for (int i = questionList.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Collections.swap(questionList, i, j);
        }

        int[] shuffledQuestions = new int[questions.length];
        int[] shuffledAnswers = new int[answers.length];

        for (int i = 0; i < questionList.size(); i++) {
            shuffledQuestions[i] = questions[questionList.get(i)];
            shuffledAnswers[i] = answers[questionList.get(i)];
        }

        questions = shuffledQuestions;
        answers = shuffledAnswers;
    }

    // Load the next question or end the game if 20 questions are done
    private void loadNextQuestion() {
        if (questionIndex >= 20) {  // Batasi sampai 20 soal
            int finalScore = score * 1;  // Ubah skala dari 20 ke 100


            // Mengirimkan data ke TotalscoreActivity
            Intent intent = new Intent(DndActivity.this, TotalscoreActivity.class);
            intent.putExtra("SCORE", finalScore);
            intent.putExtra("JAWABAN_BENAR", jawabanBenar);
            intent.putExtra("JAWABAN_SALAH", jawabanSalah);
            intent.putExtra("SOURCE", "dnd"); // <-- INI JUGA KUNCI
            startActivity(intent);
            finish();
            return;
        }

        ivhewan.setImageResource(questions[questionIndex]);
        tvnourut.setText("Soal Ke: " + (questionIndex + 1));
        tvscore.setText(  score + "/100");

        List<Integer> pilihan = new ArrayList<>();
        pilihan.add(answers[questionIndex]);

        while (pilihan.size() < 3) {
            int randomIndex = (int) (Math.random() * answers.length);
            if (!pilihan.contains(answers[randomIndex])) {
                pilihan.add(answers[randomIndex]);
            }
        }

        Collections.shuffle(pilihan);

        btn1.setImageResource(pilihan.get(0));
        btn1.setTag(pilihan.get(0));

        btn2.setImageResource(pilihan.get(1));
        btn2.setTag(pilihan.get(1));

        btn3.setImageResource(pilihan.get(2));
        btn3.setTag(pilihan.get(2));

        // Start timer for 7 seconds
        startTimer();

    }

    // Mulai timer 7 detik untuk soal
    private void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel timer sebelumnya jika ada
        }

        countDownTimer = new CountDownTimer(timerDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Tidak ada update UI di sini, timer berjalan di belakang layar
            }

            @Override
            public void onFinish() {
                // Jika waktu habis, dianggap salah
                jawabanSalah++;
                ivhewan.setImageResource(answers[questionIndex]); // Tampilkan jawaban yang benar

                // Jeda 2 detik sebelum soal berikutnya
                new Handler().postDelayed(() -> {
                    questionIndex++;
                    loadNextQuestion();
                }, 2000); // Jeda 2 detik
            }
        };

        countDownTimer.start();
    }

    private View.OnLongClickListener longClickListener = view -> {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDragAndDrop(data, shadowBuilder, view, 0);
        return true;
    };

    private View.OnDragListener dragListener = (view, event) -> {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                View draggedView = (View) event.getLocalState();
                Object tagObj = draggedView.getTag();

                if (tagObj == null) return true;  // Jika tidak ada tag, abaikan

                int draggedTag = (int) tagObj;  // Konversi aman ke int
                int correctAnswer = answers[questionIndex];

                if (draggedTag == correctAnswer) {
                    view.setBackgroundColor(Color.GREEN);
                    mediaplayerbenar.start();
                    score += 5;  // Poin hanya ditambah jika benar
                    jawabanBenar += 1;// tambah jawaban benar
                    ivhewan.setImageResource(draggedTag); // Tampilkan jawaban di soal
                } else {
                    jawabanSalah += 1;// tambah jawaban salah
                    view.setBackgroundColor(Color.RED);
                    mediaplayersalah.start();
                    ivhewan.setImageResource(draggedTag); // Tampilkan jawaban di soal
                }

                new Handler().postDelayed(() -> {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    questionIndex++;
                    loadNextQuestion();
                }, 2000); // Jeda 2 detik sebelum soal berikutnya

                break;
        }
        return true;
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel timer jika activity dihancurkan
        }
        mediaplayerbenar.release();
        mediaplayersalah.release();
    }
}