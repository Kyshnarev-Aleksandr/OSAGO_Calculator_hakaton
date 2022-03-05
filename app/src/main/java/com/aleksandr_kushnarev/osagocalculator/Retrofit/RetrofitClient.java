package com.aleksandr_kushnarev.osagocalculator.Retrofit;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import com.aleksandr_kushnarev.osagocalculator.Model.ArrayHeader;
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


}
