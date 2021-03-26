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

public class Page3Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ddebug","Page3Fragment onCreateView");
        View v = inflater.inflate(R.layout.fragment_main_page3,container,false);
        Button button = v.findViewById(R.id.btn_page2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return v;//super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ddebug","Page3Fragment onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ddebug","Page3Fragment onDestroyView");
    }
}
