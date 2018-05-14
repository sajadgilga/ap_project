package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

public class GreaterDragon extends General{
    private String willName = "Dragon's Call";
    private String willDetail = "Draw two cards from deck to hand";
    private String battleCryName = "Devour";
    private String battleCryDetail = "Send a random enemy monster card from field to graveyard";

    public GreaterDragon(){
        name = "Greater Dragon";
        initialHP = 2000;
        initialAP = 1800;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 8;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.DRAGON_BREED;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        for (int i = 0; i < 2; i++) {
            Card card = friend.getDeck().takeCard();
            friend.getHand().add(card);
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
        enemy.getGraveYard().add(enemy.getMonsterField().getMonsterCards().get(random));
        enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public String willDetail() {
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        return battleCryDetail;
    }

    public String getWillName() {
        return willName;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

}
