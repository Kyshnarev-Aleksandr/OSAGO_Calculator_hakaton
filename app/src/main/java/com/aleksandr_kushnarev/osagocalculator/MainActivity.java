package com.aleksandr_kushnarev.osagocalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView koff_BT_close,koff_KM_close,koff_KT_close,koff_KBM_close,koff_KO_close,koff_KVS_close
             ,textView_sity, textView_power, textView_driver, textView_old, textView_min_staj, textView_crash;
    TextView power_button,driver_button,old_button,min_staj_button,crash_button, sity_button;

    ImageView imageView_open_coff, imageView_delete_text;
    LinearLayout layout;
    RelativeLayout coefficient_click;

    TextView textView_head_dialog;
    BottomSheetDialog bottomSheet;
    View sheetView;
    EditText editText;

    Button button_Dialog_next;

    //если закрыто
    boolean close;

    ArrayList<String> arrayList_sity ,arrayList_power, arrayList_driver, arrayList_old, arrayList_min_staj, arrayList_crash;
    String [] string_sity, string_power, string_driver, string_old, string_min_staj, string_crash;

    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация
        Init();
        //открытие плашки коффициентов
        OpenCoff();
        //наполенние списков
        ArrayInit();
    }

    //Инициализация
    private void Init() {

        close = true;
        imageView_open_coff = findViewById(R.id.image_open_koff);
        layout = findViewById(R.id.setting_LL_open);
        coefficient_click = findViewById(R.id.coefficient_click);

        koff_BT_close = findViewById(R.id.koff_BT_close);
        koff_KM_close = findViewById(R.id.koff_KM_close);
        koff_KT_close  = findViewById(R.id.koff_KT_close);
        koff_KBM_close = findViewById(R.id.koff_KBM_close);
        koff_KO_close = findViewById(R.id.koff_KO_close);
        koff_KVS_close  = findViewById(R.id.koff_KVS_close);

        sity_button = findViewById(R.id.sity_button);
        power_button = findViewById(R.id.power_button);
        driver_button = findViewById(R.id.driver_button);
        old_button = findViewById(R.id.old_button);
        min_staj_button = findViewById(R.id.min_staj_button);
        crash_button = findViewById(R.id.crash_button);

        textView_sity = findViewById(R.id.textView_sity);
        textView_power = findViewById(R.id.textView_power);
        textView_driver = findViewById(R.id.textView_driver);
        textView_old = findViewById(R.id.textView_old);
        textView_min_staj = findViewById(R.id.textView_min_staj);
        textView_crash = findViewById(R.id.textView_crash);





    }

    //открытие плашки коффициентов
    private void OpenCoff() {

        imageView_open_coff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (close == true){
                    close = false;
                    layout.setVisibility(View.VISIBLE);
                    imageView_open_coff.setImageResource(R.drawable.ic_up);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative);
                }else {
                    close = true;
                    layout.setVisibility(View.GONE);
                    imageView_open_coff.setImageResource(R.drawable.ic_down);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative_close);
                }
            }
        });

    }

    //нажатие на ввод коффициентов
    public void Click_Coff(View view) {
        bottomSheet = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
        sheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_item, (LinearLayout)findViewById(R.id.sheet_conteiner));

        textView_head_dialog = sheetView.findViewById(R.id.text_head_dialog);
        button_Dialog_next = sheetView.findViewById(R.id.button_next);
        editText = sheetView.findViewById(R.id.edit_TT);
        imageView_delete_text = sheetView.findViewById(R.id.image_delete_text);

        listView = sheetView.findViewById(R.id.listView);

        bottomSheet.setContentView(sheetView);
        bottomSheet.show();

        switch (view.getId()){
            case R.id.sity_button:OpenDialogBottom(1);
                break;
            case R.id.power_button:OpenDialogBottom(2);
                break;
            case R.id.driver_button:OpenDialogBottom(3);
                break;
            case R.id.old_button:OpenDialogBottom(4);
                break;
            case R.id.min_staj_button:OpenDialogBottom(5);
                break;
            case R.id.crash_button:OpenDialogBottom(6);
                break;
        }
    }

    //отображение и изминение данных в шторке
    private void OpenDialogBottom(int key) {
        switch (key){
            case 1:
                textView_head_dialog.setText("Город регистраций собственника");
                button_Dialog_next.setText("Далее >");
                editText.setHint("Введите город");

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_sity);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sity_button.setTextSize(12);
                        sity_button.setPadding(0,0,0,0);
                        textView_sity.setVisibility(View.VISIBLE);
                        textView_sity.setText(editText.getText().toString());
                        editText.setText("");
                        OpenDialogBottom(2);
                    }
                });

                break;

            case 2:
                textView_head_dialog.setText("Мощность автомобиля");
                button_Dialog_next.setText("Далее >");
                editText.setHint("Введите мощность");

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_power);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        power_button.setTextSize(12);
                        power_button.setPadding(0,0,0,0);
                        textView_power.setVisibility(View.VISIBLE);
                        textView_power.setText(editText.getText().toString());
                        editText.setText("");
                        OpenDialogBottom(3);
                    }
                });

                break;



            case 3:
                textView_head_dialog.setText("Сколько водителей");
                button_Dialog_next.setText("Далее >");
                editText.setHint("Введите колличество водителей");

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_driver);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        driver_button.setTextSize(12);
                        driver_button.setPadding(0,0,0,0);
                        textView_driver.setVisibility(View.VISIBLE);
                        textView_driver.setText(editText.getText().toString());

                        if (editText.getText().toString().equals("Без ограничений")){
                            editText.setText("");
                            OpenDialogBottom(5);
                        }else {
                            editText.setText("");
                            OpenDialogBottom(4);
                        }

                    }
                });

                break;
            case 4:
                textView_head_dialog.setText("Возраст младшего из водителей");
                button_Dialog_next.setText("Далее >");
                editText.setHint("Введите возраст младшего из водителей");

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_old);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        old_button.setTextSize(12);
                        old_button.setPadding(0,0,0,0);
                        textView_old.setVisibility(View.VISIBLE);
                        textView_old.setText(editText.getText().toString());
                        editText.setText("");
                        OpenDialogBottom(5);
                    }
                });

                break;

            case 5:
                textView_head_dialog.setText("Минимальный стаж водителей");
                button_Dialog_next.setText("Далее >");
                editText.setHint("Введите минимальный стаж");


                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_min_staj);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        min_staj_button.setTextSize(12);
                        min_staj_button.setPadding(0,0,0,0);
                        textView_min_staj.setVisibility(View.VISIBLE);
                        textView_min_staj.setText(editText.getText().toString());
                        editText.setText("");
                        OpenDialogBottom(6);
                    }
                });

                break;

            case 6:
                textView_head_dialog.setText("Сколько лет небыло аварий");
                button_Dialog_next.setText("Подтвердить");
                editText.setHint("Введите сколько лет небыло аварий");

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList_crash);
                listView.setAdapter(adapter);

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

                button_Dialog_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        crash_button.setTextSize(12);
                        crash_button.setPadding(0,0,0,0);
                        textView_crash.setVisibility(View.VISIBLE);
                        textView_crash.setText(editText.getText().toString());
                        editText.setText("");
                        bottomSheet.dismiss();
                    }
                });

                break;



        }




    }


    private void ArrayInit() {

        string_sity = new String[]{"Москва", "Магнитогорск", "Магадан"};
        string_power = new String[]{"от 1 до 50", "от 51 до 99", "от 100 до 150", "от 151 до 200", "более 200"};
        string_driver = new String[]{"Без ограничений", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        string_old = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};
        string_min_staj = new String[]{"Без стажа", "Меньше одного года", "1", "2", "3", "4", "5", "6"};
        string_crash = new String[]{"1 год", "2 года ", "3 года ", "4 года", "5 лет ", "6 лет", "7 лет", "8 лет", "9 лет", "10 лет", "11 лет"};



        arrayList_sity = new ArrayList<>();
        for (String i : string_sity){
            arrayList_sity.add(i);
        }



        arrayList_power = new ArrayList<>();
        for (String i : string_power){
            arrayList_power.add(i);
        }

        arrayList_driver = new ArrayList<>();
        for (String i : string_driver){
            arrayList_driver.add(i);
        }

        arrayList_old = new ArrayList<>();
        for (String i : string_old){
            arrayList_old.add(i);
        }

        arrayList_min_staj = new ArrayList<>();
        for (String i : string_min_staj){
            arrayList_min_staj.add(i);
        }

         arrayList_crash = new ArrayList<>();
        for (String i : string_crash){
            arrayList_crash.add(i);
        }

    }


}