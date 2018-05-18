package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

public class OgreWarchief extends General{

    public OgreWarchief(){
        name = "Ogre Warchief";
        initialAP = 1500;
        initialHP = 2500;
        HP = 2500;
        AP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.DEMONIC;
    }

    public String getWillName() {
        String willName = "Last Order";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "War Stomp";
        return battleCryName;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().
                forEach(card -> card.increaseAP(300));
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().
                forEach(card -> card.decreaseHP(400));
        enemy.getCommander().decreaseHP(400);
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
        battleCry(enemy, friend);
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        super.die(enemy, friend);
        will(enemy, friend);
    }

    @Override
    public String willDetail() {
        String willDetail = "Increase all friendly monsters by 300";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = "Deal 400 damage to all enemy monsters and player";
        return battleCryDetail;
    }
}
