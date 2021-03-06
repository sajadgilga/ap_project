package View.InventoryView;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Item;
import Modules.User.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class ItemInventoryView {
    private User user;
    private ArrayList<ItemEventHandler> cardEvents = new ArrayList<>();
    private ArrayList<HBox> cardBoxes = new ArrayList<>();
    private int index = 0;

    public ItemInventoryView (User user) {
        this.user = user;
    }

    public void viewInventory() throws IOException {
        cardBoxes.clear();
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInventory.fxml"));
        Graphics.getInstance().getStage().setScene(new Scene(root));
        Graphics.getInstance().getStage().setFullScreen(true);

        //adding every card to the specified hbox holding it and setting event handlers amidst that
        HBox cardBox = null;
        for (int i = 0; i < user.getItemInventory().getItems().size(); i++){
            setCardHandler(user.getItemInventory().getItems().get(i));
            if (i % 4 == 0){
                cardBox = new HBox(50);
                cardBox.setAlignment(Pos.CENTER);
            }
            cardBox.getChildren().add(user.getItemInventory().getItems().get(i).getItemImage());
            if (i % 4 == 3){
                cardBoxes.add(cardBox);
            }
        }
        if (!cardBoxes.contains(cardBox))
            cardBoxes.add(cardBox);
        //adding the first two boxes to the scene
        for (int i = 0; i < 2; i++){
            if (cardBoxes.size() > i)
                ((VBox)root.lookup("#vBox")).getChildren().add(cardBoxes.get(i));
        }

        addHandlers(root);
    }

    /**
     * adds the necessary handlers to the buttons
     */
    private void addHandlers(Parent root){
        ImageView up = (ImageView) root.lookup("#up");
        ImageView down = (ImageView) root.lookup("#down");
        Button change = (Button) root.lookup("#change");
        VBox cardHolder = ((VBox)root.lookup("#vBox"));

        EventHandler<MouseEvent> upHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    up.setFitHeight(55);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    up.setFitHeight(50);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    for (int i = 0; i < 2; i++){
                        if (cardHolder.getChildren().size() >= 1)
                            cardHolder.getChildren().remove(0);
                    }
                    index--;
                    for (int i = 0; i < 2; i++){
                        if (index >= 0);
                        cardHolder.getChildren().add(cardBoxes.get(index + i));
                    }
                }
            }
        };
        up.addEventHandler(MouseEvent.ANY, upHandler);

        EventHandler<MouseEvent> downHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    down.setFitHeight(55);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    down.setFitHeight(50);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    for (int i = 0; i < 2; i++){
                        if (cardHolder.getChildren().size() >= 1)
                            cardHolder.getChildren().remove(0);
                    }
                    index++;
                    for (int i = 0; i < 2; i++){
                        if (cardBoxes.size() > index + i);
                        cardHolder.getChildren().add(cardBoxes.get(index + i));
                    }
                }
            }
        };
        down.addEventHandler(MouseEvent.ANY, downHandler);

        EventHandler<MouseEvent> changeHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    change.setEffect(new Glow(.4));
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    change.setEffect(null);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    removeHandler();
                    Menu.getInstance().goBacktoMenu();
                }
            }
        };
        change.addEventHandler(MouseEvent.ANY, changeHandler);
    }

    /**
     * creates new handler for each item
     */
    private void setCardHandler(Item item){
        cardEvents.add(new ItemEventHandler(item));
    }

    /**
     * for reverting back to normal page without the big item image
     */
    private void removeCardInfo(HBox bHold, Parent finalRoot){
        ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }

    /**
     * it's used for exiting the game
     */
    private void removeHandler(){
        for (int i = 0; i < user.getItemInventory().getItems().size(); i++){
            cardEvents.get(i).remove();
        }
    }

    /**
     * a class which handles events for every item
     */
    class ItemEventHandler {
        private EventHandler<MouseEvent> okHandler;
        private EventHandler<MouseEvent> moveHandler;
        private EventHandler<MouseEvent> diseqiupHandler;
        private EventHandler<MouseEvent> cardHandler;

        private Button okButton;
        private Button equip;
        private Button disequip;

        private Item item;
        private ImageView itemBig;

        EventHandler<MouseEvent> getEvent(){
            return cardHandler;
        }

        ItemEventHandler (Item item){
            cardHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                        item.getItemImage().setEffect(new Glow(0.4));
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                        item.getItemImage().setEffect(null);
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        cardInfo(item);
                    }
                }
            };
            this.item = item;
            item.getItemImage().addEventHandler(MouseEvent.ANY, cardHandler);
        }

        private void cardInfo(Item item){
            itemBig = new ImageView(item.getItemImage().getImage());
            itemBig.setFitHeight(200);
            itemBig.setFitWidth(200);
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInfoPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert root!=null;
            GridPane cardBox = (GridPane) root.lookup("#dialogBox");
            cardBox.getChildren().add(itemBig);
            ((AnchorPane)Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(root);
            okButton = new Button();
            okButton.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            okButton.setText("OK");
            okButton.setTextFill(Color.CORNSILK);
            equip = new Button("Equip");
            equip.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            equip.setTextFill(Color.CORNSILK);

            disequip = new Button("disequip");
            disequip.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            disequip.setTextFill(Color.CORNSILK);

            HBox bHold = null;
            if (user.getBackPack().getNumberOfItems(item.getName()) == user.getItemInventory().getNumberOfItem(item)) {
                bHold = new HBox(okButton, disequip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 100);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }
            else if (user.getBackPack().ContainsItem(item.getName())){
                bHold = new HBox(okButton, equip, disequip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 160);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }else {
                bHold = new HBox(okButton, equip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 100);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }
            bHold.setSpacing(50);
            Effect glow = new Glow(.4);
            ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(bHold);

            Parent finalRoot = root;
            HBox finalBHold = bHold;
            okHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                        removeCardInfo(finalBHold, finalRoot);
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        okButton.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        okButton.setEffect(null);
                }
            };
            HBox finalBHold1 = bHold;
            moveHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        user.getBackPack().add(item, 1);
                        removeCardInfo(finalBHold1, finalRoot);
                    }
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        equip.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        equip.setEffect(null);
                }
            };

            diseqiupHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        user.getBackPack().remove(item);
                        removeCardInfo(finalBHold1, finalRoot);
                    }
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        disequip.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        disequip.setEffect(null);
                }
            };

            okButton.addEventHandler(MouseEvent.ANY, okHandler);
            equip.addEventHandler(MouseEvent.ANY, moveHandler);
            disequip.addEventHandler(MouseEvent.ANY, diseqiupHandler);
        }

        public void remove(){
            item.getItemImage().removeEventHandler(MouseEvent.ANY, cardHandler);
        }
    }
}
