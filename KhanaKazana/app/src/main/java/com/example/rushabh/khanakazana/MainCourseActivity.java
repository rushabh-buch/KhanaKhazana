package com.example.rushabh.khanakazana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainCourseActivity extends AppCompatActivity {

    Button add1, sub1, add2, sub2, doneBtn;
    TextView quantity1, quantity2, dish_name1, dish_name2, price1, price2;
    int count1, count2;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course);
        add1=findViewById(R.id.plus1);
        add2=findViewById(R.id.plus2);
        sub1=findViewById(R.id.sub1);
        sub2=findViewById(R.id.sub2);
        doneBtn=findViewById(R.id.done_btn);

        quantity1=findViewById(R.id.quantity1);
        quantity2=findViewById(R.id.quantity2);
        dish_name1=findViewById(R.id.dish_name1);
        dish_name2=findViewById(R.id.dish_name2);
        price1=findViewById(R.id.dish_price1);
        price2=findViewById(R.id.dish_price2);


        Intent intent = getIntent();
        final String table = intent.getStringExtra("table");
        final String name = intent.getStringExtra("name");
        final String mobile = intent.getStringExtra("mobile");

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1=Integer.parseInt(quantity1.getText().toString());
                if (count1 >= 0) {
                    count1++;
                    quantity1.setText(""+count1);
                }   else    {
                    count1=0;
                    quantity1.setText(""+count1);
                }
            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1=Integer.parseInt(quantity1.getText().toString());
                if (count1 > 0){
                    count1--;
                    quantity1.setText(""+count1);
                }   else    {
                    count1=0;
                    quantity1.setText(""+count1);
                }
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2=Integer.parseInt(quantity2.getText().toString());
                if (count2 >= 0){
                    count2++;
                    quantity2.setText(""+count2);
                }   else    {
                    count2=0;
                    quantity2.setText(""+count2);
                }
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2=Integer.parseInt(quantity2.getText().toString());
                if (count2 > 0){
                    count2--;
                    quantity2.setText(""+ count2);
                }   else    {
                    count2=0;
                    quantity2.setText(""+count2);
                }
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference mchild = mRootReference.child(table);
//                DatabaseReference mchildname = mchild.child("name");
//                mchildname.setValue(name);
//                DatabaseReference mchilemobile =mchild.child("mobile");
//                mchilemobile.setValue(mobile);
//                int amt2=0,amt1=0;



                if(Integer.parseInt(quantity1.getText().toString()) > 0){
                    Integer price=Integer.parseInt(price1.getText().toString().replaceAll("[^0-9]",""));
                    Integer quantity=Integer.parseInt(quantity1.getText().toString());
                    menu1.amount+=(price*quantity);

                    MenuBean menuBean=new MenuBean(dish_name1.getText().toString(), price, quantity);
                    menu1.orderList.add(menuBean);
                    /* Database insernatiom...............*/


                    DatabaseReference d1 = mchild.child(dish_name1.getText().toString());
                    d1.setValue(quantity1.getText().toString());
//                    DatabaseReference qty = d1.child("Quantity");
//                    qty.setValue(quantity1.getText().toString());
//                    DatabaseReference pricedb = d1.child("Price");
//                    amt1 = price*quantity;
//                    pricedb.setValue(amt1);



                    Toast.makeText(getApplicationContext(),"Order placed successfully :)",Toast.LENGTH_SHORT).show();

                }
                if(Integer.parseInt(quantity2.getText().toString()) > 0){
                    Integer price=Integer.parseInt(price2.getText().toString().replaceAll("[^0-9]",""));
                    Integer quantity=Integer.parseInt(quantity2.getText().toString());
                    menu1.amount+=(price*quantity);

                    MenuBean menuBean=new MenuBean(dish_name2.getText().toString(), price, Integer.parseInt(quantity2.getText().toString()));
                    menu1.orderList.add(menuBean);
                    /* Database insernatiom...............*/


                    DatabaseReference d1 = mchild.child(dish_name2.getText().toString());
                    d1.setValue(quantity1.getText().toString());
//                    DatabaseReference qty = d1.child("Quantity");
//                    qty.setValue(quantity2.getText().toString());
//                    DatabaseReference pricedb = d1.child("Price");
//                    amt2 = price*quantity;
//                    pricedb.setValue(amt2);



                    Toast.makeText(getApplicationContext(),"Order placed successfully :)",Toast.LENGTH_SHORT).show();

                }

//                DatabaseReference price = mchild.child("Price");
//                int finalamt = amt1+amt2;
//                price.setValue(finalamt);

                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        super.finish();
    }


}
