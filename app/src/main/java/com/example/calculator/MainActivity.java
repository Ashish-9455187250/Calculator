package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.util.regex.MatchResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView Result_TextView, solutions;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClosed;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result_TextView = findViewById(R.id.Result_textview);
        solutions = findViewById(R.id.solution);

        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);


        assignID(buttonAC,R.id.button_AC);
        assignID(buttonPlus,R.id.button_add);
        assignID(buttonMinus,R.id.button_minus);
        assignID(buttonMultiply,R.id.button_multiply);
        assignID(buttonDivide,R.id.button_divide);
        assignID(buttonDot,R.id.button_dot);
        assignID(buttonC,R.id.button_C);
        assignID(buttonEquals, R.id.button_equal);

        assignID(buttonBrackOpen,R.id.button_open_brackett);
        assignID(buttonBrackClosed,R.id.button_close_brackett);


    }
    void assignID(MaterialButton btn, int Id){
        btn = findViewById(Id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTocalculate = solutions.getText().toString();

        if(buttonText.equals("AC")){
            solutions.setText("");
            Result_TextView.setText("0");
            return;

        }
        if(buttonText.equals("=")){
            solutions.setText(Result_TextView.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length()-1);

        }
        else {
            dataTocalculate = dataTocalculate + buttonText;
        }

        solutions.setText(dataTocalculate);

        String final_Result = getResult(dataTocalculate);
        if (!final_Result.equals("Err")){
            Result_TextView.setText(final_Result);

        }
    }
    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javasrcipt",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception E) {
            return "Err";
        }

    }
}