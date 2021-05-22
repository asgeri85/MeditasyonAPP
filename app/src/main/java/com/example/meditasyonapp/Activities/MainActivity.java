package com.example.meditasyonapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.meditasyonapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (internetVarmi(this)){
                new SplashThread().start();
            }else{
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Bağlantı hatası");
                builder.setMessage("Telefonunuzu internete bağlayın");
                builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int pid=android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                    }
                });
                builder.create().show();
            }

    }

    public boolean internetVarmi(Context context){
        final ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }

    public class SplashThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}