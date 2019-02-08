package com.gp3.GestionUsuarios.Modelos.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clasa custom para crear un PlaceHolder en un JField
 */
public class JFieldPlaceHolder implements FocusListener, KeyListener {

    private boolean hasValue = false;
    private String text;

    public JFieldPlaceHolder(Component component, String text) {
        this.text = text;
        component.addFocusListener(this);
        focusLost(new FocusEvent(component, FocusEvent.FOCUS_LOST));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(!hasValue){
            ((JTextField) e.getComponent()).setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

        if(((JTextField)e.getComponent()).getText().length()==0){
            ((JTextField) e.getComponent()).setText(this.text);
            e.getComponent().setForeground(Color.LIGHT_GRAY);
            hasValue = false;
        }else{
            hasValue = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getComponent().getForeground()!=Color.BLACK){
            e.getComponent().setForeground(Color.BLACK);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
