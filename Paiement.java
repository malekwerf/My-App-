package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Paiement extends AppCompatActivity {
    private EditText emailEditText;
    private EditText nomCarteEditText;
    private EditText numeroCarteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paiement);

        // Initialisation des champs EditText
        emailEditText = findViewById(R.id.email);
        nomCarteEditText = findViewById(R.id.nomCarteEditText);
        numeroCarteEditText = findViewById(R.id.numeroCarteEditText);

        // Initialisation du bouton et ajout du listener de clic
        Button payerButton = findViewById(R.id.payerButton);
        payerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs saisies
                String email = emailEditText.getText().toString();
                String nomCarte = nomCarteEditText.getText().toString();
                String numeroCarte = numeroCarteEditText.getText().toString();

                // Validation de l'e-mail
                if (isValidEmail(email)) {
                    // Envoi de l'e-mail de validation de paiement
                    sendValidationEmail(email);

                    // Affichage d'un message de réussite
                    Toast.makeText(Paiement.this, "Validation de paiement envoyée à " + email, Toast.LENGTH_SHORT).show();

                    // Fin de l'activité et retour à l'activité précédente
                    finish();
                } else {
                    // Affichage d'un message d'erreur si l'e-mail est invalide
                    Toast.makeText(Paiement.this, "Adresse e-mail invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Méthode pour valider l'adresse e-mail
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Méthode pour envoyer l'e-mail de validation de paiement
    private void sendValidationEmail(String email) {
        String subject = "Confirmation de paiement";
        String message = "Votre paiement a été effectué avec succès. Merci!";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        // Vérification s'il y a une application capable de gérer l'intent de messagerie
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choisissez une application de messagerie"));
        } else {
            Toast.makeText(this, "Aucune application de messagerie disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
