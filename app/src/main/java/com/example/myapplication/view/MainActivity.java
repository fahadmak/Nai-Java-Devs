package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.UsersAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String USER_NAME = "com.example.myapplication.USERNAME";
    public static final String IMAGE = "com.example.myapplication.IMAGE";


    String[] usernameArray = {
            "Paul", "Fahad", "Emmy", "Richo",
            "Kale", "Kaihura", "popcaan", "vybz"
    };
    Integer[] imageIdArray = {
            R.drawable.person1,
            R.drawable.person3,
            R.drawable.person2,
            R.drawable.person4,
            R.drawable.person5,
            R.drawable.person6,
            R.drawable.person7,
            R.drawable.person8
    };
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersAdapter usersAdapter = new UsersAdapter(this, usernameArray, imageIdArray);

        listView = findViewById(R.id.list_view_id);

        listView.setAdapter(usersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                String username = usernameArray[position];
                intent.putExtra(USER_NAME, username);

                Integer image = imageIdArray[position];
                intent.putExtra(IMAGE, image);

                startActivity(intent);
            }
        });

    }
}
