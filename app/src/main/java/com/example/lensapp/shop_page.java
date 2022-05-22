package com.example.lensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class shop_page extends AppCompatActivity {
    TextView textView;
    String name;
    private SharedPreferences def_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);
        init();

        textView = (TextView) findViewById(R.id.textView8);
        Intent intent = getIntent();
        name = intent.getStringExtra("lens_name");
        if ((name+"") != "") {
            textView.setText("Купить контактные линзы\n" + name + "\nна маркетплейсах:");
        }
    }

    public void onClickOzon(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ozon.ru/category/kontaktnye-linzy-i-aksessuary-6296/?category_was_predicted=true&from_global=true&text=" + name));
        if ((name+"") != "") {
            startActivity(browserIntent);
        }
        else Toast.makeText(this, "Название контактных линз не найдено", Toast.LENGTH_SHORT).show();
    }

    public void onClickYandex(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.yandex.ru/catalog--kontaktnye-linzy/57659/list?glfilter=7893318%3A10714190&glfilter=12782797%3A14694007&text=" + name));
        if ((name+"") != "") {
            startActivity(browserIntent);
        }
        else Toast.makeText(this, "Название контактных линз не найдено", Toast.LENGTH_SHORT).show();
    }

    public void onClickSber(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sbermegamarket.ru/catalog/?q=" + name));
        if ((name+"") != "") {
            startActivity(browserIntent);
        }
        else Toast.makeText(this, "Название контактных линз не найдено", Toast.LENGTH_SHORT).show();
    }

    public void onClickWildberries(View view) {
        String str = textView.getText().toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wildberries.ru/catalog/0/search.aspx?page=1&sort=popular&search=" + name));
        if ((name+"") != "") {
            startActivity(browserIntent);
        }
        else Toast.makeText(this, "Название контактных линз не найдено", Toast.LENGTH_SHORT).show();
    }
    public void init(){
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