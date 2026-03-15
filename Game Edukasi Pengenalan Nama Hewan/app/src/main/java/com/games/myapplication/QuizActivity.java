package com.games.myapplication;

import com.games.myapplication.TotalscoreActivity;

import android.media.MediaPlayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import android.os.CountDownTimer;
import android.os.Handler;


import java.util.Random;

public class QuizActivity extends AppCompatActivity {


    private MediaPlayer soundbtn;
    private ImageView soal;
    private ImageButton jwb1, jwb2, jwb3;
    private TextView tvscore, tvnourut;

    private int score = 0;
    private int currentQuestionIndex = 0; // Menyimpan indeks soal saat ini
    private int currentQuestionNumber = 1; // Nomor soal saat ini
    private MediaPlayer mediaPlayerBenar;
    private MediaPlayer mediaPlayerSalah;
    private int jawabanBenar = 0;
    private int jawabanSalah = 0;
    private AppDatabase db;




    private int[] images = {
            R.drawable.aardvark_img, R.drawable.aguti_img, R.drawable.alpaka_img, R.drawable.angrang_img, R.drawable.angsa_img, R.drawable.anjing_img, R.drawable.anoa_img, R.drawable.antelop_img, R.drawable.armadilo_img, R.drawable.ayam_img, R.drawable.ayam_hutan_img, R.drawable.aye_aye_img, R.drawable.babi_img, R.drawable.babi_hutan_img, R.drawable.babi_rusa_img, R.drawable.badak_img, R.drawable.banteng_img, R.drawable.belalang_img, R.drawable.belatung_img, R.drawable.beluga_img, R.drawable.belut_img, R.drawable.belut_listrik_img, R.drawable.beruang_img, R.drawable.beruang_madu_img, R.drawable.beruk_img, R.drawable.biawak_img, R.drawable.binturong_img, R.drawable.buaya_img, R.drawable.burung_img, R.drawable.burung_albatros_img, R.drawable.burung_apus_img, R.drawable.burung_bangau_img, R.drawable.burung_bangau_putih_img, R.drawable.burung_bubut_img, R.drawable.burung_beo_img, R.drawable.burung_cendet_img, R.drawable.burung_cendrawasih_img, R.drawable.burung_cikrak_img, R.drawable.burung_dedalu_img, R.drawable.burung_derkuku_img, R.drawable.burung_elang_img, R.drawable.burung_falkon_img, R.drawable.burung_gagak_img, R.drawable.burung_gagak_hutan_img, R.drawable.burung_jalak_img, R.drawable.burung_kakaktua_img, R.drawable.burung_kalkun_img, R.drawable.burung_kenari_img, R.drawable.burung_kolibri_img, R.drawable.burung_kutilang_img, R.drawable.burung_merak_img, R.drawable.burung_merpati_img, R.drawable.burung_nandu_img, R.drawable.burung_nuri_img, R.drawable.burung_pelikan_img, R.drawable.burung_perkutut_img, R.drawable.burung_puyuh_img, R.drawable.burung_rajawali_img, R.drawable.burung_rangkong_img, R.drawable.burung_tengkek_img, R.drawable.burung_walet_img, R.drawable.burung_flamingo_img, R.drawable.cacing_img, R.drawable.capung_img, R.drawable.cicak_img, R.drawable.cecurut_img, R.drawable.cheetah_img, R.drawable.cumi_cumi_img, R.drawable.domba_img, R.drawable.dugong_img, R.drawable.ermine_img, R.drawable.ferret_img, R.drawable.gajah_img, R.drawable.gaur_img, R.drawable.geko_img, R.drawable.gibon_img, R.drawable.galagos_img, R.drawable.gorila_img, R.drawable.gundi_img, R.drawable.gurita_img, R.drawable.hamster_img, R.drawable.harimau_img, R.drawable.harimau_sumatra_img, R.drawable.hiena_img, R.drawable.iguana_img, R.drawable.ikan_img, R.drawable.ikan_arwana_img, R.drawable.ikan_bandeng_img, R.drawable.ikan_bawal_img, R.drawable.ikan_baronang_img, R.drawable.ikan_belanak_img, R.drawable.ikan_betok_img, R.drawable.ikan_buntal_img, R.drawable.ikan_cakalang_img, R.drawable.ikan_cupang_img, R.drawable.ikan_dorado_img, R.drawable.ikan_dottyback_img, R.drawable.ikan_ekor_kuning_img, R.drawable.ikan_gabus_img, R.drawable.ikan_gobi_img, R.drawable.ikan_gurame_img, R.drawable.ikan_hiu_img, R.drawable.ikan_kakap_img, R.drawable.ikan_kembung_img, R.drawable.ikan_kerapu_img, R.drawable.ikan_kuwe_img, R.drawable.ikan_lamprey_img, R.drawable.ikan_lais_img, R.drawable.ikan_lele_img, R.drawable.ikan_mas_img, R.drawable.ikan_manta_img, R.drawable.ikan_mola_mola_img, R.drawable.ikan_mujair_img, R.drawable.ikan_nila_img, R.drawable.ikan_pari_img, R.drawable.ikan_piranha_img, R.drawable.ikan_pipih_img, R.drawable.ikan_tenggiri_img, R.drawable.ikan_teri_img, R.drawable.ikan_terbang_img, R.drawable.ikan_tongkol_img, R.drawable.ikan_tuna_img, R.drawable.ikan_wader_img,R.drawable.itik_img, R.drawable.jaguar_img, R.drawable.jangkrik_img, R.drawable.jerapah_img, R.drawable.kaki_seribu_img, R.drawable.kalajengking_img, R.drawable.kambing_img, R.drawable.kancil_img, R.drawable.katak_img, R.drawable.kecoa_img, R.drawable.keledai_img, R.drawable.kelelawar_img, R.drawable.kelinci_img, R.drawable.kelomang_img, R.drawable.kerang_img, R.drawable.kerbau_img, R.drawable.kepiting_img, R.drawable.kijang_img, R.drawable.koala_img, R.drawable.komodo_img, R.drawable.kuda_img, R.drawable.kuda_laut_img, R.drawable.kucing_img, R.drawable.kucing_hutan_img, R.drawable.kumbang_img, R.drawable.kungkang_img, R.drawable.kunang_kunang_img, R.drawable.kura_kura_img, R.drawable.kuskus_img, R.drawable.kangguru_img, R.drawable.kutu_img, R.drawable.laba_laba_img, R.drawable.labi_labi_img, R.drawable.lalat_img, R.drawable.landak_img, R.drawable.laron_img, R.drawable.lebah_img, R.drawable.lemur_img, R.drawable.lingsang_img, R.drawable.lintah_img, R.drawable.lobster_img, R.drawable.lumba_lumba_img, R.drawable.lutung_img, R.drawable.luwak_img, R.drawable.luwe_img, R.drawable.macan_tutul_img, R.drawable.mantis_img, R.drawable.marmut_img, R.drawable.monyet_img, R.drawable.musang_img, R.drawable.nyamuk_img, R.drawable.kalong_img, R.drawable.orangutan_img, R.drawable.owa_img, R.drawable.panda_img, R.drawable.paus_img, R.drawable.penguin_img, R.drawable.penyu_img, R.drawable.platipus_img, R.drawable.quokka_img, R.drawable.rakun_img, R.drawable.rayap_img, R.drawable.remis_img, R.drawable.rusa_img, R.drawable.sapi_img, R.drawable.semut_img, R.drawable.serigala_img, R.drawable.siamang_img, R.drawable.sidat_img, R.drawable.sigung_img, R.drawable.siput_img, R.drawable.singa_img, R.drawable.sotong_img, R.drawable.tapir_img, R.drawable.tikus_img, R.drawable.tokek_img, R.drawable.trenggiling_img, R.drawable.tupai_img, R.drawable.tupai_tanah_img, R.drawable.tuntong_img, R.drawable.udang_img, R.drawable.ular_img, R.drawable.ular_sanca_img, R.drawable.ulat_img, R.drawable.unta_img, R.drawable.viper_img, R.drawable.walabi_img, R.drawable.walrus_img, R.drawable.yak_img, R.drawable.zebra_img, R.drawable.zebu_img

    };

