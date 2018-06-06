package com.example.iem.oldtam.view.model;

import java.util.ArrayList;

public class Vote {
    private ArrayList<Integer> listVote;
    private Boolean vote_finished;

    public Vote(){
        this.listVote = new ArrayList<Integer>();
        this.vote_finished = false;
    }
}
