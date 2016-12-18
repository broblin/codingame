import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoit on 11/12/16.
 */
public class Spoon {

    List<Point> points = new ArrayList<>();

    int height;
    int width;

    public static String POINT = "0";
    public Point NO_POINT = new Point(-1,-1);

    public Spoon(int height,int width){
        this.height = height;
        this.width = width;
    }

    public void fillPoints(String line,int y){
        for(int x=0;x<line.length();x++){
            if(isPoint(line.substring(x,x+1))){
                points.add(new Point(x,y));
            }
        }
    }

    public Map<String,String> displayAllCoordByPoints() {
        Map pointsWithNeighborn = points.stream().reduce(new HashMap<>(),
                (map, point) -> {
                    String rightNeighborn =  points.stream().filter(pointToCheck -> point.y == pointToCheck.y && pointToCheck.x - point.x > 0)
                            .min((point1, point2) -> point1.x - point2.x).orElse(NO_POINT).toString();

                    String leftNeighborn = points.stream().filter(pointToCheck -> point.x == pointToCheck.x && pointToCheck.y - point.y > 0)
                            .min((point1, point2) -> point1.y - point2.y).orElse(NO_POINT).toString();
                    map.put(point.toString(), point.toString() + " " + rightNeighborn + " " + leftNeighborn);
                    return map;
                },
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        return pointsWithNeighborn;

    }

    static boolean isPoint(String str){
        return POINT.equals(str);
    }

    static void publish(Map<String,String> pointsWithNeighborn){
        pointsWithNeighborn.values().stream().forEach( toPublish -> {
                    System.out.println(toPublish);
                }
        );
    }

    class Point{
        public int x;
        public int y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return x+" "+y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public static void main(String[] args){
        /*
        List<String> test = new ArrayList<>();
        test.add("test");
        test.add("test2");
        System.out.println(test.stream().min((str1, str2) -> str1.length() - str2.length()).orElse("-1 -1"));
        */
        //exemple :
        int height=2;
        int width=3;
        String[] strTab = new String[2];
        strTab[0] = "-00";
        strTab[1] = "00-";
        Spoon player = new Spoon(height,width);
        for(int y=0;y<height;y++){
            player.fillPoints(strTab[y],y);

        }
        Spoon.publish(player.displayAllCoordByPoints());
    }


}
