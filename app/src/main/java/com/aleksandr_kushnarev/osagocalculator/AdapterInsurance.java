package com.aleksandr_kushnarev.osagocalculator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aleksandr_kushnarev.osagocalculator.Model.Branding;
import com.aleksandr_kushnarev.osagocalculator.Model.Offer;

import java.util.List;

public class AdapterInsurance extends RecyclerView.Adapter<AdapterInsurance.ViewHolder> {

    List<Offer> offer_List;
    Context context;

    public AdapterInsurance(List<Offer> offerList, Context context) {
        this.offer_List = offerList;
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
        Branding branding = offer.getBranding();
        holder.name.setText(offer.getName());
        holder.rating.setText(String.valueOf(offer.getRating()));
        holder.sum.setText(String.valueOf(offer.getPrice()) + " P");
        String url = branding.getBankLogoUrlSVG();
        SVGLoader.getSVG(holder.imageView_item, url);
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
