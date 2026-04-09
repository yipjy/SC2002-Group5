public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect() {
        super(2);
    }


    public boolean blocksIncomingDamage() {
        return true;
    }
}
