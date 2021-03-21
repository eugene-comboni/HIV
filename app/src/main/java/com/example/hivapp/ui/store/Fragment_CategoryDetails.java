package com.example.hivapp.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hivapp.Model.Product;
import com.example.hivapp.Session.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.hivapp.R;

public class Fragment_CategoryDetails extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView category_rv;
    String category;
    TextView tv, tvdetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_details, container, false);
        category = getArguments().getString("category");
        tv = view.findViewById(R.id.testdetails);
        tvdetails = view.findViewById(R.id.describecategory);
        tv.setText(category);

        if (category.equals("Non-nucleoside Reverse Transcriptase Inhibitors")) {
            tvdetails.setText("These are also called non-nukes.NNRTIs bind to a specific protein so the HIV virus can't make copies of itself, similar to jamming a zipper.");
        } else if (category.equals("Nucleotide Reverse Transcriptase Inhibitors")) {
            tvdetails.setText("NRTIs force the HIV virus to use faulty versions of building blocks so infected cells can't make more HIV.");
        } else if (category.equals("Protease Inhibitors")) {
            tvdetails.setText("These drugs block a protein that infected cells need to put together new HIV virus particles.");
        } else if (category.equals("Integrase Inhibitors")) {
            tvdetails.setText("These stop HIV from making copies of itself by blocking a key protein that allows the virus to put its DNA into the healthy cell's DNA. They're also called integrase strand transfer inhibitors (INSTIs).");
        } else if (category.equals("Fusion Inhibitors")) {
            tvdetails.setText("Unlike NRTIs, NNRTIs, PIs, and INSTIs -- which work on infected cells -- these drugs help block HIV from getting inside healthy cells in the first place.");
        } else if (category.equals("gp120 Attachment Inhibitor")) {
            tvdetails.setText("This is a new class of drug with currently just one medication, fostemsavir (Rukobia). It is for adults who have tried multiple HIV medications and whose HIV has been resistant to current available therapies. It targets the glycoprotein 120 on the surface of the virus, stopping it from being able to attach itself to the CD4 T-cells of your body’s immune system. ");
        } else if (category.equals("CCR5 Antagonist")) {
            tvdetails.setText("Maraviroc, or MVC (Selzentry), also stops HIV before it gets inside a healthy cell, but in a different way than fusion inhibitors. It blocks a specific kind of \"hook\" on the outside of certain cells so the virus can't plug in.");
        }


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Store");
        category_rv = view.findViewById(R.id.category_details_recyclerview);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        category_rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getCAtegoryDetails(category);

        return view;
    }

    private void getCAtegoryDetails(String category) {

        FirebaseRecyclerOptions<Product> option = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(mDatabase.child(category), Product.class)
                .build();

        FirebaseRecyclerAdapter<Product, ApplicationViewHolder> adapter = new FirebaseRecyclerAdapter<Product, ApplicationViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull final ApplicationViewHolder holder, int position, @NonNull Product model) {

                Glide.with(getActivity()).load(model.getImage()).into(holder.pimage);
                holder.ratingBar.setRating(Prevalent.randFloat(1.0f, 5.0f));
                holder.name.setText(model.getName());
                holder.price.setText("Price : "+model.getPrice());

                holder.proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Delivery.class);
                        intent.putExtra("name", model.getName());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("location", model.getLocation());
                        intent.putExtra("owner", model.getOwner());
                        intent.putExtra("price", model.getPrice());
                        intent.putExtra("category",category);
                        startActivity(intent);
                    }
                });

            }


            @NonNull
            @Override
            public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
                return new ApplicationViewHolder(view);
            }
        };
        category_rv.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        KenBurnsView pimage;
        RatingBar ratingBar;
        Button proceed;


        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.edt_product_name);
            pimage = itemView.findViewById(R.id.edt_product_image);
            price = itemView.findViewById(R.id.edt_product_price);
            ratingBar = itemView.findViewById(R.id.edt_product_rate);
            proceed = itemView.findViewById(R.id.edt_product_btn);


        }


    }
}
