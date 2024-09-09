package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SureliOyunActivity extends AppCompatActivity {
    private TextView ilBilgi, harfAlani, txtToplamBolumPuani, txtSure;
    private EditText tahminGir;
    private Button btnHarfAl, btnTahminEt, btnYenidenBasla;
    private Random rndIl, rndHarf;
    private int rndIlNumber, rndHarfNumber, baslangicHarfSayisi, toplamSure = 180000;
    private String gelenIl, ilBoyutu, gelenTahmin;
    private ArrayList<Character> ilHarfleri;
    private float maximumPuan = 100.0f, azaltilacakPuan, toplamPuan = 0, bolumToplamPuan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ilBilgi = (TextView) findViewById(R.id.txtIlBilgiS);
        harfAlani = (TextView) findViewById(R.id.txtHarfAlaniS);
        tahminGir = (EditText) findViewById(R.id.edtTxtTahminGirS);
        txtToplamBolumPuani = (TextView) findViewById(R.id.txtToplamPuanS);
        txtSure = (TextView) findViewById(R.id.txtSure);
        btnHarfAl = (Button) findViewById(R.id.btnHarfAlS);
        btnTahminEt = (Button) findViewById(R.id.btnTahminEtS);
        btnYenidenBasla = (Button) findViewById(R.id.btnYenidenBasla);

        //1000 = 1 saniye
        // 60.000 = 1 dakika
        // 180000 = 3 dk

        new CountDownTimer(toplamSure, 1000) {
            @Override
            public void onTick(long l) {
                txtSure.setText((l / 60000) + ":" + (l % 60000) / 1000);
            }

            @Override
            public void onFinish() {
                txtSure.setText("0:00");
                tahminGir.setEnabled(false);
                btnHarfAl.setEnabled(false);
                btnTahminEt.setEnabled(false);
                btnYenidenBasla.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Oyun Bitti..\n Toplam Puanınız: " + bolumToplamPuan + "\nTekrar Oynamak için Butona Basınız.", Toast.LENGTH_LONG).show();

            }
        }.start();

        rndHarf = new Random();
        randomDegerleriBelirle();
    }

    public void btnHarfAlS(View v) {
        if (ilHarfleri.size() > 0) {
            randomHarfAl();
            toplamPuan -= azaltilacakPuan;

            Toast.makeText(getApplicationContext(), "Kalan Puan: " + toplamPuan, Toast.LENGTH_SHORT).show();
        } else
            System.out.println("Harf kalmadı.");
    }

    public void btnTahminEtS(View v) {
        gelenTahmin = tahminGir.getText().toString();

        if (!TextUtils.isEmpty(gelenTahmin)) {
            if (gelenTahmin.equalsIgnoreCase(gelenIl)) {
                bolumToplamPuan += toplamPuan;
                Toast.makeText(getApplicationContext(), "Tebrikler doğru cevap!", Toast.LENGTH_SHORT).show();
                txtToplamBolumPuani.setText("Toplam Bölüm Puanı: " + bolumToplamPuan);
                tahminGir.setText("");
                randomDegerleriBelirle();
            } else
                System.out.println("Yanlış! Yeniden deneyiniz.");
            Toast.makeText(getApplicationContext(), "Yanlış tahmin.", Toast.LENGTH_SHORT).show();
        } else
            System.out.println("Tahmin değeri boş bırakılamaz.");
    }

    public void btnYenidenBasla(View v) {
        Intent tekrarOyna = new Intent(this, SureliOyunActivity.class);
        finish();
        startActivity(tekrarOyna);
    }

    private void randomDegerleriBelirle() {
        Sehirler sehirler = new Sehirler();
        String[] ilListe = sehirler.iller;

        ilBoyutu = "";
        rndIl = new Random();
        rndIlNumber = rndIl.nextInt(ilListe.length);
        gelenIl = ilListe[rndIlNumber];
        System.out.println(gelenIl);
        ilBilgi.setText(gelenIl.length() + " harfli ilimiz");

        baslangicHarfSayisi = 0;
        if (gelenIl.length() >= 5 && gelenIl.length() <= 7)
            baslangicHarfSayisi = 1;
        else if (gelenIl.length() >= 8 && gelenIl.length() < 10)
            baslangicHarfSayisi = 2;
        else if (gelenIl.length() >= 10)
            baslangicHarfSayisi = 3;
        System.out.println(baslangicHarfSayisi);

        for (int i = 0; i < gelenIl.length(); i++) {
            if (i < gelenIl.length() - 1) {
                ilBoyutu += "_ ";
            } else
                ilBoyutu += "_";
        }
        harfAlani.setText(ilBoyutu);

        ilHarfleri = new ArrayList<>();

        for (char c : gelenIl.toCharArray())
            ilHarfleri.add(c);

        for (int c = 0; c < baslangicHarfSayisi; c++) {
            randomHarfAl();
            azaltilacakPuan = maximumPuan / ilHarfleri.size();
            toplamPuan = maximumPuan;
        }

    }

    private void randomHarfAl() {
        rndHarfNumber = rndHarf.nextInt(ilHarfleri.size()); //İl harfleri sayısınca random harf veriyor.
        String[] txtHarfler = harfAlani.getText().toString().split(" "); //Boşluk karakterlerini siliyor.
        char[] gelenIlHarfler = gelenIl.toCharArray(); //Parçalayıp index index gezmek için

        for (int i = 0; i < gelenIl.length(); i++) {
            if (txtHarfler[i].equals("_") && gelenIlHarfler[i] == ilHarfleri.get(rndHarfNumber)) {
                txtHarfler[i] = String.valueOf(ilHarfleri.get(rndHarfNumber));
                ilBoyutu = "";

                for (int j = 0; j < gelenIl.length(); j++) {
                    if (j == i || j < gelenIl.length() - 1)
                        ilBoyutu += txtHarfler[j] + " ";
                    else
                        ilBoyutu += txtHarfler[j];
                }
                break;
            }
        }
        harfAlani.setText(ilBoyutu);
        ilHarfleri.remove(rndHarfNumber); // Alınan harfi listeden siliyor ki yeniden aynı harfi vermesin.
    }

}