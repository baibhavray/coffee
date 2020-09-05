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
    int quantity;
    CheckBox creamCheckbox;
    CheckBox chocolateCheckbox;
    String thankYouMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creamCheckbox = findViewById(R.id.creamCheck);
        chocolateCheckbox = findViewById(R.id.chocolateCheck);

        quantity = 0;
    }

    public void submit(View view) {
        display(quantity);
        if(quantity==0){
            Toast.makeText(this, "You haven't order anything yet!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Yor order is ready");
        intent.putExtra(Intent.EXTRA_TEXT, mainMessage());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.itemTV);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.rupee);
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

    public String mainMessage() {
        EditText editText = findViewById(R.id.UserName);
        String name = editText.getText().toString();
        String a = "Name: " + name + "\nQuantity:" + quantity;

        if (creamCheckbox.isChecked() && chocolateCheckbox.isChecked()) {
            displayPrice(quantity * 9);
            thankYouMessage = a + "\nYou have added Whipped Cream & Chocolate!!\n" + quantity + " items are on it's way...\nThank You...Visit Again :)";
        } else if (creamCheckbox.isChecked() || chocolateCheckbox.isChecked()) {
            displayPrice(quantity * 7);
            if (creamCheckbox.isChecked() ) {
                thankYouMessage = a + "\nYou have added Whipped Cream & Chocolate!!\nYour order is on it's way...\nThank You...Visit Again :)";
            } else {
                thankYouMessage = a + "\nYou have added Chocolate!!\nYour order is on it's way...\nThank You...Visit Again :)";
            }

        } else {
            displayPrice(quantity * 5);
            thankYouMessage = a + "\nYour order is on it's way...\nThank You...Visit Again :)";
        }
            return thankYouMessage;
    }


}
