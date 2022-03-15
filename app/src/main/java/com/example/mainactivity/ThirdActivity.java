package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mainactivity.databinding.ActivityMainBinding;
import com.example.mainactivity.databinding.ActivityThirdBinding;
import com.google.android.material.snackbar.Snackbar;

public class ThirdActivity extends AppCompatActivity {

    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIrTela4.setOnClickListener(view ->
                Snackbar.make(view, "Não é possível Prosseguir não existe tela 4", Snackbar.LENGTH_LONG).show()
        );
        binding.buttonVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela 3");
        startService(intent);
    }

}