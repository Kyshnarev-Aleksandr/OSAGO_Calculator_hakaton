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

    public OpenCoff(Activity activity, ImageView imageView, LinearLayout layout, RelativeLayout coefficient_click) {
        this.activity = activity;
        this.imageView = imageView;
        this.layout = layout;
        this.coefficient_click = coefficient_click;
    }

    public void getOpen(){
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