    private int[] correctImages = {
            R.drawable.aardvark_jawa, R.drawable.aguti_jawa, R.drawable.alpaka_jawa, R.drawable.angrang_jawa, R.drawable.angsa_jawa, R.drawable.anjing_jawa, R.drawable.anoa_jawa, R.drawable.antelop_jawa, R.drawable.armadilo_jawa, R.drawable.ayam_jawa, R.drawable.ayam_hutan_jawa, R.drawable.aye_aye_jawa, R.drawable.babi_jawa, R.drawable.babi_hutan_jawa, R.drawable.babi_rusa_jawa, R.drawable.badak_jawa, R.drawable.banteng_jawa, R.drawable.belalang_jawa, R.drawable.belatung_jawa, R.drawable.beluga_jawa, R.drawable.belut_jawa, R.drawable.belut_listrik_jawa, R.drawable.beruang_jawa, R.drawable.beruang_madu_jawa, R.drawable.beruk_jawa, R.drawable.biawak_jawa, R.drawable.binturong_jawa, R.drawable.buaya_jawa, R.drawable.burung_jawa, R.drawable.burung_albatros_jawa, R.drawable.burung_apus_jawa, R.drawable.burung_bangau_jawa, R.drawable.burung_bangau_putih_jawa, R.drawable.burung_bubut_jawa, R.drawable.burung_beo_jawa, R.drawable.burung_cendet_jawa, R.drawable.burung_cendrawasih_jawa, R.drawable.burung_cikrak_jawa, R.drawable.burung_dedalu_jawa, R.drawable.burung_derkuku_jawa, R.drawable.burung_elang_jawa, R.drawable.burung_falkon_jawa, R.drawable.burung_gagak_jawa, R.drawable.burung_gagak_hutan_jawa, R.drawable.burung_jalak_jawa, R.drawable.burung_kakaktua_jawa, R.drawable.burung_kalkun_jawa, R.drawable.burung_kenari_jawa, R.drawable.burung_kolibri_jawa, R.drawable.burung_kutilang_jawa, R.drawable.burung_merak_jawa, R.drawable.burung_merpati_jawa, R.drawable.burung_nandu_jawa, R.drawable.burung_nuri_jawa, R.drawable.burung_pelikan_jawa, R.drawable.burung_perkutut_jawa, R.drawable.burung_puyuh_jawa, R.drawable.burung_rajawali_jawa, R.drawable.burung_rangkong_jawa, R.drawable.burung_tengkek_jawa, R.drawable.burung_walet_jawa, R.drawable.burung_flamingo_jawa, R.drawable.cacing_jawa, R.drawable.capung_jawa, R.drawable.cicak_jawa, R.drawable.cecurut_jawa, R.drawable.cheetah_jawa, R.drawable.cumi_cumi_jawa, R.drawable.domba_jawa, R.drawable.dugong_jawa, R.drawable.ermine_jawa, R.drawable.ferret_jawa, R.drawable.gajah_jawa, R.drawable.gaur_jawa, R.drawable.geko_jawa, R.drawable.gibon_jawa, R.drawable.galagos_jawa, R.drawable.gorila_jawa, R.drawable.gundi_jawa, R.drawable.gurita_jawa, R.drawable.hamster_jawa, R.drawable.harimau_jawa, R.drawable.harimau_sumatra_jawa, R.drawable.hiena_jawa, R.drawable.iguana_jawa, R.drawable.ikan_jawa, R.drawable.ikan_arwana_jawa, R.drawable.ikan_bandeng_jawa, R.drawable.ikan_bawal_jawa, R.drawable.ikan_baronang_jawa, R.drawable.ikan_belanak_jawa, R.drawable.ikan_betok_jawa, R.drawable.ikan_buntal_jawa, R.drawable.ikan_cakalang_jawa, R.drawable.ikan_cupang_jawa, R.drawable.ikan_dorado_jawa, R.drawable.ikan_dottyback_jawa, R.drawable.ikan_ekor_kuning_jawa, R.drawable.ikan_gabus_jawa, R.drawable.ikan_gobi_jawa, R.drawable.ikan_gurame_jawa, R.drawable.ikan_hiu_jawa, R.drawable.ikan_kakap_jawa, R.drawable.ikan_kembung_jawa, R.drawable.ikan_kerapu_jawa, R.drawable.ikan_kuwe_jawa, R.drawable.ikan_lamprey_jawa, R.drawable.ikan_lais_jawa, R.drawable.ikan_lele_jawa, R.drawable.ikan_mas_jawa, R.drawable.ikan_manta_jawa, R.drawable.ikan_mola_mola_jawa, R.drawable.ikan_mujair_jawa, R.drawable.ikan_nila_jawa, R.drawable.ikan_pari_jawa, R.drawable.ikan_piranha_jawa, R.drawable.ikan_pipih_jawa, R.drawable.ikan_tenggiri_jawa, R.drawable.ikan_teri_jawa, R.drawable.ikan_terbang_jawa, R.drawable.ikan_tongkol_jawa, R.drawable.ikan_tuna_jawa, R.drawable.ikan_wader_jawa, R.drawable.itik_jawa, R.drawable.jaguar_jawa, R.drawable.jangkrik_jawa, R.drawable.jerapah_jawa, R.drawable.kaki_seribu_jawa, R.drawable.kalajengking_jawa, R.drawable.kambing_jawa, R.drawable.kancil_jawa, R.drawable.katak_jawa, R.drawable.kecoa_jawa, R.drawable.keledai_jawa, R.drawable.kelelawar_jawa, R.drawable.kelinci_jawa, R.drawable.kelomang_jawa, R.drawable.kerang_jawa, R.drawable.kerbau_jawa, R.drawable.kepiting_jawa, R.drawable.kijang_jawa, R.drawable.koala_jawa, R.drawable.komodo_jawa, R.drawable.kuda_jawa, R.drawable.kuda_laut_jawa, R.drawable.kucing_jawa, R.drawable.kucing_hutan_jawa, R.drawable.kumbang_jawa, R.drawable.kungkang_jawa, R.drawable.kunang_kunang_jawa, R.drawable.kura_kura_jawa, R.drawable.kuskus_jawa, R.drawable.kangguru_jawa, R.drawable.kutu_jawa, R.drawable.laba_laba_jawa, R.drawable.labi_labi_jawa, R.drawable.lalat_jawa, R.drawable.landak_jawa, R.drawable.laron_jawa, R.drawable.lebah_jawa, R.drawable.lemur_jawa, R.drawable.lingsang_jawa, R.drawable.lintah_jawa, R.drawable.lobster_jawa, R.drawable.lumba_lumba_jawa, R.drawable.lutung_jawa, R.drawable.luwak_jawa, R.drawable.luwe_jawa, R.drawable.macan_tutul_jawa, R.drawable.mantis_jawa, R.drawable.marmut_jawa, R.drawable.monyet_jawa, R.drawable.musang_jawa, R.drawable.nyamuk_jawa, R.drawable.kalong_jawa, R.drawable.orangutan_jawa, R.drawable.owa_jawa, R.drawable.panda_jawa, R.drawable.paus_jawa, R.drawable.penguin_jawa, R.drawable.penyu_jawa, R.drawable.platipus_jawa, R.drawable.quokka_jawa, R.drawable.rakun_jawa, R.drawable.rayap_jawa, R.drawable.remis_jawa, R.drawable.rusa_jawa, R.drawable.sapi_jawa, R.drawable.semut_jawa, R.drawable.serigala_jawa, R.drawable.siamang_jawa, R.drawable.sidat_jawa, R.drawable.sigung_jawa, R.drawable.siput_jawa, R.drawable.singa_jawa, R.drawable.sotong_jawa, R.drawable.tapir_jawa, R.drawable.tikus_jawa, R.drawable.tokek_jawa, R.drawable.trenggiling_jawa, R.drawable.tupai_jawa, R.drawable.tupai_tanah_jawa, R.drawable.tuntong_jawa, R.drawable.udang_jawa, R.drawable.ular_jawa, R.drawable.ular_sanca_jawa, R.drawable.ulat_jawa, R.drawable.unta_jawa, R.drawable.viper_jawa, R.drawable.walabi_jawa, R.drawable.walrus_jawa, R.drawable.yak_jawa, R.drawable.zebra_jawa, R.drawable.zebu_jawa

    };

