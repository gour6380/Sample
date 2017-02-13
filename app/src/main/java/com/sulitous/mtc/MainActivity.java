package com.sulitous.mtc;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener,
        WatingListFragment.OnAddChildListener,ChildDetailsFragment.OnEditChildListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToLoginFragment();
    }

    private void switchToLoginFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, new LoginFragment(), "Login");
        fragmentTransaction.commit();
    }

    @Override
    public void onLogin() {
        switchToWatingListFragment();
    }

    private void switchToWatingListFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, new WatingListFragment(), "Wait");
        fragmentTransaction.commit();
    }

    @Override
    public void onAddChild() {
        switchToAddChildFragment();
    }

    private void switchToAddChildFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, new AddChildFragment(), "Add");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onEditChild() {
        switchToAddChildFragment();
    }
}
