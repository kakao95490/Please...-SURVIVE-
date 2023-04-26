package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.SCALE;

public class Constants {
    public static class PlayerConstants{
        public static final int STATIC=0;
        public static final int WALKING=1;
        public static final int HIT=2;
        public static final int DEAD=3;

        public static int getSpriteAmount(int player_action){
            return switch (player_action) {
                case STATIC -> 8;
                case WALKING -> 9;
                case HIT -> 5;
                case DEAD -> 8;
                default -> 0;
            };
        }
    }
    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT =1;
        public static final int UP =2;
        public static final int DOWN =3;
        public static final int UP_LEFT =4;
        public static final int UP_RIGHT =5;
        public static final int DOWN_LEFT =6;
        public static final int DOWN_RIGHT =7;
    }

    public static class WindowConstants{
        public static final int WIDTH = 1920;
        public static final int HEIGHT = 1080;
        public static final double SCALE=3;
        public static final int FPS_TARGET = 60;
        public static final Coord SPRITE_COORD = new Coord( (WIDTH/2-TILE_SIZE/2), (HEIGHT/2-TILE_SIZE/2));
    }

    public static class MapConstants{
        public static final int TILE=64;
        public static final int TILE_SIZE= (int) (TILE*SCALE);
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
        public static final int CHAIR=17;
        public static final int CHAIR2=18;
        public static final int TABLEU=19;
        public static final int TABLED=20;
        public static final int TABLEUR=21;
        public static final int TABLEUL=22;
        public static final int TABLEDR=23;
        public static final int TABLEDL=24;
        public static final int DOUBLEWALL=25;
        public static final int DOUBLEWALLCORNERD=26;
        public static final int DOUBLEWALLCORNERU=27;
        public static final int TRIPLEWALLDOWN=28;
        public static final int TRIPLEWALLUP=29;
        public static final int PLANT=30;
    }

    public static class EntityConstants{
        public static final int BASE_MONKE=0;
        public static final int AXIE=1;
    }

    public static class Style{
        public static final Font font = Font.font("Verdana", FontWeight.BOLD, 35);
        public static final Font font2 = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);
    }

}
