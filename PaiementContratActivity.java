package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


// Importez la classe DatabaseHelper si ce n'est pas déjà fait

public class PaiementContratActivity extends AppCompatActivity {
    private EditText numeroAdherentEditText, idContratEditText;
    private Button payerContratButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paiementcontrat);

        dbHelper = new DatabaseHelper(this); // Initialisation de DatabaseHelper

        numeroAdherentEditText = findViewById(R.id.numeroAdherentEditText);
        idContratEditText = findViewById(R.id.idContratEditText);
        payerContratButton = findViewById(R.id.paiement);

        payerContratButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'ID du contrat depuis le champ de texte
                String idContrat = idContratEditText.getText().toString();
                String numAdherent = numeroAdherentEditText.getText().toString();

                // Vérifier si l'ID du contrat est valide en appelant la fonction checkIdContrat de DatabaseHelper
                if (dbHelper.checkIdContrat(Integer.parseInt(idContrat))) {
                    // Récupérer le montant correspondant à partir de la base de données en utilisant le numéro d'adhérent
                    float montant = dbHelper.getMontant(numAdherent);

                    // Passer au paiement en ligne avec le montant
                    Intent intent = new Intent(PaiementContratActivity.this, paiementenligne.class);
                    intent.putExtra("Num_adherent", numAdherent);
                    intent.putExtra("Montant", montant);
                    startActivity(intent);
                } else {
                    // ID de contrat invalide, afficher un message d'erreur
                    Toast.makeText(PaiementContratActivity.this, "ID de contrat invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

