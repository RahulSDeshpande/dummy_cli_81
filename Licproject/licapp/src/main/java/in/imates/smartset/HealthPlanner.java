package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by root on 26/12/15.
 */
public class HealthPlanner extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText txtAge;
    EditText txtPresentlyMonthlyExpense;
    EditText txtAmountRequired;
    EditText txtExistingHealth;
    EditText txtTakenByself;
    EditText txtAfterRetirement;
    EditText txtCurrentAverageCost;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_planner);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Planner");
        txtAge = (EditText)findViewById(R.id.txtAge);
        txtPresentlyMonthlyExpense = (EditText)findViewById(R.id.txtPresentlyMonthlyExpense);
        txtAmountRequired = (EditText)findViewById(R.id.txtAmountRequired);
        txtExistingHealth = (EditText)findViewById(R.id.txtExistingHealth);
        txtTakenByself = (EditText)findViewById(R.id.txtTakenByself);
    //    txtExistingCover = (EditText)findViewById(0x7f0a0218);
        txtCurrentAverageCost = (EditText)findViewById(R.id.txtCurrentAverageCost);
        txtAfterRetirement = (EditText)findViewById(R.id.txtAfterRetirement);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtAge.getText().toString().equals("") || txtPresentlyMonthlyExpense.getText().toString().equals("")
                        || txtAmountRequired.getText().toString().equals("") || txtExistingHealth.getText().toString().equals("")
                        || txtTakenByself.getText().toString().equals("") || txtCurrentAverageCost.getText().toString().equals("")
                        || txtAfterRetirement.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {
                    double d = Double.parseDouble(txtAge.getText().toString());
                    Double.parseDouble(txtPresentlyMonthlyExpense.getText().toString());
                    Double.parseDouble(txtAmountRequired.getText().toString());
                    double d2 = Double.parseDouble(txtExistingHealth.getText().toString());
                    double d3 = Double.parseDouble(txtTakenByself.getText().toString());
                    //     Double.parseDouble(txtExistingCover.getText().toString());
                    double d4 = Double.parseDouble(txtCurrentAverageCost.getText().toString());
                    double d1 = Double.parseDouble(txtAfterRetirement.getText().toString());
                    if (58D - d > 20D) {
                        d = 58D - d;
                    } else {
                        d = 20D;
                    }
                    d = d4 * Math.pow(1.0600000000000001D, d);
                    d2 += d3;
                    d3 = d - d2;
                    final String display = (new StringBuilder("Future Value For Cost Of Treatment is : ")).append(String.format("%.0f", new Object[]{
                            Double.valueOf(d)
                    })).append("\nRecommended Cover : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d3)
                    })).append("\nAmount You Are Willing To Set Aside For Fulfilling This GAP is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf((d3 + d2) - d1)
                    })).toString();
                    builder.setMessage(display).setCancelable(false).setPositiveButton("Close", new android.content.DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }

                    }).setNegativeButton("Share", new android.content.DialogInterface.OnClickListener() {


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
