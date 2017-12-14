package com.fraiman.zeev.ohmelectric;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Main extends AppCompatActivity {

    VideoView vivi;
    Uri uri;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext=findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vivi.stopPlayback();
                gogo();

            }
        });
        vivi=findViewById(R.id.vivi);
        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ohm);
        vivi.setVideoURI(uri);
        vivi.start();
    }

    private void gogo() {
        Intent go=new Intent(this, Calcul.class);
        startActivity(go);
    }
}
