package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class nouveaupass extends AppCompatActivity {

    private EditText confirmPasswordEditText,newpass;
    private Button changePasswordButton;
    private String numAdherent;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nouveaupassword);

        // Initialize views

        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        newpass=findViewById(R.id.codeEditText);
        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Get the numAdherent from the previous activity
        numAdherent = getIntent().getStringExtra("numAdherent");

        // Set onClickListener for the changePasswordButton
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("nouveaupass", "Le bouton de changement de mot de passe a été cliqué.");
// recuperer les donnes saisie
                String newPassword = newpass.getText().toString();
                String confirmationPassword = confirmPasswordEditText.getText().toString();

                // Vérifier si les champs sont vides
                if (newPassword.isEmpty() || confirmationPassword.isEmpty()) {
                    Toast.makeText(nouveaupass.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier si les mots de passe correspondent
                if (!newPassword.equals(confirmationPassword)) {
                    Toast.makeText(nouveaupass.this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mettre à jour le mot de passe dans la base de données
                dbHelper.updatePassword(numAdherent, newPassword);

                // Informer l'utilisateur que le mot de passe a été mis à jour avec succès
                Toast.makeText(nouveaupass.this, "Le mot de passe a été mis à jour avec succès.", Toast.LENGTH_SHORT).show();

                // Rediriger l'utilisateur vers une autre activité ou effectuer toute autre action nécessaire
                // Par exemple :
                // startActivity(new Intent(nouveaupass.this, NextActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
