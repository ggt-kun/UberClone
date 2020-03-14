package com.rightpath.uberclone;

import com.codename1.sms.activation.ActivationForm;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.io.Util.*;

public class CountryPickerForm extends Form {
    public CountryPickerForm(Button sourceButton, Resources flags){
        super(BoxLayout.y());

        CommonCode.initBlackTitleForm(this, "Select a Country", val -> search(val));
        Image blankIcon = Image.createImage(100,70,0);
        char lastChar = (char)-1;
        for (int iter = 0; iter < ActivationForm.COUNTRY_CODES.length;iter++){
            Button b = new Button(ActivationForm.COUNTRY_NAMES[iter],"FlagButton");
            char current = b.getText().charAt(0);
            if (current != lastChar){
                lastChar = current;
                Label l = new Label(""+ lastChar, "FlagsLetter");
                add(l);
            }
            b.setIcon(flags.getImage(ActivationForm.COUNTRY_FLAGS[iter]));
            if (b.getIcon() == null) b.setIcon(blankIcon);
            String currentCountryCode = ActivationForm.COUNTRY_CODES[iter];
            b.addActionListener( ee -> {
                sourceButton.setIcon(b.getIcon());
                sourceButton.setText("+" + currentCountryCode);
                sourceButton.getComponentForm().showBack();
            });
            add(b);
        }
    }
    protected void initGlobalToolbar(){
        super.initGlobalToolbar();
        getToolbar().setUIID("BlackToolbar");
    }
    void search(String s){}
}
