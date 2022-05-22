package com.example.lensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lensapp.app_settings.SettingsActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref, def_pref;

    public TextView users_lens, set_date, end_date, result_date, finish_data;
    DatePickerDialog.OnDateSetListener setListener1;
    DatePickerDialog.OnDateSetListener setListener2;

    private final String save_key1 = "save_key1";
    private final String save_key2 = "save_key2";
    private final String save_key3 = "save_key3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        users_lens.setText(pref.getString(save_key1, ""));
        set_date.setText(pref.getString(save_key2, ""));
        end_date.setText(pref.getString(save_key3, ""));

        SharedPreferences.Editor edit = pref.edit();
        edit.putString(save_key1, users_lens.getText().toString());
        edit.putString(save_key2, set_date.getText().toString());
        edit.putString(save_key3, end_date.getText().toString());
        edit.apply();


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar2 = Calendar.getInstance();
        final int year2 = calendar2.get(Calendar.YEAR);
        final int month2 = calendar2.get(Calendar.MONTH);
        final int day2 = calendar2.get(Calendar.DAY_OF_MONTH);



        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = "";
                if (day < 10) {
                    if (month < 10) {
                        date = "0" + day + ".0" + month + "." + year;
                    }
                    else {
                        date = "0" + day + "." + month + "." + year;
                    }
                }
                else if (month < 10) {
                    date = day + ".0" + month + "." + year;
                }
                else {
                    date = day + "." + month + "." + year;
                }
                set_date.setText(date);
                find_result();
                find_result_2();
                SharedPreferences.Editor edit = pref.edit();
                edit.putString(save_key2, set_date.getText().toString());
                edit.apply();
            }
        };

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener2, year2, month2, day2);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        setListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = "";
                if (day < 10) {
                    if (month < 10) {
                        date = "0" + day + ".0" + month + "." + year;
                    }
                    else {
                        date = "0" + day + "." + month + "." + year;
                    }
                }
                else if (month < 10) {
                    date = day + ".0" + month + "." + year;
                }
                else {
                    date = day + "." + month + "." + year;
                }
                end_date.setText(date);
                find_result(); // пересчет разницы во времени
                find_result_2();
                SharedPreferences.Editor edit = pref.edit();
                edit.putString(save_key3, end_date.getText().toString());
                edit.apply();
            }
        };
    }

    public void init() {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);

        pref = getSharedPreferences("storage_user_lens", MODE_PRIVATE);
        users_lens = findViewById(R.id.textView4);

        users_lens.setText(pref.getString(save_key1, ""));

        set_date = findViewById(R.id.textView_data_start);
        set_date.setText(pref.getString(save_key2, ""));

        end_date = findViewById(R.id.textView_data_end);
        end_date.setText(pref.getString(save_key3, ""));

        find_result();
        find_result_2();

        TextView users_lens1 = findViewById(R.id.textView4);
        TextView text1 = findViewById(R.id.textView2);
        TextView text2 = findViewById(R.id.textView_data_result);
        TextView text3 = findViewById(R.id.textView_start);
        TextView text4 = findViewById(R.id.textView_end);
        TextView text5 = findViewById(R.id.textView_remaining_time);
        TextView text6 = findViewById(R.id.textView_now_to_data);


        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout_main);
        String text = def_pref.getString("main_text_size", "Обычный");
        String back_color = def_pref.getString("background_color", "Голубой (по умолчанию)");
        if (text != null) {
            switch (text) {
                case "Крупный":
                    users_lens1.setTextSize(26);
                    text1.setTextSize(26);
                    text2.setTextSize(26);
                    text3.setTextSize(26);
                    text4.setTextSize(26);
                    text5.setTextSize(26);
                    text6.setTextSize(26);
                    set_date.setTextSize(26);
                    end_date.setTextSize(26);
                    break;
                case "Обычный":
                    users_lens1.setTextSize(22);
                    text1.setTextSize(22);
                    text2.setTextSize(22);
                    text3.setTextSize(22);
                    text4.setTextSize(22);
                    text5.setTextSize(22);
                    text6.setTextSize(22);
                    set_date.setTextSize(22);
                    end_date.setTextSize(22);
                    break;
                case "Мелкий":
                    users_lens1.setTextSize(18);
                    text1.setTextSize(18);
                    text2.setTextSize(18);
                    text3.setTextSize(18);
                    text4.setTextSize(18);
                    text5.setTextSize(18);
                    text6.setTextSize(18);
                    set_date.setTextSize(18);
                    end_date.setTextSize(18);
                    break;
            }
        }
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

    public void find_result() { // общий срок использования
        String str1 = set_date.getText().toString();
        String str2 = end_date.getText().toString();
        result_date = findViewById(R.id.textView_data_result);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = dateFormat.parse(str1);
            Date date2 = dateFormat.parse(str2);

            long milliseconds = date2.getTime() - date1.getTime();
            int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
            if (days % 10 == 1) result_date.setText("" + days + " день");
            else if ((days % 10 == 2 || days % 10 == 3 || days % 10 == 4) && (days % 100 != 11 && days % 100 != 12 && days % 100 != 13 && days % 100 != 14)) result_date.setText("" + days + " дня");
            else result_date.setText("" + days + " дней");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void find_result_2() { // количество дней от сегодняшней даты до финальной
        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        String str2 = end_date.getText().toString();
        finish_data = findViewById(R.id.textView_now_to_data);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = dateFormat.parse(currentDate);
            Date date2 = dateFormat.parse(str2);

            long milliseconds = date2.getTime() - date1.getTime();
            int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
            if (days % 10 == 1 && days != 11) finish_data.setText("" + days + " день");
            else if ((days % 10 == 2 || days % 10 == 3 || days % 10 == 4) && (days % 100 != 11 && days % 100 != 12 && days % 100 != 13 && days % 100 != 14)) finish_data.setText("" + days + " дня");
            else finish_data.setText("" + days + " дней");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickSettings(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickLens(View view) {
        Intent intent = new Intent(MainActivity.this, users_lens.class);
        startActivity(intent);
    }

    public void  onClickShop(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(MainActivity.this, shop_page.class);
        intent.putExtra("lens_name", users_lens.getText().toString());
        startActivity(intent);
    }

    public void onClickUser(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        view.startAnimation(animAlpha);
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
    }

    public void onClickNotify(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        view.startAnimation(animAlpha);
        TextView data_1 = findViewById(R.id.textView_data_end);
        if (data_1.getText().toString() != "") {
            Intent intent = new Intent(MainActivity.this, notification_settings.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Установите срок использования линз", Toast.LENGTH_SHORT).show();
        }
    }
}