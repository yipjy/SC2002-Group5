import java.util.ArrayList;
import java.util.List;


public abstract class Player extends Combatant {
    protected List<Item> items;
    protected SpecialSkill specialSkill;
    protected int cooldown;


    public Player(String name, int maxHp, int attack, int defense, int speed, SpecialSkill specialSkill) {
        super(name, maxHp, attack, defense, speed);
        this.items = new ArrayList<>();
        this.specialSkill = specialSkill;
        this.cooldown = 0;
    }


    public void addItem(Item item) { items.add(item); }


    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) return null;
        return items.get(index);
    }


    public void consumeItem(int index) {
        if (index >= 0 && index < items.size()) items.remove(index);
    }


    public List<Item> getItems() { return items; }
    public SpecialSkill getSpecialSkill() { return specialSkill; }
    public int getCooldown() { return cooldown; }


    public void setCooldown(int cooldown) {
        this.cooldown = Math.max(0, cooldown);
    }


    public void reduceCooldown() {
        if (cooldown > 0) cooldown--;
    }
}

