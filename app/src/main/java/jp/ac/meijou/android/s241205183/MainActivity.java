package jp.ac.meijou.android.s241205183;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205183.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

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
        prefDataStore = PrefDataStore.getInstance(this);

        binding.Save.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name",text);
        });

        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));


        /*
         * ボタンを押したらTextViewに値をセットする
         */

            binding.button.setOnClickListener(view -> {
                var text = binding.editTextText.getText().toString();
                binding.text.setText(text);
                // binding.text.setText(R.string.name);文字を変える
            });

        binding.clear.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.text.setText("");
            // binding.text.setText(R.string.name);文字を変える
        });

        /*
         * ボタンを押したら画像と文字を切り替える
         */
        binding.button2.setOnClickListener(view -> {

             binding.text.setText(R.string.name);
            binding.imageView.setColorFilter(Color.GREEN);
        });

        /*
         * EditTextの文字が変更されるたびにTextViewを変更する

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
*/

    }   protected void onStart(){//起動時に実行（saveした文字を表示）
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));
    }
}