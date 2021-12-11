package com.sucicf1.pokemon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private final int imageHeightDp = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNewRandomPokemon();

        Button btnReload = findViewById(R.id.buttonReload);
        btnReload.setOnClickListener((view) -> getNewRandomPokemon());
    }

    private void getNewRandomPokemon()
    {
        Pokemon pokemon = new Pokemon();
        PokemonApi.getRandomPokemon(this, pokemon, () -> {
            TextView textViewName = findViewById(R.id.textViewPokemonName);
            textViewName.setText(pokemon.getName());

            List<Stat> stats = pokemon.getStats();
            RecyclerView rvStats = findViewById(R.id.rvStats);
            rvStats.setAdapter(new StatsAdapter(stats));
            rvStats.setLayoutManager(new LinearLayoutManager(this));

            List<String> moves = pokemon.getMoves();
            RecyclerView rvMoves = findViewById(R.id.rvMoves);
            rvMoves.setAdapter(new MovesAdapter(moves));
            rvMoves.setLayoutManager(new LinearLayoutManager(this));

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = imageHeightDp * (int) getResources().getDisplayMetrics().density;

            Glide.with(this)
                    .asBitmap()
                    .load(pokemon.getFrontImageUri())
                    .apply(new RequestOptions().override(width / 3, height))
                    .centerCrop()
                    .into(new CustomTarget<Bitmap>()
                    {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource,
                                                    @Nullable Transition<? super Bitmap> transition)
                        {
                            ImageView imageViewFront = findViewById(R.id.imageViewFront);
                            imageViewFront.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder)
                        {
                        }
                    });

            Glide.with(this)
                    .asBitmap()
                    .load(pokemon.getBackImageUri())
                    .apply(new RequestOptions().override(width / 3, height))
                    .centerCrop()
                    .into(new CustomTarget<Bitmap>()
                    {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource,
                                                    @Nullable Transition<? super Bitmap> transition)
                        {
                            ImageView imageViewBack = findViewById(R.id.imageViewBack);
                            imageViewBack.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder)
                        {
                        }
                    });
        });
    }
}