package com.example.skeny.smartattendanceapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AttendanceInfo extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listview;
    DataAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_info);
        dataAdapter=new DataAdapter(this, R.layout.row_layout);
        listview=findViewById(R.id.listview);
        listview.setAdapter(dataAdapter);
        //getJSON();
    }

    public void getJSON(View view){
        new Background().execute();
    }

    class Background extends AsyncTask<Void, Void, String>{
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url="http://riyatalwar1697.000webhostapp.com/get_data.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            }
            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView attendance=findViewById(R.id.Attendance);
            //attendance.setText(result);
            json_string=result;
        }

        public void parseJSON(View view){

            if(json_string==null){

            }
            else{
                try {
                    jsonObject = new JSONObject(json_string);
                    jsonArray=jsonObject.getJSONArray("server response");
                    int count=0;
                    String courseID, attendance;
                    while(count<jsonObject.length()){
                        JSONObject JO=jsonArray.getJSONObject(count);
                        courseID=JO.getString("courseID");
                        attendance=JO.getString("attendance");
                        Data data=new Data(courseID,attendance);
                        dataAdapter.add(data);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
