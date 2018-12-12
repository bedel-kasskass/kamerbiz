package com.bedel.app.kamerbiz.Activity.Post;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bedel.app.kamerbiz.Entity.Enterprise;
import com.bedel.app.kamerbiz.R;

public class DetailsEntrepriseActivity extends AppCompatActivity {
    Toolbar toolbar;
    Enterprise enterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enterprise);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enterprise = (Enterprise) getIntent().getExtras().getSerializable("Enterprise");
        getSupportActionBar().setTitle(enterprise.getName());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
