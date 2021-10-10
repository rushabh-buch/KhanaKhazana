package com.example.rushabh.khanakazana;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class menu1 extends AppCompatActivity {

    Button orderButton, mainDoneButton;

    private static int request_code=1, request_code2=2;

    public static Integer amount;
    static ArrayList<MenuBean> orderList;
    String table;
    String name;
    String mobile;
    PersonalInformation pi = new PersonalInformation();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);


        Intent intent = getIntent();
        table = intent.getStringExtra("table");
        name = intent.getStringExtra("name");
        mobile = intent.getStringExtra("mobile");

        orderList=new ArrayList<>();
        amount=0;

        orderButton=findViewById(R.id.view_order);
        mainDoneButton=findViewById(R.id.main_done_btn);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forward = new Intent(menu1.this, OrderConfirmation.class);
                startActivity(forward);
            }
        });
    }

    public void menuSelection(View view) {

        if (view.getId()==R.id.starters){
            Intent intent=new Intent(this, StartersMenu.class);
            intent.putExtra("table",table);
            intent.putExtra("name",name);
            intent.putExtra("mobile",mobile);
            startActivityForResult(intent,request_code);
        }
        else if (view.getId()==R.id.main_course){
            Intent intent=new Intent(this, MainCourseActivity.class);
            intent.putExtra("table",table);
            intent.putExtra("name",name);
            intent.putExtra("mobile",mobile);
            startActivityForResult(intent,request_code);
        }
        else{
            Intent intent=new Intent(this, DessertActivity.class);
            intent.putExtra("table",table);
            intent.putExtra("name",name);
            intent.putExtra("mobile",mobile);
            startActivityForResult(intent,request_code);
        }
    }

    public void getbill(View view)
    {
//        String final_order=orderList.toString();
//        Toast.makeText(getApplicationContext(),final_order,Toast.LENGTH_LONG).show();
        String f_order="";
        for(int i=0; i<orderList.size(); i++){
            int quantity=orderList.get(i).getQuantity();
            int dish_price=orderList.get(i).getDish_price();
            //amount=quantity*dish_price;
            f_order+=orderList.get(i).getDish_name()+"-"+quantity+"*"+dish_price+",\n";
        }
       String amount = menu1.amount.toString();
        String msg = "Thank You for Choosing Khana Khazana\n Your Bill:-\n"+f_order+"\n Total :- Rs."+amount;
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> m = sms.divideMessage(msg);
        sms.sendMultipartTextMessage(mobile,null,m,null,null);
        //sms.sendTextMessage(mobile,null,"Thank You for Choosing Khana Khazana\n Your Bill:- "+f_order+"\n Total :- Rs."+amount,null,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank You!!");
        builder.setMessage("Please Show the SMS at the billing counter and pay your bill");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mRootReference = firebaseDatabase.getReference();

                DatabaseReference child = mRootReference.child(table);
                child.setValue(null);

                finish();

//               Intent it = new Intent(getApplicationContext(),QRCodeScanner.class);
//               startActivity(it);
               // Toast.makeText(menu1.this, "12345", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //super.finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if((requestCode==request_code) && (resultCode==RESULT_OK)){
            if(amount>0){
                orderButton.setVisibility(View.VISIBLE);
                mainDoneButton.setVisibility(View.VISIBLE);
            }   else    {
                orderButton.setVisibility(View.INVISIBLE);
                mainDoneButton.setVisibility(View.INVISIBLE);
            }
        }
    }
}
