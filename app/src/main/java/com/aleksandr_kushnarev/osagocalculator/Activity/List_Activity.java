package com.aleksandr_kushnarev.osagocalculator.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandr_kushnarev.osagocalculator.AdapterInsurance;
import com.aleksandr_kushnarev.osagocalculator.Model.ArrayOffers;
import com.aleksandr_kushnarev.osagocalculator.Model.Branding;
import com.aleksandr_kushnarev.osagocalculator.Model.Offer;
import com.aleksandr_kushnarev.osagocalculator.OpenCoff;
import com.aleksandr_kushnarev.osagocalculator.R;
import com.aleksandr_kushnarev.osagocalculator.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Activity extends AppCompatActivity {

    Toolbar toolbar;

    TextView koff_BT_close,koff_KM_close,koff_KT_close,koff_KBM_close,koff_KO_close,koff_KVS_close
            ,koff_BT_open,koff_KM_open,koff_KT_open,koff_KBM_open,koff_KO_open,koff_KVS_open;

    RecyclerView recyclerView;
    List<Offer> offer_List;
    List<Branding> branding_List;

    ImageView imageView_list;
    LinearLayout layout_list, skilet_item;
    RelativeLayout coefficient_click_list;
    Button button_add_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
        getData();

        OpenCoff open_Coff = new OpenCoff(List_Activity.this, imageView_list, layout_list ,coefficient_click_list);
        open_Coff.getOpen();

        skilet_item.setVisibility(View.VISIBLE);

        RetrofitClient.getInstance().getApi().pushInsurance().enqueue(new Callback<ArrayOffers>() {
            @Override
            public void onResponse(Call<ArrayOffers> call, Response<ArrayOffers> response) {
                skilet_item.setVisibility(View.GONE);
                ArrayOffers example = response.body();
                button_add_list.setText(example.getActionText());

                offer_List = new ArrayList<>(Arrays.asList(example.getOffers()));

                LinearLayoutManager llm = new LinearLayoutManager(List_Activity.this);
                recyclerView.setLayoutManager(llm);
                AdapterInsurance adapterInsurance = new AdapterInsurance(offer_List, branding_List, List_Activity.this);
                recyclerView.setAdapter(adapterInsurance);
            }

            @Override
            public void onFailure(Call<ArrayOffers> call, Throwable t) {

            }
        });
    }

    //получаем коффициенты
    private void getData() {
        Intent intent = getIntent();
        koff_BT_close.setText(intent.getStringExtra("BT"));
        koff_KM_close.setText(intent.getStringExtra("KM"));
        koff_KT_close.setText(intent.getStringExtra("KT"));
        koff_KBM_close.setText(intent.getStringExtra("KBM"));
        koff_KO_close.setText(intent.getStringExtra("KO"));
        koff_KVS_close.setText(intent.getStringExtra("KVS"));

        koff_BT_open.setText(intent.getStringExtra("BT"));
        koff_KM_open.setText(intent.getStringExtra("KM"));
        koff_KT_open.setText(intent.getStringExtra("KT"));
        koff_KBM_open.setText(intent.getStringExtra("KBM"));
        koff_KO_open.setText(intent.getStringExtra("KO"));
        koff_KVS_open.setText(intent.getStringExtra("KVS"));
    }


    private void init() {
        recyclerView = findViewById(R.id.recycleView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.action_bar_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView_list = findViewById(R.id.image_open_koff_list);
        layout_list = findViewById(R.id.setting_LL_open_list);
        coefficient_click_list = findViewById(R.id.coefficient_click_list);

        koff_BT_close = findViewById(R.id.koff_BT_close_list);
        koff_KM_close = findViewById(R.id.koff_KM_close_list);
        koff_KT_close  = findViewById(R.id.koff_KT_close_list);
        koff_KBM_close = findViewById(R.id.koff_KBM_close_list);
        koff_KO_close = findViewById(R.id.koff_KO_close_list);
        koff_KVS_close  = findViewById(R.id.koff_KVS_close_list);

        koff_BT_open = findViewById(R.id.koff_BT_open_list);
        koff_KM_open = findViewById(R.id.koff_KM_open_list);
        koff_KT_open = findViewById(R.id.koff_KT_open_list);
        koff_KBM_open = findViewById(R.id.koff_KBM_open_list);
        koff_KO_open = findViewById(R.id.koff_KO_open_list);
        koff_KVS_open = findViewById(R.id.koff_KVS_open_list);

        button_add_list = findViewById(R.id.button_add_list);
        skilet_item = findViewById(R.id.skilet_item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            // ...
        }
        return super.onOptionsItemSelected(item);
    }


}