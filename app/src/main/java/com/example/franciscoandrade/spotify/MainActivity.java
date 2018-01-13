package com.example.franciscoandrade.spotify;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FrameLayout fragmentContainer;
    BottomNavigationView bottomNavigation;
    MusicFragment musicFragment= new MusicFragment();
    SearchFragment searchFragment= new SearchFragment();
    PlayListFragment playListFragment= new PlayListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolBar("Spotify", false);
        fragmentContainer=(FrameLayout)findViewById(R.id.fragmentContainer);
        bottomNavigation=(BottomNavigationView)findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, musicFragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
//.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                switch (item.getItemId()){
                    case R.id.music:
                        transaction.replace(R.id.fragmentContainer, musicFragment);
                        break;
                    case R.id.search:
                        transaction.replace(R.id.fragmentContainer, searchFragment);
                        break;
                    case R.id.playlist:
                        transaction.replace(R.id.fragmentContainer, playListFragment);
                        break;
                }
                if (transaction != null) {
                    transaction.commit();
                    //If we want to add fragments to stack
                    //transaction.addToBackStack(null).commit();
                }
                return true;
            }
        });
    }


    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public class RetrofitCall extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServiceAPI sportsNewsService = retrofit.create(ServiceAPI.class);
            sportsNewsService = retrofit.create(ServiceAPI.class);

            return null;
        }
    }

}
