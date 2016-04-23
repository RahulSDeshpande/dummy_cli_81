package in.imates.smartset;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by root on 25/12/15.
 */
public class LateFee extends AppCompatActivity {

    private Toolbar mToolbar;
    static final int APPLICATION_DATE_DIALOG_ID = 998;
    static final int FUPDATE_DIALOG_ID = 999;
    static final int PREMIUMPAID_DATE_DIALOG_ID = 997;
    int ApplicationDate;
    int ApplicationMonth;
    int ApplicationYear;
    Date Applicationdate;
    private android.app.DatePickerDialog.OnDateSetListener ApplicationdatePickerListener;
    int CurrentDay;
    int CurrentMonth;
    int Currentyear;
    int FupDay;
    int FupMonth;
    Date Fupdate;
    final Calendar obj;
    int Fupyear;
    int Mode;
    Date PremiumPaidDate;
    private android.app.DatePickerDialog.OnDateSetListener PremiumpaiddatepickerListener;
    DatePicker applicationdatepicker;
    Button btnBack;
    Button btnCalculate;
    android.app.AlertDialog.Builder builder;
    int day;
    private android.app.DatePickerDialog.OnDateSetListener fupdatePickerListener;
    DatePicker fupdatepicker;
    Spinner modespinner;
    int month;
    DatePicker premiumpaiddatepicker;
    Button btnApplicationdate;
    EditText txtName;
    EditText txtPremiumAmount;
    Button btnPremiumPaidDate;
    Button btnfupdate;
    int year;
    Date date;
    public LateFee()
    {
        obj = Calendar.getInstance();
        fupdatePickerListener = new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                year = i;
                month = j;
                day = k;
               date = new Date(year - 1900, month, day);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Fupdate = date;
                btnfupdate.setText(simpledateformat.format(Fupdate));
                fupdatepicker.init(year, month, day, null);
                Fupyear = year - 1900;
                FupMonth = month;
                FupDay = day;
            }



        };
        PremiumpaiddatepickerListener = new android.app.DatePickerDialog.OnDateSetListener() {



            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                year = i;
                month = j;
                day = k;
                date = new Date(year - 1900, month, day);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                PremiumPaidDate = date;
                btnPremiumPaidDate.setText(simpledateformat.format(PremiumPaidDate));
                premiumpaiddatepicker.init(year, month, day, null);
                Currentyear = year - 1900;
                CurrentMonth = month;
                CurrentDay = day;
            }



        };
        ApplicationdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {



            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                date = new Date(i - 1900, j, k);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Applicationdate = date;
                btnApplicationdate.setText(simpledateformat.format(ApplicationDate));
                applicationdatepicker.init(i, j, k, null);
                ApplicationYear = i;
                ApplicationMonth = j;
                ApplicationDate = k;
            }



        };
    }

    protected Dialog onCreateDialog(int i)
    {
        switch (i)
        {
            default:
                return null;

            case 999:
                return new DatePickerDialog(this, fupdatePickerListener, year, month, day);

            case 998:
                return new DatePickerDialog(this, ApplicationdatePickerListener, year, month, day);

            case 997:
                return new DatePickerDialog(this, PremiumpaiddatepickerListener, year, month, day);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late_fee);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Late Fee Calculator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        btnApplicationdate = (Button) findViewById(R.id.btnApplicationdate);
        btnfupdate = (Button) findViewById(R.id.btnfupdate);
        btnPremiumPaidDate = (Button) findViewById(R.id.btnPremiumPaidDate);
        btnBack = (Button) findViewById(R.id.btnback);


       setCurrentDateOnView();
        btnfupdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                showDialog(999);
            }

        });
        btnApplicationdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                showDialog(998);
            }
        });
        btnPremiumPaidDate.setOnClickListener(new View.OnClickListener() {

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

    public void setCurrentDateOnView()
    {
        fupdatepicker = (DatePicker)findViewById(R.id.fupdatepicker);
        applicationdatepicker = (DatePicker)findViewById(R.id.applicationdatepicker);
        premiumpaiddatepicker = (DatePicker)findViewById(R.id.Premiumpaiddatepicker);
        year = obj.get(Calendar.YEAR);
        month = obj.get(Calendar.MONTH);
        day = obj.get(Calendar.DAY_OF_MONTH);

        Log.e("current date",year+"-"+(month+1)+"-"+day);
       Date obj1 = new Date(year - 1900, month, day);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnfupdate.setText(simpledateformat.format(obj1));
        btnApplicationdate.setText(simpledateformat.format(obj1));
        btnPremiumPaidDate.setText(simpledateformat.format(obj1));
        fupdatepicker.init(year, month, day, null);
        applicationdatepicker.init(year, month, day, null);
        premiumpaiddatepicker.init(year, month, day, null);
    }
}
