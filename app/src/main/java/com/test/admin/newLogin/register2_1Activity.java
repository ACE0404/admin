package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.test.admin.R;

import java.util.ArrayList;
import java.util.List;

public class register2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step2);

        final Bundle bundle1 = this.getIntent().getExtras();
        final String college = bundle1.getString("college");
        ImageButton btn1 = (ImageButton)findViewById(R.id.imgBtn1);
        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register2_1Activity.this,register1_1Activity.class);
                startActivity(intent);
            }
        });
        lv1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(register2_1Activity.this,register3_1Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("college",college);
                        bundle.putString("grade",getData().get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
        );
    }
    private List<String> getData()
    {
        List<String> data = new ArrayList<String>();
        data.add("2012级");
        data.add("2013级");
        data.add("2014级");
        data.add("2015级");
        data.add("2016级");
        data.add("2017级");
        data.add("2018级");
        data.add("2019级");
        data.add("2020级");

        return data;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register2_1Activity.this, register1_1Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
