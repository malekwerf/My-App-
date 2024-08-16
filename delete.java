package com.example.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete extends AppCompatActivity {

    private EditText editTextNumAdherent;
    private Button buttonSupprimer;
    private TextView textViewResultat;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        // Initialisation des vues
        editTextNumAdherent = findViewById(R.id.editTextNumAdherent);
        buttonSupprimer = findViewById(R.id.buttonSupprimer);
        textViewResultat = findViewById(R.id.textViewResultat);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Définir le clic du bouton de suppression
        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimerAdherent();
            }
        });
    }

    private void supprimerAdherent() {
        // Récupérer l'ID de l'adhérent saisi
        String numAdherentStr = editTextNumAdherent.getText().toString().trim();

        // Vérifier si l'ID de l'adhérent est vide
        if (numAdherentStr.isEmpty()) {
            // Afficher un message d'erreur si l'ID de l'adhérent est vide
            Toast.makeText(getApplicationContext(), "Veuillez saisir un numéro d'adhérent", Toast.LENGTH_SHORT).show();
        } else {
            // Convertir la chaîne de caractères en entier
            final int numAdherent = Integer.parseInt(numAdherentStr);

            // Créer une vue contenant le message d'alerte
            TextView messageView = new TextView(this);
            messageView.setText("Êtes-vous sûr de vouloir supprimer cet adhérent ?");
            messageView.setPadding(20, 20, 20, 20);

            // Créer une boîte de dialogue AlertDialog avec la vue personnalisée
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(messageView);
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // L'utilisateur a cliqué sur Oui, donc supprimer l'adhérent
                    boolean success = dbHelper.supprimerAdherent(numAdherent);

                    // Afficher un message en fonction du résultat de la suppression
                    if (success) {
                        Toast.makeText(getApplicationContext(), "Adhérent supprimé avec succès", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Échec de la suppression de l'adhérent", Toast.LENGTH_SHORT).show();
                    }
                    textViewResultat.setVisibility(View.VISIBLE); // Rendre le TextView visible
                }
            });
            builder.setNegativeButton("Non", null); // Si l'utilisateur clique sur Non, ne rien faire

            // Afficher la boîte de dialogue
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermer la connexion à la base de données lorsque l'activité est détruite
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
