package com.example.cineapp.src.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    public String title;
    public int runtime;
    public List<Genre> genre;
    public Poster poster;
    public Trailer trailer;
    public Casting castingShort;
    public Statistic statistics;
}
