package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Hashtable;

/**
 * Created by root on 26/12/15.
 */
public class SettlementOption extends AppCompatActivity{
    double InstallmentMode;
    double MarturitySettlement;
    double MaturityAmount;
    Hashtable<Integer, Hashtable<Double, Double>> Rate;
    Button btnBack;
    Button btnCalculate;
    AlertDialog.Builder builder,builder1;
    RadioButton halfyearly;
    RadioGroup radioInstallmentMode;
    double rate;
    EditText txtMaturityAmount;
    EditText txtNoofInvestment;
    RadioButton yearly;
    Toolbar mToolbar;

    /* renamed from: sb.group.a1licallinone.SettlementOption.1 */
    class C05031 implements View.OnClickListener {
        C05031() {
        }

        public void onClick(View v) {
            SettlementOption.this.InstallmentMode = 12.0d;
            SettlementOption.this.rate = ((Double) ((Hashtable) SettlementOption.this.Rate.get(Integer.valueOf(Integer.parseInt(SettlementOption.this.txtNoofInvestment.getText().toString())))).get(Double.valueOf(SettlementOption.this.InstallmentMode))).doubleValue();
        }
    }

    /* renamed from: sb.group.a1licallinone.SettlementOption.2 */
    class C05042 implements View.OnClickListener {
        C05042() {
        }

        public void onClick(View v) {
            SettlementOption.this.InstallmentMode = 6.0d;
            SettlementOption.this.rate = ((Double) ((Hashtable) SettlementOption.this.Rate.get(Integer.valueOf(Integer.parseInt(SettlementOption.this.txtNoofInvestment.getText().toString())))).get(Double.valueOf(SettlementOption.this.InstallmentMode))).doubleValue();
        }
    }

    /* renamed from: sb.group.a1licallinone.SettlementOption.3 */
    class C05063 implements View.OnClickListener {

        /* renamed from: sb.group.a1licallinone.SettlementOption.3.1 */
        class C05051 implements DialogInterface.OnClickListener {
            C05051() {
            }

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }

        C05063() {
        }

        public void onClick(View v) {
            if (txtMaturityAmount.getText().toString().equals("") || txtNoofInvestment.getText().toString().equals("")
                    ||radioInstallmentMode.getCheckedRadioButtonId() == -1) {
                builder1.setTitle("Alert...").setMessage("Please Fill All Details").setCancelable(false).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder1.create().show();
            } else {
                SettlementOption.this.MaturityAmount = Double.parseDouble(SettlementOption.this.txtMaturityAmount.getText().toString());
                SettlementOption.this.MarturitySettlement = (SettlementOption.this.MaturityAmount / 1000.0d) * SettlementOption.this.rate;
                SettlementOption.this.builder.setMessage("Maturity Settlement value is : " + SettlementOption.this.MarturitySettlement).setCancelable(false).setPositiveButton("Close", new C05051());
                SettlementOption.this.builder.create().show();
            }
        }
    }

    /* renamed from: sb.group.a1licallinone.SettlementOption.4 */
    class C05074 implements View.OnClickListener {
        C05074() {
        }

        public void onClick(View v) {
            SettlementOption.this.finish();
        }
    }

    public SettlementOption() {
        this.MaturityAmount = 0.0d;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement_option);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settlement Option");
        this.txtMaturityAmount = (EditText) findViewById(R.id.txtMaturityAmount);
        this.txtNoofInvestment = (EditText) findViewById(R.id.txtNoOfInstallment);
        this.radioInstallmentMode = (RadioGroup) findViewById(R.id.radioInstallmentMode);
        this.yearly = (RadioButton) findViewById(R.id.yearly);
        this.halfyearly = (RadioButton) findViewById(R.id.halfyearly);
        this.btnCalculate = (Button) findViewById(R.id.btnCalculate);
        this.btnBack = (Button) findViewById(R.id.btnback);
        this.builder = new AlertDialog.Builder(this);
        this.builder1 = new AlertDialog.Builder(this);
        this.Rate = PremiumPlan.GetBonusRate("maturitysettlement.txt", getApplicationContext());
        this.yearly.setOnClickListener(new C05031());
        this.halfyearly.setOnClickListener(new C05042());
        this.btnCalculate.setOnClickListener(new C05063());
        txtNoofInvestment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String value = txtNoofInvestment.getText().toString();
                    int val = Integer.parseInt(String.valueOf(value));
                    Log.e("kjksfjhgkfhg", String.valueOf(val));
                    if (val < 2 || val > 10) {
                        txtNoofInvestment.setError("Please Enter Value Between 2-10");
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please Enter Value",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        this.btnBack.setOnClickListener(new C05074());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}