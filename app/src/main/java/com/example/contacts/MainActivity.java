package com.example.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private final List<Person> personList = new ArrayList<>();
    private int currentViewMode = 0;
    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setting everything
        stubList = findViewById(R.id.stub_list);
        stubGrid = findViewById(R.id.stub_grid);

        stubList.inflate();
        stubGrid.inflate();

        listView = findViewById(R.id.listView);
        gridView = findViewById(R.id.gridView);

        getPersonList(); // make our 1st random list

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);
        // setting ClickListeners for our two views
        listView.setOnItemClickListener(onItemClickListener);
        gridView.setOnItemClickListener(onItemClickListener);

        Button btn1 = findViewById(R.id.random_button); // button for random changes
        btn1.setOnClickListener(this :: RandomChanges);

        switchView(); // show the results on the screen
    }

    public void RandomChanges(View view){  // do random changes and clear old
        personList.clear();
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

    private void getPersonList(){
        int random_int = (int)Math.floor(Math.random()*25+5); // from 5 to 25
        for(int i = 0; i < random_int; i++)
            personList.add(addPerson());
    }

    public Person addPerson(){
        final String gender = RandomPerson.getRandomGender();
        final String name = RandomPerson.getRandomName(gender); // save random name for email and full name
        final String surname = RandomPerson.getRandomSurname(); // save random surname for email and full name

        // create strings for more readable code to input it on the method
        final boolean online = RandomPerson.getRandomOnline();
        final String fullName = name + " " + surname;
        final String email = name.toLowerCase() + '.' + surname.toLowerCase() + "@gmail.com";
        final int imageID;
        if(gender.equals("Female")) {
            if(online){
                imageID = R.drawable.icon_female_online;
            } else
                imageID = R.drawable.icon_female;
        }
        else {
            if(online){
                imageID = R.drawable.avatar_icon_online;
            } else
                imageID = R.drawable.avatar_icon;
        }

        return new Person(imageID, fullName, online, gender, email);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//            Toast.makeText(getApplicationContext(),personList.get(position).getImageID() + personList.get(position).getTitle() + " + " + personList.get(position).isOnline()+ " + " + personList.get(position).getEmail() + " + " + personList.get(position).getGender(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Detailed_Info.class);
            intent.putExtra("image", personList.get(position).getImageID());
            intent.putExtra("fullName", personList.get(position).getTitle());
            intent.putExtra("online", personList.get(position).isOnline());
            intent.putExtra("email", personList.get(position).getEmail());
            intent.putExtra("gender", personList.get(position).getGender());
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_menu_1) {
            if (VIEW_MODE_LISTVIEW == currentViewMode) {
                currentViewMode = VIEW_MODE_GRIDVIEW;
            } else {
                currentViewMode = VIEW_MODE_LISTVIEW;
            }
            switchView();

            SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("currentViewMode", currentViewMode);
            editor.apply();
        }
        return true;
    }
}