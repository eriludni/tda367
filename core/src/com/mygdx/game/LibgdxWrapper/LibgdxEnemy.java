package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.EnemyBrain;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Lucas on 2017-05-05.
 */
public class LibgdxEnemy extends LibgdxCharacter {
    private Enemy enemyModel;
    private EnemyBrain brainModel;

    public LibgdxEnemy(Enemy enemy) {
        enemyModel = enemy;
        brainModel = enemy.getBrain();
        defineCharacter(enemyModel);

        System.out.println("LibgdxWrapper enemy created");
    }

    /**
     * Applies a Body to the enemy, by using the method defineCharacter() in its super class and sets its userdata to itself,
     * so it can be identified later
     */
    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
        getB2Body().setUserData(this);
    }

    public void createBodyFromModel(){
        defineCharacter(enemyModel);
        float vectorX = enemyModel.getX_velocity();
        float vectorY = enemyModel.getY_velocity();
        Vector2 vector2 = new Vector2(vectorX,vectorY);
        getB2Body().setLinearVelocity(vector2);
    }

    /**
     * Move the enemy rightwards with a specified force
     */
    public void moveEnemy() {
        float x = enemyModel.getBrain().updateX_Velocity();
        float y  = enemyModel.getBrain().updateY_Velocity();
        getB2Body().applyLinearImpulse(new Vector2(x,y),getB2Body().getWorldCenter(),true);
        //System.out.println(this.enemyModel.getX_velocity() + " " + this.enemyModel.getY_velocity());
    }


    public void dispose() {
        this.dispose();
    }

    /**
     * Checks if the enemy has died
     */
    public void update(float dt) {
        enemyModel.checkDead();
        enemyModel.setX_velocity(getB2Body().getLinearVelocity().x);
        enemyModel.setY_velocity(getB2Body().getLinearVelocity().y);
        moveEnemy();

        enemyModel.checkOutOfBounds();

    }

    /**
     *Getter
     */
    public Enemy getModel() {
        return enemyModel;
    }
}