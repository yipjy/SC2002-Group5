public abstract class StatusEffect {    // Author: JY
    protected int duration;


    public StatusEffect(int duration) {
        this.duration = duration;
    }


    public void onApply(Combatant target) {}
    public void onTurnStart(Combatant target) {}
    public void onTurnEnd(Combatant target) {}
    public void onExpire(Combatant target) {}


    public boolean canAct() {
        return true;
    }


    public boolean blocksIncomingDamage() {
        return false;
    }


    public void reduceDuration() {
        duration--;
    }


    public boolean isExpired() {
        return duration <= 0;
    }
}

