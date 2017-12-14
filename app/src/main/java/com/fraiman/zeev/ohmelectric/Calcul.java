package com.fraiman.zeev.ohmelectric;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Calcul extends AppCompatActivity
    implements View.OnClickListener{

    EditText etV, etR, etI;
    String stV, stR, stI;
    int v,r,i,index,countAll=0, countCorr=0, countWrong=0;

    RadioButton rbV, rbR, rbI;
    Button btnReady;
    ImageView ivVRI;
    RadioGroup rgVRI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        etV=findViewById(R.id.etV);
        etR=findViewById(R.id.etR);
        etI=findViewById(R.id.etI);
        rbV=findViewById(R.id.rbV);
        rbR=findViewById(R.id.rbR);
        rbI=findViewById(R.id.rbI);
        btnReady=findViewById(R.id.btnReady);
        btnReady.setOnClickListener(this);
        rbV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etV.setVisibility(View.INVISIBLE);
                etR.setVisibility(View.VISIBLE);
                etI.setVisibility(View.VISIBLE);
                etV.setText("");
                etR.setText("");
                etI.setText("");
            }
        });
        rbR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etV.setVisibility(View.VISIBLE);
                etR.setVisibility(View.INVISIBLE);
                etI.setVisibility(View.VISIBLE);
                etV.setText("");
                etR.setText("");
                etI.setText("");
            }
        });
        rbI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etV.setVisibility(View.VISIBLE);
                etR.setVisibility(View.VISIBLE);
                etI.setVisibility(View.INVISIBLE);
                etV.setText("");
                etR.setText("");
                etI.setText("");
            }
        });
        rgVRI=findViewById(R.id.rgVRI);
        ivVRI=findViewById(R.id.ivVRI);
        ivVRI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgVRI.clearCheck();
                etV.setVisibility(View.VISIBLE);
                etR.setVisibility(View.VISIBLE);
                etI.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!rbV.isChecked()&&!rbR.isChecked()&&!rbI.isChecked())
            knowledgeTest();
        if (rbV.isChecked())  index=1;
        if (rbR.isChecked())  index=2;
        if (rbI.isChecked())  index=3;
        controlText(index);
    }

    private void controlText(int index) {
        if (index==1)  {
            stR=etR.getText().toString();
            stI=etI.getText().toString();
            if (stR.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (stI.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            r=Integer.parseInt(stR);
            i=Integer.parseInt(stI);
            v=i*r;
            rgVRI.clearCheck();
            etV.setVisibility(View.VISIBLE);
            etV.setText(""+v);
        }
        if (index==2)  {
            stV=etV.getText().toString();
            stI=etI.getText().toString();
            if (stV.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (stI.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            v=Integer.parseInt(stV);
            i=Integer.parseInt(stI);
            double t=1.0*v/i;
            r=v/i;
            rgVRI.clearCheck();
            etR.setVisibility(View.VISIBLE);
            if (t==r)
                etR.setText(""+r);
            else
                etR.setText(""+t);
        }
        if (index==3)  {
            stV=etV.getText().toString();
            stR=etR.getText().toString();
            if (stV.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (stR.equals("")) {
                Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
                return;
            }
            v=Integer.parseInt(stV);
            r=Integer.parseInt(stR);
            double t=1.0*v/r;
            i=v/r;
            rgVRI.clearCheck();
            etI.setVisibility(View.VISIBLE);
            if (t==r)
                etI.setText(""+i);
            else
                etI.setText(""+t);
        }
    }

    private void knowledgeTest() {
        stV=etV.getText().toString();
        stR=etR.getText().toString();
        stI=etI.getText().toString();
        if (stV.equals("")) {
            Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stR.equals("")) {
            Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stI.equals("")) {
            Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show();
            return;
        }
        v=Integer.parseInt(stV);
        r=Integer.parseInt(stR);
        i=Integer.parseInt(stI);
        if (i*r==v)  {
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
            countAll++;
            countCorr++;
            etV.setText("");
            etR.setText("");
            etI.setText("");
        }
        else  {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            countAll++;
            countWrong++;
            etV.setText("");
            etR.setText("");
            etI.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.add(0,1,0,"View result");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==1)
            goViewResult();
        return super.onOptionsItemSelected(item);
    }

    private void goViewResult() {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Your results");
        String st="All answers   ="+countAll+"\n\n";
        st+="Corrent answers="+countCorr+"\n\n";
        st+="Wrong answers  ="+countWrong+"\n\n";
        if (countCorr>countWrong)
            adb.setIcon(R.drawable.correct);
        if (countCorr<countWrong)
            adb.setIcon(R.drawable.wrong);
        if (countCorr==countWrong)
            adb.setIcon(R.drawable.neutral);
        adb.setMessage(st);
        adb.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                countAll=0;
                countCorr=0;
                countWrong=0;
            }
        });
        adb.create();
        adb.show();

    }
}
