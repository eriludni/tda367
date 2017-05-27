package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 *
 * @author Lucas Ruud
 * Uses:
 * Used by: PowerUpModifier, DefaultMap, PlainMap, PlatformMap
 */
public interface IPowerUpModifier {
    int getPoinsDistance();
    int getMountainTop();
    int getMountainDiff();
    int getNumberOfPlatforms();
    int getPlatformLength();
    int getMinPlatformRow();
    int getMaxPlatformRow();
    int getNumberOfPitfalls();
    int getPitfallLength();
    int getMinPitfallRow();
    int getMaxPitfallRow();
}
