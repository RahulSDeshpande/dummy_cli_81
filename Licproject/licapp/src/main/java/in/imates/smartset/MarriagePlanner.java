package in.imates.smartset;

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
import android.app.AlertDialog;
import android.text.Editable;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by root on 26/12/15.
 */
public class MarriagePlanner extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText et_childage,et_marriage_age;
    EditText et_marriage_cost,et_annual_saving;
    EditText et_rateofreturn,et_inflationrate;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marriage_planner);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Marriage Planner");

        et_childage = (EditText) findViewById(R.id.childage);
        et_marriage_age = (EditText) findViewById(R.id.marriage_age);
        et_marriage_cost = (EditText) findViewById(R.id.marriagecost);
        et_annual_saving = (EditText) findViewById(R.id.annualsaving);
        et_rateofreturn = (EditText) findViewById(R.id.rateofreturn);
        et_inflationrate = (EditText) findViewById(R.id.inflationrate);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_childage.getText().toString().equals("") || et_marriage_age.getText().toString().equals("")
                        || et_marriage_cost.getText().toString().equals("") || et_annual_saving.getText().toString().equals("")
                        || et_rateofreturn.getText().toString().equals("") || et_inflationrate.getText().toString().equals("")) {

                    // Toast.makeText(getApplicationContext(), "Please Fill All The Details", Toast.LENGTH_LONG).show();
                    builder1.setTitle("Alert Message").setMessage("Please Fill All The Details").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {

                    double d2 = Double.parseDouble(et_childage.getText().toString());
                    double d5 = Double.parseDouble(et_marriage_age.getText().toString());
                    double d3 = Double.parseDouble(et_marriage_cost.getText().toString());
                    double d1 = Double.parseDouble(et_annual_saving.getText().toString());
                    double d = Double.parseDouble(et_rateofreturn.getText().toString());
                    double d4 = Double.parseDouble(et_inflationrate.getText().toString());

                    d2 = d5 - d2;
                    d3 = Math.abs(Math.pow(1.0D + d4 / 100D, d2) * d3);
                    d1 = Math.abs(((Math.pow(1.0D + d / 100D, d2) - 1.0D) * d1 * (1.0D + d / 100D)) / (d / 100D));
                    d4 = Math.abs(d3 - d1);
                    d = Math.abs((d4 * d) / 100D / ((1.0D + d / 100D) * (Math.pow(1.0D + d / 100D, d2) - 1.0D)));
                    final String display = (new StringBuilder("Inflation adjusted cost(Rs.) is : ")).append(String.format("%.0f", new Object[]{
                            Double.valueOf(d3)
                    })).append("\n\nFuture value of my savings(Rs.) is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d1)
                    })).append("\n\nAdditional funds required to meet expenses(Rs.) is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d4)
                    })).append("\n\nAdditional savings required per year(Rs.) is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d)
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
