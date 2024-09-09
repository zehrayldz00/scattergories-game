package com.example.isimsehiroyunu;

public class RandomHarfAl extends NormalOyunActivity {
    static void randomHarfAl() {
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
