package com.example.user.triviaapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.triviaapp.DB.entity.QuizEntity;
import com.example.user.triviaapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerAdapterViewHolder> {

    List<QuizEntity>  quizEntities;

    public Adapter(List<QuizEntity> quizEntities) {
        this.quizEntities = quizEntities;
    }

    @NonNull
    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        RecyclerAdapterViewHolder recyclerAdapterViewHolder = new RecyclerAdapterViewHolder(view);
        return recyclerAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, int position) {

        holder.gameid.setText("GAME "+quizEntities.get(position).getId());
        holder.name.setText("Name "+quizEntities.get(position).getName());
        holder.datetime.setText(quizEntities.get(position).getDateTime());
        holder.q1.setText(quizEntities.get(position).getQuestion1());
        holder.q2.setText(quizEntities.get(position).getQuestion2());
        holder.ans1.setText(quizEntities.get(position).getAnswer1());
        holder.ans2.setText(quizEntities.get(position).getAnswer2());

    }

    @Override
    public int getItemCount() {
        return quizEntities.size();
    }

    public class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView gameid,datetime,name,q1,q2,ans1,ans2;

        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            gameid = itemView.findViewById(R.id.game_id);
            datetime = itemView.findViewById(R.id.datetime);
            name = itemView.findViewById(R.id.name);
            q1 = itemView.findViewById(R.id.question1);
            q2 = itemView.findViewById(R.id.question2);
            ans1 = itemView.findViewById(R.id.ans1);
            ans2 = itemView.findViewById(R.id.ans2);
        }
    }
}
