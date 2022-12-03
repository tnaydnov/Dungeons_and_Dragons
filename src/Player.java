public abstract class Player extends Unit implements HeroicUnit{
    private Integer experience;
    private Integer level;

    public Player(int x, int y, String name, Integer health_pool, Integer attack_points, Integer defense_points) {
        super('@', x, y, name, health_pool, attack_points, defense_points);
        this.experience = 0;
        this.level = 1;
        Enemy.player = this;
        manager.setPlayer(this);
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void levelUp(){
        experience = experience - (level * 50);
        level = level + 1;
        setHealth_pool(getHealth_pool() + (10 * level));
        setCurrent_health(getHealth_pool());
        setAttack_points(getAttack_points() + (4 * level));
        setDefense_points(getDefense_points() + level);
    }

    public abstract void castAbility();

    public void addExp(int experience) {
        this.experience = this.experience + experience;
        while (this.experience >= (level * 50)) {
            levelUp();
        }
    }

    public String description() {
        return super.description() + ", Level: " + level + ", Experience: " + experience + '/' + (50 * level);
    }
    public abstract void on_GameTick();

    protected void battle(Enemy enemy){
        manager.sendMessage(getName() + " engaged in combat with " + enemy.getName() + '.');
        int rand_att = random_Attack();
        int rand_def = enemy.random_Defense();
        if (rand_att - rand_def > 0){
            manager.sendMessage(getName() + " dealt " + (rand_att-rand_def) + " damage to " + enemy.getName() +".");
            enemy.lose_health(rand_att-rand_def);
        }
        else {
            manager.sendMessage(getName() + " dealt " + 0 + " to " + enemy.getName() + ".");
        }
        manager.sendMessage(enemy.description());
    }

    public void lose_health(int damage){
        setCurrent_health(getCurrent_health() - damage);
        if (isDead()) {
            setTile('X');
            manager.sendMessage("Game is over, you lost :(");
            manager.is_GameOver();
        }
    }

    public void interact(Enemy enemy){
        this.battle(enemy);
    }


    public void interact(Player player){}

    public void moveUp() {
        Visited tile = board.getTile(getX() - 1, getY());  //move up
        tile.accept(this);
    }

    public void moveDown() {
        Visited tile = board.getTile(getX() + 1, getY());  //move down
        tile.accept(this);
    }

    public void moveLeft() {
        Visited tile = board.getTile(getX(), getY() - 1); //move left
        tile.accept(this);
    }

    public void moveRight() {
        Visited tile = board.getTile(getX(), getY() + 1); //move right
        tile.accept(this);
    }
}
