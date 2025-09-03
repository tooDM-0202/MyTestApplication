package jp.ac.meijou.android.s241205183;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205183.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s241205183.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
/*
機能作りかけ
        binding.buttonNumberAC.setOnClickListener(view -> {
            binding.result.setText(0);
        });

        binding.buttonNumber1.setOnClickListener(view -> {
            float res = Float.parseFloat(binding.result.getText().toString());
            binding.result.setText((int) (res+1));
        });

     */





    }
}