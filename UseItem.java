import java.util.List;


public class UseItem implements Action {
    private final int itemIndex;


    public UseItem(int itemIndex) { this.itemIndex = itemIndex; }


    // implementing execute from Action
    public void execute(Combatant user, List<Combatant> targets) {
        Player player = (Player) user;
        Item item = player.getItem(itemIndex);
        item.use(player, targets);
        player.consumeItem(itemIndex);
        System.out.println(player.getName() + " used " + item.getName() + "!");
    }


    // implementing canUse from Action
    public boolean canUse(Combatant user) {
        Player player = (Player) user;
        return player.getItem(itemIndex) != null;
    }


    // implementing getName from Action
    public String getName() { return "Use Item"; }


    public int getItemIndex() { return itemIndex; }
}

