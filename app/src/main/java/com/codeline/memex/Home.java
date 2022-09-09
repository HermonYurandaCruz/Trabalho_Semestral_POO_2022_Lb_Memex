package com.codeline.memex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        bottomNavigationView=findViewById(R.id.bottonNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        getSupportFragmentManager().beginTransaction().replace(R.id.conteinernavagation,homeFragment);

    }
    HomeFragment homeFragment=new HomeFragment();
    MemeirosFragment memeirosFragment=new MemeirosFragment();

    RankingFragment rankingFragment=new RankingFragment();
    NotificaoessFragment notificaoessFragment=new NotificaoessFragment();






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.conteinernavagation, homeFragment).commit();
                return true;

            case R.id.memeiros:
                getSupportFragmentManager().beginTransaction().replace(R.id.conteinernavagation,memeirosFragment).commit();
                return true;


            case R.id.ranking:
                getSupportFragmentManager().beginTransaction().replace(R.id.conteinernavagation,rankingFragment).commit();
                return true;

            case R.id.notificacoes:
                getSupportFragmentManager().beginTransaction().replace(R.id.conteinernavagation,notificaoessFragment).commit();
                return true;
        }
        return false;
    }
}