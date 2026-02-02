package com.example.alfandega;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    ImageView imagem;
    TextView texto;

    Animation aparece;
    Animation some;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            imagem = findViewById(R.id.imgSeta);
            texto = findViewById(R.id.txtLado);

            texto.setText("Toque para continuar!");
            imagem.setVisibility(View.INVISIBLE);

            aparece = new AlphaAnimation(0,1);
            some = new AlphaAnimation(1,0);

            some.setDuration(500);
            aparece.setDuration(500);

            aparece.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    imagem.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationStart(Animation animation) {
                    imagem.setVisibility(View.VISIBLE);
                }
            });

            some.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    imagem.setVisibility(View.INVISIBLE);
                    texto.setText("Toque para continuar");

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationStart(Animation animation) {
                    imagem.setVisibility(View.VISIBLE);
                }
            });

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void clicouTela(View view){

        if(Math.random() < 0.5){
            texto.setText("Siga para a esquerda.");
            imagem.setScaleX(1f);
        }else{
            texto.setText("Siga para a direita.");
            imagem.setScaleX(-1f);
        }
        imagem.startAnimation(aparece);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                imagem.startAnimation(some);
            }
        }, 2000);
    }
}