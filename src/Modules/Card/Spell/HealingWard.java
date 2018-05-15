package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class HealingWard extends Spell {
    public HealingWard(){
        name = "Healing Ward";
        manaPoint = 5;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly monster cards' HP by 200";
        spellType = SpellType.CONTINUOUS;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        //TODO
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}