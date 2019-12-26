package com.example.praticeinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee=2;
    int price=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        price=calculatePrice(numberOfCoffee,5);
        createOrderSummary();
    }

    public void createOrderSummary(){
        String priceMessage="Name: Mina\n"+"Quantity:"+numberOfCoffee+"\nTotal: "+ NumberFormat.getCurrencyInstance().format(price)+"\nThank you!";
        displayMessage(priceMessage);
    }

    private int calculatePrice(int quantity,int pricePerCup) {
        int price = quantity * pricePerCup;
        return price;
    }

    public void increment(View view){
        numberOfCoffee+=1;
        displayQuantity(numberOfCoffee);
        displayPrice(numberOfCoffee*5);
    }

    public void decrement(View view){
        numberOfCoffee-=1;
        displayQuantity(numberOfCoffee);
        displayPrice(numberOfCoffee*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}
