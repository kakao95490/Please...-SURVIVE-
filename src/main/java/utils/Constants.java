package utils;

public class Constants {
    public static class PlayerConstants{
        public static final int STATIC=0;
        public static final int WALKING=1;
        public static final int HIT=2;

        public static int getSpriteAmount(int player_action){
            return switch (player_action) {
                case STATIC -> 1;
                case WALKING -> 9;
                case HIT -> 3;
                default -> 0;
            };
        }
    }
    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT =1;
        public static final int UP =2;
        public static final int DOWN =3;

    }

    public static class WindowConstants{
        public static final int WIDTH = 1920;
        public static final int HEIGHT = 1080;
        public static final double SCALE=3;
        public static final int FPS_TARGET = 60;
    }

    public static class MapConstants{
        public static final int TILE_SIZE=64;
        public static final int FLOOR=0;
        public static final int WALLR=1;
        public static final int WALLL=2;
        public static final int WALLU=3;
        public static final int WALLD=4;
        public static final int CORNERUR=5;
        public static final int CORNERUL=6;
        public static final int CORNERDR=7;
        public static final int CORNERDL=8;
        public static final int OUTCORNERUR=9;
        public static final int OUTCORNERUL=10;
        public static final int OUTCORNERDR=11;
        public static final int OUTCORNERDL=12;
        public static final int DOORR=13;
        public static final int DOORL=14;
        public static final int DOORU=15;
        public static final int DOORD=16;
    }

}
