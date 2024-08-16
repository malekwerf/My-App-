package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InformationsPersonnellesActivity extends AppCompatActivity {

    private RadioGroup genreRadioGroup;
    private EditText nom_prenom, date_naissance, telephone, email, profession, CIN;
    private Spinner gouvernoratSpinner;
    private Button validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completerinfopersonelmalad);

        // Liaison des éléments de la mise en page avec les variables Java
        genreRadioGroup = findViewById(R.id.genreRadioGroup);
        nom_prenom = findViewById(R.id.nomPrenomEditText);
        date_naissance = findViewById(R.id.dateNaissanceEditText);
        telephone = findViewById(R.id.telephoneEditText);
        email = findViewById(R.id.emailEditText);
        profession = findViewById(R.id.professionEditText);
        CIN = findViewById(R.id.typePiece);
        gouvernoratSpinner = findViewById(R.id.gouvernoratSpinner);
        validerButton = findViewById(R.id.buttonvalide);

        // Définir les options pour le Spinner du gouvernorat
        ArrayAdapter<String> gouvernoratAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.Itemsgouvernorat);
        gouvernoratAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gouvernoratSpinner.setAdapter(gouvernoratAdapter);

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenir l'ID du bouton radio sélectionné
                int selectedGenreId = genreRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGenreRadioButton = findViewById(selectedGenreId);
                String genre = selectedGenreRadioButton.getText().toString();

                // Obtenir les autres données saisies par l'utilisateur
                String nomPrenom = nom_prenom.getText().toString();
                int Cin = Integer.parseInt(CIN.getText().toString());
                String dateNaissance = date_naissance.getText().toString();
                String tel = telephone.getText().toString();
                String Email = email.getText().toString();
                String Profession = profession.getText().toString();

                String gouvernorat = gouvernoratSpinner.getSelectedItem().toString();

                // Enregistrer les informations personnelles dans la base de données
                enregistrerInformationsPersonnelles(genre, nomPrenom,Cin, dateNaissance, tel, Email, Profession,  gouvernorat);
            }
        });
    }

    private void enregistrerInformationsPersonnelles(String genre, String nomPrenom,int CIN, String dateNaissance, String tel, String Email, String Profession, String gouvernorat) {
        // Créer une instance de la classe DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Insérer les informations personnelles dans la base de données
        boolean success = dbHelper.insererInformationsPersonnelles(genre, nomPrenom,CIN, dateNaissance, tel, Email, Profession, gouvernorat);

        // Vérifier si l'insertion a réussi et afficher un message Toast en conséquence
        if (success) {
            Toast.makeText(this, "Informations personnelles enregistrées avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de l'enregistrement des informations personnelles", Toast.LENGTH_SHORT).show();
        }
    }

}