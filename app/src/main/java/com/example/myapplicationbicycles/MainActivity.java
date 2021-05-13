package com.example.myapplicationbicycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationbicycles.R;

public class MainActivity extends AppCompatActivity {
    Animation top, bottom;

    ImageView intro;
    TextView label;
    private static int SPLASH_SCREEN = 4000;
    //4000ms = 4 sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        intro = findViewById(R.id.imageView);
        label = findViewById(R.id.textView);

        intro.setAnimation(bottom);
        label.setAnimation(top);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }
}