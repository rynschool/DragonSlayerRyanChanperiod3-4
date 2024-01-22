public class Dragon {
    private int health;
    private int level;
    private int damage;

    public Dragon (int level) {
        this.level = level;
    }

    public void standard() {
        health = 100 + (int)(Math.log(Math.pow(level, 5)));
        damage = (int)(Math.random() * (10 * level - 5 * level + 1)) + 5 * level;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setLevel (int newLevel) {
        level = newLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage () {
        return damage;
    }

    public void newDamage () {
        damage = (int)(Math.random() * (10 * level - 5 * level + 1)) + 5 * level;
    }
}