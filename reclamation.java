package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class reclamation extends AppCompatActivity {

    private EditText idReclamationEditText;
    private TextView datereclTextView, descriptionTextView, etatTextView, sinistreTextView, nomprenomTextView, emailTextView, telTextView;
    private Button soumettreReclamationButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamation);

        idReclamationEditText = findViewById(R.id.idReclamationEditText);
        datereclTextView = findViewById(R.id.daterecl);
        descriptionTextView = findViewById(R.id.description);
        etatTextView = findViewById(R.id.Etat);
        sinistreTextView = findViewById(R.id.sinistre);

        soumettreReclamationButton = findViewById(R.id.buttonrec);

        databaseHelper = new DatabaseHelper(this);

        ImageView navigationImageView = findViewById(R.id.navigationImageView);
        navigationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDetailsReclamation();
            }
        });

        soumettreReclamationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reclamation.this, ajoutreclamation.class);
                startActivity(intent);
            }
        });


    }

    private void afficherDetailsReclamation() {
        String id_reclamation = idReclamationEditText.getText().toString().trim();
        if (!id_reclamation.isEmpty()) {
            // Convertir l'ID de réclamation en int
            int id_reclamationInt = Integer.parseInt(id_reclamation);

            // Récupérer les détails de la réclamation depuis la base de données
           Cursor cursor;
            cursor = databaseHelper.getDetailsReclamationById(id_reclamationInt);
            if (cursor != null && cursor.moveToFirst()) {
                // Récupérer les informations de la réclamation depuis le curseur
                @SuppressLint("Range") int dateReclamation = cursor.getInt(cursor.getColumnIndex("date_reclamation"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") String etat = cursor.getString(cursor.getColumnIndex("Etat"));
                @SuppressLint("Range") String sinistre = cursor.getString(cursor.getColumnIndex("sinistre"));


                // Mettre à jour les TextView avec les informations récupérées
                datereclTextView.setText("date reclamation: " + dateReclamation);
                descriptionTextView.setText("description: " + description);
                etatTextView.setText("Etat: " + etat);
                sinistreTextView.setText("Sinistre: " + sinistre);


                // Rendre les TextView visibles
                datereclTextView.setVisibility(View.VISIBLE);
                descriptionTextView.setVisibility(View.VISIBLE);
                etatTextView.setVisibility(View.VISIBLE);
                sinistreTextView.setVisibility(View.VISIBLE);


                cursor.close(); // Fermer le curseur après utilisation
            } else {
                // Afficher un message si la réclamation n'est pas trouvée
                Toast.makeText(getApplicationContext(), "Réclamation non trouvée", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Afficher un message d'erreur si l'ID de réclamation est vide
            Toast.makeText(getApplicationContext(), "Veuillez saisir l'ID de réclamation", Toast.LENGTH_SHORT).show();
        }
    }
}
