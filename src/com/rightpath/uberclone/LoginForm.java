package com.rightpath.uberclone;

import com.codename1.components.SpanButton;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Effects;
import com.codename1.ui.util.Resources;

import static com.codename1.ui.CN.*;

public class LoginForm extends Form {
    public LoginForm(){
        super (new BorderLayout());
        Label squareLogo = new Label("", Resources.getGlobalResources().getImage("uber-log.png"),"SquareLogo"){
            protected Dimension calcPreferredSize(){
                Dimension size = super.calcPreferredSize();
                size.setHeight(size.getWidth());
                return size;
            }
        };
        Label placeholder = new Label();
        Container logo = LayeredLayout.encloseIn(placeholder,BorderLayout.centerAbsolute(squareLogo));
        startThread(() -> {
            final Image shadow = Effects.squareShadow(squareLogo.getPreferredW(),squareLogo.getPreferredH(), convertToPixels(14),0.35f);
            callSerially(()-> {
                logo.replace(placeholder, BorderLayout.centerAbsolute(new Label(shadow,"Container")),null);
                revalidate();
            });
        },"Shadow Maker").start();
        logo.setUIID("LogoBackground");
        add(CENTER,logo);
        Label getMovingWithUber = new Label("Get moving with Uber", "GetMovingWithUber");
        CountryCodePicker countryCodeButton = new CountryCodePicker(){
            protected void showPickerForm(){
                new EnterMobileNumberForm().show();
            }
        };
        SpanButton phoneNumber = new SpanButton("Enter your mobile number","PhoneNumberHint");
        phoneNumber.getTextComponent().setColumns(80);
        phoneNumber.getTextComponent().setRows(2);
        phoneNumber.getTextComponent().setGrowByContent(false);
        phoneNumber.setUIID("Container");
        phoneNumber.addActionListener(e -> new EnterMobileNumberForm().show());
        Container phonePicking = BorderLayout.centerCenterEastWest(phoneNumber, null, countryCodeButton);
        phonePicking.setUIID("Seperator");
        Button social = new Button("Or connect with social","ConnectWithSocialButton");
        social.addActionListener(evt -> new FacebookOrGoogleLoginForm().show());
        add(SOUTH, BoxLayout.encloseY(getMovingWithUber,phonePicking,social));

    }

    @Override
    protected boolean shouldPaintStatusBar() {
        return false;
    }

    @Override
    protected void initGlobalToolbar() {

    }
}