    private TextView tvtopscore;

    private int[] questionOrder;
    private CountDownTimer countDownTimer;
    private int remainingTime = 7; // 7 detik untuk menjawab soal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        soal = findViewById(R.id.soal);
        jwb1 = findViewById(R.id.jwb1);
        jwb2 = findViewById(R.id.jwb2);
        jwb3 = findViewById(R.id.jwb3);
        tvscore = findViewById(R.id.tvscore);
        tvnourut = findViewById(R.id.tvnourut);
        tvtopscore = findViewById(R.id.tvtopscore);


        // Inisialisasi MediaPlayer untuk efek suara
        soundbtn = MediaPlayer.create(this, R.raw.soundbtn);
        mediaPlayerBenar = MediaPlayer.create(this, R.raw.jwbbenar);
        mediaPlayerSalah = MediaPlayer.create(this, R.raw.jwbsalah);

        db = AppDatabase.getInstance(getApplicationContext());
        // Load top score from the database
        new Thread(() -> {
            String topScoreText = "Belum ada top skor";
            Quiz topQuiz = db.quizDao().getHighestScore();
            if (topQuiz != null) {
                topScoreText =  topQuiz.getSkor() + "/100";
            }

            // Update UI with the top score
            String finalTopScoreText = topScoreText;
            runOnUiThread(() -> tvtopscore.setText(finalTopScoreText));
        }).start();

