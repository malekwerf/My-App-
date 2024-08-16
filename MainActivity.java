package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private Button memberLoginButton;
    private Button nonadherentButton;
    private Button power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les boutons en utilisant findViewById
        memberLoginButton = findViewById(R.id.memberLoginButton);
        nonadherentButton = findViewById(R.id.idespacenonadherent);


        ;
        // Définir des listeners de clic pour les boutons
        memberLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur le bouton adhérent
                Intent intent = new Intent(MainActivity.this, espaceadherent1.class);
                startActivity(intent);
            }
        });

        nonadherentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur le bouton non adhérent
                Intent intent = new Intent(MainActivity.this, nonadherents.class);
                startActivity(intent);
            }
        });


    }
}
