package com.aleksandr_kushnarev.osagocalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView koff_BT_close,koff_KM_close
            ,koff_KT_close,koff_KBM_close
            ,koff_KO_close,koff_KVS_close;

    ShowDialog showDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация
        Init();

        //открытие плашки коффициентов
       OpenCoff openCoff = new OpenCoff(MainActivity.this);
       openCoff.getOpen();

       //открытие диалогового окна
       showDialog = new ShowDialog(MainActivity.this);
    }

    //Инициализация
    private void Init() {
        koff_BT_close = findViewById(R.id.koff_BT_close);
        koff_KM_close = findViewById(R.id.koff_KM_close);
        koff_KT_close  = findViewById(R.id.koff_KT_close);
        koff_KBM_close = findViewById(R.id.koff_KBM_close);
        koff_KO_close = findViewById(R.id.koff_KO_close);
        koff_KVS_close  = findViewById(R.id.koff_KVS_close);
    }

    //нажатие для открития шторки
    public void Click_Coff(View view) {
        showDialog.GetShowDialog();

        switch (view.getId()){
            case R.id.LL_city:showDialog.ADD_CITY_DIALOG();//наполнение диалога информацией
                break;
            case R.id.LL_power:showDialog.ADD_POWER_DIALOG();
                break;
            case R.id.LL_driver:showDialog.ADD_DRIVER_DIALOG();
                break;
            case R.id.LL_old:showDialog.ADD_OLD_DIALOG();
                break;
            case R.id.LL_min_staj:showDialog.ADD_MIN_STAJ_DIALOG();
                break;
            case R.id.LL_crash:showDialog.ADD_CRASH_DIALOG();
                break;
        }
    }

}