        // Acak urutan soal pada awal permainan
        questionOrder = new int[images.length];
        for (int i = 0; i < images.length; i++) {
            questionOrder[i] = i;
        }
        shuffleArray(questionOrder);

        updateQuiz();

        jwb1.setOnClickListener(v -> checkAnswer(jwb1));
        jwb2.setOnClickListener(v -> checkAnswer(jwb2));
        jwb3.setOnClickListener(v -> checkAnswer(jwb3));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerBenar != null) mediaPlayerBenar.release();
        if (mediaPlayerSalah != null) mediaPlayerSalah.release();
        if (countDownTimer != null) countDownTimer.cancel();
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public void updateQuiz() {
        if (currentQuestionNumber > 20) {
            Intent intent = new Intent(QuizActivity.this, TotalscoreActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("JAWABAN_BENAR", jawabanBenar);
            intent.putExtra("JAWABAN_SALAH", jawabanSalah);
            intent.putExtra("SOURCE", "quiz"); // <-- INI KUNCI
            startActivity(intent);
            finish();
            return;
        }

        currentQuestionIndex = questionOrder[currentQuestionNumber - 1];
        soal.setImageResource(images[currentQuestionIndex]);

        int correctAnswerImage = correctImages[currentQuestionIndex];
        int[] options = new int[3];

        int correctPosition = new Random().nextInt(3);
        options[correctPosition] = correctAnswerImage;

        for (int i = 0; i < 3; i++) {
            if (i != correctPosition) {
                int wrongAnswerIndex;
                do {
                    wrongAnswerIndex = new Random().nextInt(images.length);
                } while (wrongAnswerIndex == currentQuestionIndex);
                options[i] = correctImages[wrongAnswerIndex];
            }
        }

        shuffleArray(options);

        jwb1.setImageResource(options[0]);
        jwb1.setTag(options[0]);
        jwb1.setScaleType(ImageView.ScaleType.FIT_XY);

        jwb2.setImageResource(options[1]);
        jwb2.setTag(options[1]);
        jwb2.setScaleType(ImageView.ScaleType.FIT_XY);

        jwb3.setImageResource(options[2]);
        jwb3.setTag(options[2]);
        jwb3.setScaleType(ImageView.ScaleType.FIT_XY);

        tvscore.setText(score + "/100");
        tvnourut.setText("Soal ke: " + currentQuestionNumber);

        // Mulai timer
        startTimer();

        currentQuestionNumber++;
    }

    private void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Hentikan timer sebelumnya
        }

        countDownTimer = new CountDownTimer(7000, 1000) { // 7 detik, interval 1 detik
            @Override
            public void onTick(long millisUntilFinished) {
                // Timer berjalan di belakang layar, tidak ada update UI timer
            }

            @Override
            public void onFinish() {
                checkAnswer(null); // Anggap salah jika waktu habis
            }
        }.start();
    }

    public void checkAnswer(ImageButton selectedButton) {
        // Cek apakah selectedButton null
        if (selectedButton != null) {
            int selectedImage = (int) selectedButton.getTag();

            // Periksa apakah jawabannya benar atau salah
            if (selectedImage == correctImages[currentQuestionIndex]) {
                score += 5;
                jawabanBenar++;
                mediaPlayerBenar.start();
                selectedButton.setBackgroundColor(Color.GREEN);
            } else {
                jawabanSalah++;
                mediaPlayerSalah.start();
                selectedButton.setBackgroundColor(Color.RED);
            }
        } else {
            // Jika tidak ada tombol yang dipilih (misalnya, waktu habis), anggap salah
            jawabanSalah++;
            mediaPlayerSalah.start();
        }

        // Hilangkan warna hijau atau merah setelah 1 detik
        if (selectedButton != null) {
            selectedButton.postDelayed(() -> {
                selectedButton.setBackgroundColor(Color.TRANSPARENT);
            }, 1000); // Hapus warna setelah 1 detik
        }

        // Jeda 2 detik sebelum soal berikutnya
        new Handler().postDelayed(() -> {
            updateQuiz(); // Tampilkan soal berikutnya
        }, 2000); // Jeda 2 detik
    }
}
