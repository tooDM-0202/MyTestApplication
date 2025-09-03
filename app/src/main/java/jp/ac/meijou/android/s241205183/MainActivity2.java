package jp.ac.meijou.android.s241205183;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205183.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205183.databinding.ActivityMain4Binding;
import jp.ac.meijou.android.s241205183.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

        private ActivityMain2Binding binding;
        private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    switch (result.getResultCode()) {
                        case RESULT_OK -> {
                            Optional.ofNullable(result.getData())
                                    .map(data -> data.getStringExtra("result"))
                                    .map(text -> "Result: " + text)
                                    .ifPresent(text -> binding.Result.setText(text));
                        }
                        case RESULT_CANCELED -> {
                            binding.Result.setText("Result: Canceled");
                        }
                        default -> {
                            binding.Result.setText("Result: Unknown(" + result.getResultCode() + ")");
                        }
                    }
                }
        );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //明示的
        binding.buttonNumA.setOnClickListener((View view) -> {
            var intent = new Intent(this,MainActivity.class);
            startActivity(intent);
                });

        //暗示的
        binding.buttonNumB.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.sendbutton.setOnClickListener(view -> {
            var text =binding.editText2.getText().toString();
            var intent =new Intent(this,MainActivity4.class);
            intent.putExtra("title",text);
            startActivity(intent);
        });

        binding.button3.setOnClickListener(view -> {
            var intent =new Intent(this,MainActivity4.class);
            getActivityResult.launch(intent);
        });




    }
}