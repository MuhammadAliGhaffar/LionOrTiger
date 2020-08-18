package com.example.lionortiger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE,TWO,No
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices=new Player[9];

    int [][] winnerRowsColums = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };

    private boolean gameOver=false;

    private Button BtnReset;
    private androidx.gridlayout.widget.GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.No;
        }
        BtnReset=findViewById(R.id.BtnReset);
        gridLayout=  findViewById(R.id.gridLayout);
        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTheGame();
            }
        });


    }
    public void ImageViewIsTapped(View imageView){
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoices[tiTag]==Player.No && gameOver==false) {


            tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColums) {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.No) {

                    BtnReset.setVisibility(View.VISIBLE);

                    gameOver=true;
                    String winnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player One";
                    }
                    Toast.makeText(MainActivity.this, "Winner of game is " + winnerOfGame, Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    //For Reset Button Function
    private void resetTheGame(){

        for(int index=0;index<gridLayout.getChildCount();index++){

            ImageView imageView =(ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }

        currentPlayer = Player.ONE;
        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.No;
        }
        gameOver=false;

        BtnReset.setVisibility(View.INVISIBLE);

    }

}