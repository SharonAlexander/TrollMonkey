package com.sharonalexander.trollmonkeyhindi;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.sharonalexander.trollmonkeyhindi.helper.ShareHelper;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    static Drawer result;
    static PrimaryDrawerItem item_page1, item_page2, item_page3, item_page4, item_page5, item_page26, item_page7,
            item_page8, item_page9, item_page10, item_page11, item_page12, item_page13,
            item_page14, item_page15, item_page16, item_page17, item_page18, item_page19,
            item_page20, item_page6, item_page22, item_page23, item_page24, item_page25, item_page21;
    Toolbar toolbar;
    AccountHeader headerResult;
    DividerDrawerItem item_divider;
    SecondaryDrawerItem item_settings, item_about, item_shareTheApp, item_addRemove;
    Preferences preferences;
    boolean isPremium = false;
    int adCount = 0;
    private InterstitialAd mInterstitialAdforPages, mInterstitialAdforAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        CheckPurchase.checkpurchases(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        preferences = new Preferences(this);

        isPremium = preferences.getPremiumInfo();
        if (!isPremium) {
            MobileAds.initialize(getApplicationContext(), Constants.admob_app_id);
            mInterstitialAdforPages = new InterstitialAd(this);
            mInterstitialAdforPages.setAdUnitId(Constants.admob_interstitialpages);
            mInterstitialAdforPages.loadAd(new AdRequest.Builder().addTestDevice("EFE01EDD6C65F47A8B03AFD4526C76C9").build());

            mInterstitialAdforAddRemove = new InterstitialAd(this);
            mInterstitialAdforAddRemove.setAdUnitId(Constants.admob_interstitialaddremove);
            mInterstitialAdforAddRemove.loadAd(new AdRequest.Builder().addTestDevice("EFE01EDD6C65F47A8B03AFD4526C76C9").build());
            mInterstitialAdforPages.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAdforPages.loadAd(new AdRequest.Builder().build());
                }

            });
            mInterstitialAdforAddRemove.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAdforAddRemove.loadAd(new AdRequest.Builder().build());
                    startActivity(new Intent(MainActivity.this, AddRemovePagesActivity.class));
                }
            });
        }

        if (preferences.isFirstTimeLaunch()) {
            setDefaultDrawerItemChecks();
        }

        saveAppShareImage();
        completeNavigationDrawer();
    }

    @AfterPermissionGranted(888)
    private void saveAppShareImage() {
        String[] perms = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            boolean shareimage = new ShareHelper().saveBitmapToFile(this);
        } else {
            EasyPermissions.requestPermissions(this, this.getString(R.string.storage_permission_prompt_message),
                    888, perms);
        }
    }

    private void setDefaultDrawerItemChecks() {
        preferences.putCheckPref("page1", true);
        preferences.putCheckPref("page2", true);
        preferences.putCheckPref("page3", true);
        preferences.putCheckPref("page4", true);
        preferences.putCheckPref("page5", true);
        preferences.putCheckPref("page6", true);
        preferences.putCheckPref("page7", true);
        preferences.putCheckPref("page8", true);
        preferences.putCheckPref("page9", true);
        preferences.putCheckPref("page10", true);
        preferences.putCheckPref("page11", true);
        preferences.putCheckPref("page12", true);
        preferences.putCheckPref("page13", true);
        preferences.putCheckPref("page14", true);
        preferences.putCheckPref("page15", true);
        preferences.putCheckPref("page16", true);
        preferences.putCheckPref("page17", true);
        preferences.putCheckPref("page18", true);
        preferences.putCheckPref("page19", true);
        preferences.putCheckPref("page20", true);
        preferences.putCheckPref("page21", true);
        preferences.putCheckPref("page22", true);
        preferences.putCheckPref("page23", true);
        preferences.putCheckPref("page24", true);
        preferences.putCheckPref("page25", true);
        preferences.putCheckPref("page26", true);

        preferences.setFirstTimeLaunch(false);
    }

    private void completeNavigationDrawer() {
        createNavigationHeader();
        createDrawerLayout();
        createDrawerItems();
        addDrawerItems();
        createDrawerClicks();

        //The First Page is set here. And cannot be changed from addremove
        result.setSelection(3);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }

    @Override
    public void onBackPressed() {
        CheckPurchase.dispose();
        if (result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            Settings frag = (Settings) getFragmentManager().findFragmentByTag("settings");
            if (frag != null) {
                Fragment fragment = new ContentActivity();
                Bundle bundle = new Bundle();
                bundle.putString("id", Constants.id_page1);
                bundle.putInt("pic", R.drawable.icon_aib);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).commit();
                setTitle(getString(R.string.app_name));
            } else {
                // alertExitPic();
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getFragmentManager().beginTransaction().replace(R.id.mainFrame, new Settings(), "settings").commit();
        } else if (id == R.id.action_add_remove) {
            if (!isPremium && mInterstitialAdforAddRemove.isLoaded()) {
                mInterstitialAdforAddRemove.show();
            } else {
                startActivity(new Intent(MainActivity.this, AddRemovePagesActivity.class));
            }
        } else if (id == R.id.action_about) {
            showAlertAboutUs();
        } else if (id == R.id.action_share) {
            new ShareHelper().shareAppDetails(MainActivity.this);
        }
        return true;
    }

    private void createNavigationHeader() {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.nav_header)
                .addProfiles(
                        new ProfileDrawerItem().withName(getString(R.string.app_name)).withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
    }

    private void createDrawerLayout() {
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .build();
    }

    private void createDrawerItems() {
        item_page1 = new PrimaryDrawerItem().withIdentifier(1).withName(getString(R.string.pagename_1)).withIcon(R.drawable.icon_aib);
        item_page2 = new PrimaryDrawerItem().withIdentifier(2).withName(getString(R.string.pagename_2)).withIcon(R.drawable.icon_backbenchers);
        item_page3 = new PrimaryDrawerItem().withIdentifier(3).withName(getString(R.string.pagename_3)).withIcon(R.drawable.icon_rajnikantvscid);
        item_page4 = new PrimaryDrawerItem().withIdentifier(4).withName(getString(R.string.pagename_4)).withIcon(R.drawable.icon_saaledostkothanks);
        item_page5 = new PrimaryDrawerItem().withIdentifier(5).withName(getString(R.string.pagename_5)).withIcon(R.drawable.icon_letthissemestergo);
        item_page6 = new PrimaryDrawerItem().withIdentifier(6).withName(getString(R.string.pagename_6)).withIcon(R.drawable.icon_funkyou);
        item_page7 = new PrimaryDrawerItem().withIdentifier(7).withName(getString(R.string.pagename_7)).withIcon(R.drawable.icon_sudhdesiendings);
        item_page8 = new PrimaryDrawerItem().withIdentifier(8).withName(getString(R.string.pagename_8)).withIcon(R.drawable.icon_thescribbledstories);
        item_page9 = new PrimaryDrawerItem().withIdentifier(9).withName(getString(R.string.pagename_9)).withIcon(R.drawable.icon_unofficialarnabgoswami);
        item_page10 = new PrimaryDrawerItem().withIdentifier(10).withName(getString(R.string.pagename_10)).withIcon(R.drawable.icon_unofficialsubramanian);
        item_page11 = new PrimaryDrawerItem().withIdentifier(11).withName(getString(R.string.pagename_11)).withIcon(R.drawable.icon_bohotbhukhlagihaiyaar);
        item_page12 = new PrimaryDrawerItem().withIdentifier(12).withName(getString(R.string.pagename_12)).withIcon(R.drawable.icon_schoollifemileginadobara);
        item_page13 = new PrimaryDrawerItem().withIdentifier(13).withName(getString(R.string.pagename_13)).withIcon(R.drawable.icon_boysvsgirls);
        item_page14 = new PrimaryDrawerItem().withIdentifier(14).withName(getString(R.string.pagename_14)).withIcon(R.drawable.icon_lastbenchstudent);
        item_page15 = new PrimaryDrawerItem().withIdentifier(15).withName(getString(R.string.pagename_15)).withIcon(R.drawable.icon_bakchodbilli);
        item_page16 = new PrimaryDrawerItem().withIdentifier(16).withName(getString(R.string.pagename_16)).withIcon(R.drawable.icon_onlyanenggstudentcanunderstand);
        item_page17 = new PrimaryDrawerItem().withIdentifier(17).withName(getString(R.string.pagename_17)).withIcon(R.drawable.icon_yekyachuttiyapahai);
        item_page18 = new PrimaryDrawerItem().withIdentifier(18).withName(getString(R.string.pagename_18)).withIcon(R.drawable.icon_yekyabakchodihai);
        item_page19 = new PrimaryDrawerItem().withIdentifier(19).withName(getString(R.string.pagename_19)).withIcon(R.drawable.icon_funnyindia);
        item_page20 = new PrimaryDrawerItem().withIdentifier(20).withName(getString(R.string.pagename_20)).withIcon(R.drawable.icon_bollywoodgandu);
        item_page21 = new PrimaryDrawerItem().withIdentifier(21).withName(getString(R.string.pagename_21)).withIcon(R.drawable.icon_chalyaarjohogadekhajayega);
        item_page22 = new PrimaryDrawerItem().withIdentifier(22).withName(getString(R.string.pagename_22)).withIcon(R.drawable.icon_laughingcolors);
        item_page23 = new PrimaryDrawerItem().withIdentifier(23).withName(getString(R.string.pagename_23)).withIcon(R.drawable.icon_thedesistuff);
        item_page24 = new PrimaryDrawerItem().withIdentifier(24).withName(getString(R.string.pagename_24)).withIcon(R.drawable.icon_bollywoodclassroom);

        item_divider = new DividerDrawerItem();

        item_settings = new SecondaryDrawerItem().withIdentifier(100).withName(getString(R.string.action_settings)).withIcon(R.drawable.settings).withSelectable(false);
        item_about = new SecondaryDrawerItem().withIdentifier(101).withName(getString(R.string.about)).withIcon(R.drawable.about).withSelectable(false);
        item_shareTheApp = new SecondaryDrawerItem().withIdentifier(102).withName(R.string.sharetheapp).withIcon(R.drawable.share).withSelectable(false);
        item_addRemove = new SecondaryDrawerItem().withIdentifier(103).withName(R.string.add_remove).withIcon(R.drawable.add_remove).withSelectable(false);
    }

    private void addDrawerItems() {
        Preferences preferences = new Preferences(this);
        if (preferences.getCheckPref("page1")) {
            result.addItem(item_page1);
        }
        if (preferences.getCheckPref("page2")) {
            result.addItem(item_page2);
        }
        if (preferences.getCheckPref("page3")) {
            result.addItem(item_page3);
        }
        if (preferences.getCheckPref("page4")) {
            result.addItem(item_page4);
        }
        if (preferences.getCheckPref("page5")) {
            result.addItem(item_page5);
        }
        if (preferences.getCheckPref("page6")) {
            result.addItem(item_page6);
        }
        if (preferences.getCheckPref("page7")) {
            result.addItem(item_page7);
        }
        if (preferences.getCheckPref("page8")) {
            result.addItem(item_page8);
        }
        if (preferences.getCheckPref("page9")) {
            result.addItem(item_page9);
        }
        if (preferences.getCheckPref("page10")) {
            result.addItem(item_page10);
        }
        if (preferences.getCheckPref("page11")) {
            result.addItem(item_page11);
        }
        if (preferences.getCheckPref("page12")) {
            result.addItem(item_page12);
        }
        if (preferences.getCheckPref("page13")) {
            result.addItem(item_page13);
        }
        if (preferences.getCheckPref("page14")) {
            result.addItem(item_page14);
        }
        if (preferences.getCheckPref("page15")) {
            result.addItem(item_page15);
        }
        if (preferences.getCheckPref("page16")) {
            result.addItem(item_page16);
        }
        if (preferences.getCheckPref("page17")) {
            result.addItem(item_page17);
        }
        if (preferences.getCheckPref("page18")) {
            result.addItem(item_page18);
        }
        if (preferences.getCheckPref("page19")) {
            result.addItem(item_page19);
        }
        if (preferences.getCheckPref("page20")) {
            result.addItem(item_page20);
        }
        if (preferences.getCheckPref("page21")) {
            result.addItem(item_page21);
        }
        if (preferences.getCheckPref("page22")) {
            result.addItem(item_page22);
        }
        if (preferences.getCheckPref("page23")) {
            result.addItem(item_page23);
        }
        if (preferences.getCheckPref("page24")) {
            result.addItem(item_page24);
        }
        result.addItems(item_divider, item_addRemove, item_settings, item_about, item_shareTheApp);
    }

    private void createDrawerClicks() {
        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Fragment fragment = new ContentActivity();
                Bundle bundle = new Bundle();
                adCount = adCount + 1;
                switch ((int) drawerItem.getIdentifier()) {
                    case 1:
                        bundle.putString("id", Constants.id_page1);
                        bundle.putInt("pic", R.drawable.icon_aib);
                        break;
                    case 2:
                        bundle.putString("id", Constants.id_page2);
                        bundle.putInt("pic", R.drawable.icon_backbenchers);
                        break;
                    case 3:
                        bundle.putString("id", Constants.id_page3);
                        bundle.putInt("pic", R.drawable.icon_rajnikantvscid);
                        break;
                    case 4:
                        //alertMntAccess();
                        bundle.putString("id", Constants.id_page4);
                        bundle.putInt("pic", R.drawable.icon_saaledostkothanks);
                        break;
                    case 5:
                        bundle.putString("id", Constants.id_page5);
                        bundle.putInt("pic", R.drawable.icon_letthissemestergo);
                        break;
                    case 6:
                        bundle.putString("id", Constants.id_page6);
                        bundle.putInt("pic", R.drawable.icon_funkyou);
                        break;
                    case 7:
                        bundle.putString("id", Constants.id_page7);
                        bundle.putInt("pic", R.drawable.icon_sudhdesiendings);
                        break;
                    case 8:
                        bundle.putString("id", Constants.id_page8);
                        bundle.putInt("pic", R.drawable.icon_thescribbledstories);
                        break;
                    case 9:
                        bundle.putString("id", Constants.id_page9);
                        bundle.putInt("pic", R.drawable.icon_unofficialarnabgoswami);
                        break;
                    case 10:
                        bundle.putString("id", Constants.id_page10);
                        bundle.putInt("pic", R.drawable.icon_unofficialsubramanian);
                        break;
                    case 11:
                        bundle.putString("id", Constants.id_page11);
                        bundle.putInt("pic", R.drawable.icon_bohotbhukhlagihaiyaar);
                        break;
                    case 12:
                        bundle.putString("id", Constants.id_page12);
                        bundle.putInt("pic", R.drawable.icon_schoollifemileginadobara);
                        break;
                    case 13:
                        bundle.putString("id", Constants.id_page13);
                        bundle.putInt("pic", R.drawable.icon_boysvsgirls);
                        break;
                    case 14:
                        bundle.putString("id", Constants.id_page14);
                        bundle.putInt("pic", R.drawable.icon_lastbenchstudent);
                        break;
                    case 15:
                        bundle.putString("id", Constants.id_page15);
                        bundle.putInt("pic", R.drawable.icon_bakchodbilli);
                        break;
                    case 16:
                        bundle.putString("id", Constants.id_page16);
                        bundle.putInt("pic", R.drawable.icon_onlyanenggstudentcanunderstand);
                        break;
                    case 17:
                        bundle.putString("id", Constants.id_page17);
                        bundle.putInt("pic", R.drawable.icon_yekyachuttiyapahai);
                        break;
                    case 18:
                        bundle.putString("id", Constants.id_page18);
                        bundle.putInt("pic", R.drawable.icon_yekyabakchodihai);
                        break;
                    case 19:
                        bundle.putString("id", Constants.id_page19);
                        bundle.putInt("pic", R.drawable.icon_funnyindia);
                        break;
                    case 20:
                        bundle.putString("id", Constants.id_page20);
                        bundle.putInt("pic", R.drawable.icon_bollywoodgandu);
                        break;
                    case 21:
                        bundle.putString("id", Constants.id_page21);
                        bundle.putInt("pic", R.drawable.icon_chalyaarjohogadekhajayega);
                        break;
                    case 22:
                        bundle.putString("id", Constants.id_page22);
                        bundle.putInt("pic", R.drawable.icon_laughingcolors);
                        break;
                    case 23:
                        bundle.putString("id", Constants.id_page23);
                        bundle.putInt("pic", R.drawable.icon_thedesistuff);
                        break;
                    case 24:
                        bundle.putString("id", Constants.id_page24);
                        bundle.putInt("pic", R.drawable.icon_bollywoodclassroom);
                        break;

                    case 100://settings
                        getFragmentManager().beginTransaction().replace(R.id.mainFrame, new Settings(), "settings").commit();
                        result.closeDrawer();
                        return true;
                    case 101://about
                        showAlertAboutUs();
                        return true;
                    case 102://share the app
                        new ShareHelper().shareAppDetails(MainActivity.this);
                        return true;
                    case 103: //add remove pages
                        if (!isPremium && mInterstitialAdforAddRemove.isLoaded()) {
                            mInterstitialAdforAddRemove.show();
                        } else {
                            startActivity(new Intent(MainActivity.this, AddRemovePagesActivity.class));
                        }
                        return true;
                    default:
                        showPageError();
                        return true;
                }
                //check this implementation for on adclosed
                if (adCount == 5 && !isPremium) {
                    if (mInterstitialAdforPages.isLoaded()) {
                        mInterstitialAdforPages.show();
                    }
                    adCount = 0;
                }
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).commit();
                result.closeDrawer();
                return true;
            }
        });
    }

    private void alertMntAccess() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogTheme));
        ImageView imageView = new ImageView(MainActivity.this);
        int x = (int) (Math.random() * 3);
        if (x == 0) {
            imageView.setImageResource(R.drawable.mnt_access_pic);
        } else if (x == 1) {
            imageView.setImageResource(R.drawable.mnt_access_pic2);
        } else {
            imageView.setImageResource(R.drawable.mnt_access_pic3);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        alertdialog.setView(imageView)
//                .setTitle("കൊച്ചു കള്ളൻ..!!")
                .setPositiveButton("ഇവിടെ മാത്രം ഞെക്കുക ..", null)
                .show();
    }

    private void alertExitPic() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogTheme));
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int x = (int) (Math.random() * 3);
        if (x == 0) {
            imageView.setImageResource(R.drawable.exit_troll_pic);
        } else if (x == 1) {
            imageView.setImageResource(R.drawable.exit_troll_pic2);
        } else if (x == 2) {
            imageView.setImageResource(R.drawable.exit_troll_pic3);
        }
        alertdialog.setView(imageView)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, R.string.exit_toast, Toast.LENGTH_SHORT).show();
                        MainActivity.super.onBackPressed();
                    }
                })
                .show();
    }

    private void showPageError() {
        new AlertDialog.Builder(this)
                .setTitle("Page Error")
                .setMessage("The page cannot be displayed. \nTry another page instead")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    private void showAlertAboutUs() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo != null ? pInfo.versionName : "";
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Version:" + version + "\n" + Constants.alert_developer_info)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, R.string.permission_prompt_settings_return, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
