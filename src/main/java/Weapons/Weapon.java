package Weapons;

public abstract class Weapon {
    protected int damage;
    protected int range;
    protected int cooldown;
    protected int currentCooldown;

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public void updateCoolDown() {
        this.currentCooldown--;
    }

    public void resetCoolDown() {
        this.currentCooldown = this.cooldown;
    }
}
