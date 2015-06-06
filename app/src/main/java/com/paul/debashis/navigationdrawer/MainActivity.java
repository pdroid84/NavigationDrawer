package com.paul.debashis.navigationdrawer;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    private String[] mItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private final static int DrawerArrowToggle_color = R.color.drawer_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the application title by getTitle();
        mTitle = mDrawerTitle = getTitle();
        Log.d("DEB","mTitle ="+mTitle);
        Log.d("DEB","mDrawerTitle ="+mDrawerTitle);

        mItems = getResources().getStringArray(R.array.items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // set up the drawer's list view with items and click listener
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mItems));
        MyAdapter myAdapter = new MyAdapter(this,mItems);
        mDrawerList.setAdapter(myAdapter);
        mDrawerList.setOnItemClickListener(new DrawItemClickListner());

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                                R.string.drawer_open,R.string.drawer_close){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                Log.d("DEB", "Drawer is just Closed");
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("DEB", "Drawer is now Opened");
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.d("DEB","mActionBarDrawerToggle.onOptionsItemSelected(item)is used for item="+item.getItemId());
            return true;
        }
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Alyaws use syncState to sync the toggle state after onRestoreInstanceState has occurred.
        Log.d("DEB", "About to call the syncState for the Drawer");

        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Act on change configuration
        Log.d("DEB", "OnConfigurationChanged is called");
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawItemClickListner implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("DEB","The item selected in the drawer is ="+position);
        selectItem(position);
    }
        private void selectItem (int position){
            // Create a new fragment and specify the planet to show based on position
            android.support.v4.app.Fragment mFragment = new FragmentList();
            Bundle mBundle = new Bundle();
            mBundle.putInt("position",position);
            mFragment.setArguments(mBundle);

            // Insert the fragment by replacing any existing fragment
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_layout,mFragment);
            mFragmentTransaction.commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setItemTitle(mItems[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
        public void setItemTitle(CharSequence title){
            mTitle = title;
            Log.d("DEB","The item value passed ="+mTitle);
           // getActionBar().setTitle(mTitle);
            getSupportActionBar().setTitle(mTitle);
        }
    }
}