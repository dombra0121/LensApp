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

public class settings extends AppCompatActivity {
    private SharedPreferences pref, def_pref;
    private EditText Power_OS, Power_OD, BC_OS, BC_OD, DIA_OS, DIA_OD;
    private final String save_key1 = "save_key1";
    private final String save_key2 = "save_key2";
    private final String save_key3 = "save_key3";
    private final String save_key4 = "save_key4";
    private final String save_key5 = "save_key5";
    private final String save_key6 = "save_key6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    public void onClickSave(View view) {
        SharedPreferences.Editor edit = pref.edit();

        edit.putString(save_key1, Power_OS.getText().toString());
        edit.putString(save_key2, Power_OD.getText().toString());
        edit.putString(save_key3, BC_OS.getText().toString());
        edit.putString(save_key4, BC_OD.getText().toString());
        edit.putString(save_key5, DIA_OS.getText().toString());
        edit.putString(save_key6, DIA_OD.getText().toString());
        edit.apply();

        switch (view.getId()){
            case R.id.button_save:
                Intent intent = new Intent(this, com.example.lensapp.MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void init() {
        pref = getSharedPreferences("storage_lens", MODE_PRIVATE);
        Power_OS = findViewById(R.id.editText2);
        Power_OD = findViewById(R.id.editText2_1);
        BC_OS = findViewById(R.id.editText3);
        BC_OD = findViewById(R.id.editText3_1);
        DIA_OS = findViewById(R.id.editText4);
        DIA_OD = findViewById(R.id.editText4_1);

        Power_OS.setText(pref.getString(save_key1, ""));
        Power_OD.setText(pref.getString(save_key2, ""));
        BC_OS.setText(pref.getString(save_key3, ""));
        BC_OD.setText(pref.getString(save_key4, ""));
        DIA_OS.setText(pref.getString(save_key5, ""));
        DIA_OD.setText(pref.getString(save_key6, ""));

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);

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