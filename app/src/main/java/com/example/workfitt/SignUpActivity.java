package com.example.workfitt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Spinner dob_month, dob_date, dob_year;
    ImageButton calendar_image_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final Map<String,Integer> month_days = new HashMap<String, Integer>();
        for(String month : getResources().getStringArray(R.array.months)){
            if(month.equals("FEB")){
//                month_days.put(month,30);
            }
            else if(month.equals("APR") || month.equals("JUN") || month.equals("SEP") || month.equals("NOV")){
                month_days.put(month,30);
            }
            else{
                month_days.put(month,31);
            }
        }

        dob_year = findViewById(R.id.year_spinner);
        dob_date = findViewById(R.id.date_spinner);
        dob_month = findViewById(R.id.month_spinner);
        calendar_image_button = findViewById(R.id.calendar_image_button);

        //Setting Up Year Spinner
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String year_array[] = new String[401];
        year_array[0] = "YYYY";
        int yr = currentYear-150;
        for(int i=1;i<401;i++){
            year_array[i]=String.valueOf(yr);
            yr+=1;
        }
        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, year_array);
        dob_year.setAdapter(year_adapter);

        dob_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //dob_month.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Setting Up Date Spinner for first time with 31 days
        setDateSpinner("JAN",month_days);

        //Setting Up Month Spinner
        ArrayAdapter<String> month_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.months));
        dob_month.setAdapter(month_adapter);
        dob_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(dob_year.getSelectedItem().equals("YYYY")){
                    //dob_month.setSelection(0);
                }
                dob_date = findViewById(R.id.date_spinner);
                setDateSpinner(dob_month.getSelectedItem().toString(),month_days);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Dialog Date Picker
        calendar_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    void  showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    void setDateSpinner(String selected_month, Map<String,Integer> month_days){
        dob_year = findViewById(R.id.year_spinner);
        if(!dob_year.getSelectedItem().toString().equals("YYYY")){
            int selected_year = Integer.parseInt(dob_year.getSelectedItem().toString());

            if(selected_month.equals("FEB")){
                //checking for leap year
                boolean leap = false;
                if(selected_year % 4 == 0) {
                    if( selected_year % 100 == 0) {
                        // year is divisible by 400, hence the year is a leap year
                        if (selected_year % 400 == 0) leap = true;
                        else leap = false;
                    }
                    else leap = true;
                }
                else leap = false;

                int no_of_days = 0;
                if(leap){
                    no_of_days = 29;
                }
                else{
                    no_of_days = 28;
                }
                String dates_array[] = new String[no_of_days+1];
                dates_array[0] = "DD";
                for(int dt=1;dt<no_of_days+1;dt++){
                    if(dt<10) dates_array[dt] = "0"+String.valueOf(dt);
                    else dates_array[dt] = String.valueOf(dt);
                }
                ArrayAdapter<String> date_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, dates_array);
//                dob_date.setAdapter(date_adapter);
            }

            else{
                int no_of_days = month_days.get(selected_month);
                String dates_array[] = new String[no_of_days+1];
                dates_array[0] = "DD";
                for(int dt=1;dt<no_of_days+1;dt++){
                    if(dt<10) dates_array[dt] = "0"+String.valueOf(dt);
                    else dates_array[dt] = String.valueOf(dt);
                }
                ArrayAdapter<String> date_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, dates_array);
//                dob_date.setAdapter(date_adapter); // Newly Commented for Calendar Section
            }
            dob_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else{
            int no_of_days = 31;
            String dates_array[] = new String[no_of_days+1];
            dates_array[0] = "DD";
            for(int dt=1;dt<no_of_days+1;dt++){
                if(dt<10) dates_array[dt] = "0"+String.valueOf(dt);
                else dates_array[dt] = String.valueOf(dt);
            }
            ArrayAdapter<String> date_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, dates_array);
            dob_date.setAdapter(date_adapter);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { //triggers when date is selected
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        dob_year.setSelection(151-(currentYear-year));//index needs to be calculated and passed accordingly as arguments
        dob_month.setSelection(month+1);
        dob_date.setSelection(dayOfMonth);//small bug here

    }
}