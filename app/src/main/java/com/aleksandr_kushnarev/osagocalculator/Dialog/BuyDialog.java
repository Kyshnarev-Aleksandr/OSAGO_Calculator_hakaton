package com.aleksandr_kushnarev.osagocalculator.Dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.R;
import com.aleksandr_kushnarev.osagocalculator.SVGLoader;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BuyDialog {

    BottomSheetDialog dialog;

    public void createDialog(Context context,String name, String rating, String sum, String url){

        View dialog_View = ((Activity)context).getLayoutInflater().inflate(R.layout.buy_dialog_item, null);
        dialog = new BottomSheetDialog(context, R.style.BottomSheetDialog); // Style here
        RelativeLayout bottom_Dialog = dialog.findViewById(R.id.buy_dialog_con);

        ImageView image_View = dialog_View.findViewById(R.id.imageView_item_dialog_insurance);
        TextView text_View_Name = dialog_View.findViewById(R.id.textView_item_name_insurance);
        TextView text_View_Rating = dialog_View.findViewById(R.id.textView_item_rating_insurance);
        TextView text_View_Sum = dialog_View.findViewById(R.id.item_textView_Sum_insurance);
        Button button_ready = dialog_View.findViewById(R.id.button_ready);

        text_View_Name.setText(name);
        text_View_Rating.setText(rating);
        text_View_Sum.setText(sum);
        SVGLoader.getSVG(image_View, url);

        dialog.setContentView(dialog_View);
        dialog.show();

        button_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
