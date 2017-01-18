package net.styleru.biandroid.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.styleru.biandroid.R;
import net.styleru.biandroid.presenter.AppState;
import net.styleru.biandroid.presenter.IMainPresenter;
import net.styleru.biandroid.presenter.MainPresenter;
import net.styleru.biandroid.view.IMainView;
import net.styleru.biandroid.view.fragment.AnnouncementsFragment;
import net.styleru.biandroid.view.fragment.CommunityFragment;
import net.styleru.biandroid.view.fragment.NewsFragment;
import net.styleru.biandroid.view.fragment.StudiesFragment;
import net.styleru.biandroid.view.fragment.TimetableFragment;
import net.styleru.biandroid.view.fragment.SettingsFragment;
import net.styleru.biandroid.view.tab.SlidingTabLayout;
import net.styleru.biandroid.view.fragment.TabbedFragment;
import net.styleru.biandroid.view.adapter.tab.ViewPagerAdapter;

/**
 * Created by Tetawex on 18.07.2016.
 */
public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, IMainView,
        TabbedFragment.OnFragmentInteractionListener
{

    public static IMainPresenter presenter;

    protected ViewPager pager;
    protected ViewPagerAdapter adapter;
    protected SlidingTabLayout tabs;

    private TextView[] navbarOptions;

    private TextView actionBarTitle;

    private ActionMenuView amvMenu;
    private Toolbar actionBar;

    private AlphaAnimation animOpen;
    private AlphaAnimation animClose;

    private Fragment currentFragment;

    private LinearLayout navbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        presenter=new MainPresenter(this);

        // this layout includes the custom toolbar my_toolbar.xml
        setContentView(R.layout.activity_main);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        amvMenu = (ActionMenuView) actionBar.findViewById(R.id.amvMenu);
        amvMenu.setOnMenuItemClickListener(this::onOptionsItemSelected);

        navbarOptions=new TextView[7];

        navbarOptions[0] = (TextView) findViewById(R.id.navbar_news);
        navbarOptions[1] = (TextView) findViewById(R.id.navbar_announcements);
        navbarOptions[2] = (TextView) findViewById(R.id.navbar_timetable);
        navbarOptions[3] = (TextView) findViewById(R.id.navbar_studies);
        navbarOptions[4] = (TextView) findViewById(R.id.navbar_community);
        navbarOptions[5] = (TextView) findViewById(R.id.navbar_help);
        navbarOptions[6] = (TextView) findViewById(R.id.navbar_settings);

        for(int i=0;i<navbarOptions.length;i++)
        {
            final int q=i;
            navbarOptions[i].setOnTouchListener((v, event) ->
            {
                presenter.onNavbarItemSelected(q);
                return false;
            });
        }



        navbar=(LinearLayout) findViewById(R.id.navbar);

        ImageButton ib = (ImageButton) findViewById(R.id.closeNavBarButton);
        ib.setOnClickListener(v -> closeNavBar());

        animOpen = new AlphaAnimation(0.0f, 1.0f);
        animOpen.setDuration(300);
        animClose = new AlphaAnimation(1.0f, 0.0f);
        animClose.setDuration(300);


        setSupportActionBar(actionBar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        actionBarTitle=(TextView)findViewById(R.id.actionBarTitle);

        if (AppState.getInstance().isNavBarOpened())
            openNavBar();

        switchFragment(AppState.getInstance().getChosenFragment());
    }
    public Fragment createFragmentById(int id)
    {
        switch (id)
        {
            case 0:
                return NewsFragment.newInstance();
            case 1:
                return AnnouncementsFragment.newInstance();
            case 2:
                return TimetableFragment.newInstance();
            case 3:
                return StudiesFragment.newInstance();
            case 4:
                return CommunityFragment.newInstance();
            case 5:
                return SettingsFragment.newInstance();
            case 6:
                return SettingsFragment.newInstance();
            default:
                return SettingsFragment.newInstance();
        }
    }
    public void openNavBar()
    {
        navbar.setVisibility(View.VISIBLE);
        navbar.startAnimation(animOpen);
        AppState.getInstance().setNavBarOpened(true);
    }

    @Override
    public void switchFragment(int id)
    {
        int elevation=0;
        if(id==0||id==4||id==7)
            elevation=4;
        replaceFragment(createFragmentById(id),
                (String)(navbarOptions[id].getText()),elevation);
        AppState.getInstance().setChosenFragment(id);
    }

    public void closeNavBar()
    {
        navbar.setVisibility(View.GONE);
        navbar.startAnimation(animClose);
        AppState.getInstance().setNavBarOpened(false);
    }
    public void switchActionBarElevation(float elevation)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
         actionBar.setElevation(elevation);
    }
    public void replaceFragment(Fragment fragment,String title, int elevation)
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(currentFragment!=null)
            ft.replace(R.id.fragmentContainer, fragment);
        else
            ft.add(R.id.fragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();

        currentFragment=fragment;

        if(title!=null)
            actionBarTitle.setText(title);

        closeNavBar();

        switchActionBarElevation(TypedValue.applyDimension(TypedValue.
                COMPLEX_UNIT_DIP, elevation, getResources().getDisplayMetrics()));
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        if (id == R.id.action_nav)
        {
            openNavBar();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, amvMenu.getMenu());

        return super.onCreateOptionsMenu(menu);
    }
    public void invokeUrlIntent(String url)
    {
        if(!url.startsWith("http://")||!url.startsWith("https://"))
            url="http://"+url;
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        openNavBar();
        return true;
    }
}
