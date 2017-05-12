package com.mygdx.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.awt.*;



/**
 * Created by Niklas on 2017-05-08.
 */
public class libgdx_projectile {
    private Body b2Body;
    protected libgdx_world world = libgdx_world.getlgdxWorld();
    private boolean setForRemoval = false;

    libgdx_projectile(Point startPosition, Point targetPosition, float speed){
        Vector2 projectileVector = getDirectionVector(startPosition, targetPosition);
        projectileVector.setLength(speed);

        Body projectileBody = initiateProjectileBody(startPosition);
        projectileBody.setLinearVelocity(projectileVector);
    }

    private Vector2 getDirectionVector(Point startPosition, Point targetPosition){
        float velocityX = targetPosition.x - startPosition.x;
        float velocityY = targetPosition.y - startPosition.y;
        return new Vector2(velocityX, velocityY);
    }

    private Body initiateProjectileBody(Point startPosition){
        Body b2Body = defineBody(startPosition);

        //libgdx_body_userdata userdata = new libgdx_body_userdata();
        //b2Body.setUserData(userdata);

        b2Body.setUserData(this);

        b2Body.setGravityScale(0);
        b2Body.setBullet(true);
        return b2Body;
    }

    private Body defineBody(Point startPosition){
        BodyDef bdef = new BodyDef();
        bdef.position.set( startPosition.x / Dash.PPM, startPosition.y / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1 / Dash.PPM);
        fdef.shape = shape;

        b2Body.createFixture(fdef);

        CircleShape sensor = new CircleShape();
        sensor.setRadius(1 / Dash.PPM);

        fdef.isSensor = true;
        fdef.shape = sensor;

        b2Body.createFixture(fdef);

        return b2Body;
    }

    public boolean isSetForRemoval() {
        return setForRemoval;
    }

    public void setForRemoval() {
        setForRemoval = true;
    }

    public void dispose() {

    }
}