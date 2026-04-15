public class DefendEffect extends StatusEffect {    // Author: JY
    private static final int DEFENSE_BOOST = 10;


    public DefendEffect() {
        super(2);
    }


    public void onApply(Combatant target) {
        target.setDefense(target.getDefense() + DEFENSE_BOOST);
    }


    public void onExpire(Combatant target) {
        target.setDefense(target.getDefense() - DEFENSE_BOOST);
    }
}

