package rod;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Separator extends Pane {
    ObservableList<Node> list;
    ArrayList<RodSeparator> metadata=new ArrayList<>();
    int tracer;
    Timeline timeline=new Timeline();



    public Separator(){
        list = getChildren();
        setSeparators();
        Rectangle clip=new Rectangle(0,100,6,300);
        setClip(clip);
        animateRod();
    }

    public void setSeparators(){
        RodSeparator separator;

        for(int i=0;i<9;i++){
            if(i%2==0) {
                separator = new RodSeparator(0, i * 44, 6, 44);
                metadata.add(separator);
                list.add(separator);
            }
        }
        tracer=list.size()-1;
    }


    private void animateRod(){
        KeyFrame keyFrame=new KeyFrame(Duration.millis(2),(event)->{

            for (int i=0 ;i<list.size();i++){
                list.get(i).setTranslateY(list.get(i).getTranslateY()+1);
            }

            if(list.get(tracer).getTranslateY()>400){
                list.get(tracer).setTranslateY(-50);
                tracer-=1;
                if(tracer<0){
                    tracer=list.size()-1;
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
