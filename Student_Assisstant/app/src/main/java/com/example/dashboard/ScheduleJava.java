package com.example.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ScheduleJava extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lview;
    private Button btnedit;
    public static SharedPreferences sharedPreferences;
    public static String Day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        setviews();
        initToolbar();
        setList();
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScheduleJava.this, EditSubjects.class));
            }
        });
    }

    private void setviews(){
        toolbar =  findViewById(R.id.toolbarsd);
        lview = findViewById(R.id.lvmain);
        btnedit = findViewById(R.id.btnedit);
        sharedPreferences = getSharedPreferences(MainActivity.email, MODE_PRIVATE);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable");
    }
    private void setList(){
        String[] title = getResources().getStringArray(R.array.Main);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title);
        lview.setAdapter(simpleAdapter);
       lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               switch(position) {
                   case 0: {
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day, "Saturday").apply();
                       break;
                   }case 1: {
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day, "Sunday").apply();
                       break;
                   }case 2: {
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day, "Monday").apply();
                       break;
                   }case 3: {
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day, "Tuesday").apply();
                       break;
                   }case 4:
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day,"Wednesday").apply();
                       break;
                   case 5: {
                       startActivity(new Intent(ScheduleJava.this, Days.class));
                       sharedPreferences.edit().putString(Day, "Thursday").apply();
                       break;
                   }default: break;
               }
           }
       });
    }
    public class SimpleAdapter extends BaseAdapter{
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        public SimpleAdapter(Context context, String[] title){
            mContext = context;
            titleArray = title;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view == null){
                view = layoutInflater.inflate(R.layout.timetable_item, null);
            }
            title = view.findViewById(R.id.tvmain);
            title.setText(titleArray[position]);
            return view;}
    }
    public void onDestroy() {

        super.onDestroy();
        sharedPreferences.edit().clear();
        sharedPreferences.edit().commit();
    }

}