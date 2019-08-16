package com.uca.aerolineaapp.ui.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.ui.fragments.HomeFragment;
import com.uca.aerolineaapp.ui.fragments.NewAgencyFragment;
import com.uca.aerolineaapp.ui.fragments.NewReserveFragment;
import com.uca.aerolineaapp.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private MenuItem menuItem;
    private MenuItem menuItemLogOut;
    private MenuItem menuItemLogOutAndForget;
    private MenuItem menuItemSendFarm;
    BottomNavigationView navigationView;
    private FloatingActionButton newFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mainFragment = new HomeFragment();
        openFragment(mainFragment);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        initViews();
        initAction();
    }

    private void initViews(){
        newFlight = findViewById(R.id.new_flight);
    }

    private void initAction(){
        newFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FlightActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        navigationView.getMenu().findItem(id).setChecked(true);

        if (id == R.id.navigation_home)  {
            Fragment fragmentHome = new HomeFragment();
            openFragment(fragmentHome);
        }  else if (id == R.id.navigation_profile) {
            Fragment fragmentProfile = new ProfileFragment();
            openFragment(fragmentProfile);
        } else if (id == R.id.navigation_airline){
            Intent intent = new Intent(getApplicationContext(), AirlineActivity.class);
            startActivity(intent);
        } /*else if (id == R.id.navigation_booking) {
            Fragment fragmentBooking = new NewReserveFragment();
            openFragment(fragmentBooking);
        }*/


        return true;
    }

    private void openFragment(Fragment fragment, boolean anim) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (anim) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        fragmentTransaction.replace(R.id.content_frame, fragment);

        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragmentTransaction.commit();
    }

    private void openFragment(Fragment fragment) {
        openFragment(fragment, false);
    }

    private void clearSelected() {
        navigationView.getMenu().findItem(R.id.navigation_profile).setChecked(false);
        navigationView.getMenu().findItem(R.id.navigation_home).setChecked(false);
        //navigationView.getMenu().findItem(R.id.navigation_booking).setChecked(false);
    }



}
