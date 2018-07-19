package Modules.ItemAndAmulet;

import Modules.Warrior.Warrior;
import View.ShopView.CardView;

import javax.swing.text.html.ImageView;

public abstract class Amulet {
    protected int gillCost;
    protected String name;
    protected ImageView amuletImage;

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void castSpell(Warrior lord);

    public String toString(){
        return name;
    }

    public boolean equals(Amulet other){
        return other.getName().equals(this.getName());
    }

    public String detail(){
        return "nothing special";
    }

    public int hashCode(){
        return super.hashCode();
    }
}
