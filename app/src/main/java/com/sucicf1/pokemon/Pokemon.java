package com.sucicf1.pokemon;

import java.util.ArrayList;

public class Pokemon
{
    private int id;
    private String name;
    private String frontImageUri;
    private String backImageUri;
    private ArrayList<Stat> stats;
    private ArrayList<String> moves;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFrontImageUri()
    {
        return frontImageUri;
    }

    public void setFrontImageUri(String frontImageUri)
    {
        this.frontImageUri = frontImageUri;
    }

    public String getBackImageUri()
    {
        return backImageUri;
    }

    public void setBackImageUri(String backImageUri)
    {
        this.backImageUri = backImageUri;
    }

    public ArrayList<Stat> getStats()
    {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats)
    {
        this.stats = stats;
    }

    public ArrayList<String> getMoves()
    {
        return moves;
    }

    public void setMoves(ArrayList<String> moves)
    {
        this.moves = moves;
    }
}
