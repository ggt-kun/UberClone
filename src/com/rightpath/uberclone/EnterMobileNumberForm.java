package com.rightpath.uberclone;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

import static com.codename1.ui.CN.getCurrentForm;

public class EnterMobileNumberForm extends Form {
    public EnterMobileNumberForm(){
        super(BoxLayout.y());
        Form previous = getCurrentForm();
        getToolbar().setBackCommand("", Toolbar.BackCommandPolicy.AS_ARROW, evt -> previous.showBack());
        add(new Label("Enter your mobile number", "FlagButton"));
        CountryCodePicker countryCodeButton = new CountryCodePicker();
        TextField phoneNumber = new TextField("", "050-123-4567", 40, TextField.PHONENUMBER);
        add(BorderLayout.centerEastWest(phoneNumber, null, countryCodeButton));
        Style ps = phoneNumber.getUnselectedStyle();
        Style cs = countryCodeButton.getUnselectedStyle();
        int pl = cs.getPaddingLeft(isRTL());
        int pr = cs.getPaddingRight(isRTL());
        countryCodeButton.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        countryCodeButton.getAllStyles().setPadding(ps.getPaddingTop(),ps.getPaddingBottom(),pl,pr);
        setEditOnShow(phoneNumber);
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_FORWARD);
        fab.bindFabToContainer(this);
        fab.addActionListener(evt -> {
            String number = phoneNumber.getText();
            if(number.startsWith("0")) {
                number = number.substring(1);
            }
            new EnterSMSVerificationDigitsForm(countryCodeButton.getText() + "-" + number).show();
        });
    }
}
