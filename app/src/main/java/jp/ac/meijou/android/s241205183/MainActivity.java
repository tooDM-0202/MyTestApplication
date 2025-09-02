package jp.ac.meijou.android.s241205183;

import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.preference.PreferenceDataStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205183.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PreferenceDataStore preferenceDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        /**
         * ボタンを押したらTextViewに値をセットする
         */

            binding.button.setOnClickListener(view -> {
                var text = binding.editTextText.getText().toString();
                binding.text.setText(text);
                // binding.text.setText(R.string.name);文字を変える
            });

        /**
         * ボタンを押したら画像と文字を切り替える
         */
        binding.button2.setOnClickListener(view -> {

             binding.text.setText(R.string.name);
            binding.imageView.setColorFilter(Color.GREEN);
        });

        /**
         * EditTextの文字が変更されるたびにTextViewを変更する
         */
            binding.editTextText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //テキストが更新される直前に呼ばれる

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //文字を1つ入力されたときに呼ばれる

                }
                @Override
                public void afterTextChanged(Editable editable) {
                    //テキストが更新だれた後に呼ばれる
                    binding.text.setText(editable.toString());
                }
            });

        //binding.text.setText(R.string.text);
    }
}