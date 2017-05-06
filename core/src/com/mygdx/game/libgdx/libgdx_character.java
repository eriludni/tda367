package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Character;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Lucas on 2017-05-05.
 */
public abstract class libgdx_character extends Character{

    protected ICharacter character;
    protected Body b2Body;
    protected libgdx_world world = libgdx_world.getlgdxWorld();

    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / Dash.PPM, character.getYPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(character.getRadius() / Dash.PPM);
        fdef.shape = shape;

        b2Body.createFixture(fdef);
    }

    public Body getB2Body() {
        return b2Body;
    }
}
