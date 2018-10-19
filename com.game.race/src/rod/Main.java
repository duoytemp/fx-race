package rod;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application{

    public static void main(String [] args){ launch(args);}

    ImageView displayCar;
    MediaView carVoice;
    Group trap;
    Timeline timeline=new Timeline();
    ObservableList<Node> trapList;
    Separator separator=new Separator();
    MediaPlayer player;
    Land land;
    Sky sky;
    Scene scene;

    Image car=new Image(Asphalt.class.getResource("hilux.png").toExternalForm());
    public void start(Stage window) throws FileNotFoundException {
        scene=new Scene(createContent(),800,600);
        TranslateTransition transition=new TranslateTransition(Duration.seconds(.5),displayCar);
        scene.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case LEFT:

                    displayCar.setTranslateX(displayCar.getTranslateX()-5);
                    break;
                case RIGHT:
                    displayCar.setTranslateX(displayCar.getTranslateX()+5);
            }
        });

        scene.setOnKeyReleased(event -> {
            displayCar.setImage(car);
        });
        window.setTitle("RACE");
        window.setScene(scene);
        window.initStyle(StageStyle.UTILITY);
        window.show();
        startGame();
    }



    public Parent createContent() throws FileNotFoundException {
        trap=new Group();
        trapList=trap.getChildren();
        Pane root=new Pane();
        Image tree=new Image(new FileInputStream("C:\\Users\\Bazen\\IdeaProjects\\com.game.race\\src\\rod\\images\\redCar.PNG"));
        Media audio=new Media(Main.class.getResource("FauxCarv010Playablepseudo3DroadtestsHDhighqualityRingtone.mp3").toExternalForm());
        player=new MediaPlayer(audio);
        player.setAutoPlay(true);

        player.setCycleCount(MediaPlayer.INDEFINITE);
        carVoice=new MediaView(player);
        displayCar=new ImageView(car);
        displayCar.setFitHeight(75);
        displayCar.setFitWidth(85);
        displayCar.setTranslateX(350);
        displayCar.setTranslateY(500);

        ImageView ivTree=new ImageView(tree);
        ivTree.setTranslateY(250);
        ivTree.setTranslateX(10);
        Asphalt asphalt=new Asphalt();
        separator.setTranslateY(200);
        separator.setTranslateX(400);
        sky=new Sky();
        land=new Land();
        root.getChildren().addAll(carVoice,sky,land,asphalt,separator,trap,displayCar);
        return root;
    }



    private void startGame()
    {
         setTrapList();
        final int[] itrato = {0};
          KeyFrame keyFrame=new KeyFrame(Duration.seconds(.01),event -> {
              for(int i=0;i<trapList.size();i++){
                 trapList.get(i).setTranslateY(trapList.get(i).getTranslateY()+1);

                if(trapList.get(i).getBoundsInParent().getHeight()<74.5) {
                    trapList.get(i).setScaleY(trapList.get(i).getScaleY() + .0248);
                    trapList.get(i).setScaleX(trapList.get(i).getScaleX() + .022);
                }

                 if(trapList.get(i).getTranslateX()<397)
                   trapList.get(i).setTranslateX(trapList.get(i).getTranslateX()-1);
                 else if(trapList.get(i).getTranslateX()>403)
                  trapList.get(i).setTranslateX(trapList.get(i).getTranslateX()+1);

              }
              for(int i=0;i<trapList.size();i++){
                  if(trapList.get(i).getTranslateY()>600){
                      System.out.println("SCALE Y : "+trapList.get(i).getBoundsInParent().getHeight());
                      System.out.println("SCALE X : "+trapList.get(i).getBoundsInParent().getWidth());
                      Trap temp=new Trap();
                      temp.setTranslateY(288);
                      temp.setSize(12,15);
                      temp.setTranslateX(new Random().nextInt(35)+375);
                      trapList.remove(i);
                      trapList.add(i,temp);
                  }
              }

              checkCollision();
          });
          timeline.getKeyFrames().add(keyFrame);
          timeline.setCycleCount(Timeline.INDEFINITE);
          timeline.play();
    }

    private void checkCollision() {
      for(int i=0;i<trapList.size();i++){
          if(displayCar.getBoundsInParent().intersects(trapList.get(i).getBoundsInParent())){
              separator.stop();
              timeline.stop();
              player.stop();
              land.stop();
              sky.stop();
          }
      }
    }

    private void setTrapList() {
        Trap trap;
        double y,x,lowerBound,upperBound;
        double width,height,diffrence,heightRate,widthRate;
        Random random=new Random();
        boolean over=false;
        for(int i=0;i<3;i++){
           trap=new Trap();
           while (!over) {
               y = random.nextInt(112) + 288;

               if(i==0)y=288;
               if(i==1)y=388;
               if(i==2)y=488;

               lowerBound = (375.0 / 300) * (600 - y);
               upperBound = (425.0 / 300) * (600 - y);
               int limit = (int) (upperBound - lowerBound);
               diffrence = y - 288;
               heightRate = (63.0 / 212);
               widthRate = (70.0 / 212);
               height = (12 + ((diffrence * heightRate)));
               width = (15 + (diffrence * widthRate));
               x = lowerBound + random.nextInt(limit)-width;
               trap.setTranslateY(y);
               trap.setTranslateX(x);
               trap.setSize(height, width);
               over=generateNumber(trap);
           }
           over=false;
           trapList.add(trap);
        }
    }

    public boolean generateNumber(Trap trap)
    {
        for(int i=0;i<trapList.size();i++){

            if(trap.getBoundsInParent().intersects(trapList.get(i).getBoundsInParent())){
                return false;
            }
        }
        return true;
    }




}
