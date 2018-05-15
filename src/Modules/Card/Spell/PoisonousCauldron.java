package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class PoisonousCauldron extends Spell{
    public PoisonousCauldron(){
        name = "Poisonous Cauldron";
        manaPoint = 4;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 100 damage to all enemy monster cards and enemy player";
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