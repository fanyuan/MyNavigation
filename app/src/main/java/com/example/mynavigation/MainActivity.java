package com.example.mynavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mynavigation.databinding.ActivityMainBinding;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setAct(this);
    }

    public void navigationDemoActivity(){
        startActivity(new Intent(this,NavigationDemoActivity.class));
    }
    public void reuseNavigationActivity(){
        startActivity(new Intent(this,ReuseNavigationActivity.class));
    }
    public void mainActivity2(){
        startActivity(new Intent(this,MainActivity2.class));
    }

}