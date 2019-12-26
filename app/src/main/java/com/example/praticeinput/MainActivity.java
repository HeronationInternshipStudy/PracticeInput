package com.example.praticeinput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

           Intent intent=new Intent(Intent.ACTION_SENDTO);
           intent.setData(Uri.parse("mailto:")); //only email apps should handle this
           intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for "+user_name);
           intent.putExtra(Intent.EXTRA_TEXT,priceMessage);//이메일의 바디부분을 추가해줌
           if(intent.resolveActivity(getPackageManager())!=null){
               startActivity(intent);
           }



    }

    private int calculatePrice(int quantity,int pricePerCup) {
        int price = pricePerCup;
        if(cb_whipped_cream.isChecked()){
            price+=1;
        }
        if(cb_chocolate.isChecked()){
            price+=2;
        }

        price=price*quantity;
        return price;
    }

    public void increment(View view){
        if (numberOfCoffee == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffee+=1;
        displayQuantity(numberOfCoffee);
    }

    public void decrement(View view){
        if(numberOfCoffee==1){
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
             return;
        }
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
