import java.util.List;


public abstract class Item { // Author: R.V.
    protected String name;


    public Item(String name) { this.name = name; }


    public abstract void use(Player user, List<Combatant> targets);


    public String getName() { return name; }
}

