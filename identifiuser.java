package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class identifiuser extends AppCompatActivity {

    private EditText N_adherent, emailEditText;
    private Button recoverPasswordButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifpassword);

        // Initialize views
        N_adherent = findViewById(R.id.numEditText);
        emailEditText = findViewById(R.id.emaillEditText);
        recoverPasswordButton = findViewById(R.id.recoverPasswordButton);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set onClickListener for recoverPasswordButton
        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperer le num adherent et adresse email saisie
                String numAdherent = N_adherent.getText().toString().trim();
                String adresse = emailEditText.getText().toString().trim();

                boolean isValid = dbHelper.checkCredentials(numAdherent, adresse);

                if (isValid) {
                    // Générer et stocker le code de récupération
                    String recoveryCode = dbHelper.generateAndStoreRecoveryCode(numAdherent, adresse);

                    // Envoyer un email avec le code de récupération
                    sendRecoveryEmail(adresse, recoveryCode);

                    // Passer à l'activité suivante
                    Intent intent = new Intent(identifiuser.this, ConfirmPasswordActivity.class);
                    intent.putExtra("email", adresse);
                    intent.putExtra("recoveryCode", recoveryCode);
                    startActivity(intent);
                }

            }
        });
    }

    private void sendRecoveryEmail(String email, String recoveryCode) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Code de récupération de mot de passe");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre code de récupération de mot de passe est : " + recoveryCode);
        try {
            startActivity(Intent.createChooser(emailIntent, "Envoyer un email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(identifiuser.this, "Aucun client email installé.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace(); // Afficher les détails de l'exception dans la console de Logcat
        }
    }
}
