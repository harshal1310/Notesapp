package com.harsh1310.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Adapterclass adapter;
    FloatingActionButton buttonaddnote,blue,orange,red,sky,green,pink;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    ArrayList<Modelclass>arrayList=new ArrayList<>();
    FirebaseRecyclerOptions<Modelclass> options;
    DatabaseReference notesref;
    TextView welcome ,haveniceday;

            RecyclerView rview;
            boolean isclick=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
  rview=findViewById(R.id.Notesview);
        buttonaddnote=findViewById(R.id.addbut);
        welcome=findViewById(R.id.welcome);
        haveniceday=findViewById(R.id.haveniceday);
        red=findViewById(R.id.red);
        orange=findViewById(R.id.orange);
        sky=findViewById(R.id.sky);
        blue=findViewById(R.id.blue);
        green=findViewById(R.id.green);
        pink=findViewById(R.id.pink);
        red.setVisibility(View.INVISIBLE);
        green.setVisibility(View.INVISIBLE);
        pink.setVisibility(View.INVISIBLE);
        orange.setVisibility(View.INVISIBLE);
        sky.setVisibility(View.INVISIBLE);
        blue.setVisibility(View.INVISIBLE);

        notesref = FirebaseDatabase.getInstance().getReference().child("Notesref");
        buttonaddnote.setOnClickListener(v->addnote());
        options=new FirebaseRecyclerOptions.Builder<Modelclass>().setQuery(notesref,Modelclass.class).build();
        setuprecyclerview();

    }

    private void setuprecyclerview() {
        rview.setLayoutManager(new GridLayoutManager(this,2));
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(mLayoutManager);
        //rview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapterclass(options,getApplicationContext());
        adapter.notifyDataSetChanged();
        rview.setAdapter( adapter);
    }

    private void addnote() {
        isclick=!isclick;
        if(isclick)
        {
            welcome.setVisibility(View.INVISIBLE);
            haveniceday.setVisibility(View.INVISIBLE);
            red.setVisibility(View.VISIBLE);
            green.setVisibility(View.VISIBLE);
            pink.setVisibility(View.VISIBLE);
            orange.setVisibility(View.VISIBLE);
            sky.setVisibility(View.VISIBLE);
            blue.setVisibility(View.VISIBLE);
        }
        else
        {
            welcome.setVisibility(View.VISIBLE);
            haveniceday.setVisibility(View.VISIBLE);
            red.setVisibility(View.INVISIBLE);
            green.setVisibility(View.INVISIBLE);
            pink.setVisibility(View.INVISIBLE);
            orange.setVisibility(View.INVISIBLE);
            sky.setVisibility(View.INVISIBLE);
            blue.setVisibility(View.INVISIBLE);
        }
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#FF5858");
                startActivity(intent);
            }
        });
        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#FF581A");
                startActivity(intent);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#24DA6D");
                startActivity(intent);
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#FFC158");
                startActivity(intent);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#7958FF");
                startActivity(intent);
            }
        });

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddnotesActivity.class);
                intent.putExtra("Msg","#01BFF9");
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}