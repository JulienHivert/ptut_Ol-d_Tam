package com.example.iem.oldtam.manager;

public enum Topic {
    //(Smartphone->Tabette) S notifie sa présence sur le réseau
    INIT_ST0 {
        @Override
        public String toString() {
            return "INIT_ST0";
        }
    },
    //(Tablette->Smartphone) T diffuse la liste des chansons
    INIT_TS1 {
        @Override
        public String toString() {
            return "INIT_TS1";
        }
    },
    //T diffuse un vote
    VOTE_TS2 {
        @Override
        public String toString() {
            return "VOTE_TS2";
        }
    },
    //S diffuse sa réponse au vote
    VOTE_ST3 {
        @Override
        public String toString() {
            return "VOTE_ST3";
        }
    },
    //T diffuse la chanson en cours
    TRACK_TS4 {
        @Override
        public String toString() {
            return "TRACK_TS4";
        }
    }
}
