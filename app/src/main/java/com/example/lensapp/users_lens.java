package com.example.lensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class users_lens extends AppCompatActivity {
    private SharedPreferences pref, def_pref;
    private EditText editSave;
    private TextView editGet;
    private final String save_key1 = "save_key1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_lens);
        init();
    }

    public void onClickSave(View view) {
        editGet.setText(pref.getString(save_key1, ""));
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(save_key1, editSave.getText().toString());
        edit.apply();

        switch (view.getId()) {
            case R.id.button_save:
                Intent intent = new Intent(this, com.example.lensapp.MainActivity.class);
                intent.putExtra("editSave", editSave.getText().toString());

                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void init() {
        pref = getSharedPreferences("storage_user_lens", MODE_PRIVATE);
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);

        editSave = findViewById(R.id.editTextSave);
        editGet = findViewById(R.id.editTextGet);
        editGet.setText(pref.getString(save_key1, ""));

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout_main);
        String back_color = def_pref.getString("background_color", "Голубой (по умолчанию)");
        if (back_color != null) {
            switch (back_color) {
                case "Бирюзовый":
                    constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.turquoise_color));
                    break;
                case "Сиреневый":
                    constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.purple_color));
                    break;
                case "Красный":
                    constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.red_color));
                    break;
                case "Серый":
                    constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_color));
                    break;
                case "Зелёный":
                    constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.green_color));
                    break;
            }
        }
    }
}