package com.rightpath.uberclone;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import static com.codename1.ui.CN.*;

public class EnterSMSVerificationDigitsForm extends Form {
    public EnterSMSVerificationDigitsForm(String phone) {
        super(new BorderLayout());
        Form previous = getCurrentForm();
        getToolbar().setBackCommand("", Toolbar.BackCommandPolicy.AS_ARROW,evt -> previous.showBack());
        Container box = new Container(BoxLayout.y());
        box.setScrollableY(true);
        box.add(new SpanLabel("Enter the 4 digits code sent to you at " + phone, "FlagButton"));
        TextField[] digits = createDigits(4);
        setEditOnShow(digits[0]);
        box.add(BoxLayout.encloseX(digits));
        SpanLabel error = new SpanLabel("The SMS passcode you've entered is incorrect","ErrorLabel");
        error.setVisible(false);
        box.add(error);
        add(CENTER, box);
        Label resend = new Label("Resend code in 00:12","ResendCode");
        add(SOUTH, resend);
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_FORWARD);
        fab.bindFabToContainer(this);
        fab.addActionListener(evt -> {
            if(!isValid(toString(digits))){
                error.setVisible(true);
                erorrFields(digits);
                repaint();
            }
            new EnterPasswordForm().show();
        });
    }

    private TextField[] createDigits(int count) {
        TextField[] response = new TextField[count];
        for(int iter = 0; iter < count; iter++){
            TextField t = new TextField("", "0", 1, TextField.NUMERIC);
            t.setUIID("Digit");
            t.getHintLabel().getAllStyles().setAlignment(CENTER);
            response[iter] = t;
        }
        
        for(int iter = 0; iter < count -1; iter++){
            onTypeNext(response[iter],response[iter + 1]);
        }
        return response;
    }

    private void erorrFields(TextField[] fields) {
        for (int i = 0; i < fields.length; i++) {
            TextField f = fields[i];
            f.getAllStyles().setBorder(Border.createUnderlineBorder(2, 0xcc0000));
            f.getSelectedStyle().setBorder(Border.createUnderlineBorder(4, 0xcc0000));
        }
    }

    private String toString(TextField[] digits){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            TextField t = digits[i];
            s.append(t.getAsInt(0));
        }
        return s.toString();
    }

    public boolean isValid(String s){
        return s.startsWith("0");
    }

    private void onTypeNext(TextField current, TextField next) {
        current.addDataChangedListener((i,ii)-> {
            if(current.getText().length() == 1){
                current.stopEditing();
                next.startEditingAsync();
            }
        });
    }



}
