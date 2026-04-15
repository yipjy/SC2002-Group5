public class SmokeBombEffect extends StatusEffect {    // Author: JY
    public SmokeBombEffect() {
        super(3);
    }


    public boolean blocksIncomingDamage() {
        return true;
    }
}
