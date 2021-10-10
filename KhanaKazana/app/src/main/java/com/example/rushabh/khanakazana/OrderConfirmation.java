package com.example.rushabh.khanakazana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderConfirmation extends AppCompatActivity {


    Button order_more;
    ListView listView;
    TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);


        OrderListAdapter ola=new OrderListAdapter(this, R.layout.custom_order_list, menu1.orderList);
        listView=findViewById(R.id.list_view);
        listView.setAdapter(ola);

        amount=findViewById(R.id.total_amount);
        amount.setText("Total amount - Rs. "+menu1.amount);

        order_more=findViewById(R.id.order_more);

        order_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderConfirmation.this, menu1.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });




    }
}
