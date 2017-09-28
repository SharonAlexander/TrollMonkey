package com.sharonalexander.trollmonkey;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


public class AddRemovePagesActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox checkPage1, checkPage2, checkPage3, checkPage4, checkPage5, checkPage6, checkPage7, checkPage8,
            checkPage9, checkPage10, checkPage11, checkPage12, checkPage13, checkPage14,
            checkPage15, checkPage16, checkPage17, checkPage18, checkPage19,
            checkPage20, checkPage21, checkPage22, checkPage23, checkPage24, checkPage25, checkPage26;
    Preferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_remove_layout);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_to_add);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            linearLayout.setLayoutDirection(LinearLayout.LAYOUT_DIRECTION_RTL);
        }

        preferences = new Preferences(this);

        initialiseCheckBoxes();
        setChecks();
        setCheckListeners();
    }


    private void initialiseCheckBoxes() {
        checkPage1 = (CheckBox) findViewById(R.id.checkPage1);
        checkPage2 = (CheckBox) findViewById(R.id.checkPage2);
        checkPage3 = (CheckBox) findViewById(R.id.checkPage3);
        checkPage4 = (CheckBox) findViewById(R.id.checkPage4);
        checkPage5 = (CheckBox) findViewById(R.id.checkPage5);
        checkPage6 = (CheckBox) findViewById(R.id.checkPage6);
        checkPage7 = (CheckBox) findViewById(R.id.checkPage7);
        checkPage8 = (CheckBox) findViewById(R.id.checkPage8);
        checkPage9 = (CheckBox) findViewById(R.id.checkPage9);
        checkPage10 = (CheckBox) findViewById(R.id.checkPage10);
        checkPage11 = (CheckBox) findViewById(R.id.checkPage11);
        checkPage12 = (CheckBox) findViewById(R.id.checkPage12);
        checkPage13 = (CheckBox) findViewById(R.id.checkPage13);
        checkPage14 = (CheckBox) findViewById(R.id.checkPage14);
        checkPage15 = (CheckBox) findViewById(R.id.checkPage15);
        checkPage16 = (CheckBox) findViewById(R.id.checkPage16);
        checkPage17 = (CheckBox) findViewById(R.id.checkPage17);
        checkPage18 = (CheckBox) findViewById(R.id.checkPage18);
        checkPage19 = (CheckBox) findViewById(R.id.checkPage19);
        checkPage20 = (CheckBox) findViewById(R.id.checkPage20);
        checkPage21 = (CheckBox) findViewById(R.id.checkPage21);
        checkPage22 = (CheckBox) findViewById(R.id.checkPage22);
        checkPage23 = (CheckBox) findViewById(R.id.checkPage23);
        checkPage24 = (CheckBox) findViewById(R.id.checkPage24);
        checkPage25 = (CheckBox) findViewById(R.id.checkPage25);
        checkPage26 = (CheckBox) findViewById(R.id.checkPage26);
    }

    private void setChecks() {
        checkPage1.setChecked(preferences.getCheckPref("icu"));
        checkPage2.setChecked(preferences.getCheckPref("trollm"));
        checkPage3.setChecked(preferences.getCheckPref("trollr"));
        checkPage4.setChecked(preferences.getCheckPref("mnt"));
        checkPage5.setChecked(preferences.getCheckPref("dank"));
        checkPage6.setChecked(preferences.getCheckPref("psctrolls"));
        checkPage7.setChecked(preferences.getCheckPref("kidilan"));
        checkPage8.setChecked(preferences.getCheckPref("sct"));
        checkPage9.setChecked(preferences.getCheckPref("trollcricket"));
        checkPage10.setChecked(preferences.getCheckPref("trollfootball"));
        checkPage11.setChecked(preferences.getCheckPref("trollmcinema"));
        checkPage12.setChecked(preferences.getCheckPref("mtm"));
        checkPage13.setChecked(preferences.getCheckPref("sheru"));
        checkPage14.setChecked(preferences.getCheckPref("cinemamixer"));
        checkPage15.setChecked(preferences.getCheckPref("cybertrollers"));
        checkPage16.setChecked(preferences.getCheckPref("thengakola"));
        checkPage17.setChecked(preferences.getCheckPref("trollmollywood"));
        checkPage18.setChecked(preferences.getCheckPref("trollclashers"));
        checkPage19.setChecked(preferences.getCheckPref("outspoken"));
        checkPage20.setChecked(preferences.getCheckPref("btechtrolls"));
        checkPage26.setChecked(preferences.getCheckPref("mpling"));
        checkPage22.setChecked(preferences.getCheckPref("trollkerala"));
        checkPage23.setChecked(preferences.getCheckPref("trollreligion"));
        checkPage24.setChecked(preferences.getCheckPref("trollktu"));
        checkPage25.setChecked(preferences.getCheckPref("pravasitrolls"));
        checkPage21.setChecked(preferences.getCheckPref("onlinetm"));
    }

    private void setCheckListeners() {
        checkPage1.setOnCheckedChangeListener(this);
        checkPage2.setOnCheckedChangeListener(this);
        checkPage3.setOnCheckedChangeListener(this);
        checkPage4.setOnCheckedChangeListener(this);
        checkPage5.setOnCheckedChangeListener(this);
        checkPage6.setOnCheckedChangeListener(this);
        checkPage7.setOnCheckedChangeListener(this);
        checkPage8.setOnCheckedChangeListener(this);
        checkPage9.setOnCheckedChangeListener(this);
        checkPage10.setOnCheckedChangeListener(this);
        checkPage11.setOnCheckedChangeListener(this);
        checkPage12.setOnCheckedChangeListener(this);
        checkPage13.setOnCheckedChangeListener(this);
        checkPage14.setOnCheckedChangeListener(this);
        checkPage15.setOnCheckedChangeListener(this);
        checkPage16.setOnCheckedChangeListener(this);
        checkPage17.setOnCheckedChangeListener(this);
        checkPage18.setOnCheckedChangeListener(this);
        checkPage19.setOnCheckedChangeListener(this);
        checkPage20.setOnCheckedChangeListener(this);
        checkPage26.setOnCheckedChangeListener(this);
        checkPage22.setOnCheckedChangeListener(this);
        checkPage23.setOnCheckedChangeListener(this);
        checkPage24.setOnCheckedChangeListener(this);
        checkPage25.setOnCheckedChangeListener(this);
        checkPage21.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.checkPage1:
                if (compoundButton.isChecked()) {
//                    MainActivity.result.addItemAtPosition(MainActivity.item_page1, 1);
                    checkPage1.setChecked(true);
//                    preferences.putCheckPref("icu", true);
                } else {
//                    MainActivity.result.removeItem(1);
                    checkPage1.setChecked(true);
//                    preferences.putCheckPref("icu", false);
                }
                break;
            case R.id.checkPage2:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page2, 2);
                    checkPage2.setChecked(true);
                    preferences.putCheckPref("trollm", true);
                } else {
                    MainActivity.result.removeItem(2);
                    checkPage2.setChecked(false);
                    preferences.putCheckPref("trollm", false);
                }
                break;
            case R.id.checkPage3:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page3, 3);
                    checkPage3.setChecked(true);
                    preferences.putCheckPref("trollr", true);
                } else {
                    MainActivity.result.removeItem(3);
                    checkPage3.setChecked(false);
                    preferences.putCheckPref("trollr", false);
                }
                break;
            case R.id.checkPage4:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page4, 4);
                    checkPage4.setChecked(true);
                    preferences.putCheckPref("mnt", true);
                } else {
                    MainActivity.result.removeItem(4);
                    checkPage4.setChecked(false);
                    preferences.putCheckPref("mnt", false);
                }
                break;
            case R.id.checkPage5:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page5, 5);
                    checkPage5.setChecked(true);
                    preferences.putCheckPref("dank", true);
                } else {
                    MainActivity.result.removeItem(5);
                    checkPage5.setChecked(false);
                    preferences.putCheckPref("dank", false);
                }
                break;
            case R.id.checkPage6:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page6, 5);
                    checkPage6.setChecked(true);
                    preferences.putCheckPref("psctrolls", true);
                } else {
                    MainActivity.result.removeItem(6);
                    checkPage6.setChecked(false);
                    preferences.putCheckPref("psctrolls", false);
                }
                break;
            case R.id.checkPage7:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page7, 5);
                    checkPage7.setChecked(true);
                    preferences.putCheckPref("kidilan", true);
                } else {
                    MainActivity.result.removeItem(7);
                    checkPage7.setChecked(false);
                    preferences.putCheckPref("kidilan", false);
                }
                break;
            case R.id.checkPage8:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page8, 5);
                    checkPage8.setChecked(true);
                    preferences.putCheckPref("sct", true);
                } else {
                    MainActivity.result.removeItem(8);
                    checkPage8.setChecked(false);
                    preferences.putCheckPref("sct", false);
                }
                break;
            case R.id.checkPage9:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page9, 5);
                    checkPage9.setChecked(true);
                    preferences.putCheckPref("trollcricket", true);
                } else {
                    MainActivity.result.removeItem(9);
                    checkPage9.setChecked(false);
                    preferences.putCheckPref("trollcricket", false);
                }
                break;
            case R.id.checkPage10:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page10, 5);
                    checkPage10.setChecked(true);
                    preferences.putCheckPref("trollfootball", true);
                } else {
                    MainActivity.result.removeItem(10);
                    checkPage10.setChecked(false);
                    preferences.putCheckPref("trollfootball", false);
                }
                break;
            case R.id.checkPage11:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page11, 5);
                    checkPage11.setChecked(true);
                    preferences.putCheckPref("trollmcinema", true);
                } else {
                    MainActivity.result.removeItem(11);
                    checkPage11.setChecked(false);
                    preferences.putCheckPref("trollmcinema", false);
                }
                break;
            case R.id.checkPage12:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page12, 5);
                    checkPage12.setChecked(true);
                    preferences.putCheckPref("mtm", true);
                } else {
                    MainActivity.result.removeItem(12);
                    checkPage12.setChecked(false);
                    preferences.putCheckPref("mtm", false);
                }
                break;
            case R.id.checkPage13:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page13, 5);
                    checkPage13.setChecked(true);
                    preferences.putCheckPref("sheru", true);
                } else {
                    MainActivity.result.removeItem(13);
                    checkPage13.setChecked(false);
                    preferences.putCheckPref("sheru", false);
                }
                break;
            case R.id.checkPage14:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page14, 5);
                    checkPage14.setChecked(true);
                    preferences.putCheckPref("cinemamixer", true);
                } else {
                    MainActivity.result.removeItem(14);
                    checkPage14.setChecked(false);
                    preferences.putCheckPref("cinemamixer", false);
                }
                break;
            case R.id.checkPage15:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page15, 5);
                    checkPage15.setChecked(true);
                    preferences.putCheckPref("cybertrollers", true);
                } else {
                    MainActivity.result.removeItem(15);
                    checkPage15.setChecked(false);
                    preferences.putCheckPref("cybertrollers", false);
                }
                break;
            case R.id.checkPage16:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page16, 5);
                    checkPage16.setChecked(true);
                    preferences.putCheckPref("thengakola", true);
                } else {
                    MainActivity.result.removeItem(16);
                    checkPage16.setChecked(false);
                    preferences.putCheckPref("thengakola", false);
                }
                break;
            case R.id.checkPage17:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page17, 5);
                    checkPage17.setChecked(true);
                    preferences.putCheckPref("trollmollywood", true);
                } else {
                    MainActivity.result.removeItem(17);
                    checkPage17.setChecked(false);
                    preferences.putCheckPref("trollmollywood", false);
                }
                break;
            case R.id.checkPage18:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page18, 5);
                    checkPage18.setChecked(true);
                    preferences.putCheckPref("trollclashers", true);
                } else {
                    MainActivity.result.removeItem(18);
                    checkPage18.setChecked(false);
                    preferences.putCheckPref("trollclashers", false);
                }
                break;
            case R.id.checkPage19:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page19, 5);
                    checkPage19.setChecked(true);
                    preferences.putCheckPref("outspoken", true);
                } else {
                    MainActivity.result.removeItem(19);
                    checkPage19.setChecked(false);
                    preferences.putCheckPref("outspoken", false);
                }
                break;
            case R.id.checkPage20:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page20, 5);
                    checkPage20.setChecked(true);
                    preferences.putCheckPref("btechtrolls", true);
                } else {
                    MainActivity.result.removeItem(20);
                    checkPage20.setChecked(false);
                    preferences.putCheckPref("btechtrolls", false);
                }
                break;
            case R.id.checkPage21:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page21, 5);
                    checkPage21.setChecked(true);
                    preferences.putCheckPref("onlinetm", true);
                } else {
                    MainActivity.result.removeItem(21);
                    checkPage21.setChecked(false);
                    preferences.putCheckPref("onlinetm", false);
                }
                break;
            case R.id.checkPage22:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page22, 5);
                    checkPage22.setChecked(true);
                    preferences.putCheckPref("trollkerala", true);
                } else {
                    MainActivity.result.removeItem(22);
                    checkPage22.setChecked(false);
                    preferences.putCheckPref("trollkerala", false);
                }
                break;
            case R.id.checkPage23:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page23, 5);
                    checkPage23.setChecked(true);
                    preferences.putCheckPref("trollreligion", true);
                } else {
                    MainActivity.result.removeItem(23);
                    checkPage23.setChecked(false);
                    preferences.putCheckPref("trollreligion", false);
                }
                break;
            case R.id.checkPage24:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page24, 5);
                    checkPage24.setChecked(true);
                    preferences.putCheckPref("trollktu", true);
                } else {
                    MainActivity.result.removeItem(24);
                    checkPage24.setChecked(false);
                    preferences.putCheckPref("trollktu", false);
                }
                break;
            case R.id.checkPage25:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page25, 5);
                    checkPage25.setChecked(true);
                    preferences.putCheckPref("pravasitrolls", true);
                } else {
                    MainActivity.result.removeItem(25);
                    checkPage25.setChecked(false);
                    preferences.putCheckPref("pravasitrolls", false);
                }
                break;
            case R.id.checkPage26:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_page26, 5);
                    checkPage26.setChecked(true);
                    preferences.putCheckPref("mpling", true);
                } else {
                    MainActivity.result.removeItem(26);
                    checkPage26.setChecked(false);
                    preferences.putCheckPref("mpling", false);
                }
                break;
        }
    }
}
