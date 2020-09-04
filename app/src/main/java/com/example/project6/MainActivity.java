package com.example.project6;

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
    int quantity = 0;
    CheckBox checkbox;
    CheckBox checkbox2;
    int items;
    boolean cBox;
    boolean cBox2;
    String thanx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox = (CheckBox) findViewById(R.id.chechBox);
        cBox = checkbox.isChecked();
        checkbox2 = (CheckBox) findViewById(R.id.chechBox2);
        cBox2 = checkbox2.isChecked();
        items = quantity;
    }

    public void submit(View view) {
        display(quantity);
        if(quantity==0){
            Toast.makeText(this, "You haven't order anything yet!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Yor order is ready");
        intent.putExtra(Intent.EXTRA_TEXT, mainMessage());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private int calcPrice() {
        return quantity * 5;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.zero);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.rupee);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
            Toast.makeText(this, "You can't have less than one coffee", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

//    public void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.ThanQ);
//        priceTextView.setText(message);
//    }

    public String mainMessage() {
        EditText editText = (EditText) findViewById(R.id.UserName);
        String name = editText.getText().toString();
        String a = "Name:" + name + "\nQuantity:" + items;

        if (cBox && cBox2) {
            displayPrice(quantity * 9);
            thanx = a + "\nYou have added Whipped Cream & Chocolate!!\n" + items + " items are on it's way...\nThank You...Visit Again :)";
        } else if (cBox || cBox2) {
            displayPrice(quantity * 7);
            if (cBox) {
                thanx = a + "\nYou have added Whipped Cream & Chocolate!!\nYour order is on it's way...\nThank You...Visit Again :)";
            } else {
                thanx = a + "\nYou have added Chocolate!!\nYour order is on it's way...\nThank You...Visit Again :)";
            }

        } else {
            displayPrice(quantity * 5);
            thanx = a + "\nYour order is on it's way...\nThank You...Visit Again :)";
        }
            return thanx;
    }


}
