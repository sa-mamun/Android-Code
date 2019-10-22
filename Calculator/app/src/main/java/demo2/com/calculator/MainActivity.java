package demo2.com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_ac, btn_div, btn_mul, btn_sub, btn_add, btn_equal, btn_decimal;
    EditText et;
    TextView tv;
    Float valueOne, valueTwo;
    boolean add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);

        btn_0 = (Button) findViewById(R.id.button0);
        btn_1 = (Button) findViewById(R.id.button1);
        btn_2 = (Button) findViewById(R.id.button2);
        btn_3 = (Button) findViewById(R.id.button3);
        btn_4 = (Button) findViewById(R.id.button4);
        btn_5 = (Button) findViewById(R.id.button5);
        btn_6 = (Button) findViewById(R.id.button6);
        btn_7 = (Button) findViewById(R.id.button7);
        btn_8 = (Button) findViewById(R.id.button8);
        btn_9 = (Button) findViewById(R.id.button9);
        btn_ac = (Button) findViewById(R.id.buttonAC);
        btn_add = (Button) findViewById(R.id.buttonAdd);
        btn_sub = (Button) findViewById(R.id.buttonSub);
        btn_mul = (Button) findViewById(R.id.buttonMul);
        btn_div = (Button) findViewById(R.id.buttonDiv);
        btn_decimal = (Button) findViewById(R.id.buttonDecimal);
        btn_equal = (Button) findViewById(R.id.buttonEqual);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + "9");
            }
        });

        btn_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.setText(et.getText() + ".");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueOne = Float.parseFloat(et.getText() + "");
                add = true;
                et.setText(null);

            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueOne = Float.parseFloat(et.getText() + "");
                sub = true;
                et.setText(null);

            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueOne = Float.parseFloat(et.getText() + "");
                mul = true;
                et.setText(null);

            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueOne = Float.parseFloat(et.getText() + "");
                div = true;
                et.setText(null);

            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueTwo = Float.parseFloat(et.getText() + "");

                if (add == true)
                {
                    tv.setText(valueOne + valueTwo + "");
                }

                if (sub == true)
                {
                    tv.setText(valueOne - valueTwo + "");
                }

                if (mul == true)
                {
                    tv.setText(valueOne * valueTwo + "");
                }

                if (div == true)
                {
                    tv.setText(valueOne / valueTwo + "");
                }

            }
        });

        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText(null);
                tv.setText("0");
            }
        });
    }
}
