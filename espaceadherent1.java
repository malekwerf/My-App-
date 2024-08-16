package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class espaceadherent1 extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    TextView inscrire, motdepassoublie;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espaceadherent);

        // Initialisation des vues
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        inscrire = findViewById(R.id.inscrireLink);
        motdepassoublie = findViewById(R.id.forgotPasswordLink);

        // Initialisation de l'instance de DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Définir le clic du bouton de connexion
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs de l'interface utilisateur
                String Num_adherent = usernameEditText.getText().toString().trim();
                String mot_de_passe = passwordEditText.getText().toString().trim();

                // Vérifier si les champs sont vides
                if (!Num_adherent.isEmpty() && !mot_de_passe.isEmpty()) {
                    // Vérifier les informations d'identification dans la base de données
                    if (seconnecter(Integer.parseInt(Num_adherent), mot_de_passe)) {
                        // Rediriger vers l'activité espace adhérent si les informations sont correctes
                        Intent intent = new Intent(espaceadherent1.this, adherentespace.class);
                        intent.putExtra("Num_adherent", Num_adherent);
                        startActivity(intent);

                        // Démarrer l'activité PaiementContratActivity


                    } else {
                        // Afficher un message d'erreur si les informations sont incorrectes
                        Toast.makeText(getApplicationContext(), "Numéro d'adhérent ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // Afficher un message d'erreur si les champs sont vides
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Définir le clic pour l'inscription
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(espaceadherent1.this, souscription.class);
                startActivity(intent);
            }
        });

        // Définir le clic pour la récupération du mot de passe
        motdepassoublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(espaceadherent1.this, identifiuser.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour valider les informations d'identification dans la base de données
    private boolean seconnecter(int Num_adherent, String mot_de_passe) {
        // Récupérer une instance de la base de données en écriture
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Colonnes à récupérer dans la requête
        String[] projection = {"Num_adherent"};

        // Clause de sélection pour la requête
        String selection = "Num_adherent = ? AND mot_de_passe = ?";

        // Convertir le numéro d'adhérent en String
        String Num_adherentString = String.valueOf(Num_adherent);

        // Arguments de sélection pour remplacer les "?" dans la clause de sélection
        String[] selectionArgs = {Num_adherentString, mot_de_passe};

        // Exécuter la requête SELECT sur la table "adherents"
        Cursor cursor = db.query(
                "adherents",            // Nom de la table
                projection,             // Colonnes à récupérer
                selection,              // Clause de sélection
                selectionArgs,          // Arguments de sélection
                null,                   // Pas de clause GROUP BY
                null,                   // Pas de clause HAVING
                null                    // Pas de clause ORDER BY
        );

        // Retourner true si au moins une ligne correspond aux identifiants fournis, sinon false
        boolean hasData = cursor.getCount() > 0;

        // Fermer le curseur pour libérer les ressources
        cursor.close();

        return hasData;
    }
}
