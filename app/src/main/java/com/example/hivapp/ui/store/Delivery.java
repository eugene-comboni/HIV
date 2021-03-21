package com.example.hivapp.ui.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hivapp.Dashboard;
import com.example.hivapp.Login;
import com.example.hivapp.R;
import com.example.hivapp.Session.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Delivery extends AppCompatActivity {
    TextView tvdetails, mname, mowner, mlocation, mprice, mdate, username, muid;
    ImageView imageView;
    String categoryy;
    TextInputEditText textInputEditText;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        if (Prevalent.currentOnlineUser == null) {
            startActivity(new Intent(this, Login.class));
        }
        loadingBar = new ProgressDialog(this);
        tvdetails = findViewById(R.id.deliverydes);
        mname = findViewById(R.id.delivelyname);
        mowner = findViewById(R.id.delivelyowner);
        mlocation = findViewById(R.id.delivelylocation);
        mprice = findViewById(R.id.delivelyprice);
        mdate = findViewById(R.id.delivelydate);
        username = findViewById(R.id.delivelyusername);
        muid = findViewById(R.id.delivelyuid);
        imageView = findViewById(R.id.delivelyimage);
        textInputEditText = findViewById(R.id.delivelylocation2);


        Intent intent = getIntent();
        categoryy = intent.getStringExtra("category");
        tvdetails.setText(categoryy);

        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String location = intent.getStringExtra("location");
        String owner = intent.getStringExtra("owner");
        String price = intent.getStringExtra("price");

        mname.setText("Medication Name :" + name);
        mowner.setText("Medication Distributor :" + owner);
        mlocation.setText("Distributor Location :" + location);
        mprice.setText("Medication Price :" + price);
        mdate.setText("Delivery Recorded On  :" + Prevalent.giveDate());
        Glide.with(this).load(image).into(imageView);
        username.setText("UserName Name:" + Prevalent.currentOnlineUser.getUsername());
        muid.setText("User Unique id :" + Prevalent.currentOnlineUser.getId());
/*
        if (categoryy.equals("Non-nucleoside Reverse Transcriptase Inhibitors")) {
            tvdetails.setText("-> Non-nucleoside reverse transcriptase inhibitors (NNRTIs) act by binding directly to the enzyme at a site different from the nucleoside binding component, thereby causing an allosteric inhibition of the transcriptase. \n->The three members of this group that are used clinically are nevirapine, delavirdine and efavirenz.");
        } else if (categoryy.equals("Nucleotide Reverse Transcriptase Inhibitors")) {
            tvdetails.setText("-> The nucleoside/nucleotide reverse transcriptase inhibitors (NRTIs) were the first class of antiretroviral drugs to be approved by the FDA.\n->NRTIs are taken as prodrugs and must be taken into the host cell and phosphorylated before they become active. \n->Once inside the host cell, cellular kinases will activate the drug. The drug exerts its effect through its structure. \n->NRTIs lack a 3’-hydroxyl group at the 2’-deoxyribosyl moiety and will have either a nucleoside or nucleotide as a base. \n->Due to the missing 3’hydroxyl group, the NRTI prevents the formation of a 3’-5’-phosphodiester bond in growing DNA chains and can thus prevent replication of the virus. \n->An interesting feature of these drugs is that their incorporation during RNA-dependent DNA or DNA-dependent DNA synthesis, which inhibits the production of either positive or negative strands of the DNA.");
        } else if (categoryy.equals("Protease Inhibitors")) {
            tvdetails.setText("->Protease inhibitors are one type of antiretroviral drug used to treat HIV. The goal of these drugs is to reduce the amount of HIV virus in the body (called the viral load) to levels that are undetectable. \n->This slows the progression of HIV and helps treat symptoms.");
        } else if (categoryy.equals("Integrase Inhibitors")) {
            tvdetails.setText("Integrase inhibitors (INIs) are a class of antiretroviral drug designed to block the action of integrase, a viral enzyme that inserts the viral genome into the DNA of the host cell. Since integration is a vital step in retroviral replication, blocking it can halt further spread of the virus.");
        } else if (categoryy.equals("Fusion Inhibitors")) {
            tvdetails.setText("Entry inhibitors, also known as fusion inhibitors, are a class of antiviral drugs that prevent a virus from entering a cell, for example, by blocking a receptor. Entry inhibitors are used to treat conditions such as HIV ");
        } else if (categoryy.equals("gp120 Attachment Inhibitor")) {
            tvdetails.setText("Attachment inhibitors are a class of drugs that bind to the gp120 protein on the outer surface of HIV, preventing HIV from binding to and entering CD4 T lymphocytes (CD4 cells). Attachment inhibitors are part of a larger class of HIV drugs called entry inhibitors.");
        } else if (categoryy.equals("CCR5 Antagonist")) {
            tvdetails.setText("CCR5 co-receptor antagonists prevent HIV-1 from entering and infecting immune cells by blocking CCR5 cell-surface receptor. Small molecule antagonists of CCR5 bind to a hydrophobic pocket formed by the transmembrane helices of the CCR5 receptor.");
        }
*/


    }

    public void SendRequest(View view) {
        String mylocation = textInputEditText.getText().toString().trim();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String location = intent.getStringExtra("location");
        String owner = intent.getStringExtra("owner");
        String price = intent.getStringExtra("price");
        String username = Prevalent.currentOnlineUser.getUsername();
        String muid = Prevalent.currentOnlineUser.getId();
        String mdate = Prevalent.giveDate();

        if (TextUtils.isEmpty(mylocation)) {
            textInputEditText.setError("Location is required!!");
            return;
        } else {
            loadingBar.setTitle("Create Delivery");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    com.example.hivapp.Model.Delivery delivery = new com.example.hivapp.Model.Delivery(name, image, location, owner, mdate, price, username, muid, mylocation);
                    String dd = Prevalent.Date();
                    String uniqueid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    RootRef.child("MyDeliveries").child(muid).child(mylocation).setValue(delivery)
                            .addOnCompleteListener(task -> {
                                Toast.makeText(Delivery.this, "Congratulations " + username + " , your delivery has been created.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(Delivery.this, Dashboard.class);
                                startActivity(intent);
                            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }


}