package com.harsh1310.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddnotesActivity extends AppCompatActivity {
DatabaseReference notesref;
EditText title,desc,inputdate;
ImageButton back,undo,redo,delete,save,mic,textword,img;
ConstraintLayout constraintLayout;
String str;
@SuppressLint("ResourceAsColor")
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
    Intent intent = getIntent();
    str = intent.getStringExtra("Msg");
       title=findViewById(R.id.inputtitle);
       desc=findViewById(R.id.inputcontent);
      back=findViewById(R.id.back);
       save=findViewById(R.id.save);
       undo=findViewById(R.id.undo);
       redo=findViewById(R.id.redo);
       img=findViewById(R.id.img);
       textword=findViewById(R.id.textword);
       mic=findViewById(R.id.mic);
       constraintLayout=findViewById(R.id.constlayout);
back.setOnClickListener(v->backbut());

    if(str.contains("#FFC158")) {
        constraintLayout.setBackgroundColor(Color.parseColor("#FFC158"));
        Log.d("check",str);
    }
    else if(str.contains("#01BFF9"))
        constraintLayout.setBackgroundColor(Color.parseColor("#01BFF9"));
    else if(str.contains("#24DA6D"))
        constraintLayout.setBackgroundColor(Color.parseColor("#24DA6D"));
    else if(str.contains("#FF588A"))
        constraintLayout.setBackgroundColor(Color.parseColor("#FF588A"));
    else if(str.contains("#7958FF"))constraintLayout.setBackgroundColor(Color.parseColor("#7958FF"));

    else
        constraintLayout.setBackgroundColor(Color.parseColor("#FF5858"));

    notesref= FirebaseDatabase.getInstance().getReference().child("Notesref");
      save.setOnClickListener(v->upload());
    }

    private void backbut() {
    finish();
    }

    private void upload() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        Modelclass model=new Modelclass(title.getText().toString(),desc.getText().toString(),strDate,str);
    notesref.push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
    }
});
}
}