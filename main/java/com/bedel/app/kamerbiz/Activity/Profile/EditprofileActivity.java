package com.bedel.app.kamerbiz.Activity.Profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.bedel.app.kamerbiz.R;



public class EditprofileActivity extends AppCompatActivity {

    private RelativeLayout relLayout1;
    private RelativeLayout relLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        relLayout1 = (RelativeLayout) findViewById(R.id.relLayout1);
        relLayout2 = (RelativeLayout) findViewById(R.id.relLayout2);
    }

}
