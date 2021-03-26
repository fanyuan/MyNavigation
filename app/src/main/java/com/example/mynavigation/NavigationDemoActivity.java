package com.example.mynavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import static androidx.navigation.Navigation.findNavController;

public class NavigationDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_demo);
    }
    @Override
    public boolean onSupportNavigateUp() {
        //com.example.mynavigation.MainPage1Fragment fragment = new MainPage1Fragment();
        //Page1Fragment p = new Page1Fragment();

        return findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }

    public void Navigation01(View v){
        NavController controller = findNavController(this, R.id.my_nav_host_fragment);
        controller.navigate(R.id.action_page2);
    }

    public void Navigation02(View v){
        NavController controller = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        controller.navigate(R.id.action_page3);
    }

    public void Navigation03(View v){
        NavController controller = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        controller.navigateUp();
    }
}