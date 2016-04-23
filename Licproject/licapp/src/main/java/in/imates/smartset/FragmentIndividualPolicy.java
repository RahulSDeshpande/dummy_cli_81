package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by root on 2/1/16.
 */
public class FragmentIndividualPolicy extends Fragment implements View.OnClickListener{

    EditText et_policyno;
    Button btnPremiumstatus;
    Button btnRevivalamount;
    Button btnBonusamount;
    Button btnLoanamount;
    Button btnNomdetails;
    AlertDialog.Builder builder;
    String policyNo;
    String SMS = null;
    public static final String LicPhone = "56767877";

    String altmsg = "If You Accept Then SMS Charges May Apply As Per Your Network Provider";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_individual_policy,container,false);

        et_policyno = (EditText) rootView.findViewById(R.id.etPolicyno);
        et_policyno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        btnPremiumstatus = (Button) rootView.findViewById(R.id.btnPremiumstatus);
        btnRevivalamount = (Button) rootView.findViewById(R.id.btnRevivalamount);
        btnBonusamount = (Button) rootView.findViewById(R.id.btnBonusamount);
        btnLoanamount = (Button) rootView.findViewById(R.id.btnLoanamount);
        btnNomdetails = (Button) rootView.findViewById(R.id.btnNomdetails);
        btnPremiumstatus.setOnClickListener(this);
        btnRevivalamount.setOnClickListener(this);
        btnBonusamount.setOnClickListener(this);
        btnLoanamount.setOnClickListener(this);
        btnNomdetails.setOnClickListener(this);
       policyNo = et_policyno.getText().toString();
        builder = new AlertDialog.Builder(getActivity());
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPremiumstatus:
                SMS = "ASKLIC " + policyNo + " PREMIUM";
                showAlert(SMS);
                break;
            case R.id.btnRevivalamount:
                SMS = "ASKLIC " + policyNo + " REVIVAL";
                showAlert(SMS);
                break;
            case R.id.btnBonusamount:
                SMS = "ASKLIC " + policyNo + " BONUS";
                showAlert(SMS);
                break;
            case R.id.btnLoanamount:
                SMS = "ASKLIC " + policyNo + " LOAN";
                showAlert(SMS);
                break;
            case R.id.btnNomdetails:
                SMS = "ASKLIC " + policyNo + " NOM";
                showAlert(SMS);
                break;
        }

    }

    private void showAlert(final String msg) {
        builder.setTitle("Alert...").setMessage(altmsg).setCancelable(false).setPositiveButton("Close", new android.content.DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.cancel();
            }

        }).setNegativeButton("Accept", new android.content.DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialoginterface, int i) {
                    sendSMS(msg);
            }

        });
        builder.create().show();
    }

    public void sendSMS(String msg) {
     //   ((TelephonyManager) getSystemService("phone")).getDeviceId();
        SmsManager smsManager1 = SmsManager.getDefault();
        smsManager1.sendTextMessage(LicPhone, null, msg, null, null);
        Toast.makeText(getActivity(), "SMS Send Successfully", Toast.LENGTH_LONG).show();

    }
}
