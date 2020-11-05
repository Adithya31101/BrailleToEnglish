import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Screen extends Application {
    @Override
    public void start(Stage stage) {
        // Label for name
        Text info = new Text("Please draw the Braille characters on this tile and then we will send you the text!");
        Text totalChars = new Text("Total Characters registered: ");
        Text Chars = new Text("0");
        Button convert = new Button("Convert To Text");
        convert.setVisible(false);
        Button reset = new Button("Reset string");
        reset.setVisible(false);

        Text text = new Text();

        char b[][] = {{'0','0'},{'0','0'},{'0','0'}};
        int bArray[] = new int[20];
        char bChar[] = new char[6];
        int[] chars = {0};



        Circle circle1 = new Circle(20.0f, Color.WHITE);
        circle1.setStroke(Color.BLACK);
        
        
        Circle circle2 = new Circle(20.0f, Color.WHITE);
        circle2.setStroke(Color.BLACK);
        

        Circle circle3 = new Circle(20.0f, Color.WHITE);
        circle3.setStroke(Color.BLACK);
        

        Circle circle4 = new Circle(20.0f, Color.WHITE);
        circle4.setStroke(Color.BLACK);

        Circle circle5 = new Circle(20.0f, Color.WHITE);
        circle5.setStroke(Color.BLACK);

        Circle circle6 = new Circle(20.0f, Color.WHITE);
        circle6.setStroke(Color.BLACK);

        Button capture = new Button("Capture");
// Button Event Handlers
        EventHandler<ActionEvent> captureChar = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                for(int i=0;i<6; i++){
                    bChar[i] = '\0';
                }
                circle1.setFill(Color.WHITE);
                circle2.setFill(Color.WHITE);
                circle3.setFill(Color.WHITE);
                circle4.setFill(Color.WHITE);
                circle5.setFill(Color.WHITE);
                circle6.setFill(Color.WHITE);
                int c = 0;
                for(int i=0; i<3; i++){
                        if(b[i][0] != '0'){
                            bChar[c] = b[i][0];
                            b[i][0] = '0';
                            c++;
                        }
                }
                for(int i=0; i<3; i++){
                        if(b[i][1] != '0'){
                            bChar[c] = b[i][1];
                            b[i][1] = '0';
                            c++;
                        }
                }
                if(bChar[0] == '\0'){
                    bArray[chars[0]] = 9;
                } else {
                    String code = new String(bChar);
                    bArray[chars[0]] =  Integer.parseInt(code.trim());
                }
                Chars.setText(Integer.toString(chars[0] + 1));
                chars[0] = chars[0] + 1;
                convert.setVisible(true);
                reset.setVisible(true);
            }
        };

        EventHandler<ActionEvent> convertBraille = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Braille b = new Braille(bArray, chars[0]);
                try {
                    String decodedText = b.sendPOST(b.arr);
                    text.setText(decodedText.toUpperCase());
                } catch (Exception err){
                    System.out.println(err);
                }
            }
        };

        EventHandler<ActionEvent> resetString = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                circle1.setFill(Color.WHITE);
                circle2.setFill(Color.WHITE);
                circle3.setFill(Color.WHITE);
                circle4.setFill(Color.WHITE);
                circle5.setFill(Color.WHITE);
                circle6.setFill(Color.WHITE);
                for(int i=0;i<chars[0]; i++){
                    bArray[i] = 9;
                }
                Chars.setText("0");
                chars[0] = 0;
                convert.setVisible(false);
                reset.setVisible(false);
                for (int i = 0; i < 6; i++) {
                    bChar[i] = '\0';
                }
                text.setText("");
            }
        };
