package demo.com.jsonparsingbyasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Model> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        String api = "https://androidtutorialpoint.com/api/MobileJSONArray.json";
        new GetJsonValue().execute(api);

    }


    public class GetJsonValue extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                if ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONArray jsonArray = new JSONArray(s);

                for (int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Model model = new Model(object.get("rom").toString(), object.get("screenSize").toString(), object.get("backCamera").toString(), object.get("companyName").toString(), object.get("name").toString(), object.get("frontCamera").toString(),object.get("battery").toString(),object.get("operatingSystem").toString(),object.get("processor").toString(),object.get("ram").toString(), object.get("url").toString());
                    arrayList.add(model);
                }

                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, arrayList);
                listView.setAdapter(customAdapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }

//            try {
//                JSONArray jsonArray = new JSONArray(s);
//                JSONObject jsonObject = jsonArray.getJSONObject(0);
//
//
//
//                new GetJsonImage().execute(jsonObject.get("url").toString());
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            super.onPostExecute(s);
        }
    }


}
