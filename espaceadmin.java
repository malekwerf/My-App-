package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class espaceadmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espaceadmin);

        // Récupération des TextViews

        TextView textgestioncontrats=findViewById(R.id.textgestionContrats);
        TextView textHistoriquePaiement = findViewById(R.id.textHistoriquePaiement);
        TextView gestiondeprofils = findViewById(R.id.textgestiondeprofils);

        // Action de clic sur le texte pour afficher la liste des contrats


        // Action de clic sur le texte pour afficher le paiement du contrat
        textgestioncontrats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mettez ici le code pour afficher l'historique des paiements
                Intent intent = new Intent(espaceadmin.this, gestioncontrat.class);
                startActivity(intent);
            }
        });

        // Action de clic sur le texte pour afficher l'historique des paiements
        textHistoriquePaiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mettez ici le code pour afficher l'historique des paiements
                Intent intent = new Intent(espaceadmin.this, paiementadmin.class);
                startActivity(intent);
            }
        });
        gestiondeprofils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mettez ici le code pour afficher la liste des contrats
                Intent intent = new Intent(espaceadmin.this, Gestiondeprofils.class);
                startActivity(intent);
            }
        });
        TextView historiqueReclamationsTextView = findViewById(R.id.histori);
        historiqueReclamationsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "Historique des réclamations cliqué");
                Intent intent = new Intent(espaceadmin.this, historiquerecl.class);
                startActivity(intent);
            }
        });
    }

}
