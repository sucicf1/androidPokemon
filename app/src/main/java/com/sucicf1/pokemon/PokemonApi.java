package com.sucicf1.pokemon;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

public class PokemonApi
{
    public static void getPokemonByName(Context context, Pokemon pokemon, String name, LoadPokemonCallback callback)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + name;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    try
                    {
                        pokemon.setId(response.getInt("id"));
                        pokemon.setName(response.getString("name"));
                        pokemon.setBackImageUri(response.getJSONObject("sprites")
                                .getString("back_default"));
                        pokemon.setFrontImageUri(response.getJSONObject("sprites")
                                .getString("front_default"));

                        JSONArray jsonStats = response.getJSONArray("stats");
                        ArrayList<Stat> stats = new ArrayList<>();
                        for (int i = 0; i < jsonStats.length(); ++i)
                        {
                            Stat stat = new Stat();
                            stat.setBaseStat(jsonStats.getJSONObject(i).getInt("base_stat"));
                            stat.setEffort(jsonStats.getJSONObject(i).getInt("effort"));
                            stat.setName(jsonStats.getJSONObject(i).getJSONObject("stat")
                                    .getString("name"));
                            stats.add(stat);
                        }
                        pokemon.setStats(stats);

                        JSONArray jsonMoves = response.getJSONArray("moves");
                        ArrayList<String> moves = new ArrayList<>();
                        for (int i = 0; i < jsonMoves.length(); ++i)
                            moves.add(jsonMoves.getJSONObject(i).getJSONObject("move")
                                    .getString("name"));
                        pokemon.setMoves(moves);

                        callback.onSuccess();
                    } catch (JSONException e)
                    {
                        Log.e("error", e.getMessage());
                    }
                },
                error -> {
                    Log.e("error", error.getMessage());
                }
        );
        requestQueue.add(getRequest);
    }

    public static void getRandomPokemon(Context context, Pokemon pokemon, LoadPokemonCallback callback)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String apiUrl = "https://pokeapi.co/api/v2/pokemon/";

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    try
                    {
                        int count = response.getInt("count");
                        Random rand = new Random();
                        int index = rand.nextInt(count);

                        String apiUrl2 = "https://pokeapi.co/api/v2/pokemon/?limit=1&offset=" + index;
                        JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, apiUrl2, null,
                                response2 -> {
                                    try
                                    {
                                        getPokemonByName(context, pokemon,
                                                response2.getJSONArray("results")
                                                        .getJSONObject(0).getString("name"),
                                                callback::onSuccess);
                                    } catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }, error2 -> {
                            Log.e("error", error2.getMessage());
                        });
                        requestQueue.add(getRequest2);
                    } catch (JSONException e)
                    {
                        Log.e("error", e.getMessage());
                    }
                },
                error -> {
                }
        );
        requestQueue.add(getRequest);
    }
}