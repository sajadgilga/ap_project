package Main;

import Control.GameControll.GameControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import View.ShopView.CardShopView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception{
        GameControl gameControl = new GameControl("./src/Files/save/");
        Graphics.getInstance().setStage(primaryStage);
//        Menu.getInstance().startGame(gameControl);
        CardShopView cardShopView = new CardShopView();
        cardShopView.viewCardShop();

        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean menu(){
        return true;
    }
}