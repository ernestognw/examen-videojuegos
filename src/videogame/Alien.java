package videogame;

import java.awt.*;

public class Alien extends Item {
    private Animation animation;
    private Animation animationDamage;
    private Animation animationDestroy;
    private int speed;
    private int armor;
    private int destroyDelay;
    private boolean isDestroying;
    private boolean destroyed;

    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        speed = 5;
        animation = new Animation(Assets.alien, 100);
        animationDamage = new Animation(Assets.alienDamage, 100);
        animationDestroy = new Animation(Assets.alienDestroy, 100);
        armor = Commons.ALIEN_ARMOR;
        destroyDelay = Assets.alienDestroy.length;
        destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void startDestroying() {
        isDestroying = true;
        width = Commons.ALIEN_DESTROY_WIDTH;
        x -= (Commons.ALIEN_DESTROY_WIDTH - Commons.ALIEN_WIDTH) / 2;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void act(int direction) {
        animation.tick();
        animationDamage.tick();
        if(isDestroying) animationDestroy.tick();
        x += direction * speed;
    }

    @Override
    public void tick() {
    }

    @Override
    public String toString() {
        return "[a] x:" + x + " y:" + y + " armor:" + armor;
    }

    @Override
    public void render(Graphics g) {
        Animation toRender;
        if (isDestroying) {
            toRender = animationDestroy;
            if (destroyDelay >= 0) {
                destroyDelay--;
            } else {
                destroyed = true;
            }
        } else if (armor <= Commons.ALIEN_ARMOR / 2) {
            toRender = animationDamage;
        } else {
            toRender = animation;
        }
        g.drawImage(toRender.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
