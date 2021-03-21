package com.example.hivapp.ui.Info;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hivapp.R;

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    TextView textView;
    public Button yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        no = (Button) findViewById(R.id.dialogdismiss);
        textView = findViewById(R.id.dialogshow);
//        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        //setImage(ContextCompat.getDrawable(c, R.drawable.ic_menu_bus));
    }

    public void setMessage(String message){
        textView.setText(message);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dialogdismiss:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
