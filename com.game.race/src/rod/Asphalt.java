package rod;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asphalt extends StackPane {
  public Asphalt(){
       Polygon polygon=new Polygon(375,300,
               425,300,
               800,600,
               0,600,
               375,300);
       polygon.setFill(Color.rgb(153,153,153));
       setTranslateY(300);
       getChildren().addAll(polygon);
   }
}
