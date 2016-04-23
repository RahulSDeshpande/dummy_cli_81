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
public class HabitSaving extends AppCompatActivity {
    Toolbar mToolbar;
    Button btnBack;
    Button btnCalculate;
    Spinner ddlHabit;
    EditText txtDailyExpense;
    EditText txtHabit;
    EditText txtHabitSince;
    EditText txtName;
    EditText txtPresentAge;
    AlertDialog.Builder builder,builder1;
    String Habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_saving);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Habit Saving Calculator");
        txtName = (EditText)findViewById(R.id.txtName);
        txtPresentAge = (EditText)findViewById(R.id.txtPresentAge);
        txtHabitSince = (EditText)findViewById(R.id.txtHabitSince);
        txtDailyExpense = (EditText)findViewById(R.id.txtDailyExpense);
        ddlHabit = (Spinner)findViewById(R.id.ddlHabit);
        builder = new AlertDialog.Builder(this);
        builder1 = new AlertDialog.Builder(this);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        ddlHabit.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if (i == 0)
                {
                    Habit = "Tobacco";
                } else
                {
                    if (i == 1)
                    {
                        Habit = "Smoking";
                        return;
                    }
                    if (i == 2)
                    {
                        Habit = "Drink";
                        return;
                    }
                    if (i == 3)
                    {
                        Habit = "Drug";
                        return;
                    }
                    if (i == 4)
                    {
                        Habit = "Other";
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
                if (txtName.getText().toString().equals("") || txtPresentAge.getText().toString().equals("")
                        || txtHabitSince.getText().toString().equals("") || txtDailyExpense.getText().toString().equals("")) {
                    builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false).
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder1.create().show();
                } else {

                    double d = Double.parseDouble(txtPresentAge.getText().toString());
                    double d2 = Double.parseDouble(txtHabitSince.getText().toString());
                    double d1 = Double.parseDouble(txtDailyExpense.getText().toString()) * 365D;
                    d2 = ((Math.pow(1.0800000000000001D, d2) - 1.0D) * d1 * 1.0800000000000001D) / 0.10000000000000001D;
                    String name = txtName.getText().toString();
                    d = ((Math.pow(1.0800000000000001D, 65D - d) - 1.0D) * d1 * 1.0800000000000001D) / 0.10000000000000001D;
                    final String display = (new StringBuilder("Dear ")).append(name).append(" ,due to as your ").append(Habit).append(" habit you cant save as ").append(String.format("%.0f", new Object[]{
                            Double.valueOf(d2)
                    })).append(" and also if you left your bad habit as ").append(Habit).append(" then you save amount in future ").append(String.format("%.2f", new Object[]{
                            Double.valueOf(d)
                    })).append(" which helpful for you & your loving family.so ALERT TODAY.").toString();
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
