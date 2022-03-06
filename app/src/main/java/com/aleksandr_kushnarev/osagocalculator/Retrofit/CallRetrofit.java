package com.aleksandr_kushnarev.osagocalculator.Retrofit;

import android.app.Activity;

import com.aleksandr_kushnarev.osagocalculator.Activity.MainActivity;
import com.aleksandr_kushnarev.osagocalculator.Model.ArrayHeader;
import com.aleksandr_kushnarev.osagocalculator.Model.Factor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {

    private static final Integer Position_BT_COFF = 0;
    private static final Integer Position_KM_COFF = 1;
    private static final Integer Position_KT_COFF = 2;
    private static final Integer Position_KBM_COFF = 3;
    private static final Integer Position_KVS_COFF = 4;
    private static final Integer Position_KO_COFF = 5;

    public void getDataCoff(Activity activity, Map<String, String> map){

        RetrofitClient.getInstance().getApi().pushCoff(map).enqueue(new Callback<ArrayHeader>() {
            @Override
            public void onResponse(Call<ArrayHeader> call, Response<ArrayHeader> response) {
                if (response.isSuccessful()){
                    ArrayHeader array_Header = response.body();
                    List<Factor> list = new ArrayList<>(Arrays.asList(array_Header.getFactors()));

                    String BT = list.get(Position_BT_COFF).getHeaderValue();
                    String KM = list.get(Position_KM_COFF).getHeaderValue();
                    String KT = list.get(Position_KT_COFF).getHeaderValue();
                    String KBM = list.get(Position_KBM_COFF).getHeaderValue();
                    String KVS = list.get(Position_KVS_COFF).getHeaderValue();
                    String KO = list.get(Position_KO_COFF).getHeaderValue();

                    ((MainActivity)activity).addCoff(BT,KM,KT,KBM,KVS,KO);
                }

            }

            @Override
            public void onFailure(Call<ArrayHeader> call, Throwable t) {

            }
        });



    }


}
