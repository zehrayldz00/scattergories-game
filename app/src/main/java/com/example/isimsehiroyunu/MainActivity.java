package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAnasayfaAyar(View v){
        int viewId = v.getId();

        if (viewId == R.id.btnNormalOyun) {
            aktiviteyeGec("NormalOyun");
        } else if (viewId == R.id.btnSureliOyun) {
            aktiviteyeGec("SureliOyun");
        } else if (viewId == R.id.btnCikis) {
            cikisYap();
        }
    }
    private void aktiviteyeGec(String aktiviteIsmi){
        if(aktiviteIsmi.equals("NormalOyun"))
            intent = new Intent(this, NormalOyunActivity.class);
        else
            intent = new Intent(this, SureliOyunActivity.class);

        startActivity(intent);
    }
    private void cikisYap(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cikisYap();
    }

}