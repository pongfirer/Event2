package sut.game01.core.screen;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.util.Clock;
import react.UnitSlot;
import playn.core.ImageLayer;
import sut.game01.core.sprite.Pec;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Button;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.layout.AxisLayout;
import sut.game01.core.debug.DebugDrawBox2D;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;


public class TestScreen extends UIScreen {
    private Image imageBack;
    private ImageLayer imageLayer1;
    private	float x = 0f;
    private	float y = 0f;
    private World world;
    private final ScreenStack ss;
    public static float M_PER_PIXEL = 1/26.666667f;
    private static int width = 24;
    private static int height = 18;
    private DebugDrawBox2D debugDraw;
    private static boolean showDebugDraw=true;

    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }
    private Root root;
    private Pec pec;
    private Pec pec2;



    @Override
    public void wasAdded() {
        super.wasAdded();
        Vec2 gravity = new Vec2(0.0f, 10.0f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        if(showDebugDraw){
            CanvasImage image = graphics().createImage(
                    (int)(width/TestScreen.M_PER_PIXEL),
                    (int)(height/TestScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw=new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit |
                    DebugDraw.e_jointBit |
                    DebugDraw.e_aabbBit
            );
            debugDraw.setCamera(0,0,1f/TestScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }

        //Image bgImage = assets().getImage("images/bg.png");
        //ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        //layer.add(bgLayer);


        Body ground = world.createBody(new BodyDef());
        PolygonShape groundshape = new PolygonShape();
        groundshape.setAsEdge(new Vec2(2f, height-2),
                              new Vec2(width-2f, height-2));
        ground.createFixture(groundshape, 0.0f);
        pec = new Pec(world, 300f, 200f);
        pec2 = new Pec(world, 200f, 200f);


        layer.add(pec.layer());
        layer.add(pec2.layer());


    }

    @Override
    public void update(int delta){

        pec.update(delta);
        pec2.update(delta);
        world.step(0.033f, 10 , 10);
        super.update(delta);
    }


    @Override
    public void paint(Clock clock){
        super.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
        pec.paint(clock);
        pec2.paint(clock);
    }


    @Override
    public void wasShown() {
        super.wasShown();
        final Screen home = new HomeScreen(ss);



        /*
        imageBack.addListener(new Pointer.Adapter(){
            public void onEmit(){
                ss.remove(TestScreen.this);
            }
        });*/


        root = iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(), layer
        );
        root.setSize(width(), height());
        root.add(
                new Button("Back").onClick(new UnitSlot(){
                    public void onEmit(){
                        ss.remove(TestScreen.this);
                    }
                }
                ));
        root.add(
                new Button("Push").onClick(new UnitSlot(){
                    public void onEmit(){
                        Pec.body.applyForce(new Vec2(800f, 0f), Pec.body.getPosition());
                    }
                }
                ));

    }

}
