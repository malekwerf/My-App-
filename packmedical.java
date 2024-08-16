package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class packmedical extends AppCompatActivity {

    private Spinner couvertureSpinner;
    private Button submitButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packmedical);

        // Initialisation des vues
        couvertureSpinner = findViewById(R.id.couvertureSpinner);
        submitButton = findViewById(R.id.submitbutton);

        // Adapter pour le Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.ITEMCOUVERTURE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        couvertureSpinner.setAdapter(adapter);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Définir le clic du bouton de soumission
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(packmedical.this, InformationsPersonnellesActivity.class);
                startActivity(intent);
                validerPackMedical();
            }
        });
    }

    // Méthode appelée lorsque le bouton de soumission est cliqué
    private void validerPackMedical() {
        String choixCouverture = couvertureSpinner.getSelectedItem().toString();
        // Vous pouvez effectuer d'autres opérations ici en fonction du choix de l'utilisateur
        Toast.makeText(this, "Choix de couverture médicale: " + choixCouverture, Toast.LENGTH_SHORT).show();

        // Enregistrer le choix dans la base de données
        enregistrerChoixDansBaseDeDonnees(choixCouverture);
    }

    // Méthode pour enregistrer le choix de l'utilisateur dans la base de données
    private void enregistrerChoixDansBaseDeDonnees(String choixCouverture) {
        // Appel de la méthode d'insertion dans la base de données du dbHelper
        boolean success = dbHelper.insererChoix(choixCouverture);

        if (success) {
            Toast.makeText(this, "Choix enregistré avec succès dans la base de données", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de l'enregistrement du choix dans la base de données", Toast.LENGTH_SHORT).show();
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
