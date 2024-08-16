package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class packscolaire extends AppCompatActivity {

    private EditText emailEditText, telephoneEditText, nombrePersonnesEditText;
    private Spinner etablissementSpinner, formuleSpinner;
    private Button validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packscolaire);

        // Liaison des éléments de la mise en page avec les variables Java
        emailEditText = findViewById(R.id.Email);
        telephoneEditText = findViewById(R.id.numteltEditText);
        nombrePersonnesEditText = findViewById(R.id.nombrePersonnesEditText);
        etablissementSpinner = findViewById(R.id.etablissementSpinner);
        formuleSpinner = findViewById(R.id.formuleSpinner);
        validerButton = findViewById(R.id.valider);

        // Définir les options pour le Spinner de l'établissement
        ArrayAdapter<String> etablissementAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itemEtablissement);
        etablissementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etablissementSpinner.setAdapter(etablissementAdapter);

        // Définir les options pour le Spinner de la formule
        ArrayAdapter<String> formuleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itemFormule);
        formuleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formuleSpinner.setAdapter(formuleAdapter);

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenir les données saisies par l'utilisateur
                String email = emailEditText.getText().toString();
                String telephone = telephoneEditText.getText().toString();
                String etablissement = etablissementSpinner.getSelectedItem().toString();
                String formule = formuleSpinner.getSelectedItem().toString();
                String nombrePersonnesStr = nombrePersonnesEditText.getText().toString();

                // Vérifier si le champ de nombre de personnes est vide
                if (nombrePersonnesStr.isEmpty()) {
                    Toast.makeText(packscolaire.this, "Veuillez entrer le nombre de personnes", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertir le nombre de personnes en entier
                int nombrePersonnes = Integer.parseInt(nombrePersonnesStr);

                // Enregistrer les informations dans la base de données
                enregistrerInformationsScolaires(email, telephone, etablissement, formule, nombrePersonnes);
            }
        });
    }

    private void enregistrerInformationsScolaires(String email, String telephone, String etablissement, String formule, int nombrePersonnes) {
        // Créer une instance de la classe DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Insérer les informations scolaires dans la base de données
        boolean success = dbHelper.insererInformationsScolaires(email, telephone, etablissement, formule, nombrePersonnes);

        // Vérifier si l'insertion a réussi et afficher un message Toast en conséquence
        if (success) {
            Toast.makeText(this, "Informations scolaires enregistrées avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de l'enregistrement des informations scolaires", Toast.LENGTH_SHORT).show();
        }
    }
}
