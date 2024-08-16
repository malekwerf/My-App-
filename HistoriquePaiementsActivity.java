package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HistoriquePaiementsActivity extends AppCompatActivity {

    private TextView montanttext, mode_depaiement, refe, etat, date_expiration;
    private EditText id_contrat;
    private Button afficherButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paiementadherent);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Récupération des vues
        id_contrat = findViewById(R.id.idcontrat);
        montanttext = findViewById(R.id.montanttext);
        mode_depaiement = findViewById(R.id.mode_depaiement);
        refe = findViewById(R.id.refe);
        etat = findViewById(R.id.etat);
        date_expiration = findViewById(R.id.date_expiration);
        afficherButton = findViewById(R.id.afficherButton);

        // Gestionnaire de clics pour le bouton d'affichage
        afficherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDetailsPaiement();
            }
        });
    }


    private void afficherDetailsPaiement() {
        // Récupérer l'id de contrat entré par l'utilisateur
        String idcontratStr = id_contrat.getText().toString().trim();
        if (!idcontratStr.isEmpty()) {
            int  id_contrat = Integer.parseInt(idcontratStr);
            // Récupérer les informations de paiement correspondant au id de contrat
            Cursor cursor = dbHelper.getHistoriquePaiementByIdcontrat( id_contrat);
            if (cursor != null) {
                // Vérifier si le curseur contient des lignes de données
                if (cursor.getCount() > 0) {
                    // Le curseur contient des données, déplacer le curseur à la première ligne
                    cursor.moveToFirst();

                    // Récupérer les informations de paiement depuis le curseur
                    @SuppressLint("Range") String montant = cursor.getString(cursor.getColumnIndex("montant"));
                    @SuppressLint("Range") String modeDePaiement = cursor.getString(cursor.getColumnIndex("mode_de_paiement"));
                    @SuppressLint("Range") String refPaiement = cursor.getString(cursor.getColumnIndex("ref_paiement"));
                    @SuppressLint("Range") String Etat = cursor.getString(cursor.getColumnIndex("Etat"));
                    @SuppressLint("Range") String dateDexpiration = cursor.getString(cursor.getColumnIndex("date_dexpiration"));

                    // Afficher les détails de paiement
                    montanttext.setText("Montant: " + montant);
                    mode_depaiement.setText("Mode de paiement: " + modeDePaiement);
                    refe.setText("Référence paiement: " + refPaiement);
                    etat.setText("État: " + Etat);
                    date_expiration.setText("Date d'expiration: " + dateDexpiration);

                    // Déterminer quelle icône afficher en fonction de l'état du paiement
                    if (Etat.equals("payé")) {
                        // Afficher l'icône de point vert
                        etat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_point_vert, 0, 0, 0);
                    } else if (Etat.equals("impayé")) {
                        // Afficher l'icône de point rouge
                        etat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_point_rouge, 0, 0, 0);
                    }

                    // Rendre les TextView visibles
                    montanttext.setVisibility(View.VISIBLE);
                    mode_depaiement.setVisibility(View.VISIBLE);
                    refe.setVisibility(View.VISIBLE);
                    etat.setVisibility(View.VISIBLE);
                    date_expiration.setVisibility(View.VISIBLE);
                } else {
                    // Le curseur est vide, aucune donnée trouvée
                    Toast.makeText(getApplicationContext(), "Aucune donnée de paiement trouvée pour cet adhérent", Toast.LENGTH_SHORT).show();
                }
                // Fermer le curseur
                cursor.close();
            } else {
                // Afficher un message d'erreur si le curseur est null
                Toast.makeText(getApplicationContext(), "Erreur lors de la récupération des données de paiement", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Afficher un message d'erreur si le numéro d'adhérent est vide
            Toast.makeText(getApplicationContext(), "Veuillez saisir le numéro d'adhérent", Toast.LENGTH_SHORT).show();
        }
    }
}