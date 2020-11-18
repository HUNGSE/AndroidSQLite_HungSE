package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button add_btn, remove_btn, cancel_btn;
    EditText name_edit;
    ListView lvName;
    ArrayList nameList;
    ArrayList idList;
    ArrayAdapter adapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new
                DataUser(this, "userdb.sql", null, 1);
        idList= new ArrayList();
       nameList= new ArrayList();
        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_edit = findViewById(R.id.name_edit);
        lvName=findViewById(R.id.name_lv);
        getNameList();
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,nameList);
        lvName.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(name_edit.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });
        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.removeUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Succesful", Toast.LENGTH_SHORT).show();
            }
        });
        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private ArrayList getNameList(){
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getALL().iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());
        }
        return nameList;
    }
}