package com.example.myapp;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ajoutreclamation extends AppCompatActivity {

    EditText nomPrenomEditText, telephoneEditText, emailEditText, descriptionEditText;
    Spinner sinistreSpinner;
    Button boutonSoumettre;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutreclamation);

        // Initialisation des vues
        nomPrenomEditText = findViewById(R.id.nomPrenomEditText);
        telephoneEditText = findViewById(R.id.telephoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        sinistreSpinner = findViewById(R.id.sinistreTextView);
        boutonSoumettre = findViewById(R.id.bouton1);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Création de l'adaptateur pour le spinner avec les options définies dans Constants.ITEMSINISTRE
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.ITEMSINISTRE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sinistreSpinner.setAdapter(adapter);

        // Gestionnaire d'événements du bouton Soumettre
        boutonSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs des champs
                String nomPrenom = nomPrenomEditText.getText().toString();
                String telephone = telephoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String sinistre = sinistreSpinner.getSelectedItem().toString();

                // Insertion de la réclamation dans la base de données
                long result = dbHelper.insertReclamation(nomPrenom, telephone, email, description, sinistre);

                // Vérification du succès de l'insertion
                if (result != -1) {
                    Toast.makeText(ajoutreclamation.this, "Réclamation ajoutée avec succès", Toast.LENGTH_SHORT).show();
                    // Réinitialisation des champs après l'insertion
                    nomPrenomEditText.setText("");
                    telephoneEditText.setText("");
                    emailEditText.setText("");
                    descriptionEditText.setText("");
                    sinistreSpinner.setSelection(0); // Sélectionne le premier élément du spinner
                } else {
                    Toast.makeText(ajoutreclamation.this, "Erreur lors de l'ajout de la réclamation", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
