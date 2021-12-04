package demoGUI.userview;

import javax.swing.*;

public abstract class AbstractScreen extends JFrame {
    AbstractScreen(String str){
        super(str);
    };


    //TODO protected?
    protected abstract void setFrame();
}
