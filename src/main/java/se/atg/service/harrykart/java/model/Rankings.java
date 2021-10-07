package se.atg.service.harrykart.java.model;

import java.util.ArrayList;

public class Rankings {
    private ArrayList<Rank> rankings;

    public Rankings() {
    }

    public Rankings(ArrayList<Rank> rankings) {
        this.rankings = rankings;
    }

    public ArrayList<Rank> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Rank> rankings) {
        this.rankings = rankings;
    }
}
