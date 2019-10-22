package demo.com.jsonparsingbyasynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Model> arrayList;

    TextView rom,ram,screenSize,backCamera,companyName,name,frontCamera,operatingSystem,processor,battery;
    ImageView imageView;


    public CustomAdapter(Context context, ArrayList<Model> arrayList) {
        super(context, R.layout.item_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_row, parent, false);

        rom = convertView.findViewById(R.id.rom);
        ram = convertView.findViewById(R.id.ram);
        screenSize = convertView.findViewById(R.id.screenSize);
        companyName = convertView.findViewById(R.id.companyName);
        backCamera = convertView.findViewById(R.id.backCamera);
        name = convertView.findViewById(R.id.name);
        frontCamera = convertView.findViewById(R.id.frontCamera);
        operatingSystem = convertView.findViewById(R.id.operatingSystem);
        processor = convertView.findViewById(R.id.processor);
        battery = convertView.findViewById(R.id.battery);
        imageView = convertView.findViewById(R.id.imageView);

        rom.setText(arrayList.get(position).getRom());
        ram.setText(arrayList.get(position).getRam());
        screenSize.setText(arrayList.get(position).getScreenSize());
        companyName.setText(arrayList.get(position).getCompanyName());
        backCamera.setText(arrayList.get(position).getBackCamera());
        name.setText(arrayList.get(position).getName());
        frontCamera.setText(arrayList.get(position).getFrontCamera());
        operatingSystem.setText(arrayList.get(position).getOperatingSystem());
        processor.setText(arrayList.get(position).getProcessor());
        battery.setText(arrayList.get(position).getBattery());

        new ImageLoad().execute(arrayList.get(position).getUrl().toString());


        return convertView;
    }

    public class ImageLoad extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                connection.connect();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);

            super.onPostExecute(bitmap);
        }
    }
}
