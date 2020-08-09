package com.example.tictac;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // player representation
    // 0 = x
    // 1 = o
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // state meaning
    // 0 = x , 1 = O , 2 = null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void PlayerTap(View view){
        ImageView img  =  (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x2);
                activePlayer = 1;
                TextView statusView  = findViewById(R.id.statusView);
                statusView.setText("Player 2's chance");
            }
            else{
                img.setImageResource(R.drawable.o2);
                activePlayer = 0;
                TextView statusView  = findViewById(R.id.statusView);
                statusView.setText("Player 1's chance");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if any player has won
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){
                // somebody has won - find out how!
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "Player 1 has won";
                    ImageView winner = findViewById(R.id.happy1);
                    winner.setImageResource(R.drawable.happy);


                }
                else{
                    winnerStr = "Player 2 has won";
                    ImageView winner = findViewById(R.id.happy1);
                    winner.setImageResource(R.drawable.happy);

                }
                // update the status bar for winner announcement
                TextView statusView  = findViewById(R.id.statusView);
                statusView.setText(winnerStr);
            }
        }

    }


    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.happy1)).setImageResource(0);

        TextView status = findViewById(R.id.statusView);
        status.setText("X's Turn - Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
