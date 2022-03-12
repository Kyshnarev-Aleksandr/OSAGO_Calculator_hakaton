package com.aleksandr_kushnarev.osagocalculator;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class OpenCoff {

    ImageView image_View;
    LinearLayout layout;
    RelativeLayout coefficient_click;
    Boolean close = true;

    public OpenCoff(ImageView image_View, LinearLayout layout, RelativeLayout coefficient_click) {
        this.image_View = image_View;
        this.layout = layout;
        this.coefficient_click = coefficient_click;
    }
    public void getOpen(){
        //нажатие на картинку для открытия коффициента
        image_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (close == true){
                    close = false;
                    layout.setVisibility(View.VISIBLE);
                    image_View.setImageResource(R.drawable.ic_up);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative);
                }else {
                    close = true;
                    layout.setVisibility(View.GONE);
                    image_View.setImageResource(R.drawable.ic_down);
                    coefficient_click.setBackgroundResource(R.drawable.backgraund_relative_close);
                }
            }
        });
    }
}
