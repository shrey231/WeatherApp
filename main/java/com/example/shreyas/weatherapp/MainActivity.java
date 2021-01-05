package com.example.shreyas.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public double tempConvert(double temp){
        temp = temp-273;
        temp = temp*9;
        temp = temp/5;
        temp = temp+32;

        return temp;

    }


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

    TextView date;
    TextView city;

    TextView quotes;

    TextView currentmin;
    TextView currentmax;
    TextView currenttemp;
    TextView currenttime;

    TextView fore1min;
    TextView fore1max;
    TextView fore2min;
    TextView fore2max;
    TextView fore3min;
    TextView fore3max;
    TextView fore4min;
    TextView fore4max;

    TextView fore1time;
    TextView fore2time;
    TextView fore3time;
    TextView fore4time;

    EditText code;
    Button click;
    JSONObject weatherCurrent;
    JSONObject weatherForecast;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        display = (TextView) findViewById(R.id.id_Display);
        code = (EditText) findViewById(R.id.editText);
        click = (Button) findViewById(R.id.id_click);
        forecast1 = (TextView) findViewById(R.id.id_forecast1);
        forecast2 = (TextView) findViewById(R.id.id_forecast2);
        forecast3 = (TextView) findViewById(R.id.id_forecast3);
        forecast4 = (TextView) findViewById(R.id.id_forecast4);

        fore1time = (TextView) findViewById(R.id.id_fore1time);
        fore2time = (TextView) findViewById(R.id.id_fore2time);
        fore3time = (TextView) findViewById(R.id.id_fore3time);
        fore4time = (TextView) findViewById(R.id.id_fore4time);

        current = (ImageView) findViewById(R.id.id_Currentpic);
        fore1 = (ImageView) findViewById(R.id.id_forecast1pic);
        fore2 = (ImageView) findViewById(R.id.id_forecast2pic);
        fore3 = (ImageView) findViewById(R.id.id_forecast3pic);
        fore4 = (ImageView) findViewById(R.id.id_forecast4pic);

        date = (TextView) findViewById(R.id.id_date);
        city = (TextView) findViewById(R.id.id_city);

        quotes = (TextView) findViewById(R.id.id_quotes);

        currentmin = (TextView) findViewById(R.id.id_currentmin);
        currentmax = (TextView) findViewById(R.id.id_currentmax);
        currenttemp = (TextView) findViewById(R.id.id_currenttemp);
        currenttime = (TextView) findViewById(R.id.id_currenttime);
        fore1min = (TextView) findViewById(R.id.id_fore1min);
        fore1max = (TextView) findViewById(R.id.id_fore1max);
        fore2min = (TextView) findViewById(R.id.id_fore2min);
        fore2max = (TextView) findViewById(R.id.id_fore2max);
        fore3min = (TextView) findViewById(R.id.id_fore3min);
        fore3max = (TextView) findViewById(R.id.id_fore3max);
        fore4min = (TextView) findViewById(R.id.id_fore4min);
        fore4max = (TextView) findViewById(R.id.id_fore4max);






        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                zip =  code.getText().toString();
                AsyncThread weatherThread = new AsyncThread();
                weatherThread.execute();


            }
        });

    }

    public  class AsyncThread extends AsyncTask<Void,Void,Void>{





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

                //Description
                String currentdesc = weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("description");
                String capital1 = currentdesc.substring(0,1).toUpperCase();
                String currentcapital = capital1 + currentdesc.substring(1);
                display.setText(weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main")+"\n"+currentcapital);

                String currentfore1desc = weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
                String capital2 = currentfore1desc.substring(0,1).toUpperCase();
                String currentcapital2 = capital2 + currentfore1desc.substring(1);
                forecast1.setText(weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main")+"\n"+currentcapital2);

                String currentfore2desc = weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description");
                String capital3 = currentfore2desc.substring(0,1).toUpperCase();
                String currentcapital3 = capital3 + currentfore2desc.substring(1);
                forecast2.setText(weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main")+"\n"+currentcapital3);

                String currentfore3desc = weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("description");
                String capital4 = currentfore3desc.substring(0,1).toUpperCase();
                String currentcapital4 = capital4 + currentfore3desc.substring(1);
                forecast3.setText(weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main")+"\n"+currentcapital4);

                String currentfore4desc = weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("description");
                String capital5 = currentfore4desc.substring(0,1).toUpperCase();
                String currentcapital5 = capital5 + currentfore4desc.substring(1);
                forecast4.setText(weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main")+"\n"+currentcapital5);

                //Date
                long unixSeconds2 = Long.valueOf(weatherCurrent.getString("dt"));

                Date date2 = new Date(unixSeconds2*1000L);

                SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, MMMM d, yyyy");
                sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String formattedDate2 = sdf2.format(date2);

                date.setText(formattedDate2);
                //City
                String cities = weatherCurrent.getString("name");
                String formatcity = weatherCurrent.getString("name");
                for(int i =0;i<cities.length();i++){
                    if(cities.charAt(i) == ' '){
                        formatcity = cities.substring(0,i) + "\n" + cities.substring(i);
                    }
                }
                city.setText(formatcity);


                //Time
                long unixSeconds = Long.valueOf(weatherCurrent.getString("dt"));

                Date date1 = new Date(unixSeconds*1000L);

                SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String formattedDate = sdf.format(date1);

                currenttime.setText((CharSequence) formattedDate);

                long fore1value = Long.valueOf(weatherForecast.getJSONArray("list").getJSONObject(0).getString("dt"));

                Date datefore1 = new Date(fore1value*1000L);

                SimpleDateFormat foreformat = new SimpleDateFormat("h:mm a");
                foreformat.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String time1 = foreformat.format(datefore1);

                fore1time.setText((CharSequence) time1);

                long fore2value = Long.valueOf(weatherForecast.getJSONArray("list").getJSONObject(1).getString("dt"));

                Date datefore2 = new Date(fore2value*1000L);

                SimpleDateFormat foreformat2 = new SimpleDateFormat("h:mm a");
                foreformat2.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String time2 = foreformat2.format(datefore2);

                fore2time.setText((CharSequence) time2);

                long fore3value = Long.valueOf(weatherForecast.getJSONArray("list").getJSONObject(2).getString("dt"));

                Date datefore3 = new Date(fore3value*1000L);

                SimpleDateFormat foreformat3 = new SimpleDateFormat("h:mm a");
                foreformat3.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String time3 = foreformat3.format(datefore3);

                fore3time.setText((CharSequence) time3);

                long fore4value = Long.valueOf(weatherForecast.getJSONArray("list").getJSONObject(3).getString("dt"));

                Date datefore4 = new Date(fore4value*1000L);

                SimpleDateFormat foreformat4 = new SimpleDateFormat("h:mm a");
                foreformat4.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String time4 = foreformat4.format(datefore4);

                fore4time.setText(time4);

                //Temperature

                Double tempcurr = weatherCurrent.getJSONObject("main").getDouble("temp");
                int convert = (int)tempConvert(tempcurr);
                currenttemp.setText(Integer.toString(convert)+"\u00b0F");

                Double templow = weatherCurrent.getJSONObject("main").getDouble("temp_min");
                int convert2 = (int)tempConvert(templow);
                currentmin.setText("Low: "+Integer.toString(convert2)+"\u00b0F");

                Double temphigh = weatherCurrent.getJSONObject("main").getDouble("temp_max");
                int convert3 = (int)tempConvert(temphigh);
                currentmax.setText("High: "+Integer.toString(convert3)+"\u00b0F");


                Double templow2 = weatherForecast.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_min");
                int convert4 = (int)tempConvert(templow2);
                fore1min.setText("Low: "+Integer.toString(convert4)+"\u00b0F");

                Double temphigh2 = weatherForecast.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_max");
                int convert5 = (int)tempConvert(temphigh2);
                fore1max.setText("High: "+Integer.toString(convert5)+"\u00b0F");

                Double templow3 = weatherForecast.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_min");
                int convert6 = (int)tempConvert(templow3);
                fore2min.setText("Low: "+Integer.toString(convert6)+"\u00b0F");

                Double temphigh3 = weatherForecast.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_max");
                int convert7 = (int)tempConvert(temphigh3);
                fore2max.setText("High: "+Integer.toString(convert7)+"\u00b0F");

                Double templow4 = weatherForecast.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_min");
                int convert8 = (int)tempConvert(templow4);
                fore3min.setText("Low: "+Integer.toString(convert8)+"\u00b0F");

                Double temphigh4 = weatherForecast.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_max");
                int convert9 = (int)tempConvert(temphigh4);
                fore3max.setText("High: "+Integer.toString(convert9)+"\u00b0F");

                Double templow5 = weatherForecast.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_min");
                int convert10 = (int)tempConvert(templow5);
                fore4min.setText("Low: "+Integer.toString(convert10)+"\u00b0F");

                Double temphigh5 = weatherForecast.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_max");
                int convert11 = (int)tempConvert(temphigh5);
                fore4max.setText("High: "+Integer.toString(convert11)+"\u00b0F");


                //Sunny
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                    current.setImageResource(R.drawable.sunny);
                    quotes.setText("Feel the sun's glory ~Leona");
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                    fore1.setImageResource(R.drawable.sunny);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                    fore2.setImageResource(R.drawable.sunny);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                    fore3.setImageResource(R.drawable.sunny);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                    fore4.setImageResource(R.drawable.sunny);
                }
                //Snow
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                    current.setImageResource(R.drawable.snow);
                    quotes.setText("Do not tempt the blizzard ~Anivia");
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                    fore1.setImageResource(R.drawable.snow);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                    fore2.setImageResource(R.drawable.snow);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                    fore3.setImageResource(R.drawable.snow);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                    fore4.setImageResource(R.drawable.snow);
                }
                //Clouds
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                    current.setImageResource(R.drawable.cloudy);
                    quotes.setText("We may see a couple of clouds but nothing to worry about ~Forecast Janna");
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                    fore1.setImageResource(R.drawable.cloudy);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                    fore2.setImageResource(R.drawable.cloudy);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                    fore3.setImageResource(R.drawable.cloudy);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                    fore4.setImageResource(R.drawable.cloudy);
                }
                //Haze
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                    current.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                    fore1.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                    fore2.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                    fore3.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Haze")) {
                    fore4.setImageResource(R.drawable.mist);
                }
                //Mist
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                    current.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                    fore1.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                    fore2.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                    fore3.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                    fore4.setImageResource(R.drawable.mist);
                }
                //Fog
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                    current.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                    fore1.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                    fore2.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                    fore3.setImageResource(R.drawable.mist);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                    fore4.setImageResource(R.drawable.mist);
                }

                //Rain
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                    current.setImageResource(R.drawable.rain);
                    quotes.setText("A little bit of precipitation is nothing to have a bad hair ~Forecast Janna");
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                    fore1.setImageResource(R.drawable.rain);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                    fore2.setImageResource(R.drawable.rain);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                    fore3.setImageResource(R.drawable.rain);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                    fore4.setImageResource(R.drawable.rain);
                }
                //Thunder
                if (weatherCurrent.getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                    current.setImageResource(R.drawable.thunder);
                    quotes.setText("The thunder calls ~Volibear");
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                    fore1.setImageResource(R.drawable.thunder);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                    fore2.setImageResource(R.drawable.thunder);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                    fore3.setImageResource(R.drawable.thunder);
                }
                if (weatherForecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorm")) {
                    fore4.setImageResource(R.drawable.thunder);


                }
                current.setVisibility(View.VISIBLE);
                fore1.setVisibility(View.VISIBLE);
                fore2.setVisibility(View.VISIBLE);
                fore3.setVisibility(View.VISIBLE);
                fore4.setVisibility(View.VISIBLE);

                weatherForecast = null;
                weatherCurrent = null;




            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Enter a Valid Zip Code",Toast.LENGTH_SHORT).show();

            }catch(Exception e){
                e.printStackTrace();
                display.setText("Current: ");
                forecast1.setText("Forecast: ");
                forecast2.setText("Forecast: ");
                forecast3.setText("Forecast: ");
                forecast4.setText("Forecast: ");

                current.setVisibility(View.INVISIBLE);
                fore1.setVisibility(View.INVISIBLE);
                fore2.setVisibility(View.INVISIBLE);
                fore3.setVisibility(View.INVISIBLE);
                fore4.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext(),"Enter a Valid Zip Code",Toast.LENGTH_SHORT).show();
            }


        }
    }
}
