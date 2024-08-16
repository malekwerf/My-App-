package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmPasswordActivity extends AppCompatActivity {

    private EditText recoveryCodeEditText;
    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        // Initialisation des vues
        recoveryCodeEditText = findViewById(R.id.textRecoveryCode);
        sendEmailButton = findViewById(R.id.buttonSendEmail);
        Intent intent=getIntent();
        String email= intent.getStringExtra("email");
        String recoveryCode= intent.getStringExtra("recoveryCode");

        sendRecoveryEmail(email,recoveryCode);
        // Ajouter un écouteur de clic au bouton d'envoi d'email
        // Ajouter un écouteur de clic au bouton "Nouveau mot de passe"
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recoveryCode = recoveryCodeEditText.getText().toString().trim();

                // Vérifier si le champ du code de récupération est vide
                if (!recoveryCode.isEmpty()) {
                    // Appeler la méthode sendRecoveryEmail
                    sendRecoveryEmail("example@example.com", recoveryCode);
                    // Rediriger vers la page nouveaupass
                    startActivity(new Intent(ConfirmPasswordActivity.this, nouveaupass.class));
                    // Finir l'activité actuelle
                    finish();
                } else {
                    // Le champ du code de récupération est vide, affichez un message d'erreur
                    Toast.makeText(ConfirmPasswordActivity.this, "Veuillez entrer le code de récupération", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Aucun client email installé.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace(); // Afficher les détails de l'exception dans la console de Logcat
        }
    }
}
