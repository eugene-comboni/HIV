package com.example.hivapp.ui.book;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hivapp.Dashboard;
import com.example.hivapp.Login;
import com.example.hivapp.Model.Appointment;
import com.example.hivapp.NetworkCheck.Netcheck;
import com.example.hivapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.hivapp.Session.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

public class BookFragment extends Fragment {

    TextInputEditText inputname, userapp, description, inputphone, adress;
    private TextInputEditText datee;
    private TextInputEditText description_tv;
    Button saveAppointment;

    private static int TIME_OUT = 8000;
    ProgressDialog pDialog;
    private String date, time = "";
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    String username, location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_book, container, false);

        pDialog = new ProgressDialog(getActivity());

        inputname = layout.findViewById(R.id.edt_app_name);
        adress = layout.findViewById(R.id.edt_book_user_phone);
        userapp = layout.findViewById(R.id.edt_user_owner);
        description_tv = layout.findViewById(R.id.edt_app_description);
        saveAppointment = layout.findViewById(R.id.edt_btn_save1);
        pDialog = new ProgressDialog(getActivity());

        String name = "Dr. Joan T. Maitha";

        inputname.setText(name);
        inputname.setEnabled(false);
        String phone = "+254719124578";
        inputphone = layout.findViewById(R.id.edt_book_consu_phone);
        inputphone.setText(phone);
        inputphone.setEnabled(false);

        if (Prevalent.currentOnlineUser == null) {
            FancyToast.makeText(getActivity(), "No user Logged In", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
        } else {
            username = Prevalent.currentOnlineUser.getUsername();
            userapp.setEnabled(false);
            userapp.setText(username);
            location = "Nyeri";
            adress.setText(location);
            adress.setEnabled(false);

            saveAppointment.setOnClickListener(v -> {
                SaveAppointment();
            });

        }


        datee = layout.findViewById(R.id.edt_booking_date);
        datee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        // Toast.makeText(Patient_BookAppointmentActivity.this, date , Toast.LENGTH_SHORT).show();
                        datee.setText(date);
                        onStart();


                    }
                }, day, month, year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });
        return layout;
    }

    public void SaveAppointment() {


        String cname1 = "Dr Joan T Maitha";
        String cphone = "+254719124578";
        String uname = Prevalent.currentOnlineUser.getUsername();
        String uphone = "+254720256987";
        String udescri = description_tv.getText().toString().trim();
        String udate = datee.getText().toString();
        String image = Prevalent.currentOnlineUser.getImageURL();
        String id = Prevalent.currentOnlineUser.getId();

        if (TextUtils.isEmpty(udescri)) {
            description_tv.setError("description is required!!");
            return;
        } else if (TextUtils.isEmpty(udate)) {
            datee.setError("Date is required!!");
        }else
        {
            pDialog.setTitle("Updating Account");
            pDialog.setMessage("Please wait, while we are checking the credentials.");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();
            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (!(dataSnapshot.child("Appointments").child(id).child(uname).exists())) {
                        Appointment appointment = new Appointment(cname1, cphone, uname, uphone, udescri, udate, image, "Pending");
                        RootRef.child("Appointments").child(id).child(udescri).setValue(appointment)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull final Task<Void> task) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // This method will be executed once the timer is over
                                                if (Netcheck.internetChack(getActivity())) {

                                                    if (task.isSuccessful()) {
                                                        FancyToast.makeText(getActivity(), "Congratulations your Appointment has been created.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                                        pDialog.dismiss();
                                                        SetLayout(udescri, udate);
                                                        startActivity(new Intent(getActivity(), Dashboard.class));

                                                    } else {
                                                        pDialog.dismiss();
                                                        FancyToast.makeText(getActivity(), "Network Error: Please try again after some time...", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                                    }

                                                } else {
                                                    AlertDialog.Builder builder;
                                                    builder = new AlertDialog.Builder(getActivity());
                                                    //Setting message manually and performing action on button click
                                                    builder.setMessage("Please Check Your Internet Connection")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    getActivity().finish();
                                                                }
                                                            });
                                                    //Creating dialog box
                                                    AlertDialog alert = builder.create();
                                                    alert.show();

                                                }
                                            }
                                        }, TIME_OUT);

                                    }
                                });

                    } else {
                        FancyToast.makeText(getActivity(), "Appointment already exists.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        pDialog.dismiss();
                        FancyToast.makeText(getActivity(), "Please try again using another date.", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();


                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    FancyToast.makeText(getActivity(), String.valueOf(databaseError), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    pDialog.dismiss();
                }
            });
        }

    }

    private void SendToDatabase(final String cname, final String cphone, final String uname, final String uphone, final String udescri, final String udate, final String image) {

    }

    private void SetLayout(String udescri, String udate) {
        datee.setText(udate);
        datee.setEnabled(false);

        description_tv.setEnabled(false);
        description_tv.setText(udescri);
    }
}