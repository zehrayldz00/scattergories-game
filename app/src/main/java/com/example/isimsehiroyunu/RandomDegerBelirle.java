package com.example.isimsehiroyunu;

import static com.example.isimsehiroyunu.RandomHarfAl.randomHarfAl;

import java.util.ArrayList;
import java.util.Random;

public class RandomDegerBelirle extends NormalOyunActivity{
    static void randomDegerleriBelirle() {
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
}
