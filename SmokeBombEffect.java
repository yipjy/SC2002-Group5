public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect() {
        super(3);
    }


    public boolean blocksIncomingDamage() {
        return true;
    }
}
