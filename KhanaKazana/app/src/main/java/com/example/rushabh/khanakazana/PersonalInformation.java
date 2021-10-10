package com.example.rushabh.khanakazana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalInformation extends AppCompatActivity {
    Button showmenu;
    EditText name,mobile;
    TextView tnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        showmenu = (Button)findViewById(R.id.showmenu);
        name = (EditText)findViewById(R.id.name);
        mobile = (EditText)findViewById(R.id.mobile);
        tnumber = (TextView)findViewById(R.id.tnumber);
        Intent intent = getIntent();
        final String table = intent.getStringExtra("table");
        tnumber.setText(table);

        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cname = name.getText().toString();
                String cmobile = mobile.getText().toString();
                Intent i  = new Intent(getApplicationContext(),menu1.class);
                i.putExtra("name",cname);
                i.putExtra("mobile",cmobile);
                i.putExtra("table",table);
                startActivity(i);
                finish();
            }
        });

    }
}
