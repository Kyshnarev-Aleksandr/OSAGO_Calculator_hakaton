package com.aleksandr_kushnarev.osagocalculator.Retrofit;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import com.aleksandr_kushnarev.osagocalculator.Model.Example;
import com.aleksandr_kushnarev.osagocalculator.Model.Factor;
import com.aleksandr_kushnarev.osagocalculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://mock.sravni-team.ru/mobile/internship/v1/osago/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private static final Integer Position_BT_COFF = 0;
    private static final Integer Position_KM_COFF = 1;
    private static final Integer Position_KT_COFF = 2;
    private static final Integer Position_KBM_COFF = 3;
    private static final Integer Position_KVS_COFF = 4;
    private static final Integer Position_KO_COFF = 5;


    private  RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized  RetrofitClient getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

    public void GetDataCoff(Activity activity , Map<String, String> map){

        RetrofitClient.getInstance().getApi().createUser(map).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                TextView BT_close = activity.findViewById(R.id.koff_BT_close);
                TextView KM_close = activity.findViewById(R.id.koff_KM_close);
                TextView KT_close  = activity.findViewById(R.id.koff_KT_close);
                TextView KBM_close = activity.findViewById(R.id.koff_KBM_close);
                TextView KO_close = activity.findViewById(R.id.koff_KO_close);
                TextView KVS_close  = activity.findViewById(R.id.koff_KVS_close);
                TextView BT_open = activity.findViewById(R.id.BT_sum);
                TextView KM_open = activity.findViewById(R.id.KM_cum);
                TextView KT_open = activity.findViewById(R.id.KT_cum);
                TextView KBM_open = activity.findViewById(R.id.KBM_cum);
                TextView KO_open = activity.findViewById(R.id.KO_sum);
                TextView KVS_open = activity.findViewById(R.id.KVS_sum);
                Button button_Add = activity.findViewById(R.id.button_add);

                if (response.isSuccessful()){
                    Example example = response.body();
                    List<Factor> list = new ArrayList<>(Arrays.asList(example.getFactors()));

                    BT_close.setText(list.get(Position_BT_COFF).getHeaderValue());
                    KM_close.setText(list.get(Position_KM_COFF).getHeaderValue());
                    KT_close.setText(list.get(Position_KT_COFF).getHeaderValue());
                    KBM_close.setText(list.get(Position_KBM_COFF).getHeaderValue());
                    KVS_close.setText(list.get(Position_KVS_COFF).getHeaderValue());
                    KO_close.setText(list.get(Position_KO_COFF).getHeaderValue());

                    BT_open.setText(list.get(Position_BT_COFF).getValue());
                    KM_open.setText(list.get(Position_KM_COFF).getValue());
                    KT_open.setText(list.get(Position_KT_COFF).getValue());
                    KBM_open.setText(list.get(Position_KBM_COFF).getValue());
                    KVS_open.setText(list.get(Position_KVS_COFF).getValue());
                    KO_open.setText(list.get(Position_KO_COFF).getValue());

                    button_Add.setBackgroundResource(R.drawable.background_button_ready);
                    button_Add.setTextColor(Color.WHITE);
                    button_Add.setClickable(true);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

}
