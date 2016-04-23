package in.imates.smartset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.gsm.SmsManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by root on 2/1/16.
 */
public class FragmentPensionPolicy extends Fragment implements View.OnClickListener {

    EditText et_policyno;
    Button btnPolicystatus;
    Button btnExtcerdue;
    Button btnAnnamt;
    Button btnChkretinf;
    Button btnAnnreldate;
    Button btnAnnpmtthry;
    AlertDialog.Builder builder;
    String policyNo;
    String SMS = null;
    public static final String LicPhone = "56767877";
    String altmsg = "If You Accept Then SMS Charges May Apply As Per Your Network Provider";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pension_policy,container,false);
        et_policyno = (EditText) rootView.findViewById(R.id.policyno);
        et_policyno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        btnPolicystatus = (Button) rootView.findViewById(R.id.btnPolicystatus);
        btnExtcerdue = (Button) rootView.findViewById(R.id.btnExtcerdue);
        btnAnnamt = (Button) rootView.findViewById(R.id.btnAnnamt);
        btnChkretinf = (Button) rootView.findViewById(R.id.btnChkretinf);
        btnAnnreldate = (Button) rootView.findViewById(R.id.btnAnnreldate);
        btnAnnpmtthry = (Button) rootView.findViewById(R.id.btnAnnpmtthry);
        btnPolicystatus.setOnClickListener(this);
        btnExtcerdue.setOnClickListener(this);
        btnAnnamt.setOnClickListener(this);
        btnChkretinf.setOnClickListener(this);
        btnAnnreldate.setOnClickListener(this);
        btnAnnpmtthry.setOnClickListener(this);
        policyNo = et_policyno.getText().toString();
        builder = new AlertDialog.Builder(getActivity());


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPolicystatus:
                SMS = "LICPENSION " + policyNo + " STAT";
                showAlert(SMS);
                break;
            case R.id.btnExtcerdue:
                SMS = "LICPENSION " + policyNo + " ECDUE";
                showAlert(SMS);
                break;
            case R.id.btnAnnamt:
                SMS = "LICPENSION " + policyNo + " AMOUNT";
                showAlert(SMS);
                break;
            case R.id.btnChkretinf:
                SMS = "LICPENSION " + policyNo + " CHQRET";
                showAlert(SMS);
                break;
            case R.id.btnAnnreldate:
                SMS = "LICPENSION " + policyNo + " ANNPD";
                showAlert(SMS);
                break;
            case R.id.btnAnnpmtthry:
                SMS = "LICPENSION " + policyNo + " PDTHRU";
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
        Toast.makeText(getActivity(),"SMS Send Successfully",Toast.LENGTH_LONG).show();
    }
}
