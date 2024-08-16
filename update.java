package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {

    private EditText editTextNumAdherent, editTextNom, editTextPrenom, editTextAdresse, editTextTelephone, editTextMotDePasse;
    private Button buttonModifier;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        // Initialisation des vues
        editTextNumAdherent = findViewById(R.id.editTextNumAdherent);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextAdresse = findViewById(R.id.editTextAdresse);
        editTextTelephone = findViewById(R.id.editTextTelephone);
        editTextMotDePasse = findViewById(R.id.editTextMotDePasse);
        buttonModifier = findViewById(R.id.buttonModifier);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Ajouter un écouteur de texte pour le champ Numéro d'adhérent
        editTextNumAdherent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @SuppressLint("Range")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Vérifier si le champ numéro d'adhérent est vide
                if (s.toString().trim().isEmpty()) {
                    clearFields();
                    return;
                }

                // Récupérer les détails de l'adhérent correspondant au numéro d'adhérent saisi
                Cursor cursor = dbHelper.getAdherentDetails(Integer.parseInt(s.toString().trim()));

                // Vérifier si un adhérent a été trouvé avec ce numéro
                if (cursor != null && cursor.moveToFirst()) {
                    // Afficher les détails de l'adhérent dans les champs correspondants
                    editTextNom.setText(cursor.getString(cursor.getColumnIndex("nom")));
                    editTextPrenom.setText(cursor.getString(cursor.getColumnIndex("prenom")));
                    editTextAdresse.setText(cursor.getString(cursor.getColumnIndex("adresse")));
                    editTextTelephone.setText(cursor.getString(cursor.getColumnIndex("telephone")));
                    editTextMotDePasse.setText(cursor.getString(cursor.getColumnIndex("mot_de_passe")));
                } else {
                    // Afficher un message si aucun adhérent n'est trouvé avec ce numéro
                  //  Toast.makeText(update.this, "Aucun adhérent trouvé avec ce numéro", Toast.LENGTH_SHORT).show();
                    clearFields();
                }

                // Fermer le curseur après utilisation
                if (cursor != null) {
                    cursor.close();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Définir le clic du bouton de modification
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierAdherent();
            }
        });
    }

    private void clearFields() {
        editTextNom.setText("");
        editTextPrenom.setText("");
        editTextAdresse.setText("");
        editTextTelephone.setText("");
        editTextMotDePasse.setText("");
    }

    private void modifierAdherent() {
        // Récupérer les valeurs des champs saisis par l'utilisateur
        String numAdherentStr = editTextNumAdherent.getText().toString().trim();
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String adresse = editTextAdresse.getText().toString().trim();
        String telephone = editTextTelephone.getText().toString().trim();
        String motDePasse = editTextMotDePasse.getText().toString().trim();

        // Vérifier si le num de l'adhérent est vide
        if (!numAdherentStr.isEmpty()) {
            int numAdherent = Integer.parseInt(numAdherentStr);

            // Appeler la méthode updateAdherent de DatabaseHelper pour mettre à jour l'adhérent
            boolean success = dbHelper.updateAdherent(numAdherent, nom, prenom, adresse, Integer.parseInt(telephone), motDePasse);

            // Afficher un message en fonction du résultat de la modification
            if (success) {
                Toast.makeText(getApplicationContext(), "Adhérent mis à jour avec succès", Toast.LENGTH_SHORT).show();
                editTextNom.setText("");
                editTextNumAdherent .setText("");
                        editTextPrenom.setText("");
                editTextAdresse.setText("");
                        editTextTelephone.setText("");
                editTextMotDePasse.setText("");

            } else {
                Toast.makeText(getApplicationContext(), "Échec de la mise à jour de l'adhérent", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Afficher un message d'erreur si l'ID de l'adhérent est vide
            Toast.makeText(getApplicationContext(), "Veuillez saisir un numéro d'adhérent", Toast.LENGTH_SHORT).show();
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
