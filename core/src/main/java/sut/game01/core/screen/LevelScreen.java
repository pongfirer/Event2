package sut.game01.core.screen;


import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Button;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.layout.AxisLayout;

/**
 * Created by all user on 27/2/2557.
 */
public class LevelScreen extends UIScreen {

    private ScreenStack ss;
    private Root root;

    public LevelScreen(ScreenStack ss){
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        super.wasShown();
        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.setSize(width(),height());
        root.add(new Button("BACK").onClick((new UnitSlot() {
            public void onEmit() {
                ss.remove(ss.top());
            }
        })));
    }
}
