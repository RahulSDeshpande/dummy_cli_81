package in.imates.smartset;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
public class PaidUpValue extends AppCompatActivity {

    Toolbar mToolbar;
    static final int BIRTHDATE_DIALOG_ID = 998;
    static final int DOC_DIALOG_ID = 997;
    static final int FUPDATE_DIALOG_ID = 999;
    DatePicker Birthdatepicker;
    int CurrentDay;
    int CurrentMonth;
    int CurrentYear;
    DatePicker Currentdatepicker;
    int DocDay;
    int DocGetYear;
    int DocMonth;
    DatePicker Docdatepicker;
    int Docyear;
    int FupDay;
    int FupMonth;
    DatePicker Fupdatepicker;
    int Fupyear;
    double Loanvalue;
    String Mode;
    double ModeFactor;
    double ModeValue;
    double PaidUpValue;
    int PlanNo;
    double SurrenderValue;
    int Term;
    private android.app.DatePickerDialog.OnDateSetListener birthdatePickerListener;
    Button btnBack;
    Button btnCalculate;
    android.app.AlertDialog.Builder builder;
    int day;
    private android.app.DatePickerDialog.OnDateSetListener docdatePickerListener;
    private android.app.DatePickerDialog.OnDateSetListener fupdatePickerListener;
    String halfyearly;
    Spinner modeSpinner;
    int month;
    String monthly;
    String quarterly;
    TextView txtAge;
    Button btnBirthdate;
    Button btnCalculationDate;
    Button btnDOC;
    Button btnFUP;
    TextView txtPlanName;
    TextView txtPlanNo;
    TextView txtPremiumAmount;
    TextView txtSA;
    TextView txtTerm;
    int year;
    String yearly;
    Date date;

    public PaidUpValue()
    {
        PaidUpValue = 0.0D;
        ModeFactor = 0.0D;
        SurrenderValue = 0.0D;
        Loanvalue = 0.0D;
        ModeValue = 0.0D;
        fupdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                year = i;
                month = j;
                day = k;
                date = new Date(year - 1900, month, day);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                btnFUP.setText(simpledateformat.format(date));
                Fupdatepicker.init(year, month, day, null);
                Fupyear = year - 1900;
                FupMonth = month;
                FupDay = day;
            }

        };
        birthdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                date = new Date(i - 1900, j, k);
                Object obj = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                btnBirthdate.setText(((SimpleDateFormat) (obj)).format(date));
                Birthdatepicker.init(i, j, k, null);
                obj = Calendar.getInstance();
         //       long l = (new Date(((Calendar) (obj)).get(Calendar.YEAR) - 1900, ((Calendar) (obj)).get(Calendar.MONTH), ((Calendar) (obj)).get(Calendar.DAY_OF_MONTH))).getYear() - datepicker.getYear();
                long l = (CurrentYear-(i-1900));
                txtAge.setText(String.valueOf(l));

            }

        };
        docdatePickerListener = new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                date = new Date(i - 1900, j, k);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                btnDOC.setText(simpledateformat.format(date));
                Docdatepicker.init(i, j, k, null);
                Docyear = i - 1900;
                DocMonth = j + 1;
                DocDay = k;
                DocGetYear = i;
            }

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paidup_value);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PaidUp Value Calculator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        btnBirthdate = (Button) findViewById(R.id.btnbirthdate);
        btnDOC = (Button) findViewById(R.id.btnDoc);
        btnFUP = (Button) findViewById(R.id.btnfupdate);
        btnCalculationDate = (Button) findViewById(R.id.btncurrentdate);
        txtAge = (TextView) findViewById(R.id.txtAge);
        btnBack = (Button) findViewById(R.id.btnback);

        setCurrentDateOnView();

        btnBirthdate.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                showDialog(998);
            }

        });

        btnFUP.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                showDialog(999);
            }

        });
        btnDOC.setOnClickListener(new android.view.View.OnClickListener() {

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

            case 999:
                return new DatePickerDialog(this, fupdatePickerListener, year, month, day);

            case 998:
                return new DatePickerDialog(this, birthdatePickerListener, year, month, day);

            case 997:
                return new DatePickerDialog(this, docdatePickerListener, year, month, day);
        }
    }

    public void setCurrentDateOnView()
    {
        Birthdatepicker = (DatePicker)findViewById(R.id.birthdatepicker);
        Docdatepicker = (DatePicker)findViewById(R.id.Docdatepicker);
        Fupdatepicker = (DatePicker)findViewById(R.id.fupdatepicker);
        Currentdatepicker = (DatePicker) findViewById(R.id.currentdatepicker);
        Object obj = Calendar.getInstance();
        year = ((Calendar) (obj)).get(Calendar.YEAR);
        month = ((Calendar) (obj)).get(Calendar.MONTH);
        day = ((Calendar) (obj)).get(Calendar.DAY_OF_MONTH);
        obj = new Date(year - 1900, month, day);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnBirthdate.setText(simpledateformat.format(((Date) (obj))));
        btnFUP.setText(simpledateformat.format(((Date) (obj))));
        btnCalculationDate.setText(simpledateformat.format(((Date) (obj))));
        btnDOC.setText(simpledateformat.format(((Date) (obj))));
        Birthdatepicker.init(year, month, day, null);
        Fupdatepicker.init(year, month, day, null);
        Docdatepicker.init(year, month, day, null);
        Currentdatepicker.init(year, month, day, null);
        CurrentYear = year - 1900;
        CurrentMonth = month;
        CurrentDay = day;
    }
}//activity
