package com.example.mynavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class Page2Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ddebug","Page2Fragment onCreateView");
        View v = inflater.inflate(R.layout.fragment_main_page2,container,false);
        Button button1 = v.findViewById(R.id.btn_page1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_page1);
            }
        });
        Button button3 = v.findViewById(R.id.btn_page3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_page3);
            }
        });
        return v;//super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ddebug","Page2Fragment onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ddebug","Page2Fragment onDestroyView");
    }
}
