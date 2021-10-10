package com.example.rushabh.retrivalapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String key1,value;
    ListView listView, listView2, listView3, listView4, listView5;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRoot = firebaseDatabase.getReference();

    public static ArrayList<String> table1_list=new ArrayList<>();
    public static ArrayList<String> table2_list=new ArrayList<>();
    public static ArrayList<String> table3_list=new ArrayList<>();
    public static ArrayList<String> table4_list=new ArrayList<>();
    public static ArrayList<String> table5_list=new ArrayList<>();

    ArrayAdapter<String> arrayAdapter, arrayAdapter2, arrayAdapter3, arrayAdapter4, arrayAdapter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        listView2=findViewById(R.id.listView2);
        listView3=findViewById(R.id.listView3);
        listView4=findViewById(R.id.listView4);
        listView5=findViewById(R.id.listView5);


        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,table1_list);
        arrayAdapter2=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,table2_list);
        arrayAdapter3=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,table3_list);
        arrayAdapter4=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,table4_list);
        arrayAdapter5=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,table5_list);


        listView.setAdapter(arrayAdapter);
        listView2.setAdapter(arrayAdapter2);
        listView3.setAdapter(arrayAdapter3);
        listView4.setAdapter(arrayAdapter4);
        listView5.setAdapter(arrayAdapter5);

         mRoot.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        String key = ds.getKey();

                        switch(key)
                        {
                            case "1":
                                table1_list.clear();
                                for(DataSnapshot d:ds.getChildren())
                                {
                                    key1 = d.getKey();
                                    value = d.getValue().toString();

                                    table1_list.add(key1+"-"+value);
                                    arrayAdapter.notifyDataSetChanged();
                                }


                                break;
                            case "2":
                                table2_list.clear();
                                for(DataSnapshot d:ds.getChildren())
                                {
                                    table2_list.add(d.getKey()+"-"+d.getValue());
                                    arrayAdapter2.notifyDataSetChanged();
                                }
                                break;
                            case "3":
                                table3_list.clear();
                                for(DataSnapshot d:ds.getChildren())
                                {
                                    table3_list.add(d.getKey()+"-"+d.getValue());
                                    arrayAdapter3.notifyDataSetChanged();
                                }
                                break;
                            case "4":
                                table4_list.clear();
                                for(DataSnapshot d:ds.getChildren())
                                {
                                    table4_list.add(d.getKey()+"-"+d.getValue());
                                    arrayAdapter4.notifyDataSetChanged();
                                }
                                break;
                            case "5":
                                table5_list.clear();
                                for(DataSnapshot d:ds.getChildren())
                                {
                                    table5_list.add(d.getKey()+"-"+d.getValue());
                                    arrayAdapter5.notifyDataSetChanged();
                                }
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),"No data Found", Toast.LENGTH_SHORT).show();
                                break;

                        }

                    }
            }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }


         });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp=(TextView) view;
                Toast.makeText(MainActivity.this, temp.getText()+" removed from list.", Toast.LENGTH_SHORT).show();
                table1_list.remove(temp.getText());
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp=(TextView) view;
                Toast.makeText(MainActivity.this, temp.getText()+" removed from list.", Toast.LENGTH_SHORT).show();
                table2_list.remove(temp.getText());
                arrayAdapter2.notifyDataSetChanged();
            }
        });
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp=(TextView) view;
                Toast.makeText(MainActivity.this, temp.getText()+" removed from list.", Toast.LENGTH_SHORT).show();
                table3_list.remove(temp.getText());
                arrayAdapter3.notifyDataSetChanged();
            }
        });
        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp=(TextView) view;
                Toast.makeText(MainActivity.this, temp.getText()+" removed from list.", Toast.LENGTH_SHORT).show();
                table4_list.remove(temp.getText());
                arrayAdapter4.notifyDataSetChanged();
            }
        });
        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temp=(TextView) view;
                Toast.makeText(MainActivity.this, temp.getText()+" removed from list.", Toast.LENGTH_SHORT).show();
                table5_list.remove(temp.getText());
                arrayAdapter5.notifyDataSetChanged();
            }
        });
    }


}
