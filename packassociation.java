package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class packassociation extends AppCompatActivity {

    private EditText nomprenomEditText, emailEditText, numTelEditText, professionEditText;
    private Spinner typeAssuranceSpinner;
    private Button validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.association);

        // Liaison des éléments de la mise en page avec les variables Java
        nomprenomEditText = findViewById(R.id.Nom);
        emailEditText = findViewById(R.id.emailEditText);
        numTelEditText = findViewById(R.id.Numtel);
        professionEditText = findViewById(R.id.proffesion);
        typeAssuranceSpinner = findViewById(R.id.typeAssuranceSpinner);
        validerButton = findViewById(R.id.buttonid);

        // Définir les options pour le Spinner du type d'assurance
        ArrayAdapter<String> typeAssuranceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itemtype);
        typeAssuranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAssuranceSpinner.setAdapter(typeAssuranceAdapter);

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies par l'utilisateur
                String nomprenom = nomprenomEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int numTel = Integer.parseInt(numTelEditText.getText().toString());
                String profession = professionEditText.getText().toString();
                String typeAssurance = typeAssuranceSpinner.getSelectedItem().toString();

                // Enregistrer les informations dans la base de données
                enregistrerPackAssociation(nomprenom, email, numTel, profession, typeAssurance);
            }
        });
    }

    private void enregistrerPackAssociation(String nomprenom, String email, int numTel, String profession, String typeAssurance) {
        // Créer une instance de la classe DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Insérer les informations dans la base de données
        boolean success = dbHelper.insererPackAssociation(nomprenom, email, numTel, profession, typeAssurance);

        // Afficher un message Toast pour indiquer si l'insertion a réussi ou non
        if (success) {
            Toast.makeText(this, "Informations du pack association enregistrées avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de l'enregistrement des informations du pack association", Toast.LENGTH_SHORT).show();
        }
    }
}
