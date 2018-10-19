package rod;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RodSeparator extends Rectangle{
    private Rectangle rectangle;
    private boolean outOfbound;
    int intialPosition;
    int destination;
    int restartPoint;

    public RodSeparator(int x,int y,int width,int height){
        setWidth(width);
        setHeight(height);
        setTranslateX(x);
        setTranslateY(y);
        setFill(Color.WHITE);

        intialPosition=y;
        destination = 400-intialPosition;
        restartPoint =-1*(50+intialPosition);
    }

}
