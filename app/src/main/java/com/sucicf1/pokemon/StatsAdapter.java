package com.sucicf1.pokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatsAdapter extends
        RecyclerView.Adapter<StatsAdapter.ViewHolder>
{
        List<Stat> stats;

        public StatsAdapter(List<Stat> stats)
        {
            this.stats = stats;
        }

        @NonNull
        @Override
        public StatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType)
        {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.item_stat, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            holder.textViewStatName.setText(stats.get(position).getName());
            holder.textViewBaseStat.setText(String.valueOf(stats.get(position).getBaseStat()));
            holder.textViewEffort.setText(String.valueOf(stats.get(position).getEffort()));
        }

        @Override
        public int getItemCount()
        {
            if (stats != null)
            {
                return stats.size();
            }
            else
            {
                return 0;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView textViewStatName;
            public TextView textViewBaseStat;
            public TextView textViewEffort;

            public ViewHolder(View itemView)
            {
                super(itemView);
                textViewStatName = itemView.findViewById(R.id.textViewStatName);
                textViewBaseStat = itemView.findViewById(R.id.textViewBaseStat);
                textViewEffort = itemView.findViewById(R.id.textViewEffort);
            }
        }
}
