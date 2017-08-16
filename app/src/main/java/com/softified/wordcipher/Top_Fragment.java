package com.softified.wordcipher;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Top_Fragment extends Fragment {

    OnFragmentInteractionListener inter;
    Button reset_button;

    private static TextView scoreView;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            inter = (OnFragmentInteractionListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top, container, false);

        scoreView = (TextView) view.findViewById(R.id.score);
        scoreView.setText("" + 0);

        reset_button = (Button) view.findViewById(R.id.reset_button);


        reset_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        resetButton();

                    }
                }
        );
        return view;


    }


    public interface OnFragmentInteractionListener {
        void reset(int a);

    }

    public void resetButton() {
        inter.reset(1);
    }

    public void set_score(int a) {
        scoreView = (TextView) view.findViewById(R.id.score);


        scoreView.setText("" + a);

    }

    public void reset_replay() {

        if (reset_button.getText().toString().equalsIgnoreCase("Replay")) {
            reset_button.setText("Reset");
        } else {
            reset_button.setText("Replay");
        }
    }
}

