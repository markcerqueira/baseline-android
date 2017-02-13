package gg.mark.baselineandroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize Fast Android Networking library
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String NETWORKING_FRAGMENT_TAG = "NETWORKING_FRAGMENT_TAG";
    private static final String IMAGE_LOADING_FRAGMENT_TAG = "IMAGE_LOADING_FRAGMENT_TAG";
    private static final String LIST_VIEW_DEMO_FRAGMENT_TAG = "LIST_VIEW_DEMO_FRAGMENT_TAG";

    private static final Set<String> sFragmentTagsSet;
    static {
        sFragmentTagsSet = new HashSet<>();
        sFragmentTagsSet.add(NETWORKING_FRAGMENT_TAG);
        sFragmentTagsSet.add(IMAGE_LOADING_FRAGMENT_TAG);
        sFragmentTagsSet.add(LIST_VIEW_DEMO_FRAGMENT_TAG);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.networking) {
            showFragment(new NetworkFragment(), NETWORKING_FRAGMENT_TAG);
        } else if (id == R.id.image_loading) {
            showFragment(new ImageLoadingFragment(), IMAGE_LOADING_FRAGMENT_TAG);
        } else if (id == R.id.list_view_demo) {
            showFragment(new ListViewDemoFragment(), LIST_VIEW_DEMO_FRAGMENT_TAG);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Fragment fragment, String tag) {
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Remove all previous fragments
        for (String fragmentTag : sFragmentTagsSet) {
            Fragment existingFragment = fm.findFragmentByTag(fragmentTag);
            if (existingFragment != null) {
                ft.remove(existingFragment);
            }
        }

        ft.add(R.id.main_content_view, fragment,tag).commit();
    }
}
