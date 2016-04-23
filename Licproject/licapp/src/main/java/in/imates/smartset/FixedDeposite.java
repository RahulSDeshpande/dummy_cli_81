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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by root on 26/12/15.
 */
public class FixedDeposite extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    EditText et_amtinvested;
    EditText et_invrate;
    EditText et_term;
    Spinner modespinner;
    EditText et_taxrate;
    AlertDialog.Builder builder,builder1;
    int Mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_deposite);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fixed Deposite");
        et_amtinvested = (EditText) findViewById(R.id.amtinvested);
        et_invrate = (EditText) findViewById(R.id.invrate);
        et_term = (EditText) findViewById(R.id.term);
        modespinner = (Spinner) findViewById(R.id.modespinner);
        et_taxrate = (EditText) findViewById(R.id.taxrate);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        modespinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if (i == 0)
                {
                    Mode = 1;
                } else
                {
                    if (i == 1)
                    {
                        Mode = 2;
                        return;
                    }
                    if (i == 2)
                    {
                        Mode = 4;
                        return;
                    }
                    if (i == 3)
                    {
                        Mode = 12;
                        return;
                    }
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_amtinvested.getText().toString().equals("") || et_invrate.getText().toString().equals("")
                        || et_term.getText().toString().equals("") || et_taxrate.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {

                    double d = Double.parseDouble(et_amtinvested.getText().toString());
                    double d2 = Double.parseDouble(et_invrate.getText().toString());
                    double d3 = Double.parseDouble(et_term.getText().toString());
                    double d1 = Double.parseDouble(et_taxrate.getText().toString());
                    d2 = d * Math.pow(1.0D + d2 / (double) (Mode * 100), (double) Mode * d3);
                    d1 = (d1 * 1.145D) / 100D;
                    final String display = (new StringBuilder("Maturity Amount is : ")).append(String.format("%.0f", new Object[]{
                            Double.valueOf(d2)
                    })).append("\nAfter Tax Maturity is : ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d2 - (d2 - d) * d1)
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
