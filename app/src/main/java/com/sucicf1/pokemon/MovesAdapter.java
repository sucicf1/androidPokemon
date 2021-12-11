package com.sucicf1.pokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovesAdapter extends
        RecyclerView.Adapter<MovesAdapter.ViewHolder>
{
    List<String> moves;

    public MovesAdapter(List<String> moves)
    {
        this.moves = moves;
    }

    @NonNull
    @Override
    public MovesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_move, parent, false);
        MovesAdapter.ViewHolder viewHolder = new MovesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.textViewMoveName.setText(moves.get(position));
    }

    @Override
    public int getItemCount()
    {
        if (moves != null)
        {
            return moves.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewMoveName;

        public ViewHolder(View itemView)
        {
            super(itemView);
            textViewMoveName = itemView.findViewById(R.id.textViewMoveName);
        }
    }
}
