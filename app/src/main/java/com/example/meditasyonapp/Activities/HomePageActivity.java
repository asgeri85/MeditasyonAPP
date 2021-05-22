package com.example.meditasyonapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.meditasyonapp.Adapters.HomeCardAdapter;
import com.example.meditasyonapp.ApiService.ApiUtils;
import com.example.meditasyonapp.ApiService.MeditasyonDao;
import com.example.meditasyonapp.Classes.Meditasyonlar;
import com.example.meditasyonapp.R;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private HomeCardAdapter adapter;
    private MeditasyonDao dao;
    private List<Meditasyonlar>meditasyonlarArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar=findViewById(R.id.toolbarHome);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav);
        recyclerView=findViewById(R.id.rvHome);
        dao= ApiUtils.getDao();
        meditasyonlarArrayList=new ArrayList<>();
        toolbar.setTitle("MeditasyonApp");
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        meditasyonGetir();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_rahat){
            kategroiGetir("3");
        }

        if (item.getItemId()==R.id.action_gelisim){
            kategroiGetir("1");
        }

        if (item.getItemId()==R.id.action_populer){
            kategroiGetir("2");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

        }else{
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_update){
            Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void meditasyonGetir(){
        dao.meditasyonGetir().enqueue(new Callback<List<Meditasyonlar>>() {
            @Override
            public void onResponse(Call<List<Meditasyonlar>> call, Response<List<Meditasyonlar>> response) {
                meditasyonlarArrayList=response.body();
                adapter=new HomeCardAdapter(meditasyonlarArrayList,HomePageActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Meditasyonlar>> call, Throwable t) {

            }
        });
    }

    public void kategroiGetir(String kategori){
        dao.kategori(kategori).enqueue(new Callback<List<Meditasyonlar>>() {
            @Override
            public void onResponse(Call<List<Meditasyonlar>> call, Response<List<Meditasyonlar>> response) {
                meditasyonlarArrayList=response.body();
                adapter=new HomeCardAdapter(meditasyonlarArrayList,HomePageActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Meditasyonlar>> call, Throwable t) {

            }
        });
    }
}