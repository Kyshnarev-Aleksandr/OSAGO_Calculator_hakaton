package com.aleksandr_kushnarev.osagocalculator;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.Retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;

public class ShowDialog {

    Activity activity;

    BottomSheetDialog bottomSheet;
    View sheetView;
    TextView textView_head_dialog;
    Button button_Dialog_next;
    EditText editText;
    ImageView imageView_delete_text;
    ArrayAdapter<String> adapter;
    ListView listView;

    TextView textView_crash;
    TextView textView_min_staj;
    TextView textView_old;
    TextView textView_driver;
    TextView textView_power;
    TextView textView_city;

    public ShowDialog(Activity activity) {
        this.activity = activity;
    }

    //Создание диалога
    public void GetShowDialog(){
        bottomSheet = new BottomSheetDialog(activity, R.style.BottomSheetDialogTheme);
        sheetView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(R.layout.bottom_sheet_item, (LinearLayout)activity.findViewById(R.id.sheet_conteiner));

        textView_head_dialog = sheetView.findViewById(R.id.text_head_dialog);
        button_Dialog_next = sheetView.findViewById(R.id.button_next);
        editText = sheetView.findViewById(R.id.edit_TT);
        imageView_delete_text = sheetView.findViewById(R.id.image_delete_text);

        listView = sheetView.findViewById(R.id.listView);

        bottomSheet.setContentView(sheetView);
        bottomSheet.show();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(adapter.getItem(position));

            }
        });

        imageView_delete_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

        //методы для Наполнение диалога
    public void ADD_CITY_DIALOG(){
        String []string_city = activity.getResources().getStringArray(R.array.city);
        TextView city_button = activity.findViewById(R.id.sity_button);
        textView_city = activity.findViewById(R.id.textView_sity);

        textView_head_dialog.setText("Город регистраций собственника");
        button_Dialog_next.setText("Далее >");
        editText.setHint("Введите город");

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_city);
        listView.setAdapter(adapter);

        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_button.setTextSize(12);
                city_button.setPadding(0,0,0,0);
                textView_city.setVisibility(View.VISIBLE);
                textView_city.setText(editText.getText().toString());
                editText.setText("");
               ADD_POWER_DIALOG();
            }
        });
    }

    public void ADD_POWER_DIALOG(){
        String []string_power = activity.getResources().getStringArray(R.array.power);
        TextView power_button = activity.findViewById(R.id.power_button);
        textView_power = activity.findViewById(R.id.textView_power);

        textView_head_dialog.setText("Мощность автомобиля");
        button_Dialog_next.setText("Далее >");
        editText.setHint("Введите мощность");

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_power);
        listView.setAdapter(adapter);

        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                power_button.setTextSize(12);
                power_button.setPadding(0,0,0,0);
                textView_power.setVisibility(View.VISIBLE);
                textView_power.setText(editText.getText().toString());
                editText.setText("");
                ADD_DRIVER_DIALOG();

            }
        });
    }

    public void ADD_DRIVER_DIALOG(){
        String [] string_driver = activity.getResources().getStringArray(R.array.driver);
        TextView driver_button = activity.findViewById(R.id.driver_button);
        textView_driver = activity.findViewById(R.id.textView_driver);

        textView_head_dialog.setText("Сколько водителей");
        button_Dialog_next.setText("Далее >");
        editText.setHint("Введите колличество водителей");

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_driver);
        listView.setAdapter(adapter);


        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driver_button.setTextSize(12);
                driver_button.setPadding(0,0,0,0);
                textView_driver.setVisibility(View.VISIBLE);
                textView_driver.setText(editText.getText().toString());

                if (editText.getText().toString().equals("Без ограничений")){
                    editText.setText("");
                    ADD_MIN_STAJ_DIALOG();
                }else {
                    editText.setText("");
                    ADD_OLD_DIALOG();
                }

            }
        });
    }

    public void ADD_OLD_DIALOG(){
        String [] string_old = activity.getResources().getStringArray(R.array.old);
        TextView old_button = activity.findViewById(R.id.old_button);
        textView_old = activity.findViewById(R.id.textView_old);

        textView_head_dialog.setText("Возраст младшего из водителей");
        button_Dialog_next.setText("Далее >");
        editText.setHint("Введите возраст младшего из водителей");

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_old);
        listView.setAdapter(adapter);



        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_button.setTextSize(12);
                old_button.setPadding(0,0,0,0);
                textView_old.setVisibility(View.VISIBLE);
                textView_old.setText(editText.getText().toString());
                editText.setText("");
                ADD_MIN_STAJ_DIALOG();
            }
        });



    }

    public void ADD_MIN_STAJ_DIALOG(){
        String [] string_min_staj = activity.getResources().getStringArray(R.array.min_staj);
        TextView min_staj_button = activity.findViewById(R.id.min_staj_button);
        textView_min_staj = activity.findViewById(R.id.textView_min_staj);

        textView_head_dialog.setText("Минимальный стаж водителей");
        button_Dialog_next.setText("Далее >");
        editText.setHint("Введите минимальный стаж");


        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_min_staj);
        listView.setAdapter(adapter);


        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min_staj_button.setTextSize(12);
                min_staj_button.setPadding(0,0,0,0);
                textView_min_staj.setVisibility(View.VISIBLE);
                textView_min_staj.setText(editText.getText().toString());
                editText.setText("");
                ADD_CRASH_DIALOG();
            }
        });
    }

    public void ADD_CRASH_DIALOG(){
        String [] string_crash = activity.getResources().getStringArray(R.array.crash);
        TextView crash_button = activity.findViewById(R.id.crash_button);
        textView_crash = activity.findViewById(R.id.textView_crash);

        textView_head_dialog.setText("Сколько лет небыло аварий");
        button_Dialog_next.setText("Подтвердить");
        editText.setHint("Введите сколько лет небыло аварий");

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, string_crash);
        listView.setAdapter(adapter);

        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crash_button.setTextSize(12);
                crash_button.setPadding(0,0,0,0);
                textView_crash.setVisibility(View.VISIBLE);
                textView_crash.setText(editText.getText().toString());
                editText.setText("");
                bottomSheet.dismiss();

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("city", textView_city.getText().toString());
                    map.put("power", textView_power.getText().toString());
                    map.put("driver", textView_driver.getText().toString());
                    map.put("old", textView_old.getText().toString());
                    map.put("staj", textView_min_staj.getText().toString());
                    map.put("crash", textView_crash.getText().toString());

                    RetrofitClient.getInstance().GetDataCoff(activity, map);

            }
        });


    }

}