//Event Handlers
        //Mouse Events 
        EventHandler<MouseEvent> dot1Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(b[0][0] == '0') {
                    circle1.setFill(Color.BLACK);
                    b[0][0] = '1';
                }
                else {
                    circle1.setFill(Color.WHITE);
                    b[0][0] = '0';
                }
            }
        };

        EventHandler<MouseEvent> dot2Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (b[1][0] == '0'){
                    circle2.setFill(Color.BLACK);
                    b[1][0] = '2';
                } else {
                    circle2.setFill(Color.WHITE);
                    b[1][0] = '0';
                }
            }
        };

        EventHandler<MouseEvent> dot3Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (b[2][0] == '0') {
                    circle3.setFill(Color.BLACK);
                    b[2][0] = '3';
                }
                else {
                    circle3.setFill(Color.WHITE);
                    b[2][0] = '0';
                }

            }
        };

        EventHandler<MouseEvent> dot6Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (b[2][1] == '0'){
                    circle6.setFill(Color.BLACK);
                    b[2][1] = '6';
                }else{
                    circle6.setFill(Color.WHITE);
                    b[2][1] = '0';
                }
            }
        };

        EventHandler<MouseEvent> dot5Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (b[1][1] == '0'){
                    circle5.setFill(Color.BLACK);
                    b[1][1] = '5';
                } else {
                    circle5.setFill(Color.WHITE);
                    b[1][1] = '0';
                }
            }
        };

        EventHandler<MouseEvent> dot4Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (b[0][1] == '0'){
                    circle4.setFill(Color.BLACK);
                    b[0][1] = '4';
                } else {
                    circle4.setFill(Color.WHITE);
                    b[0][1] = '0';
                }
            }
        };
        
        //Key Board Events
        EventHandler<KeyEvent> handleKeyPress = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent key){
                switch(key.getCode()){
                    case DIGIT1: {
                        if (b[0][0] == '0') {
                            circle1.setFill(Color.BLACK);
                            b[0][0] = '1';
                        } else {
                            circle1.setFill(Color.WHITE);
                            b[0][0] = '0';
                        }
                        break;
                    }

                    case DIGIT2: {
                        if (b[1][0] == '0') {
                            circle2.setFill(Color.BLACK);
                            b[1][0] = '2';
                        } else {
                            circle2.setFill(Color.WHITE);
                            b[1][0] = '0';
                        }
                        break;
                    }

                    case DIGIT3: {
                        if (b[2][0] == '0') {
                            circle3.setFill(Color.BLACK);
                            b[2][0] = '3';
                        } else {
                            circle3.setFill(Color.WHITE);
                            b[2][0] = '0';
                        }
                        break;
                    }

                    case DIGIT4: {
                        if (b[0][1] == '0') {
                            circle4.setFill(Color.BLACK);
                            b[0][1] = '4';
                        } else {
                            circle4.setFill(Color.WHITE);
                            b[0][1] = '0';
                        }
                        break;
                    }

                    case DIGIT5: {
                        if (b[1][1] == '0') {
                            circle5.setFill(Color.BLACK);
                            b[1][1] = '5';
                        } else {
                            circle5.setFill(Color.WHITE);
                            b[1][1] = '0';
                        }
                        break;
                    }

                    case DIGIT6: {
                        if (b[2][1] == '0') {
                            circle6.setFill(Color.BLACK);
                            b[2][1] = '6';
                        } else {
                            circle6.setFill(Color.WHITE);
                            b[2][1] = '0';
                        }
                        break;
                    }
                }

            }
        };
        
        circle1.addEventFilter(MouseEvent.MOUSE_CLICKED, dot1Clicked);
        circle2.addEventFilter(MouseEvent.MOUSE_CLICKED, dot2Clicked);
        circle3.addEventFilter(MouseEvent.MOUSE_CLICKED, dot3Clicked);
        circle4.addEventFilter(MouseEvent.MOUSE_CLICKED, dot4Clicked);
        circle5.addEventFilter(MouseEvent.MOUSE_CLICKED, dot5Clicked);
        circle6.addEventFilter(MouseEvent.MOUSE_CLICKED, dot6Clicked);
        capture.setOnAction(captureChar);
        capture.setDefaultButton(true);
        convert.setOnAction(convertBraille);
        reset.setOnAction(resetString);
        reset.setCancelButton(true);

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();
        GridPane circleGrid = new GridPane();
        // Setting size for the pane
        gridPane.setMinSize(800, 600);
        circleGrid.setMinSize(250,200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        circleGrid.setPadding(new Insets(2, 2, 2, 2));
        gridPane.setVgap(30);
        gridPane.setHgap(20);
        circleGrid.setVgap(10);
        circleGrid.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        // gridPane.setGridLinesVisible(true);
        circleGrid.setAlignment(Pos.CENTER);
        info.setWrappingWidth(600.0f);
        // circleGrid.setGridLinesVisible(true);

            circleGrid.add(circle1, 0, 0);
            circleGrid.add(circle2, 0, 1);
            circleGrid.add(circle3, 0, 2);
            circleGrid.add(circle6, 1, 2);
            circleGrid.add(circle5, 1, 1);
            circleGrid.add(circle4, 1, 0);
        // Arranging all the nodes in the grid
        gridPane.add(info, 0, 0, 4, 1);
        gridPane.add(totalChars, 0,2);
        gridPane.add(Chars, 1, 2);
        gridPane.add(circleGrid, 0, 3);
        gridPane.add(capture, 1, 3);
        gridPane.add(convert, 0, 4);
        gridPane.add(reset, 1, 4 );
        gridPane.add(text, 0, 5, 3, 1);

        // Styling nodes

        info.setStyle("-fx-font: normal 20px 'Ubuntu'; ");
        text.setStyle("-fx-font: bold 30px 'Ubuntu' ");
        totalChars.setStyle("-fx-font: normal 20px 'Ubuntu' ");
        Chars.setStyle("-fx-font: normal 20px 'Ubuntu' ");
        capture.setStyle("-fx-background-radius: 10em; " + "-fx-min-width: 100px; " + "-fx-min-height: 100px; "
                + "-fx-max-width: 100px; " + "-fx-max-height: 100px;" + "-fx-font-size: 1.5em");
        convert.setPrefWidth(300);
        reset.setStyle("-fx-background-radius: 2em; -fx-background-color: #fa4947; -fx-font-weight: 700; -fx-text-base-color: white; -fx-font-size: 1.5em");
        convert.setStyle("-fx-background-radius: 2em; -fx-background-color: #32b2ff; -fx-font-weight: 700;  -fx-text-base-color: white; -fx-font-size: 1.5em");

        // Setting the back ground color
        gridPane.setStyle("-fx-background-color: white;");
        Scene scene = new Scene(gridPane);
        scene.setOnKeyPressed(handleKeyPress);
        stage.setTitle("Braille to Text");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}