package com.byusa.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = blue, 1 = red

    int activePlayer = 0; //set the active player to zero by default

    boolean gameIsActive = true; // for not keep playing after loosing

    //2 means unplayed

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view){
        ImageView counter = (ImageView) view;

        //System.out.println(counter.getTag().toString()); //naming them to know which one is tapped.

        int tappedCounter = Integer.parseInt(counter.getTag().toString());// this is for the number of tagged (tag 0 to 8)

        if(gameState[tappedCounter] == 2 && gameIsActive) { // if game at tapped number/space is 2(2 unplayed) and game is active

            gameState[tappedCounter] = activePlayer; //if it is 2 player didn't play /the gamestate at a tag position is given 0 or 1,

            counter.setTranslationY(-1000f); //start position, move the image up at the begining when clicked
/////////////////////////////////////////////////////////////////////
                /*changing the players*/
            //////////////////////////////
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.bluecross_opt);//set the image to be bluecross
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.reddott_opt);//set the image to be reddot
                activePlayer = 0;

            }
 ///////////////////////////////////////////////////////////////////

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);//end position cames back in 3 sec

            for (int[] winningPosition : winningPositions) { // for each winningPosition in winningPositions
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) { //if X X X and game
                    //someone has won

                    gameIsActive = false; //after winning

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Blue";

                    }

                    //Someone has won
                    //winner message
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    //create a new layout and make it appear
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                   // Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    //layout.startAnimation(slideUp);
                    layout.setVisibility(View.VISIBLE); // to make the layout visible from invisble

                } else {
                    boolean gameIsOver = true;

                    for (int counterState : gameState) { //check all the games

                        if (counterState == 2)
                            gameIsOver = false; // any of them is 2 then set is it to false
                    }

                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE); // to make the layout visible from invisble

                    }
                }

            }
        }
    }
    //when you play again it becomes visible
    public void playAgain(View view){

        gameIsActive =true; //true again when he plyas

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE); //remove the message

        activePlayer = 0;

        for(int i = 0; i<gameState.length; i++){

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0); //reset to an empty image //get the each of the image views

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
