package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunActivity extends AppCompatActivity {

    static TextView ilBilgi;
    static TextView harfAlani;
    private TextView txtToplamBolumPuani;
    private EditText tahminGir;
    static Random rndIl;
    static Random rndHarf;
    static int rndIlNumber;
    static int rndHarfNumber;
    static int baslangicHarfSayisi;
    static String gelenIl;
    static String ilBoyutu;
    private String gelenTahmin;
    static ArrayList<Character> ilHarfleri;
    static float maximumPuan = 100.0f;
    static float azaltilacakPuan;
    static float toplamPuan = 0;
    private float bolumToplamPuan = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ilBilgi = (TextView) findViewById(R.id.txtIlBilgiN);
        harfAlani = (TextView) findViewById(R.id.txtHarfAlaniN);
        tahminGir = (EditText) findViewById(R.id.edtTxtTahminGirN);
        txtToplamBolumPuani = (TextView) findViewById(R.id.txtToplamPuanN);

        Sehirler sehirler = new Sehirler();
        String[] ilListe = sehirler.iller;

        rndHarf = new Random();
        RandomDegerBelirle randomDegerBelirle = new RandomDegerBelirle();
        RandomDegerBelirle.randomDegerleriBelirle();

        RandomHarfAl randomHarf = new RandomHarfAl();

    }

    public void btnHarfAl(View v) {
        RandomHarfAl randomHarf = new RandomHarfAl();
        if (ilHarfleri.size() > 0) {
            randomHarf.randomHarfAl();
            toplamPuan -= azaltilacakPuan;

            Toast.makeText(getApplicationContext(), "Kalan Puan: " + toplamPuan, Toast.LENGTH_SHORT).show();
        } else
            System.out.println("Harf kalmadı.");
    }

    public void btnTahminEt(View v) {
        gelenTahmin = tahminGir.getText().toString();

        if (!TextUtils.isEmpty(gelenTahmin)) {
            if (gelenTahmin.equalsIgnoreCase(gelenIl)) {
                bolumToplamPuan += toplamPuan;
                Toast.makeText(getApplicationContext(), "Tebrikler doğru cevap!", Toast.LENGTH_SHORT).show();
                txtToplamBolumPuani.setText("Toplam Bölüm Puanı: " + bolumToplamPuan);
                tahminGir.setText("");
                RandomDegerBelirle.randomDegerleriBelirle();
            } else {
                System.out.println("Yanlış! Yeniden deneyiniz.");
                Toast.makeText(getApplicationContext(), "Yanlış tahmin.", Toast.LENGTH_SHORT).show();
            }
        } else
            System.out.println("Tahmin değeri boş bırakılamaz.");
    }

}