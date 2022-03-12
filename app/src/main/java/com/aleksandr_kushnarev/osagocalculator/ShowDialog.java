package com.aleksandr_kushnarev.osagocalculator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.Activity.MainActivity;
import com.aleksandr_kushnarev.osagocalculator.Retrofit.CallRetrofit;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;

public class ShowDialog {

    Context context;
    BottomSheetDialog Mydialog;
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

        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.bottom_sheet_item, null);
        Mydialog = new BottomSheetDialog(context, R.style.BottomSheetDialog); // Style here
        RelativeLayout bottomDialog = Mydialog.findViewById(R.id.sheet_conteiner);

        textView_head_dialog = dialogView.findViewById(R.id.text_head_dialog);
        button_Dialog_next = dialogView.findViewById(R.id.button_next);
        edit_Text = dialogView.findViewById(R.id.edit_TT);
        imageView_delete_text = dialogView.findViewById(R.id.image_delete_text);
        image_go_back_dialog = dialogView.findViewById(R.id.image_go_back_dialog);
        button_Dialog_next.setText(R.string.Button_dialog_next);
        Mydialog.setContentView(dialogView);
        Mydialog.show();

        //обязательный запрос при скрытий диалога
        Mydialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
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
                    Mydialog.dismiss();
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
