package com.example.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Days extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;

    private String[] preferedDay;
    public static String[] Saturday = {"--","--","--","--","--"};
    public static String[] Sunday = {"--","--","--","--","--"};
    public static String[] Monday = {"--","--","--","--","--"};
    public static String[] Tuesday = {"--","--","--","--","--"};
    public static String[]  Wednesday = {"--","--","--","--","--"};
    public static String[] Thursday = {"--","--","--","--","--"};
    public static String[] Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        setup();
        initToolbar();
        setArrays();
        setuplview();

    }

    private void setArrays() {
        Thursday[0] = ScheduleJava.sharedPreferences.getString("Thur2", "--");
        Thursday[0] = ScheduleJava.sharedPreferences.getString("Thur1", "--");
        Thursday[0] = ScheduleJava.sharedPreferences.getString("Thur3", "--");
        Thursday[0] = ScheduleJava.sharedPreferences.getString("Thur4", "--");
        Thursday[0] = ScheduleJava.sharedPreferences.getString("Thur5", "--");
        Sunday[0] = ScheduleJava.sharedPreferences.getString("Sun1", "--");
        Sunday[1] = ScheduleJava.sharedPreferences.getString("Sun2", "--");
        Sunday[2] = ScheduleJava.sharedPreferences.getString("Sun3", "--");
        Sunday[3] = ScheduleJava.sharedPreferences.getString("Sun4", "--");
        Sunday[4] = ScheduleJava.sharedPreferences.getString("Sun5", "--");
        Monday[0] = ScheduleJava.sharedPreferences.getString("Mon1", "--");
        Monday[1] = ScheduleJava.sharedPreferences.getString("Mon2", "--");
        Monday[2] = ScheduleJava.sharedPreferences.getString("Mon3", "--");
        Monday[3] = ScheduleJava.sharedPreferences.getString("Mon4", "--");
        Monday[4] = ScheduleJava.sharedPreferences.getString("Mon5", "--");
        Tuesday [0] = ScheduleJava.sharedPreferences.getString("Tues1", "--");
        Tuesday [1] = ScheduleJava.sharedPreferences.getString("Tues2", "--");
        Tuesday [2] = ScheduleJava.sharedPreferences.getString("Tues3", "--");
        Tuesday [3] = ScheduleJava.sharedPreferences.getString("Tues4", "--");
        Tuesday [4] = ScheduleJava.sharedPreferences.getString("Tues5", "--");
        Wednesday[0] = ScheduleJava.sharedPreferences.getString("Wed1", "--");
        Wednesday[1] = ScheduleJava.sharedPreferences.getString("Wed2", "--");
        Wednesday[2] = ScheduleJava.sharedPreferences.getString("Wed3", "--");
        Wednesday[3] = ScheduleJava.sharedPreferences.getString("Wed4", "--");
        Wednesday[4] = ScheduleJava.sharedPreferences.getString("Wed5", "--");
        Saturday[0] = ScheduleJava.sharedPreferences.getString("Sat1", "--");
        Saturday[1] = ScheduleJava.sharedPreferences.getString("Sat2", "--");
        Saturday[2] = ScheduleJava.sharedPreferences.getString("Sat3", "--");
        Saturday[3] = ScheduleJava.sharedPreferences.getString("Sat4", "--");
        Saturday[4] = ScheduleJava.sharedPreferences.getString("Sat5", "--");

    }

    private void setup (){
        listView = findViewById(R.id.lvday);
        toolbar = findViewById(R.id.toolbarday);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ScheduleJava.sharedPreferences.getString(ScheduleJava.Day, null));
    }
    private void setuplview(){
        Time =  getResources().getStringArray(R.array.time);
        String selDay = ScheduleJava.sharedPreferences.getString(ScheduleJava.Day, null);
        if (selDay.equalsIgnoreCase("Saturday")){
            preferedDay = Saturday;
        }else if (selDay.equalsIgnoreCase("Sunday")){
            preferedDay = Sunday;
        }else if (selDay.equalsIgnoreCase("Monday")){
            preferedDay = Monday;
        }else if (selDay.equalsIgnoreCase("Tuesday")){
            preferedDay = Tuesday;
        }else if (selDay.equalsIgnoreCase("Wednesday")){
            preferedDay = Wednesday;
        }else {
            preferedDay = Thursday;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(Days.this, preferedDay, Time);
        listView.setAdapter(simpleAdapter);
    }
    public class SimpleAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectarray;
        private String[] timearray;

        private ImageView imageView;
        public SimpleAdapter(Context context, String[] subjectarray, String [] time){
            mContext = context;
            this.timearray = time;
            this.subjectarray = subjectarray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectarray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectarray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view == null){
                view = layoutInflater.inflate(R.layout.dayitem, null);
            }
            time = view.findViewById(R.id.tvtime);
            subject = view.findViewById(R.id.tvday);
            subject.setText(subjectarray[position]);
            time.setText(timearray[position]);

            return view;}
    }
}