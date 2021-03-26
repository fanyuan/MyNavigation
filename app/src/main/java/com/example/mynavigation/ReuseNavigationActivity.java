package com.example.mynavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import android.os.Bundle;
import android.view.View;

import static androidx.navigation.Navigation.findNavController;

public class ReuseNavigationActivity extends AppCompatActivity {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_navigation);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        navController = findNavController(this, R.id.my_nav_host_fragment);
        ReuseFragmentNavigator fragmentNavigator = new ReuseFragmentNavigator(this,fragment.getChildFragmentManager(),fragment.getId());
        //获取导航器提供者
        NavigatorProvider provider = navController.getNavigatorProvider();
        //把自定义的Fragment导航器添加进去
        provider.addNavigator(fragmentNavigator);
        //手动创建导航图
        NavGraph navGraph = initNavGraph(provider,fragmentNavigator);
        navController.setGraph(navGraph);
    }
    //手动创建导航图，把3个目的地添加进来
    private NavGraph initNavGraph(NavigatorProvider provider, ReuseFragmentNavigator fragmentNavigator) {
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        //用自定义的导航器来创建目的地
        FragmentNavigator.Destination destination1 = fragmentNavigator.createDestination();
        destination1.setId(R.id.action_page2);
        destination1.setClassName(Page2Fragment.class.getCanonicalName());
        destination1.setLabel("Page2Fragment");
        navGraph.addDestination(destination1);

        FragmentNavigator.Destination destination2 = fragmentNavigator.createDestination();
        destination2.setId(R.id.action_page1);
        destination2.setClassName(Page1Fragment.class.getCanonicalName());
        destination2.setLabel("Page1Fragment");
        navGraph.addDestination(destination2);

        FragmentNavigator.Destination destination3 = fragmentNavigator.createDestination();
        destination3.setId(R.id.action_page3);
        destination3.setClassName(Page3Fragment.class.getCanonicalName());
        destination3.setLabel("Page3Fragment");
        navGraph.addDestination(destination3);

        navGraph.setStartDestination(R.id.action_page2);

        return navGraph;
    }
    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }
    public void Navigation01(View v){
        NavController controller = findNavController(this, R.id.my_nav_host_fragment);
        navController.navigate(R.id.action_page1);
    }

    public void Navigation02(View v){
        NavController controller = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        navController.navigate(R.id.action_page2);
    }

    public void Navigation03(View v){
        NavController controller = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        navController.navigate(R.id.action_page3);
    }
}