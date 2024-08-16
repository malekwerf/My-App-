package com.example.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class packstartup extends AppCompatActivity {

    private EditText numAdherentTextView;
    private Spinner typeAssuranceSpinner,montantCapitalSpinner;
    private Button soumissionerButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        // Liaison des éléments de la mise en page avec les variables Java
        numAdherentTextView = findViewById(R.id.numAdherentTextView);
        montantCapitalSpinner = findViewById(R.id.montantCapitalSpinner);
        typeAssuranceSpinner = findViewById(R.id.typeAssuranceSpinner);
        soumissionerButton = findViewById(R.id.soumissioner);

        // Définir les options pour le Spinner du montant du capital
        ArrayAdapter<String> montantCapitalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itemmontant);
        montantCapitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        montantCapitalSpinner.setAdapter(montantCapitalAdapter);

        // Définir les options pour le Spinner du type d'assurance
        ArrayAdapter<String> typeAssuranceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itemTyperisque);
        typeAssuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAssuranceSpinner.setAdapter(typeAssuranceAdapter);

        soumissionerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies par l'utilisateur
                String numAdherent = numAdherentTextView.getText().toString();
                String montantCapital = montantCapitalSpinner.getSelectedItem().toString();
                String typeAssurance = typeAssuranceSpinner.getSelectedItem().toString();

                // Enregistrer les informations dans la base de données
                enregistrerPackStartup(numAdherent, montantCapital, typeAssurance);
            }
        });
    }

    private void enregistrerPackStartup(String numAdherent, String montantCapital, String typeAssurance) {
        // Créer une instance de la classe DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Insérer les informations dans la base de données
        boolean success = dbHelper.insererPackStartup(numAdherent, montantCapital, typeAssurance);

        // Afficher un message Toast pour indiquer si l'insertion a réussi ou non
        if (success) {
            Toast.makeText(this, "Informations du pack startup enregistrées avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de l'enregistrement des informations du pack startup", Toast.LENGTH_SHORT).show();
        }
    }
}
