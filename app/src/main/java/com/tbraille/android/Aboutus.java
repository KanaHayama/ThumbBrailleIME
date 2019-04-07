package com.tbraille.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
//                .setImage(R.drawable.dummy_image)
//                .addItem(versionElement)
//                .addItem(adsElement)
                .setDescription("Thumb Braille, just input your word with ONE THUMB!!!")
                .addGroup("Connect with us")
                .addEmail("thumbbraille@gmail.com")
                .addWebsite("http://thumbbraille.com/")
                .addFacebook("unknown")
                .addTwitter("unknown")
                .addYoutube("unknown")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("KanaHayama/ThumbBrailleIME")
                .addInstagram("unknown")
                .create();
        setContentView(aboutPage);
    }
}
