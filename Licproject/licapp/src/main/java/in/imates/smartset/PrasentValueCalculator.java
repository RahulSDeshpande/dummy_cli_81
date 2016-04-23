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
public class PrasentValueCalculator extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText et_amount;
    EditText et_rateofinterest;
    EditText et_noofyears;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_value);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Present Value Calculator");
        et_amount = (EditText) findViewById(R.id.amount);
        et_rateofinterest = (EditText) findViewById(R.id.rateofinterest);
        et_noofyears = (EditText) findViewById(R.id.noofyears);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_amount.getText().toString().equals("") || et_rateofinterest.getText().toString().equals("")
                        || et_noofyears.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {
                    double d = Double.parseDouble(et_amount.getText().toString());
                    double d1 = Double.parseDouble(et_rateofinterest.getText().toString());
                    double d2 = Double.parseDouble(et_noofyears.getText().toString());
                    d /= Math.pow(1.0D + d1 / 100D, d2);
                    final String display = (new StringBuilder("Present Value is : ")).append(String.format("%.2f", new Object[]{
                            Double.valueOf(d)
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
