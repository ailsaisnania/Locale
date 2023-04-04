package com.example.tugaslocalestarter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.LocaleListCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public EditText price;
    public TextView kode;
    public Button total;
    public TextView label;
    public TextView label2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kode = findViewById(R.id.code);
        price = findViewById(R.id.price);
        total = findViewById(R.id.total);
        label = findViewById(R.id.label);
        label2 = findViewById(R.id.label2);


        String codeLanguage = String.valueOf(LocaleListCompat.getAdjustedDefault());
        TextView code = findViewById(R.id.code);
        code.setText(codeLanguage);


        total.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String harga = price.getText().toString();

                if (TextUtils.isEmpty(harga)){
                    Toast.makeText(MainActivity.this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

//                if(codeLanguage.equals("[en_US]")) {
//                    String resultDollar = formatDollar((Double.parseDouble(harga)/15016)*100);
//                    String resultsatuan = formatDollar(Double.parseDouble(harga)/15016);
//                    label.setText(resultDollar);
//                    label2.setText(resultsatuan);
//                }
//
//                if(codeLanguage.equals("[ja_JP]")){
//                    String resultJpn = formatJpn((Double.parseDouble(harga)/112) * 100);
//                    String resultsatuan = formatJpn((Double.parseDouble(harga)/112));
//                    label.setText(resultJpn);
//                    label2.setText(resultsatuan);
//                }
                   else{
                    String resultDollar = formatDollar((Double.parseDouble(harga)/15016)*100);
                    String resultsatuan = formatDollar(Double.parseDouble(harga)/15016);
                    label.setText(resultDollar);
                    label2.setText(resultsatuan);

//                       String resultRupiah = formatRupiah(Double.parseDouble(harga) * 100);
//                    String resultsatuan= formatRupiah(Double.parseDouble(harga));
//                        label.setText(resultRupiah);
//                    label2.setText(resultsatuan);
                    }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    private String formatDollar(Double number){
        NumberFormat formatDollar = NumberFormat.getCurrencyInstance(Locale.US);
        return formatDollar.format(number);
    }

    private String formatJpn(Double number){
        NumberFormat formatJpn = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        return formatJpn.format(number);
    }

    private void showHelp() {
        // Create the intent.
        Intent helpIntent = new Intent(this, HelpActivity.class);
        // Start the HelpActivity.
        startActivity(helpIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles options menu item clicks.
     *
     * @param item      Menu item
     * @return boolean  True if menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.action_language){
            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}