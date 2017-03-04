package com.newproject.jordi.hackupc17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.newproject.jordi.hackupc17.Entities.UserRanking;

import java.util.ArrayList;

public class ActivityRanking extends AppCompatActivity {

    ListView listViewRanking;
    ArrayList<UserRanking> userslist = new ArrayList<UserRanking>();
    UserRanking auserranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        auserranking = new UserRanking("Antonio Orozco", "700 pt", "1", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("Gerard Reches", "1000 pt", "2", R.drawable.ic_android_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("Elsa Pataki", "300 pt", "3", R.drawable.ic_battery_charging_80_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("Son Goku", "500 pt", "4", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("John Snow", "20 pt", "5", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("Catwoman cat", "90 pt", "6", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("Don Patch", "999 pt", "7", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);
        auserranking = new UserRanking("e2ofoiefio", "80 pt", "8", R.drawable.ic_insert_emoticon_black_24dp);
        userslist.add(auserranking);


        listViewRanking = (ListView) findViewById(R.id.lv_ranking);
        AdapterRankingList customAdapter = new AdapterRankingList(getApplicationContext(),R.layout.cell_user_ranking, userslist);
        listViewRanking.setAdapter(customAdapter);

    }
}
