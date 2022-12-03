public abstract class Enemy extends Unit{
    private Integer experience_value;
    protected static Player player;

    public Enemy(char tile, int x, int y, String name, Integer health_pool, Integer attack_points, Integer defense_points, Integer experience_value) {
        super(tile, x, y, name, health_pool, attack_points, defense_points);
        this.experience_value = experience_value;
        addToEnemy(this);
    }

    public abstract void on_GameTick();

    public void interact(Player player){
        manager.sendMessage(getName() + " engaged in combat with " + player.getName() + '.');
        int rand_att = random_Attack();
        int rand_def = player.random_Defense();
        if (rand_att - rand_def > 0){
            manager.sendMessage(getName() + " dealt " + (rand_att - rand_def) + " damage to " + player.getName() + ".");
            player.lose_health(rand_att-rand_def);
        }
        else {
            manager.sendMessage(getName() + " dealt " + 0 + " to " + player.getName() + ".");
        }
        manager.sendMessage(description());
    }

    public void interact(Enemy enemy){}

    public void lose_health(int num){
        this.setCurrent_health(getCurrent_health() - num);
        if (isDead()){
//            player.swapTiles(this);
            manager.sendMessage(getName() + " died. " + player.getName() + " gained " + experience_value + " experience.");
            player.addExp(this.experience_value);
            manager.removeEnemy(this);
        }
    }

}
