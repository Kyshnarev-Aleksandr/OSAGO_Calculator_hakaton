package com.aleksandr_kushnarev.osagocalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView imageView_open_coff;
    LinearLayout layout;
    RelativeLayout coefficient_click;

    //если закрыто
    boolean close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        close = true;
        imageView_open_coff = findViewById(R.id.image_open_koff);
        layout = findViewById(R.id.setting_LL_open);
        coefficient_click = findViewById(R.id.coefficient_click);

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
}