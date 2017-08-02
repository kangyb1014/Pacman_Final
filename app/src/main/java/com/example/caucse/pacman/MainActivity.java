package com.example.caucse.pacman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView map[][] = new ImageView[14][14];          //맵을 위한 14*14 배열

        String Pname = getPackageName();                    //밑에 getidentifier 함수를 사용하기 위해 패키지 이름을 가져옴
        int resID = 0;                                      //이미지의 주소값(?)을 가져오기 위한 변수

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {

                if (i < 10) {
                    if (j < 10)
                        resID = getResources().getIdentifier("map" + "0" +  i + "0" + j, "id", Pname);
                    else
                        resID = getResources().getIdentifier("map" + "0" +i + j, "id", Pname);
                }
                else{
                    if (j < 10)
                        resID = getResources().getIdentifier("map" + i + "0" + j, "id", Pname);
                    else
                        resID = getResources().getIdentifier("map" + i + j, "id", Pname);
                }
                map[i][j] = (ImageView) findViewById(resID);
            }
        }
//////////////////////여기까지는 배열에 이미지의 주소값을 넣는 과정입니다///////////////////////////

        final int map_state[][] = {
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,0,1,0,0,1,0,0,0,1,0,1,0},
                {0,1,1,1,0,1,1,1,1,0,1,1,1,0},
                {0,1,0,0,0,1,0,0,1,0,0,0,1,0},
                {0,1,0,1,1,1,1,0,1,1,1,0,1,0},
                {1,1,1,1,0,0,1,1,1,0,1,1,1,1},
                {0,1,0,1,0,1,1,1,0,0,1,0,1,0},
                {0,1,0,1,1,1,0,1,1,1,1,0,1,0},
                {0,1,0,0,0,1,0,0,1,0,0,0,1,0},
                {0,1,1,1,0,1,1,1,1,0,1,1,1,0},
                {0,1,0,1,0,0,1,0,0,0,1,0,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,3,0,0,0,0,0,0,0}
        };


        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 14; j++)
            {
                if(map_state[i][j] == 0)
                    map[i][j].setImageResource(R.drawable.wall);
                if(map_state[i][j] == 1)
                    map[i][j].setImageResource(R.drawable.coin);
                if(map_state[i][j] == 2)
                    map[i][j].setImageResource(R.drawable.empty);
                if(map_state[i][j] == 3)
                    map[i][j].setImageResource(R.drawable.pacman_r);
            }
        }
////////////////////맵 초기화////////////////////
        Button button_up =(Button)findViewById(R.id.btn_up);
        Button button_down =(Button)findViewById(R.id.btn_down);
        Button button_left =(Button)findViewById(R.id.btn_left);
        Button button_right =(Button)findViewById(R.id.btn_right);


         num_of_coins = 99;
         pac_row = 13;
         pac_column = 6;
         pac_state = 1;


        // map_state  0: wall 1:coin 2:empty 3:pacman

        button_up.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                int next;
                if(num_of_coins == 0) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pac_row < 1)
                    next = 13;
                else
                    next = pac_row - 1;
                if(map_state[next][pac_column] == 1 || map_state[next][pac_column] == 2)
                {
                    if(map_state[next][pac_column] == 1)
                        num_of_coins--;

                    map_state[pac_row][pac_column] = 2;
                    map[pac_row][pac_column].setImageResource(R.drawable.empty);

                    map_state[next][pac_column] = 3;
                    if(pac_state == 1) {
                        map[next][pac_column].setImageResource(R.drawable.pacman_u_off);
                        pac_state = 0;
                    }
                    else {
                        map[next][pac_column].setImageResource(R.drawable.pacman_u);
                        pac_state = 1;
                    }

                    if(pac_row < 1) {
                        pac_row = 13;
                    }
                    else
                        pac_row--;
                }
            }
        });

        button_down.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                int next;
                if(num_of_coins == 0) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pac_row > 12)
                    next = 0;
                else
                    next = pac_row + 1;

                if(map_state[next][pac_column] == 1 || map_state[next][pac_column] == 2)
                {
                    if(map_state[next][pac_column] == 1)
                        num_of_coins--;

                    map_state[pac_row][pac_column] = 2;
                    map[pac_row][pac_column].setImageResource(R.drawable.empty);

                    map_state[next][pac_column] = 3;
                    if(pac_state == 1) {
                        map[next][pac_column].setImageResource(R.drawable.pacman_d_off);
                        pac_state = 0;
                    }
                    else {
                        map[next][pac_column].setImageResource(R.drawable.pacman_d);
                        pac_state = 1;
                    }
                    if(pac_row > 12)
                        pac_row = 0;
                    else
                    pac_row++;
                }

            }
        });

        button_right.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                int next;
                if(num_of_coins == 0) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pac_column > 12)
                    next = 0;
                else
                    next = pac_column + 1;

                if(map_state[pac_row][next] == 1 || map_state[pac_row][next] == 2)
                {
                    if(map_state[pac_row][next] == 1)
                        num_of_coins--;

                    map_state[pac_row][pac_column] = 2;
                    map[pac_row][pac_column].setImageResource(R.drawable.empty);

                    map_state[pac_row][next] = 3;
                    if(pac_state == 1) {
                        map[pac_row][next].setImageResource(R.drawable.pacman_r_off);
                        pac_state = 0;
                    }
                    else {
                        map[pac_row][next].setImageResource(R.drawable.pacman_r);
                        pac_state = 1;
                    }
                    if(pac_column > 12)
                        pac_column = 0;
                    else
                        pac_column++;
                }
            }
        });

        button_left.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                int next;
                if(num_of_coins == 0) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pac_column < 1)
                    next = 13;
                else
                    next = pac_column - 1;

                if(map_state[pac_row][next] == 1 || map_state[pac_row][next] == 2)
                {
                    if(map_state[pac_row][next] == 1)
                        num_of_coins--;

                    map_state[pac_row][pac_column] = 2;
                    map[pac_row][pac_column].setImageResource(R.drawable.empty);

                    map_state[pac_row][next] = 3;
                    if(pac_state == 1) {
                        map[pac_row][next].setImageResource(R.drawable.pacman_l_off);
                        pac_state = 0;
                    }
                    else {
                        map[pac_row][next].setImageResource(R.drawable.pacman_l);
                        pac_state = 1;
                    }
                    if(pac_column < 1)
                        pac_column = 13;
                    else
                        pac_column--;
                }
            }
        });



    }

    static int pac_row; static int pac_column; static int num_of_coins;
    static int pac_state;
}
