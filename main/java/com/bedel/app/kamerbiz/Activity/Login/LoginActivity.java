package com.bedel.app.kamerbiz.Activity.Login;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bedel.app.kamerbiz.Activity.Boot.BootActivity;
import com.bedel.app.kamerbiz.Activity.Home.HomeActivity;
import com.bedel.app.kamerbiz.R;
import com.bedel.app.kamerbiz.Tools.DialogTools;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int RC_SIGN_IN = 001;
    private static final String TAG = LoginActivity.class.getSimpleName();


    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private LinearLayout firstcontainer,secondcontainer;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private EditText phoneNumberField, smsCodeVerificationField;
    private Button startVerficationButton, verifyPhoneButton;

    private String verificationid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFirebaseAuth();
        setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        phoneNumberField = findViewById(R.id.phone_number_edt);
        firstcontainer = findViewById(R.id.firstcontainer);
        secondcontainer = findViewById(R.id.secondcontainer);
        smsCodeVerificationField = findViewById(R.id.sms_code_edt);
        startVerficationButton = findViewById(R.id.start_auth_button);
        verifyPhoneButton = findViewById(R.id.verify_auth_button);
        startVerficationButton.setOnClickListener(this);
        verifyPhoneButton.setOnClickListener(this);

    }

    public void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };
    }



    private boolean validateSMSCode(){
    String code = smsCodeVerificationField.getText().toString();
    if (TextUtils.isEmpty(code)) {
        smsCodeVerificationField.setError("Enter verification Code.");
        return false;
    }

    return true;
}


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.start_auth_button:
                if (!validatePhoneNumberAndCode()) {
                 return;
                }
                DialogTools.showConfirm(LoginActivity.this, "Nous allons verifiez le numero de téléphone:\n\n"+phoneNumberField.getText().toString()+"\n\n Es-ce correct,ou voulez-vous modifiez le numero?", new Runnable() {
                @Override
                public void run() {
                    startPhoneNumberVerification(phoneNumberField.getText().toString());
                    DialogTools.showProgress(LoginActivity.this, "Connexion...");
                }
                });


                break;
            case  R.id.verify_auth_button:
                if (!validateSMSCode()) {
                    return;
                }

                verifyPhoneNumberWithCode(verificationid, smsCodeVerificationField.getText().toString());
                break;
        }

    }

    //verified userenterinfos
    private boolean validatePhoneNumberAndCode() {
        String phoneNumber = phoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }

    //first choice verified phonenumber(firebase take code in your phone auttomatically)
    private void startPhoneNumberVerification(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            DialogTools.hideProgress();
                            FirebaseUser user = task.getResult().getUser();
                            finish();
                            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));


                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                DialogTools.hideProgress();
                                smsCodeVerificationField.setError("Invalid code.");

                            }

                        }
                    }
                });
    }

    //second choice verified phonenumber (user enter code)
    private void verifyPhoneNumberWithCode(String verificationId, String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                DialogTools.hideProgress();
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                firstcontainer.setVisibility(View.GONE);
                secondcontainer.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);
                verificationid = verificationId;
                mResendToken = forceResendingToken;
            }
        };

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}

