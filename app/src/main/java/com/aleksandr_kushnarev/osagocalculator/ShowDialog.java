package com.aleksandr_kushnarev.osagocalculator;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.Activity.MainActivity;
import com.aleksandr_kushnarev.osagocalculator.Retrofit.CallRetrofit;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;

public class ShowDialog {

    Activity activity;
    BottomSheetDialog bottomSheet;
    View sheetView;
    TextView textView_head_dialog;
    Button button_Dialog_next;
    ImageButton image_go_back_dialog;
    EditText editText;
    ImageView imageView_delete_text;
    ArrayAdapter<String> adapter;
    ListView listView;

    HashMap<String, String> map = new HashMap<String, String>();
    CallRetrofit callRetrofit = new CallRetrofit();

    static final int MAX_METHOD = 6;
    static final int MIN_METHOD = 1;
    static final int NEXT_METHOD = 1;
    static final int SIZE = 12;
    static final int PADDING = 0;

    public ShowDialog(Activity activity) {
        this.activity = activity;
    }
    //Создание диалога
    public void getShowDialog(){
        bottomSheet = new BottomSheetDialog(activity, R.style.BottomSheetDialogTheme);
        sheetView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(R.layout.bottom_sheet_item, (LinearLayout)activity.findViewById(R.id.sheet_conteiner));

        textView_head_dialog = sheetView.findViewById(R.id.text_head_dialog);
        button_Dialog_next = sheetView.findViewById(R.id.button_next);
        editText = sheetView.findViewById(R.id.edit_TT);
        imageView_delete_text = sheetView.findViewById(R.id.image_delete_text);
        image_go_back_dialog = sheetView.findViewById(R.id.image_go_back_dialog);

        button_Dialog_next.setText(R.string.Button_dialog_next);

        listView = sheetView.findViewById(R.id.listView);
        bottomSheet.setContentView(sheetView);
        bottomSheet.show();

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
    }

    //Вставляем данные в поля диалога
    public void setDataBodyDialog(TextView text_button, TextView textView, String[] stringArray,
                                  int hint, String key_map, int next_key){
        textView_head_dialog.setText(text_button.getText().toString());
        editText.setHint(hint);

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(adapter);

        if (next_key == MAX_METHOD){
            button_Dialog_next.setText(R.string.text_button_dialog);
        }
        button_Dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_button.setTextSize(SIZE);
                text_button.setPadding(PADDING,PADDING,PADDING,PADDING);
                textView.setVisibility(View.VISIBLE);
                textView.setText(editText.getText().toString());
                map.put(key_map, editText.getText().toString());
                callRetrofit.getDataCoff(activity, map);
                editText.setText("");
                if (next_key < MAX_METHOD){
                    ((MainActivity)activity).dataDialog(next_key + NEXT_METHOD);
                }else {
                    bottomSheet.dismiss();
                }
            }
        });
        image_go_back_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (next_key > MIN_METHOD) ((MainActivity)activity).dataDialog(next_key - NEXT_METHOD);
            }
        });
    }
}
