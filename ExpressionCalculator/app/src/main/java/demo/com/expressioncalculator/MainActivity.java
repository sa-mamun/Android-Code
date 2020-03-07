package demo.com.expressioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView textView1, resultView;

    Button clearBtn, bracketStartBtn,bracketEndBtn, percentageBtn, divideBtn, addBtn, subtractBtn, multiplyBtn, equalBtn;

    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, pointBtn, zeroBtn;

    Editable expression;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        resultView = findViewById(R.id.textView2);

        clearBtn = findViewById(R.id.buttonClearText);
        bracketStartBtn = findViewById(R.id.buttonBracketStart);
        percentageBtn = findViewById(R.id.buttonPercentage);
        bracketEndBtn = findViewById(R.id.buttonBracketEnd);
        divideBtn = findViewById(R.id.buttonDivide);
        addBtn = findViewById(R.id.buttonAdd);
        subtractBtn = findViewById(R.id.buttonSubtraction);
        multiplyBtn = findViewById(R.id.buttonMultiply);
        equalBtn = findViewById(R.id.buttonEqual);

        resultView.setText("0");

        oneBtn = findViewById(R.id.button1);
        twoBtn = findViewById(R.id.button2);
        threeBtn = findViewById(R.id.button3);
        fourBtn = findViewById(R.id.button4);
        fiveBtn = findViewById(R.id.button5);
        sixBtn = findViewById(R.id.button6);
        sevenBtn = findViewById(R.id.button7);
        eightBtn = findViewById(R.id.button8);
        nineBtn = findViewById(R.id.button9);
        pointBtn = findViewById(R.id.buttonPoint);
        zeroBtn = findViewById(R.id.buttonZero);

        clearBtn.setOnClickListener(this);
        bracketStartBtn.setOnClickListener(this);
        bracketEndBtn.setOnClickListener(this);
        percentageBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        subtractBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        equalBtn.setOnClickListener(this);
        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        fourBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);
        pointBtn.setOnClickListener(this);
        zeroBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonClearText:

                textView1.setText(null);
                resultView.setText("0");
                break;

            case R.id.buttonBracketStart:

                textView1.setText(textView1.getText() + "(");
                break;

            case R.id.buttonBracketEnd:

                textView1.setText(textView1.getText() + ")");
                break;

            case R.id.buttonDivide:

                textView1.setText(textView1.getText() + "/");
                break;

            case R.id.buttonAdd:

                textView1.setText(textView1.getText() + "+");
                break;

            case R.id.buttonSubtraction:

                textView1.setText(textView1.getText() + "-");
                break;

            case R.id.buttonMultiply:

                textView1.setText(textView1.getText() + "*");
                break;

            case R.id.buttonPercentage:

                textView1.setText(textView1.getText() + "%");
                break;

            case R.id.buttonPoint:

                textView1.setText(textView1.getText() + ".");
                break;

            case R.id.button1:

                textView1.setText(textView1.getText() + "1");
                break;

            case R.id.button2:

                textView1.setText(textView1.getText() + "2");
                break;

            case R.id.button3:

                textView1.setText(textView1.getText() + "3");
                break;

            case R.id.button4:

                textView1.setText(textView1.getText() + "4");
                break;

            case R.id.button5:

                textView1.setText(textView1.getText() + "5");
                break;

            case R.id.button6:

                textView1.setText(textView1.getText() + "6");
                break;

            case R.id.button7:

                textView1.setText(textView1.getText() + "7");
                break;

            case R.id.button8:

                textView1.setText(textView1.getText() + "8");
                break;

            case R.id.button9:

                textView1.setText(textView1.getText() + "9");
                break;

            case R.id.buttonZero:

                textView1.setText(textView1.getText() + "0");
                break;

            case R.id.buttonEqual:

                try {
                    // Create an object of DoubleEvaluator for getting result in double type
                    DoubleEvaluator evaluator = new DoubleEvaluator();

                    expression = (Editable) textView1.getText();
                    result = evaluator.evaluate(String.valueOf(expression));
                    resultView.setText(result.toString());
                }
                // Catching error for wrong expressions otherwise app will crash
                catch (Exception e){
                    e.printStackTrace();
                }
                break;

        }

    }
}
