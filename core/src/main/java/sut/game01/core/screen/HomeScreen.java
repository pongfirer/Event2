package sut.game01.core.screen;

import playn.core.Font;
import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

public class HomeScreen extends UIScreen {

    private ScreenStack ss;
    private Root root;

    public static final Font TITLE_FONT =
            graphics().createFont(
                    "Helvetica",
                    Font.Style.PLAIN,
                    24);

    public HomeScreen(ScreenStack ss){
        this.ss = ss;
    }
    @Override
    public void wasShown() {
        super.wasShown();
        /*root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.addStyles(Style.BACKGROUND
                .is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5)
                        .inset(5, 10)));
        root.setSize(width(),height());
        root.add(new Label("Event Driven Programming")
                .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Button("START").onClick((new UnitSlot() {
            public void onEmit() {
                ss.push(new TestScreen(ss));
            }
        })));*/

    }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bg = assets().getImage("images/bg2.png");
        ImageLayer bgl = graphics().createImageLayer(bg);
        layer.add(bgl);

        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer);
        root.setSize(width(),height());
        root.add(new Button("START GAME").onClick((new UnitSlot() {
            public void onEmit() {
                ss.push(new TestScreen(ss));
            }
        })));
        root.add(new Button("SELECT LEVEL").onClick(new UnitSlot() {
            @Override
            public void onEmit() {
                ss.push(new LevelScreen(ss));
            }
        }));
    }
}
