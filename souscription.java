package com.example.myapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class souscription extends AppCompatActivity {
    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText addressEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private Button signUpButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.souscription);

        databaseHelper = new DatabaseHelper(this);

        nomEditText = findViewById(R.id.nomEditText1);
        prenomEditText = findViewById(R.id.userEditText2);
        addressEditText = findViewById(R.id.addressEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);

        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies par l'utilisateur
                String nom = nomEditText.getText().toString().trim();
                String prenom = prenomEditText.getText().toString().trim();
                String adresse = addressEditText.getText().toString().trim();
                String telephone = phoneNumberEditText.getText().toString().trim();
                String mot_de_passe = passwordEditText.getText().toString().trim();

                // Vérifier si l'adhérent existe déjà
                if (databaseHelper.adherentExists(nom, prenom, telephone)) {
                    // Adhérent existe déjà
                    Toast.makeText(souscription.this, "Adhérent déjà existant !", Toast.LENGTH_SHORT).show();
                } else {
                    // Insérer les données dans la table adherents de la base de données
                    long result = databaseHelper.insertAdherent(nom, prenom, adresse, telephone, mot_de_passe);

                    // Vérifier si l'insertion a réussi
                    if (result != -1) {
                        // L'insertion a réussi
                        Toast.makeText(souscription.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                        finish();
                        // Vous pouvez rediriger l'utilisateur vers une autre activité ou effectuer d'autres actions ici
                    } else {
                        // L'insertion a échoué
                        Toast.makeText(souscription.this, "Échec de l'inscription. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
