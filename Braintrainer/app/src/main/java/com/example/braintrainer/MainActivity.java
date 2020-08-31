package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int x,y,z,q,l;
    int count=0,total=1;
    TextView tim,ques,score;
    Button b1,b2,b3,b4,go,rt;
    Button[] p={b1,b2,b3,b4};
    boolean ch=true;
    CountDownTimer cd;
    ConstraintLayout CL,cl1;
    LinearLayout ll;

    public void ran()
    {
        x = new Random().nextInt(209) + 1;
        y = new Random().nextInt(200) + 1;
        z=x+y;
    }

    public void generalran() {
        q = new Random().nextInt(409) + 2;

    }

    public void qu()
    {
        ran();
        ques.setText(String.valueOf(x)+"+"+String.valueOf(y)+"=");
        setbuttontext();

    }

    public void cl(View view)
    {
        Button b=(Button)view;
        int t=Integer.parseInt(b.getText().toString());
        if(ch){
        if(t==z)
        {
            score.setText(String.valueOf(++count)+"/"+String.valueOf(total));
        }
           total++;
            score.setText(String.valueOf(count)+"/"+String.valueOf(total));
            qu();
        }
    }
    public void setbuttontext()
    {

        generalran();
        l = new Random().nextInt(3)+0;
        if(l==0) {
            b1.setText(String.valueOf(z));
            generalran();
            b2.setText(String.valueOf(q));
            generalran();
            b3.setText(String.valueOf(q));
            generalran();
            b4.setText(String.valueOf(q));
        }
        else if(l==1) {
            b1.setText(String.valueOf(q));
            generalran();
            b2.setText(String.valueOf(z));
            generalran();
            b3.setText(String.valueOf(q));
            generalran();
            b4.setText(String.valueOf(q));
        }
        else if(l==2) {
            b1.setText(String.valueOf(q));
            generalran();
            b2.setText(String.valueOf(q));
            generalran();
            b3.setText(String.valueOf(z));
            generalran();
            b4.setText(String.valueOf(q));
        }
        else if(l==3) {
            b1.setText(String.valueOf(q));
            generalran();
            b2.setText(String.valueOf(q));
            generalran();
            b3.setText(String.valueOf(q));
            generalran();
            b4.setText(String.valueOf(z));
        }


    }

    public void ret(View view)
    {
        ch=true;
        count=0;
        total=1;
        tim.setText(String.valueOf(30));
        score.setText(String.valueOf(0));
        cd.cancel();
        cd();
        qu();

    };

    public void cd()
    {
       cd= new CountDownTimer(30000,1000)
        {
            @Override
            public void onTick(long l) {
                tim.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                ch=false;
            }
        }.start();

    }

  public void go(View view)
  {

      CL.setVisibility(View.VISIBLE);
      qu();
      cd();
      go.setVisibility(View.INVISIBLE);
      tim.setVisibility(View.VISIBLE);
      ques.setVisibility(View.VISIBLE);
      score.setVisibility(View.VISIBLE);
      ll.setVisibility(View.VISIBLE);
      rt.setVisibility(View.VISIBLE);
  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tim=findViewById(R.id.timmertextView);
        ques=findViewById(R.id.questextView2);
        score=findViewById(R.id.scoretextView3);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        go= findViewById(R.id.button6);
        rt=findViewById(R.id.button5);
        CL=findViewById(R.id.constraindl);
        cl1=findViewById(R.id.ma);
        ll=findViewById(R.id.linearLayout);
        tim.setVisibility(View.INVISIBLE);
        ques.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.INVISIBLE);
        rt.setVisibility(View.INVISIBLE);



    }
}