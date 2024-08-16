package com.example.myapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
        import androidx.appcompat.app.AppCompatActivity;

public class news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ne);

        // Récupération des ImageView
          ImageView imagePackScolaire = findViewById(R.id.imagePackScolaire);

         ImageView imagePackmedical = findViewById(R.id.imagePackScolaire1);

         ImageView imagePackstartup = findViewById(R.id.imagePackScolaire2);

         ImageView imagePackassociation = findViewById(R.id.imagePackScolaire3);

        // Ajout du OnClickListener à chaque ImageView
        imagePackScolaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer une nouvelle activité ou effectuer une autre action souhaitée
                Intent intent = new Intent(news.this, packscolaire.class);
                startActivity(intent);
            }
        });

        imagePackmedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer une nouvelle activité ou effectuer une autre action souhaitée
                Intent intent = new Intent(news.this, packmedical.class);
                startActivity(intent);
            }
        });

        imagePackstartup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer une nouvelle activité ou effectuer une autre action souhaitée
                Intent intent = new Intent(news.this, packstartup.class);
                startActivity(intent);
            }
        });

        imagePackassociation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer une nouvelle activité ou effectuer une autre action souhaitée
                Intent intent = new Intent(news.this, packassociation.class);
                startActivity(intent);
            }
        });
    }
}
