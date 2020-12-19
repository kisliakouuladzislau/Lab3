package by.kisliakouu.menuandservice;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MathService mathService;
    boolean isMainServiceBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MathService.MathServiceBinder binder = (MathService.MathServiceBinder) service;
            mathService = binder.getService();
            isMainServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isMainServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MathService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        isMainServiceBound = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        TextView resultView = (TextView) findViewById(R.id.view_result);
        EditText firstNumber = (EditText) findViewById(R.id.enter_first_number);
        EditText secondNumber = (EditText) findViewById(R.id.enter_second_number);
        switch(itemId){
            case R.id.addition_operation:
                resultView.setText(Integer.toString(mathService.addTwoNumbers(
                        Integer.parseInt(firstNumber.getText().toString())
                        ,Integer.parseInt(secondNumber.getText().toString()))));
                return true;
            case R.id.subtraction_operation:
                resultView.setText(Integer.toString(mathService.subTwoNumbers(
                        Integer.parseInt(firstNumber.getText().toString())
                        ,Integer.parseInt(secondNumber.getText().toString()))));
                return true;
            case R.id.multiplication_operation:
                resultView.setText(Integer.toString(mathService.multiplicationTwoNumbers(
                        Integer.parseInt(firstNumber.getText().toString())
                        ,Integer.parseInt(secondNumber.getText().toString()))));
                return true;
            case R.id.division_operation:
                resultView.setText(Integer.toString(mathService.divisionTwoNumbers(
                        Integer.parseInt(firstNumber.getText().toString())
                        ,Integer.parseInt(secondNumber.getText().toString()))));
                return true;
            case R.id.remainder_division:
                resultView.setText(Integer.toString(mathService.divisionWithRemainderTwoNumbers(
                        Integer.parseInt(firstNumber.getText().toString())
                        ,Integer.parseInt(secondNumber.getText().toString()))));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
