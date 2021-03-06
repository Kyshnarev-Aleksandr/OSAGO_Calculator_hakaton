package com.aleksandr_kushnarev.osagocalculator.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksandr_kushnarev.osagocalculator.Dialog.BuyDialog;
import com.aleksandr_kushnarev.osagocalculator.OpenCoff;
import com.aleksandr_kushnarev.osagocalculator.R;
import com.aleksandr_kushnarev.osagocalculator.Dialog.ShowDialog;


public class MainActivity extends AppCompatActivity {

    TextView koff_BT_close,koff_KM_close,koff_KT_close,koff_KBM_close,koff_KO_close,koff_KVS_close
            ,koff_BT_open,koff_KM_open,koff_KT_open,koff_KBM_open,koff_KO_open,koff_KVS_open;
    TextView city_button, textView_city, power_button, textView_power, driver_button, textView_driver,
            old_button, textView_old, min_staj_button, textView_min_staj, crash_button, textView_crash;
    Button button_add;
    ShowDialog show_Dialog;
    ImageView image_View;
    LinearLayout layout;
    RelativeLayout coefficient_click;

    static final int CITY_DIALOG = 1;
    static final int POWER_DIALOG = 2;
    static final int DRIVER_DIALOG = 3;
    static final int OLD_DIALOG = 4;
    static final int MIN_STAJ_DIALOG = 5;
    static final int CRASH_DIALOG = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Инициализация
        init();
        //открытие плашки коффициентов
       OpenCoff open_Coff = new OpenCoff(image_View, layout ,coefficient_click);
       open_Coff.getOpen();

       getIntentInsurance();

       button_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               start_new_activity();
           }
       });
    }

    //получение данных о страховой и открытие шторки
    private void getIntentInsurance() {
      Intent intent = getIntent();
      boolean key = intent.getBooleanExtra("key", false);
        if (key == true){
            BuyDialog buyDialog = new BuyDialog();
            buyDialog.createDialog(this,
                    intent.getStringExtra("name"),
                    intent.getStringExtra("rating"),
                    intent.getStringExtra("sum"),
                    intent.getStringExtra("url"));
        }
    }
    //Переход на след активити
    public void start_new_activity() {
        Intent intent = new Intent(MainActivity.this, List_Activity.class);
        intent.putExtra(String.valueOf(R.string.BT), koff_BT_close.getText().toString());
        intent.putExtra(String.valueOf(R.string.KM), koff_KM_close.getText().toString());
        intent.putExtra(String.valueOf(R.string.KT), koff_KT_close.getText().toString());
        intent.putExtra(String.valueOf(R.string.KBM), koff_KBM_close.getText().toString());
        intent.putExtra(String.valueOf(R.string.KVS), koff_KVS_close.getText().toString());
        intent.putExtra(String.valueOf(R.string.KO), koff_KO_close.getText().toString());
        startActivity(intent);
    }
    //Инициализация
    private void init() {
        koff_BT_close = findViewById(R.id.koff_BT_close);
        koff_KM_close = findViewById(R.id.koff_KM_close);
        koff_KT_close  = findViewById(R.id.koff_KT_close);
        koff_KBM_close = findViewById(R.id.koff_KBM_close);
        koff_KO_close = findViewById(R.id.koff_KO_close);
        koff_KVS_close  = findViewById(R.id.koff_KVS_close);

        koff_BT_open = findViewById(R.id.koff_BT_open);
        koff_KM_open = findViewById(R.id.koff_KM_open);
        koff_KT_open = findViewById(R.id.koff_KT_open);
        koff_KBM_open = findViewById(R.id.koff_KBM_open);
        koff_KO_open = findViewById(R.id.koff_KO_open);
        koff_KVS_open = findViewById(R.id.koff_KVS_open);

        button_add = findViewById(R.id.button_add);

        city_button = findViewById(R.id.sity_button);
        textView_city = findViewById(R.id.textView_sity);
        power_button = findViewById(R.id.power_button);
        textView_power = findViewById(R.id.textView_power);
        driver_button = findViewById(R.id.driver_button);
        textView_driver = findViewById(R.id.textView_driver);
        old_button = findViewById(R.id.old_button);
        textView_old = findViewById(R.id.textView_old);
        min_staj_button = findViewById(R.id.min_staj_button);
        textView_min_staj = findViewById(R.id.textView_min_staj);
        crash_button = findViewById(R.id.crash_button);
        textView_crash = findViewById(R.id.textView_crash);

        image_View = findViewById(R.id.image_open_koff);
        layout = findViewById(R.id.setting_LL_open);
        coefficient_click = findViewById(R.id.coefficient_click);
    }
    //нажатие на поле для открытия шторки
    public void click_Coff(View view) {
        //открытие диалогового окна
        show_Dialog = new ShowDialog(this);
        show_Dialog.getShowDialog();
        switch (view.getId()){
            case R.id.LL_city:dataDialog(CITY_DIALOG);
                break;
            case R.id.LL_power:dataDialog(POWER_DIALOG);
                break;
            case R.id.LL_driver:dataDialog(DRIVER_DIALOG);
                break;
            case R.id.LL_old:dataDialog(OLD_DIALOG);
                break;
            case R.id.LL_min_staj:dataDialog(MIN_STAJ_DIALOG);
                break;
            case R.id.LL_crash:dataDialog(CRASH_DIALOG);
                break;
        }
    }
    //Метод для отображение информаций в диалоге
    public void dataDialog(int key){
        switch (key){
            case CITY_DIALOG: show_Dialog.setDataBodyDialog(city_button, textView_city,
                        R.string.hint_dialog_city, "crash", key);
                break;
            case POWER_DIALOG: show_Dialog.setDataBodyDialog(power_button, textView_power,
                        R.string.hint_dialog_power, "power", key);
                break;
            case DRIVER_DIALOG: show_Dialog.setDataBodyDialog(driver_button, textView_driver,
                        R.string.hint_dialog_drivers, "drivers", key);
                break;
            case OLD_DIALOG: show_Dialog.setDataBodyDialog(old_button, textView_old,
                        R.string.hint_dialog_old_drivers, "old", key);
                break;
            case MIN_STAJ_DIALOG: show_Dialog.setDataBodyDialog(min_staj_button, textView_min_staj,
                        R.string.hint_dialog_min_staj, "staj", key);
                break;
            case CRASH_DIALOG: show_Dialog.setDataBodyDialog(crash_button, textView_crash,
                        R.string.hint_dialog_no_accidents, "accidents", key);
                break;
        }
    }
    //получили данные и вставлем их
    public void addCoff(String BT, String KM, String KT, String KBM, String KVS, String KO){
        koff_BT_close.setText(BT);
        koff_KM_close.setText(KM);
        koff_KT_close.setText(KT);
        koff_KBM_close.setText(KBM);
        koff_KO_close.setText(KO);
        koff_KVS_close.setText(KVS);

        koff_BT_open.setText(BT);
        koff_KM_open.setText(KM);
        koff_KT_open.setText(KT);
        koff_KBM_open.setText(KBM);
        koff_KO_open.setText(KO);
        koff_KVS_open.setText(KVS);

        //если заполнены не все поля то кнопку не разблокируем
        if (!textView_city.getText().toString().equals("") && !textView_power.getText().toString().equals("")
                && !textView_driver.getText().toString().equals("") && !textView_min_staj.getText().toString().equals("")
                && !textView_crash.getText().toString().equals("")){
            button_add.setBackgroundResource(R.drawable.background_button_ready);
            button_add.setTextColor(Color.WHITE);
            button_add.setEnabled(true);
        }
    }
}