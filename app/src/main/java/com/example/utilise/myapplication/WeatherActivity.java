package com.example.utilise.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {


    EditText mEcrireVille;
    Button mAjouteVille;
    ListView mListVille;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

   public static String Test = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mEcrireVille = (EditText) findViewById(R.id.add_ville);
        mAjouteVille = (Button) findViewById(R.id.validation);
        mListVille = (ListView) findViewById(R.id.list_ville);


        arrayList = new ArrayList<String>();

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
        mListVille.setAdapter(adapter);

        mAjouteVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this line adds the data of your EditText and puts in your array
                if(mEcrireVille.getText().length() != 0)
                arrayList.add(mEcrireVille.getText().toString());
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        mListVille.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(Test,arrayList.get(position));
                startActivity(intent);
            }

            public void onItemClick(AdapterView<?>adapter,View v, int position){
            }
        });

    }
}
