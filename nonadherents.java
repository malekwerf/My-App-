package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class nonadherents extends AppCompatActivity {
    private TextView contacttext,newstext,agencetext,reclamationtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasadherents); // Chargez le bon layout ici

        // Récupération des boutons depuis le layout XML
        contacttext = findViewById(R.id.contacttext);
        newstext = findViewById(R.id.newstext);
        agencetext = findViewById(R.id.agencetext);
        reclamationtext = findViewById(R.id.reclamationtext);

        // Ajout d'un écouteur de clic pour chaque bouton
        agencetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur le bouton 1
                Intent intent = new Intent(nonadherents.this, listeagences.class);
                startActivity(intent);
            }
        });

        reclamationtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nonadherents.this, reclamation.class);
                startActivity(intent);
            }
        });

        contacttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur le bouton 3
                Intent intent = new Intent(nonadherents.this, contact.class);
                startActivity(intent);
                // Ajoutez votre code ici pour gérer le clic sur le bouton 3
            }
        });

        newstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nonadherents.this, news.class);
                startActivity(intent);
            }
        });
    }
}
