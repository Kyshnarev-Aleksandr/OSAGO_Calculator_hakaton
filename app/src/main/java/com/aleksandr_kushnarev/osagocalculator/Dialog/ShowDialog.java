package com.aleksandr_kushnarev.osagocalculator.Dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.Activity.MainActivity;
import com.aleksandr_kushnarev.osagocalculator.R;
import com.aleksandr_kushnarev.osagocalculator.Retrofit.CallRetrofit;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;

public class ShowDialog {

    Context context;
    BottomSheetDialog My_dialog;
    TextView textView_head_dialog;
    Button button_Dialog_next;
    ImageButton image_go_back_dialog;
    EditText edit_Text;
    ImageView imageView_delete_text;

    HashMap<String, String> map = new HashMap<String, String>();
    CallRetrofit call_Retrofit = new CallRetrofit();

    static final int MAX_METHOD = 6;
    static final int MIN_METHOD = 1;
    static final int NEXT_METHOD = 1;
    static final int SIZE = 12;
    static final int PADDING = 0;

    public ShowDialog(Context context) {
        this.context = context;
    }
    //Создание диалога
    public void getShowDialog(){

        View dialog_View = ((Activity)context).getLayoutInflater().inflate(R.layout.bottom_sheet_item, null);
        My_dialog = new BottomSheetDialog(context, R.style.BottomSheetDialog); // Style here
        RelativeLayout bottom_Dialog = My_dialog.findViewById(R.id.sheet_conteiner);

        textView_head_dialog = dialog_View.findViewById(R.id.text_head_dialog);
        button_Dialog_next = dialog_View.findViewById(R.id.button_next);
        edit_Text = dialog_View.findViewById(R.id.edit_TT);
        imageView_delete_text = dialog_View.findViewById(R.id.image_delete_text);
        image_go_back_dialog = dialog_View.findViewById(R.id.image_go_back_dialog);
        button_Dialog_next.setText(R.string.Button_dialog_next);
        My_dialog.setContentView(dialog_View);
        My_dialog.show();

        //обязательный запрос при скрытий диалога
        My_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                call_Retrofit.getDataCoff(context, map);
            }
        });

        imageView_delete_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_Text.setText("");
            }
        });
    }

    //Вставляем данные в поля диалога
    public void setDataBodyDialog(TextView text_button, TextView textView,
                                  int hint, String key_map, int next_key){
        textView_head_dialog.setText(text_button.getText().toString());
        edit_Text.setHint(hint);
        if(!textView.equals("")){
            edit_Text.setText(textView.getText().toString());
        }

            //если последний метод то текст на кнопке подтвердить
        if (next_key == MAX_METHOD){
            button_Dialog_next.setText(R.string.text_button_dialog);
        }

        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_button.setTextSize(SIZE);
                text_button.setPadding(PADDING,PADDING,PADDING,PADDING);
                textView.setVisibility(View.VISIBLE);
                textView.setText(edit_Text.getText().toString());
                map.put(key_map, edit_Text.getText().toString());
                edit_Text.setText("");
                if (next_key < MAX_METHOD){
                    ((MainActivity)context).dataDialog(next_key + NEXT_METHOD);
                }else {
                    My_dialog.dismiss();
                    call_Retrofit.getDataCoff(context, map);
                }
            }
        });
        image_go_back_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (next_key > MIN_METHOD) ((MainActivity)context).dataDialog(next_key - NEXT_METHOD);
            }
        });
    }
}
