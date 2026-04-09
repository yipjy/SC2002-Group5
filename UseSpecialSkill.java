import java.util.List;


public class UseSpecialSkill implements Action {


    // implementing execute from Action
    public void execute(Combatant user, List<Combatant> targets) {
        Player player = (Player) user;
        player.getSpecialSkill().activate(player, targets);
        player.setCooldown(3);
        System.out.println(player.getName() + " uses their special skill!");
    }


    // implementing canUse from Action
    public boolean canUse(Combatant user) {
        Player player = (Player) user;
        return player.getCooldown() == 0;
    }


    // implementing getName from Action
    public String getName() { return "Special Skill"; }
}
