package com.example.contacts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Person> personList = new ArrayList<>();
    private int currentViewMode = 0;
    private final String[] name = {"Oleg", "Igor", "Sergey", "Mike", "Jack", "Tonny", "Nick", "Mark", "Luke"};
    private final String[] surname = {"Storozhev", "Ivanov", "Sikorsky", "Tvist", "Green", "Tsivinskiy", "Melnik", "Vorotov", "Govologorov"};

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stubList = findViewById(R.id.stub_list);
        stubGrid = findViewById(R.id.stub_grid);

        stubList.inflate();
        stubGrid.inflate();

        listView = findViewById(R.id.listView);
        gridView = findViewById(R.id.gridView);

        getPersonList();

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        listView.setOnItemClickListener(onItemClickListener);
        gridView.setOnItemClickListener(onItemClickListener);

        Button btn1 = findViewById(R.id.random_button);
        btn1.setOnClickListener(this :: RandomChanges);

        switchView();
    }
    public void RandomChanges(View view){
        getPersonList();
        switchView();
    }

    private void switchView(){
        if(VIEW_MODE_LISTVIEW == currentViewMode){
            stubList.setVisibility(ViewStub.VISIBLE);
            stubGrid.setVisibility(View.GONE);
        }else{
            stubList.setVisibility(View.GONE);
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters(){
        if(VIEW_MODE_LISTVIEW == currentViewMode){
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, personList);
            listView.setAdapter(listViewAdapter);
        } else{
          gridViewAdapter = new GridViewAdapter(this, R.id.gridView, personList);
          gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Person> getPersonList(){
        int random_int = (int)Math.floor(Math.random()*25+5); // from 5 to 46

        if(!personList.isEmpty())
            personList.clear();

        for(int i = 0; i < random_int; i++)
            personList.add(new Person(R.drawable.avatar_icon, randomFullName(), getRandomBoolean()));

        return personList;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public String randomFullName(){
        int first_name = (int) (Math.random()*9);
        int second_name = (int) (Math.random()*9);
        return name[first_name] + " " + surname[second_name];

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            // do smthing
            Toast.makeText(getApplicationContext(),personList.get(position).getTitle() + " + " + personList.get(position).isOnline(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode){
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else{
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                switchView();

                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();
                break;
        }
        return true;
    }
}