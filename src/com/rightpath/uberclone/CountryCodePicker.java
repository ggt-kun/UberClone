package com.rightpath.uberclone;

import static com.codename1.sms.activation.ActivationForm.*;
import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

import java.io.IOException;


public class CountryCodePicker extends Button {
    private Resources flagResource;
    public CountryCodePicker(){
        setUIID("CountryCodePicker");
        addActionListener(evt -> showPickerForm());
        String code = L10NManager.getInstance().getLocale();
        if (code != null){
            String[] countryCodes;
            if (code.length()==2){
                countryCodes = COUNTRY_ISO2;
            }else{
                if (code.length() != 3) return;
                countryCodes = COUNTRY_ISO3;
            }
            code = code.toUpperCase();
            try{
                flagResource = Resources.open("/flag.res");
            }catch (IOException err){
                Log.e(err);
            }
            Image blankIcon = Image.createImage(100,70,0);
            for (int iter=0;iter<countryCodes.length;iter++){
                if (code.equals(countryCodes[iter])){
                    setText("+" + COUNTRY_CODES[iter]);
                    setIcon(flagResource.getImage(COUNTRY_FLAGS[iter]));
                    if (getIcon() == null) setIcon(blankIcon);
                    return;
                }
            }
        }
    }
    protected void showPickerForm(){
        final Form f = getCurrentForm();
        final Transition t = f.getTransitionOutAnimator();
        f.setTransitionOutAnimator(CommonTransitions.createEmpty());
        Form tf = new CountryPickerForm(this,flagResource);
        tf.addShowListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                f.setTransitionOutAnimator(t);
                f.removeShowListener(this);
            }
        });
        tf.show();
    }
}
