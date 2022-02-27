package com.aleksandr_kushnarev.osagocalculator;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class OpenCoff {

    Activity activity;
    ImageView imageView;
    LinearLayout layout;
    RelativeLayout coefficient_click;

    Boolean close = true;

    public OpenCoff(Activity context) {
        this.activity = context;
    }

    public void getOpen(){
        //инициализация
        imageView = activity.findViewById(R.id.image_open_koff);
        layout = activity.findViewById(R.id.setting_LL_open);
        coefficient_click = activity.findViewById(R.id.coefficient_click);

        //нажатие на картинку для открытия коффициента
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (close == true){
                    close = false;
                    layout.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.ic_up);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative);
                }else {
                    close = true;
                    layout.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.ic_down);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative_close);
                }
            }
        });


    }



}
