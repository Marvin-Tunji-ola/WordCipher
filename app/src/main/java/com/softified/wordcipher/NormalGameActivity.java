package com.softified.wordcipher;

/**
 * Created by Marvin on 8/15/2017.
 */


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class NormalGameActivity extends AppCompatActivity implements Top_Fragment.OnFragmentInteractionListener{

    TextView dyna, desc;
    EditText editor;
    Top_Fragment top_fragment;

    String answer, description;
    int total_score = 0;
    int i =0;

    String[][] words = {
            {"ATOM", "Smallest Composition of all Matter"},
            {"CAMEL", "Long Neck, Long Desert Distance Travel without water"},
            {"SNAKE", "Crawls in curvy Ziz-zag, poisonous"},
            {"RAT", "Just A Rat"},
            {"DOG", "Stop Barking"},
            {"AIR", "Can't see, Can't Touch but Can Feel it"},
            {"MOUSE", "A computer Accessory and an Animal"},
            {"NOTHING", "greater than God,\n" +
                    "more evil than the devil,\n" +
                    "the poor have it,\n" +
                    "the rich need it,\n" +
                    "and if you eat it, you'll die?"},
            {"COFFIN","Who makes it, has no need of it.\n" +
                    "Who buys it, has no use for it. \n" +
                    "Who uses it can neither see nor feel it. \n" +
                    "What is it?"},
            {"TOWEL","it gets wetter and wetter the more it dries"},
            {"WATERMELON","There was a green house. \n" +
                    "Inside the green house there was a white house. \n" +
                    "Inside the white house there was a red house. \n" +
                    "Inside the red house there were lots of babies"},
            {"MUSHROOM","A kind of room with no doors or windows"},
            {"PALM","A kind of tree you carry in your hand"},
            {"INCORRECTLY","A word in the dictionary spelled incorrectly"},
            {"SECRET","when you have it, you want to share it. \n" +
                    "If you share it, you don\'t have it"},
            {"FIRE","Feed it and It wil live, yet give it a drink and It will die"},
            {"WINDOW","An invention that lets you look right through a wall"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);


        dyna = (TextView) findViewById(R.id.dyna);
        editor = (EditText) findViewById(R.id.editor);
        editor.setOnEditorActionListener( new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event){
                if(id == EditorInfo.IME_ACTION_DONE ||
                        id == EditorInfo.IME_ACTION_GO ||
                        id == EditorInfo.IME_ACTION_NEXT ||
                        id == EditorInfo.IME_ACTION_SEND) {
                    check();

                }
                return true;
            }
        });

        desc = (TextView) findViewById(R.id.desc);
        top_fragment = (Top_Fragment) getSupportFragmentManager().findFragmentById(R.id.top_fragment);

        words = arrayShuffle(words);
        update();

    }



    public void check(){
        String input = editor.getText().toString();
        if(i >= words.length ){
            if(i == words.length ){
                if (input.equalsIgnoreCase(answer)) {

                    top_fragment.reset_replay();
                    total_score += 1;
                    top_fragment.set_score(total_score);
                    editor.setText(null);
                }else {

                    dyna.setTextColor(Color.RED);

                }
            }
            dyna.setText("Score:"+ total_score);
            desc.setText("Click Replay  to Play Again ");
            i++;
        }else {
            if (!input.isEmpty()) {
                if (input.equalsIgnoreCase(answer)) {
                    update();
                    total_score += 1;
                    top_fragment.set_score(total_score);
                } else {
                    dyna.setTextColor(Color.RED);

                }
            }
        }
    }

    public void update(){
        dyna.setTextColor(Color.parseColor("#ff669900"));
        answer = words[i][0];
        description = words[i][1];
        desc.setText(description);
        dyna.setText(wordShuffle(answer));
        editor.setText(null);
        i++;

    }


    @Override
    public void reset(int a) {
        words = arrayShuffle(words);
        i = 0;
        editor.setText(null);
        total_score = 0;
        top_fragment.reset_replay();
        top_fragment.set_score(total_score);
        update();

    }

    public static String[][] arrayShuffle(String[][] words){
        String[][] output = new String[words.length][2];
        int[] storedIndex= new int[words.length];
        for (int e = 0 ; e <storedIndex.length;e++){
            storedIndex[e] = -1;
        }

        boolean isexist = false;

        Random random = new Random(System.currentTimeMillis());

        int i = 0;
        while(i < words.length) {
            int index = random.nextInt(words.length);

            for (int e : storedIndex){

                if(index == e){
                    isexist = true;
                    break;
                }else{
                    isexist = false;
                }
            }


            if(isexist == false){
                storedIndex[i] = index;
                output[i][0] = words[index][0];
                output[i][1] = words[index][1];
                i++;
            }
        }


        return output;
    }


    public static String wordShuffle(String input){

        char[] words = input.toCharArray();
        char[] output = new char[words.length];
        int[] storedIndex= new int[words.length];
        for (int e = 0 ; e <storedIndex.length;e++){
            storedIndex[e] = -1;
        }
        boolean isexist = false;

        Random random = new Random(System.currentTimeMillis());

        int i = 0;
        while(i < words.length) {
            int index = random.nextInt(words.length);

            for (int e : storedIndex){
                if(index == e){
                    isexist = true;
                    break;
                }else{
                    isexist = false;
                }
            }


            if(isexist == false){
                storedIndex[i] = index;
                output[i] = words[index];
                i++;
            }
        }

        String outString = "";
        for(char e: output){
            outString +=e;
        }
        return outString;
    }

}
