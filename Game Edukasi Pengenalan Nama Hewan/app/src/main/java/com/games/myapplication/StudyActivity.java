package com.games.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

import android.media.MediaPlayer;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class StudyActivity extends AppCompatActivity {

    private ImageView ivHewan;
    private Spinner spinner;
    private ImageButton btnPrevious, btnNext;
    private Button btnBcIndo, btnBcJawa;

    private ArrayList<String> daftarHewan;
    private int indexHewan = 0;

    private ImageView ivNameHewan;
    private String currentLanguage = "indo"; // Default bahasa

    private boolean isLanguageSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_study);

        // Menambahkan listener untuk window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi view
        ivHewan = findViewById(R.id.ivhewan);
        spinner = findViewById(R.id.spinner);
        btnPrevious = findViewById(R.id.btnprevious);
        btnNext = findViewById(R.id.btnnext);
        btnBcIndo = findViewById(R.id.btnbcindo);
        btnBcJawa = findViewById(R.id.btnbcjawa);
        ivNameHewan = findViewById(R.id.ivnamehewan);
        ivNameHewan.setImageResource(R.drawable.kosong);


        // Isi daftar hewan
        daftarHewan = new ArrayList<>(Arrays.asList(
                "AARDVARK", "AGUTI", "ALPAKA", "ANGRANG", "ANGSA", "ANJING", "ANOA", "ANTELOP",
                "ARMADILO", "AYAM", "AYAM HUTAN", "AYE-AYE", "BABI", "BABI HUTAN", "BABI RUSA",
                "BADAK", "BANTENG", "BELALANG", "BELATUNG", "BELUGA", "BELUT", "BELUT LISTRIK",
                "BERUANG", "BERUANG MADU", "BERUK", "BIAWAK", "BINTURONG", "BUAYA", "BURUNG",
                "BURUNG ALBATROS", "BURUNG APUS", "BURUNG BANGAU", "BURUNG BANGAU PUTIH", "BURUNG BUBUT",
                "BURUNG BEO", "BURUNG CENDET", "BURUNG CENDRAWASIH", "BURUNG CIKRAK", "BURUNG DEDALU",
                "BURUNG DERKUKU", "BURUNG ELANG", "BURUNG FALKON", "BURUNG GAGAK", "BURUNG GAGAK HUTAN",
                "BURUNG JALAK", "BURUNG KAKAKTUA", "BURUNG KALKUN", "BURUNG KENARI", "BURUNG KOLIBRI",
                "BURUNG KUTILANG", "BURUNG MERAK", "BURUNG MERPATI", "BURUNG NANDU", "BURUNG NURI",
                "BURUNG PELIKAN", "BURUNG PERKUTUT", "BURUNG PUYUH", "BURUNG RAJAWALI", "BURUNG RANGKONG",
                "BURUNG TENGKEK", "BURUNG WALET", "BURUNG FLAMINGO", "CACING", "CAPUNG", "CICAK", "CECURUT",
                "CHEETAH", "CUMI-CUMI", "DOMBA", "DUGONG", "ERMINE", "FERRET", "GAJAH", "GAUR", "GEKO",
                "GIBON", "GALAGOS", "GORILA", "GUNDI", "GURITA", "HAMSTER", "HARIMAU", "HARIMAU SUMATRA",
                "HIENA", "IGUANA", "IKAN", "IKAN ARWANA", "IKAN BANDENG", "IKAN BAWAL", "IKAN BARONANG",
                "IKAN BELANAK", "IKAN BETOK", "IKAN BUNTAL", "IKAN CAKALANG", "IKAN CUPANG", "IKAN DORADO",
                "IKAN DOTTYBACK", "IKAN EKOR KUNING", "IKAN GABUS", "IKAN GOBI", "IKAN GURAME", "IKAN HIU",
                "IKAN KAKAP", "IKAN KEMBUNG", "IKAN KERAPU", "IKAN KUWE", "IKAN LAMPREY", "IKAN LAIS",
                "IKAN LELE", "IKAN MAS", "IKAN MANTA", "IKAN MOLA-MOLA", "IKAN MUJAIR", "IKAN NILA",
                "IKAN PARI", "IKAN PIRANHA", "IKAN PIPIH", "IKAN TENGGIRI", "IKAN TERI", "IKAN TERBANG",
                "IKAN TONGKOL", "IKAN TUNA", "IKAN WADER", "ITIK", "JAGUAR", "JANGKRIK", "JERAPAH",
                "KAKI SERIBU", "KALAJENGKING", "KAMBING", "KANCIL", "KATAK", "KECOA", "KELEDAI", "KELELAWAR",
                "KELINCI", "KELOMANG", "KERANG", "KERBAU", "KEPITING", "KIJANG", "KOALA", "KOMODO", "KUDA",
                "KUDA LAUT", "KUCING", "KUCING HUTAN", "KUMBANG", "KUNGKANG", "KUNANG-KUNANG", "KURA-KURA",
                "KUSKUS", "KANGGURU", "KUTU", "LABA-LABA", "LABI-LABI", "LALAT", "LANDAK", "LARON", "LEBAH",
                "LEMUR", "LINGSANG", "LINTAH", "LOBSTER", "LUMBA-LUMBA", "LUTUNG", "LUWAK", "LUWE",
                "MACAN TUTUL", "MANTIS", "MARMUT", "MONYET", "MUSANG", "NYAMUK", "KALONG", "ORANGUTAN",
                "OWA", "PANDA", "PAUS", "PENGUIN", "PENYU", "PLATIPUS", "QUOKKA", "RAKUN", "RAYAP", "REMIS",
                "RUSA", "SAPI", "SEMUT", "SERIGALA", "SIAMANG", "SIDAT", "SIGUNG", "SIPUT", "SINGA", "SOTONG",
                "TAPIR", "TIKUS", "TOKEK", "TRENGGILING", "TUPAI", "TUPAI TANAH", "TUNTONG", "UDANG", "ULAR",
                "ULAR SANCA", "ULAT", "UNTA", "VIPER", "WALABI", "WALRUS", "YAK", "ZEBRA", "ZEBU"
        ));

        // Custom spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                daftarHewan
        );

        btnBcIndo.setOnClickListener(v -> {
            currentLanguage = "indo";
            isLanguageSelected = true; // Tandai bahwa bahasa sudah dipilih
            tampilkanHewan(daftarHewan.get(indexHewan));
            playSound(daftarHewan.get(indexHewan), "indo");
        });

        btnBcJawa.setOnClickListener(v -> {
            currentLanguage = "jawa";
            isLanguageSelected = true; // Tandai bahwa bahasa sudah dipilih
            tampilkanHewan(daftarHewan.get(indexHewan));
            playSound(daftarHewan.get(indexHewan), "jawa");
        });


        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Mengubah background spinner sesuai tema
        if (isDarkTheme()) {
            spinner.setBackgroundResource(R.drawable.spinner_background_dark); // Tema Gelap
        } else {
            spinner.setBackgroundResource(R.drawable.spinner_background_light); // Tema Terang
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexHewan = position;

                // Reset gambar nama hewan jika bahasa belum dipilih
                if (!isLanguageSelected) {
                    ivNameHewan.setImageResource(R.drawable.kosong); // Reset gambar nama hewan
                } else {
                    tampilkanHewan(daftarHewan.get(indexHewan)); // Tampilkan gambar nama hewan jika bahasa sudah dipilih
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnPrevious.setOnClickListener(v -> {
            indexHewan = (indexHewan - 1 + daftarHewan.size()) % daftarHewan.size();
            spinner.setSelection(indexHewan);
        });

        btnNext.setOnClickListener(v -> {
            indexHewan = (indexHewan + 1) % daftarHewan.size();
            spinner.setSelection(indexHewan);
        });


    }

    // Fungsi untuk memeriksa apakah aplikasi menggunakan tema gelap
    private boolean isDarkTheme() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    private void tampilkanHewan(String selectedAnimal) {
        // Ubah ke lowercase dan ganti spasi dengan underscore
        String namaHewan = selectedAnimal.toLowerCase().replace(" ", "_").replace("-", "_");

        // Gambar utama hewan (misal: ayam_hutan_img.png)
        int resGambarHewan = getResources().getIdentifier(namaHewan + "_img", "drawable", getPackageName());
        ivHewan.setImageResource(resGambarHewan != 0 ? resGambarHewan : R.drawable.kosong);

        // Hanya tampilkan gambar nama hewan jika bahasa sudah dipilih
        if (isLanguageSelected) {
            // Gambar nama sesuai bahasa (misal: ayam_hutan_jawa.png / aye_aye_indo.png)
            String namaGambar = namaHewan + "_" + currentLanguage;
            int resGambarNama = getResources().getIdentifier(namaGambar, "drawable", getPackageName());
            ivNameHewan.setImageResource(resGambarNama != 0 ? resGambarNama : R.drawable.kosong);
        }
    }

    private void playSound(String selectedAnimal, String language) {
        // Ubah ke lowercase dan ganti spasi dengan underscore
        String namaHewan = selectedAnimal.toLowerCase().replace(" ", "_").replace("-", "_");

        // Tentukan nama file suara
        String soundFileName = namaHewan + "_" + language;

        // Coba untuk memuat dan memutar file suara
        int soundResId = getResources().getIdentifier(soundFileName, "raw", getPackageName());
        if (soundResId != 0) {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResId);
            mediaPlayer.start();
        } else {
            // Jika file tidak ditemukan, tampilkan Toast sebagai fallback
            Toast.makeText(this, "Suara tidak ditemukan untuk " + selectedAnimal, Toast.LENGTH_SHORT).show();
        }
    }

}

