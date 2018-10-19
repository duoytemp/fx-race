package rod;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Sky  extends Pane {


    ObservableList<Node> list;
    Timeline timeline=new Timeline();

    public Sky(){
        setMinWidth(800);
        setMaxWidth(800);
        setMinHeight(300);
        setMaxHeight(300);
        setStyle("" +
                "-fx-background-color:linear-gradient(to bottom ,rgb(250,250,250),rgb(240,240,240),rgb(230,230,230),rgb(220,220,220),rgb(200,200,200),rgb(150,150,150),rgb(100,100,100),rgb(50,50,50),rgb(0,0,0));");

        Rectangle rectangle=new Rectangle(800,300);
        setClip(rectangle);


        Group cloud=new Group();
        list=cloud.getChildren();
        getChildren().add(cloud);
        moveCloud();
    }

    public void moveCloud(){
        Image cloude=new Image(Sky.class.getResource("cloud2.png").toExternalForm());

       for(int i=0;i<5;i++) {
           ImageView cloud = new ImageView(cloude);
           cloud.setFitWidth(50);
           cloud.setFitHeight(50);
           cloud.setTranslateX(new Random().nextInt(800));
           cloud.setTranslateY(new Random().nextInt(200)+300);
           list.add(cloud);
       }
        KeyFrame keyFrame=new KeyFrame(Duration.millis(20),(event)-> {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTranslateY() < 300){
                    list.get(i).setScaleY(list.get(i).getScaleY() + .01);
                    list.get(i).setScaleX(list.get(i).getScaleX() + .03);
                }
                list.get(i).setTranslateY(list.get(i).getTranslateY() - 1);


                if (list.get(i).getTranslateX() > 425)
                    list.get(i).setTranslateX(list.get(i).getTranslateX() + 1);

                else if (list.get(i).getTranslateX() < 375)
                    list.get(i).setTranslateX(list.get(i).getTranslateX() - 1);


                 if(list.get(i).getTranslateY()<-150){
                    list.get(i).setTranslateY(new Random().nextInt(200)+300);
                    list.get(i).setTranslateX(new Random().nextInt(800));
                    list.get(i).setScaleY(1);
                    list.get(i).setScaleX(1);
                }
             }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void stop(){
        timeline.stop();
    }
}
