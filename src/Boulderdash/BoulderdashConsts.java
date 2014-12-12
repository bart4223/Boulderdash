package Boulderdash;

public abstract class BoulderdashConsts {

    // --------------------- Sprites -----------------------------------
    public final static Integer SPRITE_AIR                           = 0;
    public final static Integer SPRITE_BENDER_FOOT_DOWN              = 42;//1;
    public final static Integer SPRITE_DIAMOND                       = 2;
    public final static Integer SPRITE_BENDER_FOOT_UP                = 43;//3;
    public final static Integer SPRITE_BOULDER                       = 4;
    public final static Integer SPRITE_EARTH                         = 5;
    public final static Integer SPRITE_DOOR_OPEN_NONE                = 6;
    public final static Integer SPRITE_DOOR_CLOSE                    = 7;
    public final static Integer SPRITE_DOOR_OPEN_QUARTER             = 8;
    public final static Integer SPRITE_DOOR_OPEN_HALF                = 9;
    public final static Integer SPRITE_DOOR_OPEN_THREEQUARTER        = 10;
    public final static Integer SPRITE_DOOR_OPEN                     = 11;
    public final static Integer SPRITE_MONSTER                       = 12;
    public final static Integer SPRITE_MONSTER_INVERSE               = 13;
    public final static Integer SPRITE_BRICK_CORNER_TOPLEFT          = 14;
    public final static Integer SPRITE_BRICK_TOP                     = 15;
    public final static Integer SPRITE_BRICK_CORNER_TOPRIGHT         = 16;
    public final static Integer SPRITE_BRICK_RIGHT                   = 17;
    public final static Integer SPRITE_BRICK_LEFT                    = 18;
    public final static Integer SPRITE_BRICK_CORNER_BOTTOMLEFT       = 19;
    public final static Integer SPRITE_BRICK_BOTTOM                  = 20;
    public final static Integer SPRITE_BRICK_CORNER_BOTTOMRIGHT      = 21;
    public final static Integer SPRITE_BRICK_LEFT_END_BOTTOM         = 22;
    public final static Integer SPRITE_BRICK_BOTTOM_END_LEFT         = 23;
    public final static Integer SPRITE_BRICK_BOTTOM_END_RIGHT        = 24;
    public final static Integer SPRITE_BRICK_RIGHT_END_TOP           = 25;
    public final static Integer SPRITE_BRICK_FULL                    = 26;
    public final static Integer SPRITE_BOMB                          = 27;
    public final static Integer SPRITE_DOOR_BACK                     = 28;
    public final static Integer SPRITE_DOOR_OPEN_QUARTER_FRONT       = 29;
    public final static Integer SPRITE_DOOR_OPEN_HALF_FRONT          = 30;
    public final static Integer SPRITE_DOOR_OPEN_THREEQUARTER_FRONT  = 31;
    public final static Integer SPRITE_DOOR_OPEN_FRONT               = 32;
    public final static Integer SPRITE_EXPLOSION                     = 33;
    public final static Integer SPRITE_HEART_ACTIVE                  = 34;
    public final static Integer SPRITE_HEART_INACTIVE                = 35;
    public final static Integer SPRITE_FUSIBLE_RIGHT                 = 36;
    public final static Integer SPRITE_FUSIBLE_CORNER_RIGHT          = 37;
    public final static Integer SPRITE_FUSIBLE_TOP                   = 38;
    public final static Integer SPRITE_FUSIBLE_CORNER_LEFT           = 39;
    public final static Integer SPRITE_FUSIBLE_LEFT                  = 40;
    public final static Integer SPRITE_FLAME                         = 41;
    public final static Integer SPRITE_NEW_BENDER_FOOT_DOWN          = 42;
    public final static Integer SPRITE_NEW_BENDER_FOOT_UP            = 43;

    // --------------------- Mimics ------------------------------------
    public final static String MIMIC_ACTION_PLAYER_MOVE              = "Player.Move";
    public final static String MIMIC_ACTION_PLAYER_STOMP             = "Player.Stomp";
    public final static String MIMIC_ACTION_PLAYER_UP                = "Player.Up";
    public final static String MIMIC_ACTION_PLAYER_DOWN              = "Player.Down";
    public final static String MIMIC_ACTION_PLAYER_LEFT              = "Player.Left";
    public final static String MIMIC_ACTION_PLAYER_RIGHT             = "Player.Right";
    public final static String MIMIC_ACTION_PLAYER_KILLED            = "Player.Killed";
    public final static String MIMIC_ACTION_DOOR_OPEN                = "Door.Open";
    public final static String MIMIC_ACTION_DOOR_CLOSE               = "Door.Close";
    public final static String MIMIC_ACTION_EXPLOSION                = "Explosion";

    // --------------------- SOUNDS ---------------------------------
    public final static String SOUND_SPLASH_FEAR                     = "Fear";
    public final static String SOUND_ROLLING_STONE                   = "RollingStone";
    public final static String SOUND_BENDER_WALK                     = "Walk";

}
