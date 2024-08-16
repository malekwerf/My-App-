package com.example.myapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class adherentespace extends AppCompatActivity {

    private static final int POWERBI_REQUEST_CODE = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espaceadherent);

        // Récupération des TextViews
        TextView textListeContrats = findViewById(R.id.textListeContrats);
        TextView textPaiementContrat = findViewById(R.id.textPaiementContrat);
        TextView textHistoriquePaiement = findViewById(R.id.textHistoriquePaiement);
        TextView textadministrateur = findViewById(R.id.textadmin);
        TextView textPowerbi = findViewById(R.id.textPowerbi);

        // Action de clic sur le texte pour afficher la page des contrats
        textListeContrats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adherentespace.this, ListeContratsActivity.class);
                startActivity(intent);
            }
        });

        // Action de clic sur le texte pour afficher le paiement du contrat
        textPaiementContrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adherentespace.this, PaiementContratActivity.class);
                startActivity(intent);
            }
        });

        // Action de clic sur le texte pour afficher l'historique des paiements
        textHistoriquePaiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adherentespace.this, HistoriquePaiementsActivity.class);
                startActivity(intent);
            }
        });

        // Action de clic sur le texte pour afficher l'espace administrateur
        textadministrateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDialogue();
            }
        });

        // Action de clic sur le texte pour ouvrir l'interface Power BI
        textPowerbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherpower();
            }
        });

    }


    private void afficherDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrez le code d'accès");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String code = input.getText().toString();
                if (code.equals("1962")) {
                    Intent intent = new Intent(adherentespace.this, espaceadmin.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(adherentespace.this, "Code incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void afficherpower() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.powerbi.com/links/gG_xAnTpxr?ctid=dbd6664d-4eb9-46eb-99d8-5c43ba153c61&pbi_source=linkShare"));
        startActivityForResult(intent, POWERBI_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == POWERBI_REQUEST_CODE) {
            // Gérez ici le résultat si nécessaire
        }
    }
}
