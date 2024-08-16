package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.DatabaseHelper;

public class contact extends AppCompatActivity {

    private EditText nomEditText, telephoneEditText, societeEditText, emailEditText, objetEditText, messageEditText;
    private Button envoyerButton;

    private String nom,  telephone, societe, email,  objet, messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        // Liaison des éléments de la mise en page avec les variables Java
        nomEditText = findViewById(R.id.nomEditText);
        telephoneEditText = findViewById(R.id.telephoneEditText);
        societeEditText = findViewById(R.id.societeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        objetEditText = findViewById(R.id.objetEditText);
        messageEditText = findViewById(R.id.messageEditText);
        envoyerButton = findViewById(R.id.envoyer);

        // Définir un écouteur de clic pour le bouton d'envoi
        envoyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies
                nom = nomEditText.getText().toString();
                telephone = telephoneEditText.getText().toString();
                societe = societeEditText.getText().toString();
                email = emailEditText.getText().toString();
                objet = objetEditText.getText().toString();
                messages = messageEditText.getText().toString();

                // Insérer les données dans la base de données
                DatabaseHelper dbHelper = new DatabaseHelper(contact.this);
                boolean isSuccess = dbHelper.insertContact(nom, telephone, societe, email, objet, messages);

                // Vérifier si l'insertion a réussi
                if (isSuccess) {
                    Toast.makeText(contact.this, "Données envoyé avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(contact.this, "Échec de l'enregistrement des données", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
