package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IncompleteFormsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomplete_forms);
        ListView v = (ListView)findViewById(R.id.inc_list);
        FormAdapter ad = new FormAdapter(this, 0, KumuApp.getAppStorage().getUnfinishedForms());
        v.setAdapter(ad);

        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Form f = (Form)adapterView.getItemAtPosition(i);
                KumuApp.getAppStorage().setCurrentForm(f);
                Intent in = new Intent(IncompleteFormsActivity.this, FormActivity.class);
                startActivity(in);
            }
        });

        super.onCreateDrawer();
    }
}
