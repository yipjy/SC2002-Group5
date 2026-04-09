import java.util.ArrayList;
import java.util.List;


public class BattleEngine {
    private Player player;
    private List<Enemy> activeEnemies;
    private List<Enemy> initialWave;
    private List<Enemy> backupWave;
    private TurnOrderStrategy turnOrderStrategy;
    private GameCLI cli;
    private int roundCount;
    private boolean backupSpawned;
    private boolean battleOver;


    public BattleEngine(Player player, List<Enemy> initialWave, List<Enemy> backupWave,
            TurnOrderStrategy turnOrderStrategy, GameCLI cli) {
        this.player = player;
        this.initialWave = new ArrayList<>(initialWave);
        this.backupWave = new ArrayList<>(backupWave);
        this.activeEnemies = new ArrayList<>(initialWave);
        this.turnOrderStrategy = turnOrderStrategy;
        this.cli = cli;
        this.roundCount = 1;
        this.backupSpawned = false;
        this.battleOver = false;
    }


    public List<Combatant> getAliveCombatants() {
        List<Combatant> aliveCombatants = new ArrayList<>();
        if (player != null && player.isAlive()) aliveCombatants.add(player);
        for (Enemy enemy : activeEnemies) {
            if (enemy != null && enemy.isAlive()) aliveCombatants.add(enemy);
        }
        return aliveCombatants;
    }


    public void runBattle() {
        while (!battleOver) {
            cli.displayRoundStart(roundCount);
            List<Combatant> turnOrder = turnOrderStrategy.determineOrder(getAliveCombatants());
            for (Combatant actor : turnOrder) {
                if (battleOver) break;
                processTurn(actor);
            }
            checkBattleEnd();
            if (!battleOver) {
                roundCount++; 
            }
        }
    }


    private void processTurn(Combatant actor) {
        if (actor == null || !actor.isAlive()) return;


        actor.startTurn();


        if (!actor.isAlive()) {
            removeDeadEnemies();
            spawnBackupIfNeeded();
            checkBattleEnd();
            updateCooldown(actor);
            actor.endTurn();
            return;
        }


        if (!actor.canAct()) {
            System.out.println(actor.getName() + " is stunned and skips their turn!");
            removeDeadEnemies();
            spawnBackupIfNeeded();
            checkBattleEnd();
            updateCooldown(actor);
            actor.endTurn();
            return;
        }


        Action action;
        List<Combatant> targets;


        if (actor instanceof Player) {
            action = getPlayerChosenAction((Player) actor);
            targets = getPlayerChosenTargets((Player) actor, action);
        } else {
            action = new BasicAttack();
            targets = getEnemyTargets();
        }


        if (action != null && action.canUse(actor)) {
            action.execute(actor, targets);
        }


        removeDeadEnemies();
        spawnBackupIfNeeded();
        checkBattleEnd();
        updateCooldown(actor);
        actor.endTurn();
    }


    private void updateCooldown(Combatant actor) {
        if (actor instanceof Player) {
            Player playerActor = (Player) actor;
            if (playerActor.getCooldown() > 0) {
                playerActor.setCooldown(playerActor.getCooldown() - 1);
            }
        }
    }


    private void removeDeadEnemies() {
        List<Enemy> aliveEnemies = new ArrayList<>();
        for (Enemy enemy : activeEnemies) {
            if (enemy != null && enemy.isAlive()) aliveEnemies.add(enemy);
        }
        activeEnemies = aliveEnemies;
    }


    private void spawnBackupIfNeeded() {
        if (backupSpawned) return;
        if (!isInitialWaveCleared()) return;
        if (backupWave != null && !backupWave.isEmpty()) {
            activeEnemies.addAll(backupWave);
            backupSpawned = true;
            System.out.println("\n*** Backup enemies have arrived! ***");
        }
    }


    private boolean isInitialWaveCleared() {
        for (Enemy enemy : initialWave) {
            if (enemy != null && enemy.isAlive()) return false;
        }
        return true;
    }


    private List<Combatant> getEnemyTargets() {
        List<Combatant> targets = new ArrayList<>();
        if (player != null && player.isAlive()) targets.add(player);
        return targets;
    }


    private Action getPlayerChosenAction(Player playerActor) {
        cli.displayStatus(playerActor, activeEnemies);
        return cli.chooseAction(playerActor);
    }


    private List<Combatant> getPlayerChosenTargets(Player playerActor, Action action) {
        return cli.chooseTargets(playerActor, action, activeEnemies);
    }


    private void checkBattleEnd() {
        if (player == null || !player.isAlive()) {
            battleOver = true;
            return;
        }
        boolean hasAliveEnemy = false;
        for (Enemy enemy : activeEnemies) {
            if (enemy != null && enemy.isAlive()) {
                hasAliveEnemy = true;
                break;
            }
        }
        if (!hasAliveEnemy && (backupSpawned || backupWave == null || backupWave.isEmpty())) {
            battleOver = true;
        }
    }


    public int getRoundCount() { return roundCount; }
    public boolean isBattleOver() { return battleOver; }
    public Player getPlayer() { return player; }
    public List<Enemy> getActiveEnemies() { return activeEnemies; }
}

