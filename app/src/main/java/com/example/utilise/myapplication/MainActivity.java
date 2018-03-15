package com.example.utilise.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTextCity, mTextDegree;
    ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextCity = (TextView) findViewById(R.id.textView);
        mTextDegree = (TextView) findViewById(R.id.textView2);
        mImageView = (ImageView) findViewById(R.id.imageView);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString(WeatherActivity.Test);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable(WeatherActivity.Test);
        }

        Weather(newString);

    }

    public void Weather(String ville){
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ville+"&appid=69e2057750aa120eec79a246d9afde3f&units=metric";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main_object = response.getJSONObject("main");
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object = array.getJSONObject(0);
                            String  temp = String.valueOf(main_object.getDouble("temp_max"));
                            String description = object.getString("description");
                            String city = response.getString("name");

                            mTextDegree.setText(temp+"C");
                            mTextCity.setText(city);
                            if(description.equals("clear sky")){
                                mImageView.setImageResource(R.drawable.sun);
                            }

                            if(description.equals("broken clouds")){
                                mImageView.setImageResource(R.drawable.clouds);
                            }

                            if(description.equals("few clouds")){
                                mImageView.setImageResource(R.drawable.clouds);
                            }

                            if(description.equals("mist")){
                                mImageView.setImageResource(R.drawable.mist);
                            }

                            if(description.equals("light rain")){
                                mImageView.setImageResource(R.drawable.rain);
                            }

                            if(description.equals("light intensity shower rain")){
                                mImageView.setImageResource(R.drawable.rain);
                            }



                            if(description.equals("shower rain")){
                                mImageView.setImageResource(R.drawable.rain);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }


}
