package com.example.praticeinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee=2;
    int price=0;
    String user_name="";
    CheckBox cb_whipped_cream;
    CheckBox cb_chocolate;
    EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb_whipped_cream=(CheckBox)findViewById(R.id.cb_whipped_cream);
        cb_chocolate=(CheckBox)findViewById(R.id.cb_chocolate);
        et_name=(EditText)findViewById(R.id.et_name);
    }

    public void submitOrder(View view) {
        price=calculatePrice(numberOfCoffee,5);
        user_name=et_name.getText().toString();
        createOrderSummary();
    }

    public void createOrderSummary(){
        String priceMessage="";
        Boolean addWhippedCream=cb_whipped_cream.isChecked();
        Boolean addChocolate=cb_chocolate.isChecked();
           priceMessage = "Name: "+user_name;
           priceMessage+="\nAdd whipped cream? "+addWhippedCream;
           priceMessage+="\nAdd chocolate? "+addChocolate;
           priceMessage+="\nQuantity:" + numberOfCoffee;
           priceMessage+="\nTotal: " + NumberFormat.getCurrencyInstance().format(price);
           priceMessage+="\nThank you!";

        displayMessage(priceMessage);
    }

    private int calculatePrice(int quantity,int pricePerCup) {
        int price = quantity * pricePerCup;
        if(cb_whipped_cream.isChecked()){
            price+=2;
        }
        if(cb_chocolate.isChecked()){
            price+=3;
        }
        return price;
    }

    public void increment(View view){
        numberOfCoffee+=1;
        displayQuantity(numberOfCoffee);
    }

    public void decrement(View view){
        numberOfCoffee-=1;
        displayQuantity(numberOfCoffee);
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
