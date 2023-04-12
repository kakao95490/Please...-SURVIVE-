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
}
