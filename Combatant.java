import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Combatant {    // Author: JY
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<StatusEffect> statusEffects;


    public Combatant(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }


    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }


    public void setHp(int hp) {
        if (hp < 0) this.hp = 0;
        else if (hp > maxHp) this.hp = maxHp;
        else this.hp = hp;
    }


    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }


    public boolean isAlive() { return hp > 0; }


    public void takeDamage(int damage) {
        if (damage < 0) damage = 0;
        hp = hp - damage;
        if (hp < 0) hp = 0;
    }


    public void heal(int amount) {
        if (amount < 0) return;
        hp = hp + amount;
        if (hp > maxHp) hp = maxHp;
    }


    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
        effect.onApply(this);
    }


    public List<StatusEffect> getStatusEffects() { return statusEffects; }


    public boolean hasStatusEffect(Class<? extends StatusEffect> effectType) {
        for (StatusEffect effect : statusEffects) {
            if (effectType.isInstance(effect)) return true;
        }
        return false;
    }


    public boolean canAct() {
        for (StatusEffect effect : statusEffects) {
            if (!effect.canAct()) return false;
        }
        return true;
    }


    public boolean isInvulnerable() {
        for (StatusEffect effect : statusEffects) {
            if (effect.blocksIncomingDamage()) return true;
        }
        return false;
    }


    public void startTurn() {
        for (StatusEffect effect : statusEffects) {
            effect.onTurnStart(this);
        }
    }


    public void endTurn() {
        Iterator<StatusEffect> iterator = statusEffects.iterator();
        while (iterator.hasNext()) {
            StatusEffect effect = iterator.next();
            effect.onTurnEnd(this);
            effect.reduceDuration();
            if (effect.isExpired()) {
                effect.onExpire(this);
                iterator.remove();
            }
        }
    }
}

