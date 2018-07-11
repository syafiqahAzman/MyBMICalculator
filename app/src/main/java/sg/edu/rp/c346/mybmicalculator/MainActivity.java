package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWt, etHt;
    Button btnCal, btnRst;
    TextView tvDate, tvBMI;

    float height = Float.parseFloat(etHt.getText().toString());
    float weight = Float.parseFloat(etWt.getText().toString());






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHt = findViewById(R.id.editTextHt);
        etWt = findViewById(R.id.editTextWt);
        btnCal = findViewById(R.id.buttonCal);
        btnRst = findViewById(R.id.buttonRst);
        tvBMI = findViewById(R.id.textViewBMI);
        tvDate = findViewById(R.id.textViewDate);




        btnRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etHt.setText(null);
                etWt.setText(null);
                tvDate.setText(getResources().getString(R.string.CalDate));
                tvBMI.setText(getResources().getString(R.string.CalBMI));



            }
        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float calculate =  weight / (height * height);

                //Create a Calendar object with current date and time
                Calendar now = Calendar.getInstance();

                String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR)+ " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

                SharedPreferences.Editor prefEdit = prefs.edit();


                prefEdit.putString("dateTime", datetime);
                prefEdit.putFloat("bmi",calculate );
                prefEdit.commit();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();







        prefEdit.putFloat("wt", weight);
        prefEdit.putFloat("ht", height);

        prefEdit.commit();



    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        float height = prefs.getFloat("ht", 0);
        float weight = prefs.getFloat("wt", 0);
        float  calculate = prefs.getFloat("bmi", 0);
        String dateTime = prefs.getString("dateTime", null);

        etWt.setText(null);
        etHt.setText(null);
        tvDate.setText("Last Calculated Date:" + dateTime);
        tvBMI.setText("Last Calculated BMI:" + calculate);



    }
}
