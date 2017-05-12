package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by Niklas on 2017-05-09.
 */
public class MyContactListener implements ContactListener {
    private World world;

    private libgdx_world lgdxWorld = libgdx_world.getlgdxWorld();
    private libgdx_player lgdxPlayer = lgdxWorld.getPlayerCharacter();
    private ArrayList<libgdx_enemy> lgdxEnemies = lgdxWorld.getEnemyCharacters();
    private libgdx_enemy lgdxEnemy;

    public MyContactListener(World world){
        this.world = world;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body body = fixtureA.getBody();

        //System.out.println(fixtureA.getShape().getType().equals(fixtureB.getShape().getType()));
        if(fixtureA.getBody().isBullet()){
            //libgdx_body_userdata userdata = (libgdx_body_userdata) fixtureA.getBody().getUserData();
            //userdata.isSetForRemoval = true;

            libgdx_projectile userdata = (libgdx_projectile) fixtureA.getBody().getUserData();
            userdata.setForRemoval();
        }
        if(fixtureB.getBody().isBullet()){
            //libgdx_body_userdata userdata = (libgdx_body_userdata) fixtureB.getBody().getUserData();
            //userdata.isSetForRemoval = true;

            libgdx_projectile userdata = (libgdx_projectile) fixtureB.getBody().getUserData();
            userdata.setForRemoval();
        }

        if(fixtureA.getBody().getUserData() instanceof libgdx_player || fixtureB.getBody().getUserData() instanceof libgdx_player) {
            if(fixtureA.getBody().getUserData() instanceof libgdx_player && fixtureB.getBody().getUserData() instanceof libgdx_enemy) {
                lgdxPlayer.reduceHealth(1);
                System.out.println(lgdxPlayer.getHealth());
                System.out.println("contact_1");
            }
            else if(fixtureA.getBody().getUserData() instanceof  libgdx_enemy && fixtureB.getBody().getUserData() instanceof libgdx_player) {
                lgdxPlayer.reduceHealth(1);
                System.out.println(lgdxPlayer.getHealth());
                System.out.println("contact_2");
            }
        }
        if(fixtureA.getBody().isBullet() || fixtureB.getBody().isBullet()) {
            if((fixtureA.getBody().isBullet() && fixtureB.getBody().getUserData() instanceof libgdx_enemy) || (fixtureA.getBody().getUserData() instanceof libgdx_enemy && fixtureB.getBody().isBullet())) {

                if(lgdxEnemies.contains(fixtureA.getBody().getUserData())) {
                    lgdxEnemy = (libgdx_enemy) fixtureA.getBody().getUserData();
                }
                else if(lgdxEnemies.contains(fixtureB.getBody().getUserData())) {
                    lgdxEnemy = (libgdx_enemy) fixtureB.getBody().getUserData();
                }

                lgdxEnemy.reduceHealth(1);

                System.out.println(lgdxEnemy.getHealth());
                System.out.println(lgdxEnemy.getB2Body().getUserData());
                System.out.println("contact_3");
            }

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
