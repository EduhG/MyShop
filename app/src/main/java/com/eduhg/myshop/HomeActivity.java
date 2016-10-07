package com.eduhg.myshop;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //Declaring All The Variables Needed

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        Assign view variables to their respective view in xml by findViewByID method
         */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        /*
        Creating an Adapter and setting the adapter to the viewPager
        setSupportActionBar method takes the toolbar and sets it as
        the default action bar thus making the toolbar work like a normal
        action bar.
         */
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

        /*
        TabLayout.newTab() method creates a tab.
         */
        final TabLayout.Tab home = tabLayout.newTab();
        final TabLayout.Tab inbox = tabLayout.newTab();
        final TabLayout.Tab star = tabLayout.newTab();

        /*
        Setting Title text for our tabs respectively
         */
        home.setText("Home");
        inbox.setText("Inbox");
        star.setText("Star");

        /*
        Adding the tabs to our tablayout at appropriate positions
        As I want home at first position I am passing home and 0 as argument to
        the tablayout and like wise for other tabs as well
         */
        tabLayout.addTab(home, 0);
        tabLayout.addTab(inbox, 1);
        tabLayout.addTab(star, 2);

        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indiactor below the tabs
         */

        /*tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));*/

        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new DailyReportsFragment(), "Daily Reports");
        adapter.addFragment(new MyStockFragment(), "My Stock");

        viewPager.setAdapter(adapter);
    }

    public class TabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
