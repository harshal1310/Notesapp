package com.harsh1310.notesapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adapterclass extends FirebaseRecyclerAdapter<Modelclass,Adapterclass.NoteHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
DatabaseReference notesref;
Context con;
    public Adapterclass(@NonNull FirebaseRecyclerOptions<Modelclass> options,Context con) {

        super(options);
        this.con=con;
    }

    @Override
    protected void onBindViewHolder(@NonNull Adapterclass.NoteHolder holder, int position, @NonNull Modelclass model) {
        holder.Title.
        setText(model.getTitle());
        holder.Description.setText(model.getcontent());
        holder.date.setText(String.valueOf(model.getdate()));
        holder.cardView.setBackgroundColor(Color.parseColor(model.getcolor()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(con,AddnotesActivity.class);
                intent.putExtra("Msg",holder.cardView.getCardBackgroundColor().toString());
                v.getContext().startActivity(intent);
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        notesref=   FirebaseDatabase.getInstance().getReference().child("Notesref");
            String id=getRef(position).getKey();
            notesref.child(id).removeValue();
            }
        });
    }

    @NonNull
    @Override
    public Adapterclass.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsofnotes,
                parent, false);

        return new NoteHolder(v);
    }
    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView Title;
        TextView Description;
        TextView date;
        ImageButton edit,del;
CardView cardView;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            Description = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            edit=itemView.findViewById(R.id.edit);
            cardView=itemView.findViewById(R.id.cardview);
            del=itemView.findViewById(R.id.del);
        }

    }
}
