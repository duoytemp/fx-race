package rod;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Trap extends Pane {
    Image image;
    ImageView iv;
    Timeline timeline=new Timeline();
    public Trap(){
        image=new Image(Trap.class.getResource("hilux.png").toExternalForm());
        iv = new ImageView(image);
        getChildren().add(iv);
    }

    public void setSize(double height,double width){
        iv.setFitHeight(height);
        iv.setFitWidth(width);
    }
    private void moveTrap() {
        KeyFrame keyFrame=new KeyFrame(Duration.millis(9),event -> {
            iv.setTranslateY(iv.getTranslateY()+.5);

            if(iv.getTranslateX()>390)
              iv.setTranslateX(iv.getTranslateX()+.1);
            else if(iv.getTranslateX()<384)
              iv.setTranslateX(iv.getTranslateX()-.1);

            iv.setScaleX(iv.getScaleX()+.005);
            iv.setScaleY(iv.getScaleY()+.005);
            if(iv.getTranslateY()>650){
                System.out.println("In condition");
                iv.setTranslateY(280);
                iv.setScaleX(1);
                iv.setScaleY(1);
                iv.setTranslateX(new Random().nextInt(25)+375);
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
