package EnComplement;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * On prend un point P au hasard de coordonnées (x, y) tel que 0 x 1 et 0 y 1. Si x2 + y2 < 1,
 * alors le point est àl'intérieur du quart de disque de rayon 1, sinon le point est à l'extérieur.
 * Objectif : nb items à l'intérieur du cercle
 * on sait que items != null
 * Created by benoit on 26/12/2017.
 */
public class InsideCircle {

    static long nbPointsInsideCircleOfOne(double[][] items){
        Predicate<double[]> isInsideCircle = item -> item[0]*item[0]+item[1]*item[1] < 1;
        return Stream.of(items).filter(isInsideCircle).count();
    }

    public static void main(String[] args){
        double[][] tabs = new double[][]{new double []{0.5,0.6},new double[]{2,1},new double[]{0.5,0.1}};
        System.out.println(nbPointsInsideCircleOfOne(tabs));
    }


}
