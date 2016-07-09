import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by rkasha on 19-Mar-16.
 */
public class MyApplication extends Application {

    public static void main(String[] args){
        System.out.println("Hai");
        launch(args);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ravi's Window");

        final Button button = new Button();
        final Button button2 = new Button();
        button.setText("Clickjkljlkk");
        button2.setText("Click");

        button.setOnAction((e) -> {
            if(e.getSource()==button){
            System.out.println("boss");
            return;
        }
        });

        button2.setOnAction((e) -> {
            if(e.getSource()==button2){
                System.out.println("kasha");
                return;
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().addAll(button,button2);
//        layout.getAlignment().getHpos()

        Scene scene = new Scene(layout,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
