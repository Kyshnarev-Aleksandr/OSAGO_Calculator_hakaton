package com.aleksandr_kushnarev.osagocalculator.Retrofit;

import com.aleksandr_kushnarev.osagocalculator.Model.ArrayHeader;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("rationDetail")
    Call<ArrayHeader> createUser(@FieldMap Map<String, String> params);
}
