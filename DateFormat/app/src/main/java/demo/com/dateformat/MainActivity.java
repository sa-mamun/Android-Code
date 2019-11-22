package demo.com.dateformat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the widgets reference from XML layout
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            // Do something with the chosen date
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            int actualMonth = month+1; // Because month index start from zero
            // Display the unformatted date to TextView
            tv.setText("Year : " + year + ", Month : " + actualMonth + ", Day : " + day + "\n\n");

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style medium and US locale
            DateFormat df_medium_us = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
            String df_medium_us_str = df_medium_us.format(chosenDate);
            // Display the formatted date
            tv.setText(tv.getText() + df_medium_us_str + " (DateFormat.MEDIUM, Locale.US)\n");

            // Format the date using style medium and UK locale
            DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
            String df_medium_uk_str = df_medium_uk.format(chosenDate);
            // Display the formatted date
            tv.setText(tv.getText() + df_medium_uk_str + " (DateFormat.MEDIUM, Locale.UK)\n");

            // Format the date using style short
            DateFormat df_short = DateFormat.getDateInstance(DateFormat.SHORT);
            String df_short_str = df_short.format(chosenDate);
            // Display the formatted date
            tv.setText(tv.getText() + df_short_str + " (DateFormat.SHORT)\n");

            // Format the date using style long
            DateFormat df_long = DateFormat.getDateInstance(DateFormat.LONG);
            String df_long_str = df_long.format(chosenDate);
            // Display the formatted date
            tv.setText(tv.getText() + df_long_str + " (DateFormat.LONG)\n");

            // Format the date using style full
            DateFormat df_full = DateFormat.getDateInstance(DateFormat.FULL);
            String df_full_str = df_full.format(chosenDate);
            // Display the formatted date
            tv.setText(tv.getText() + df_full_str + " (DateFormat.FULL)\n");
        }
    }
}
