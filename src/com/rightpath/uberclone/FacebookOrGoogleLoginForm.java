package com.rightpath.uberclone;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import static com.codename1.ui.CN.getCurrentForm;

public class FacebookOrGoogleLoginForm extends Form {
    public FacebookOrGoogleLoginForm(){
        super(BoxLayout.y());
        Form previous = getCurrentForm();
        getToolbar().setBackCommand("", Toolbar.BackCommandPolicy.AS_ARROW, evt -> previous.showBack());
        add(new Label("Choose an account","FlagButton"));
        Resources r = Resources.getGlobalResources();
        Button facebook = new Button("Facebook",r.getImage("facebook.png"),"FlagButton");
        Button google = new Button("Google",r.getImage("google.png"),"FlagButton");
        add(facebook).add(google);
    }
}
