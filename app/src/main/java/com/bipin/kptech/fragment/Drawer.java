package com.bipin.kptech.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bipin.kptech.R;

/**
 * Created by Bipin on 8/30/2016.
 */
@SuppressWarnings("ALL")
public class Drawer extends Fragment {

    public static DrawerLayout mDrawerLayout;
    public static View containerView;
    private String TAG = Drawer.class.getSimpleName();
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean isHomeScreenRequested;
    private boolean isCourseScreenRequested;
    private boolean isTeamScreenRequested;
    private boolean isServiceScreenRequested;
    private boolean isManagementScreenRequested;
    private boolean isGalleryScreenRequested;
    private boolean isContactScreenRequested;

    public Drawer() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        isHomeScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isCourseScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isTeamScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isServiceScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isManagementScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isGalleryScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);
        isContactScreenRequested = getActivity().getApplicationContext()
                .getResources().getBoolean(R.bool.profile_screen_requested_false);


        final View rootView = inflater.inflate(R.layout.nav_fragment, container, getActivity()
                .getApplicationContext().getResources().getBoolean(R.bool.attach_to_root));
        final TextView txtHomeDrawer    = (TextView) rootView.findViewById(R.id.txtHomeDrawer);
        final TextView txtCourseDrawer  = (TextView) rootView.findViewById(R.id.txtCourseDrawer);
        final TextView txtTeamDrawer    = (TextView) rootView.findViewById(R.id.txtTeamDrawer);
        final TextView txtServiceDrawer = (TextView) rootView.findViewById(R.id.txtServiceDrawer);
        final TextView txtMagntDrawer   = (TextView) rootView.findViewById(R.id.txtMagntDrawer);
        final TextView txtGalleryDrawer = (TextView) rootView.findViewById(R.id.txtGalleryDrawer);
        final TextView txtContactDrawer = (TextView) rootView.findViewById(R.id.txtContactDrawer);

        //click event for home drawer menu
        txtHomeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHomeScreenRequested) {
                    isHomeScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isHomeScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Course drawer menu
        txtCourseDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCourseScreenRequested) {
                    isCourseScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isCourseScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Team drawer menu
        txtTeamDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTeamScreenRequested) {
                    isTeamScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isTeamScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Services drawer menu
        txtServiceDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceScreenRequested) {
                    isServiceScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isServiceScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Management drawer menu
        txtMagntDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isManagementScreenRequested) {
                    isManagementScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isManagementScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Gallery drawer menu
        txtGalleryDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGalleryScreenRequested) {
                    isGalleryScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isGalleryScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });
        //click event for Contact drawer menu
        txtContactDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isContactScreenRequested) {
                    isContactScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isContactScreenRequested = getActivity().getApplicationContext().getResources().getBoolean(R.bool.profile_screen_requested_false);
                            final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.body_container_home, new Home());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            mDrawerLayout.closeDrawer(containerView);
                        }
                    }, getActivity().getApplicationContext().getResources().getInteger(R.integer.button_click_delay));
                }
            }
        });


        return rootView;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @SuppressLint("NewApi")
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @SuppressLint("NewApi")
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @SuppressLint("NewApi")
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }
}
