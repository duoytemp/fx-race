package rod;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Random;

public class Land extends Pane {
    ObservableList<Node> leftList;
    ObservableList<Node> rightList;

    Timeline timeline = new Timeline();

    public Land() {
        Group leftGroup = new Group();
        Group rightGroup = new Group();
        rightList = rightGroup.getChildren();
        leftList = leftGroup.getChildren();
        setStyle("-fx-background-color:rgb(33,73,24);");
        setMaxHeight(400);
        setMinHeight(400);
        setMaxWidth(800);
        setMinWidth(800);
        setTranslateY(300);
        getChildren().addAll(leftGroup, rightGroup);
        animateTree();
    }

    private void animateTree() {
        setleftList();
        Random random = new Random();
        KeyFrame leftkeyFrame = new KeyFrame(Duration.seconds(.1), (event) -> {
            for (int i = 0; i < leftList.size(); i++) {
                leftList.get(i).setTranslateX(leftList.get(i).getTranslateX() - 3);
                leftList.get(i).setTranslateY(leftList.get(i).getTranslateY() + 1);
                leftList.get(i).setScaleY(leftList.get(i).getScaleY() + .2);
                leftList.get(i).setScaleX(leftList.get(i).getScaleX() + .2);

                if (leftList.get(i).getTranslateX() < -100) {
                    leftList.get(i).setTranslateX(random.nextInt(375));
                    leftList.get(i).setTranslateY(-20);
                    leftList.get(i).setScaleY(1);
                    leftList.get(i).setScaleX(1);
                }
            }
        });

        KeyFrame rightKeyFrame = new KeyFrame(Duration.seconds(.1), event -> {
            for (int i = 0; i < rightList.size(); i++) {
                rightList.get(i).setTranslateX(rightList.get(i).getTranslateX() + 3);
                rightList.get(i).setTranslateY(rightList.get(i).getTranslateY() + 1);
                rightList.get(i).setScaleY(rightList.get(i).getScaleY() + .2);
                rightList.get(i).setScaleX(rightList.get(i).getScaleX() + .2);

                if (rightList.get(i).getTranslateX() > 900) {
                    rightList.get(i).setTranslateX(random.nextInt(375) + 425);
                    rightList.get(i).setTranslateY(-20);
                    rightList.get(i).setScaleY(1);
                    rightList.get(i).setScaleX(1);
                }
            }
        });
        timeline.getKeyFrames().addAll(leftkeyFrame, rightKeyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void setleftList() {
        Image image5 = new Image(Land.class.getResource("images/forest/tree3.PNG").toExternalForm());

        Random random = new Random();
        ImageView tree;
        for (int i = 1; i < 10; i++) {
            for (int j = 10; j > i; j--) {
                tree = new ImageView(image5);
                tree.setFitWidth(10);
                tree.setFitHeight(10);
                tree.setTranslateY(random.nextInt(300));
                tree.setTranslateX(-100);
                leftList.add(tree);
            }
        }

        for (int i = 1; i < 10; i++) {
            for (int j = 10; j > i; j--) {
                tree = new ImageView(image5);
                tree.setFitWidth(10);
                tree.setFitHeight(10);
                tree.setTranslateY(-10);
                tree.setTranslateX(900);
                rightList.add(tree);
            }
        }
    }

    public void stop() {
        timeline.stop();
    }

}