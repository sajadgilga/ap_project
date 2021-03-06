package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class LargeMPPotion extends Item{

    public LargeMPPotion(){
        gillCost = 4000;
        name = "Large MP Potion";
        itemImage = new ImageView(new Image(new File("./src/Files/Images/Items/" + this.name + ".png").toURI().toString()));

    }

    @Override
    public boolean equals(Item other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String spelldetail() {
        String detail = "Increase Player's MP by 8";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.setManaPoint(player.getManaPoint() + 8);
        System.out.println("player mana point was increased by 8");
    }
}
