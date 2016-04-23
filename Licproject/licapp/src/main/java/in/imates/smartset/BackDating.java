package in.imates.smartset;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 26/12/15.
 */
public class BackDating extends AppCompatActivity {

    Toolbar mToolbar;
    static final int BIRTHDATE_DIALOG_ID = 998;
    static final int DOC_DIALOG_ID = 997;
    int CurrentDay;
    int CurrentMonth;
    int CurrentYear;
    int DocYear;
    int Docday;
    int Docmonth;
    int FinancialEndDate;
    int FinancialEndMonth;
    int FinancialStartDate;
    int FinancialStartMonth;
    int FinancialYear;
    double ModeValue;
    private android.app.DatePickerDialog.OnDateSetListener birthdatePickerListener;
    DatePicker birthdatepicker;
    Button btnBack;
    Button btnCalculate;
    android.app.AlertDialog.Builder builder;
    DatePicker currentdatepicker;
    int day;
    private android.app.DatePickerDialog.OnDateSetListener docdatePickerListener;
    DatePicker docdatepicker;
    Spinner modespinner;
    int month;
    Spinner spPlans;
    Button btnBirthdate;
    Button btnCurrentDate;
    TextView txtDab;
    Button btnDoc;
    TextView txtPpt;
    TextView txtPremiumAmount;
    TextView txtSumAssured;
    TextView txtTerm;
    int year;
    Date date;

    public BackDating()
    {
        FinancialStartDate = 1;
        FinancialStartMonth = 4;
        FinancialEndDate = 31;
        FinancialEndMonth = 3;
        ModeValue = 0.0D;
        birthdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                date = new Date(i - 1900, j, k);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                btnBirthdate.setText(simpledateformat.format(date));
                birthdatepicker.init(i, j, k, null);
            }

        };
        docdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                date = new Date(i - 1900, j, k);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                btnDoc.setText(simpledateformat.format(date));
                docdatepicker.init(i, j, k, null);
                DocYear = i - 1900;
                Docmonth = j;
                Docday = k;
                boolean flag = true;
                int l = CurrentMonth + 1;
                int i1 = j + 1;
                int j1 = CurrentYear + 1900;
                if (l > 4)
                {
                    if (i != j1)
                    {
                        j = 0;
                    } else
                    if (i1 > l || i1 < 4)
                    {
                        j = 0;
                    } else
                    {
                        j = ((flag) ? 1 : 0);
                        if (i1 == l)
                        {
                            j = ((flag) ? 1 : 0);
                            if (k > CurrentDay)
                            {
                                j = 0;
                            }
                        }
                    }
                } else
                {
                    j = j1 - 1;
                    if (i != j1 && i != j)
                    {
                        j = 0;
                    } else
                    if (i == j && i1 < 4)
                    {
                        j = 0;
                    } else
                    if (i == j1 && i1 > l)
                    {
                        j = 0;
                    } else
                    {
                        j = ((flag) ? 1 : 0);
                        if (i == j1)
                        {
                            j = ((flag) ? 1 : 0);
                            if (i1 == l)
                            {
                                j = ((flag) ? 1 : 0);
                                if (k > CurrentDay)
                                {
                                    j = 0;
                                }
                            }
                        }
                    }
                }
                if (j == 0)
                {
                    btnDoc.setError("Date is Invalid");
                    return;
                } else
                {
                    btnDoc.setError(null);
                    return;
                }
            }

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backdating);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back Dating");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        btnBirthdate = (Button) findViewById(R.id.btnbirthdate);
        btnDoc = (Button) findViewById(R.id.btnDoc);
        btnCurrentDate = (Button) findViewById(R.id.btncurrentdate);
        btnBack = (Button) findViewById(R.id.btnback);

        setCurrentDateOnView();
        btnBirthdate.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                showDialog(998);
            }

        });
        btnDoc.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                showDialog(997);
            }

        });
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

    protected Dialog onCreateDialog(int i)
    {
        switch (i)
        {
            default:
                return null;

            case 998:
                return new DatePickerDialog(this, birthdatePickerListener, year, month, day);

            case 997:
                return new DatePickerDialog(this, docdatePickerListener, year, month, day);
        }
    }

    public void setCurrentDateOnView()
    {
        birthdatepicker = (DatePicker)findViewById(R.id.birthdatepicker);
        docdatepicker = (DatePicker)findViewById(R.id.Docdatepicker);
        currentdatepicker = (DatePicker)findViewById(R.id.currentdatepicker);
        Object obj = Calendar.getInstance();
        year = ((Calendar) (obj)).get(Calendar.YEAR);
        month = ((Calendar) (obj)).get(Calendar.MONTH);
        day = ((Calendar) (obj)).get(Calendar.DAY_OF_MONTH);
        obj = new Date(year - 1900, month, day);
        Object obj1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnBirthdate.setText(((SimpleDateFormat) (obj1)).format(((Date) (obj))));
        btnCurrentDate.setText(((SimpleDateFormat) (obj1)).format(((Date) (obj))));
        btnDoc.setText(((SimpleDateFormat) (obj1)).format(((Date) (obj))));
        birthdatepicker.init(year, month, day, null);
        docdatepicker.init(year, month, day, null);
        currentdatepicker.init(year, month, day, null);
        CurrentYear = year - 1900;
        CurrentMonth = month;
        CurrentDay = day;
        FinancialYear = CurrentYear + 1;
        obj1 = new Date(CurrentYear, FinancialStartMonth, FinancialStartDate);
        Date date = new Date(FinancialYear, FinancialEndMonth, FinancialEndDate);
        if (((Date) (obj1)).getYear() > ((Date) (obj)).getYear() && date.getYear() <= ((Date) (obj)).getYear())
        {
            builder.setMessage("Enter Correct date ").setCancelable(false).setPositiveButton("Close", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            });
            builder.create().show();
        }
    }
}
