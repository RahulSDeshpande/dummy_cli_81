package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.Hashtable;

/**
 * Created by root on 26/12/15.
 */
public class HumanLifeValue extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText et_txtName;
    EditText et_txtAge;
    EditText et_txtretairmentAge;
    EditText et_income1415;
    EditText et_income1314;
    EditText et_income1213;
    EditText et_txtcurrentexp;
    EditText et_txtcurrentinterest;
    EditText et_txtoutloan;
    EditText et_txtextlifecover;
    AlertDialog.Builder builder,builder1;
    double annualExp;
    double annualIncome;
    double humanValue;
    double insuranceCoverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_life_value);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Human Life Value Calculator");
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        et_txtName = (EditText) findViewById(R.id.txtName);
        et_txtAge = (EditText) findViewById(R.id.txtAge);
        et_txtretairmentAge = (EditText) findViewById(R.id.txtretairmentAge);
        et_income1415 = (EditText) findViewById(R.id.income1415);
        et_income1314 = (EditText) findViewById(R.id.income1314);
        et_income1213 = (EditText) findViewById(R.id.income1213);
        et_txtcurrentexp = (EditText) findViewById(R.id.txtcurrentexp);
        et_txtcurrentinterest = (EditText) findViewById(R.id.txtcurrentinterest);
        et_txtoutloan = (EditText) findViewById(R.id.txtoutloan);
        et_txtextlifecover = (EditText) findViewById(R.id.txtextlifecover);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_txtName.getText().toString().equals("") || et_txtAge.getText().toString().equals("") ||
                        et_txtretairmentAge.getText().toString().equals("") || et_income1415.getText().toString().equals("")
                        || et_income1314.getText().toString().equals("") || et_income1213.getText().toString().equals("")
                        || et_txtcurrentexp.getText().toString().equals("") || et_txtcurrentinterest.getText().toString().equals("")
                        || et_txtoutloan.getText().toString().equals("") || et_txtextlifecover.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {
                    String Name = et_txtName.getText().toString();
                    double d = Double.parseDouble(et_txtAge.getText().toString());
                    double d1 = (Double.parseDouble(et_income1415.getText().toString()) + Double.parseDouble(et_income1314.getText().toString()) + Double.parseDouble(et_income1213.getText().toString())) / 3D;
                    if (d <= 35D) {
                        d = 25D;
                    } else if (d <= 45D) {
                        d = 20D;
                    } else if (d <= 55D) {
                        d = 12D;
                    } else if (d <= 60D) {
                        d = 10D;
                    } else {
                        d = 5D;
                    }
                    final String display = (new StringBuilder("Dear, ")).append(Name).append(" Your Human life value is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d1 * d)
                    })).toString();
                    builder.setMessage(display).setCancelable(false).setPositiveButton("Close", new android.content.DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }

                    }).setNegativeButton("Share", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i) {
                            Intent intent = new Intent("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.TEXT", display);
                            startActivity(Intent.createChooser(intent, "Share With.."));
                        }

                    });
                    builder.create().show();
                }
            }


        });

        btnBack = (Button) findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
