package com.example.shreyas.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    String zip = "08852";
    TextView display;
    TextView forecast1;
    TextView forecast2;
    TextView forecast3;
    TextView forecast4;
    ImageView current;
    ImageView fore1;
    ImageView fore2;
    ImageView fore3;
    ImageView fore4;

    EditText code;
    Button click;
    JSONObject weatherCurrent;
    JSONObject weatherForecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        display = (TextView)findViewById(R.id.id_Display);
        code = (EditText)findViewById(R.id.editText);
        click = (Button)findViewById(R.id.id_click);
        forecast1 = (TextView)findViewById(R.id.id_forecast1);
        forecast2 = (TextView)findViewById(R.id.id_forecast2);
        forecast3 = (TextView)findViewById(R.id.id_forecast3);
        forecast4 = (TextView)findViewById(R.id.id_forecast4);

        current = (ImageView)findViewById(R.id.id_Currentpic);
        fore1 = (ImageView)findViewById(R.id.id_forecast1pic);
        fore2 = (ImageView)findViewById(R.id.id_forecast2pic);
        fore3 = (ImageView)findViewById(R.id.id_forecast3pic);
        fore4 = (ImageView)findViewById(R.id.id_forecast4pic);





        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zip =  code.getText().toString();
                AsyncThread weatherThread = new AsyncThread();
                weatherThread.execute();
            }
        });
    }

    public class AsyncThread extends AsyncTask<Void,Void,Void>{



        @Override
        protected Void doInBackground(Void... voids) {
            //Background Thread - Used to download weather info


            try {
                URL keycurrent = new URL ("http://api.openweathermap.org/data/2.5/weather?zip="+zip+",us&APPID=af63210dc86c32be17cf7f55fb1fedfe");
                URL keyforecast = new URL ("http://api.openweathermap.org/data/2.5/forecast?zip="+zip+",us&APPID=af63210dc86c32be17cf7f55fb1fedfe");
                URLConnection connection = keycurrent.openConnection();
                BufferedReader rn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                URLConnection connection1 = keyforecast.openConnection();
                BufferedReader rn1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
                weatherCurrent = new JSONObject(rn.readLine());
                weatherForecast = new JSONObject(rn1.readLine());
                rn.close();
                rn1.close();



            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                display.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main"));
                forecast1.setText(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main"));
                forecast2.setText(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main"));
                forecast3.setText(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main"));
                forecast4.setText(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main"));

                //Sunny
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")){
                    current.setImageResource(R.drawable.sunny);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")){
                    fore1.setImageResource(R.drawable.sunny);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")){
                    fore2.setImageResource(R.drawable.sunny);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")){
                    fore3.setImageResource(R.drawable.sunny);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")){
                    fore4.setImageResource(R.drawable.sunny);
                }
                //Snow
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")){
                    current.setImageResource(R.drawable.snow);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")){
                    fore1.setImageResource(R.drawable.snow);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")){
                    fore2.setImageResource(R.drawable.snow);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")){
                    fore3.setImageResource(R.drawable.snow);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")){
                    fore4.setImageResource(R.drawable.snow);
                }
                //Clouds
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")){
                    current.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")){
                    fore1.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")){
                    fore2.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")){
                    fore3.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")){
                    fore4.setImageResource(R.drawable.cloudy);
                }
                //Haze
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")){
                    current.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")){
                    fore1.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")){
                    fore2.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")){
                    fore3.setImageResource(R.drawable.cloudy);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")){
                    fore4.setImageResource(R.drawable.cloudy);
                }

                //Rain
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")){
                    current.setImageResource(R.drawable.rain);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")){
                    fore1.setImageResource(R.drawable.rain);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")){
                    fore2.setImageResource(R.drawable.rain);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")){
                    fore3.setImageResource(R.drawable.rain);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")){
                    fore4.setImageResource(R.drawable.rain);
                }
                //Thunder
                if(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")){
                    current.setImageResource(R.drawable.thunder);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")){
                    fore1.setImageResource(R.drawable.thunder);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")){
                    fore2.setImageResource(R.drawable.thunder);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")){
                    fore3.setImageResource(R.drawable.thunder);
                }
                if(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")){
                    fore4.setImageResource(R.drawable.thunder);
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
