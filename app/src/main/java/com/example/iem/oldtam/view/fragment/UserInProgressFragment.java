package com.example.iem.oldtam.view.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iem.oldtam.R;

public class UserInProgressFragment extends Fragment {
    Context context;

    TextView titleTextView;
    TextView artistTextView;
    TextView lyricsTextView;

    public UserInProgressFragment() {

    }

    public static UserInProgressFragment newInstance() {
        UserInProgressFragment fragment = new UserInProgressFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (getActivity() != null) {
            getActivity().setTitle("Musique en cours");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_progress, container, false);

        initViews(view);
        setValuesInViews();

        return view;
    }


    private void initViews(View view) {
        titleTextView = view.findViewById(R.id.progress_title);
        artistTextView = view.findViewById(R.id.progress_artist);
        lyricsTextView = view.findViewById(R.id.progress_lyrics);
    }

    private void setValuesInViews() {
        //TODO remplacer les chaînes par les valeurs de la source de données
        titleTextView.setText("Titre");
        artistTextView.setText("Artiste");
        lyricsTextView.setText("Paroles de la chanson");
    }
}
