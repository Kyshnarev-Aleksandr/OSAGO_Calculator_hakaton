package com.aleksandr_kushnarev.osagocalculator;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aleksandr_kushnarev.osagocalculator.Model.Branding;
import com.aleksandr_kushnarev.osagocalculator.Model.Offer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterInsurance extends RecyclerView.Adapter<AdapterInsurance.ViewHolder> {

    List<Offer> offer_List;
    List<Branding> branding_List;
    Context context;

    public AdapterInsurance(List<Offer> offerList, List<Branding> brandingList, Context context) {
        this.offer_List = offerList;
        this.branding_List = brandingList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterInsurance.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.insurance_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInsurance.ViewHolder holder, int position) {
        Offer offer = offer_List.get(position);

        branding_List = new ArrayList<>(Arrays.asList(offer.getBranding()));
        Branding branding = branding_List.get(0);

        holder.name.setText(offer.getName());
        holder.rating.setText(String.valueOf(offer.getRating()));
        holder.sum.setText(String.valueOf(offer.getPrice()) + " P");

        //Переделаю, найду нормальную библиотеку для загрузки SVG!
        Glide.with(context).load("https://s44751.cos.ngenix.net/d0b31aa9-9912-4497-bc13-db50eab90b1f.svg").into(holder.imageView_item);

        //Picasso.get().load("https://avatars.mds.yandex.net/i?id=355ba6a774a9fdc4f10e699ecc5cab7c-5869368-images-thumbs&n=13").into(holder.imageView_item);
    }

    @Override
    public int getItemCount() {
        return offer_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_item, image_star;
        TextView name, rating, sum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_star = itemView.findViewById(R.id.Image_star);
            imageView_item = itemView.findViewById(R.id.imageView_item);

            name = itemView.findViewById(R.id.textView_item_name);
            rating = itemView.findViewById(R.id.textView_item_rating);
            sum = itemView.findViewById(R.id.item_textView_Sum);

        }
    }
}
