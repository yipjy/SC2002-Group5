public class ArcaneBlastBuffEffect extends StatusEffect {
    private static final int ATTACK_BOOST = 10;


    public ArcaneBlastBuffEffect() {
        super(Integer.MAX_VALUE);
    }


    public void onApply(Combatant target) {
        target.setAttack(target.getAttack() + ATTACK_BOOST);
    }


    public void reduceDuration() {
        // lasts until end of level, so do nothing
    }


    public boolean isExpired() {
        return false;
    }
}
