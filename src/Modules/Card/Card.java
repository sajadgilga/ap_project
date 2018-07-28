package Modules.Card;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Card implements Cloneable{
    protected String name;
    protected int gillCost;
    protected int manaPoint;
    protected Warrior enemy;
    protected Warrior friend;
    protected Image cardImage;
    protected CardView cardView;
    protected CardView cardViewBig;

    public CardView getCardViewBig() {
        return cardViewBig;
    }

    protected int id;
    public static int cardNumbers = 0;

    public Card(){
        id = Card.cardNumbers;
        Card.cardNumbers++;
    }

    public void reset(){

    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView (CardView cardView) {
        this.cardView = cardView;
    }

    public Warrior getEnemy() {
        return enemy;
    }

    public void setEnemy(Warrior enemy) {
        this.enemy = enemy;
    }

    public Warrior getFriend() {
        return friend;
    }

    public void setFriend(Warrior friend) {
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public String toString(){
        return name;
    }

    public String detail(){
        return "";
    }

    private String spellDetail(){
        return "nothing special";
    }

    private String willDetail(){
        return "nothing special";
    }

    private String battleCryDetail(){
        return "nothing special";
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other){
        return ((Card)other).getId() == this.getId();
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

    public Image getCardImage () {
        return cardImage;
    }



    public void setCardImage (Image cardImage) {
        this.cardImage = cardImage;
    }

    public Card renew(){
        return this;
    }
}
