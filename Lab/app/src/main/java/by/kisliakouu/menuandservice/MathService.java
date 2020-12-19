package by.kisliakouu.menuandservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MathService extends Service {

    public class MathServiceBinder extends Binder {
        public MathService getService(){
            return MathService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MathServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
    }

    public int addTwoNumbers(int firstNumber, int secondNumber){
        return firstNumber + secondNumber;
    }

    public int subTwoNumbers(int firstNumber, int secondNumber){
        return firstNumber - secondNumber;
    }

    public int multiplicationTwoNumbers(int firstNumber, int secondNumber){
        return firstNumber * secondNumber;
    }

    public int divisionTwoNumbers(int firstNumber, int secondNumber){
        if(secondNumber == 0){
            return 0;
        }
        return firstNumber / secondNumber;
    }

    public int divisionWithRemainderTwoNumbers(int firstNumber, int secondNumber){
        if(secondNumber == 0) {
            return 0;
        }
        return firstNumber % secondNumber;
    }
}
