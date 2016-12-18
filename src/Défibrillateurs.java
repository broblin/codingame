import java.util.stream.Stream;

/**
 * Created by benoit on 18/12/16.
 */
public class Défibrillateurs {

    public static double EARTH_RADIUS=6371;
    public static double DEGREE_TO_RADIUS_RATIO = 0.0174533;

    public static double findLat(String[] data){
        return convertToDouble(data[5]);
    }

    public static double findLong(String[] data){
        return convertToDouble(data[4]);
    }

    public static double convertToDouble(String value){
        return Double.parseDouble(value.replace(",", "."));
    }

    public static double convertDegreeToRadius(double value){
        return value*DEGREE_TO_RADIUS_RATIO;
    }

    public static Double calcDistance(double longUser,double latUser,double longDefib,double latDefib){
        double x = (longUser-longDefib)*Math.cos((latUser+latDefib)/2);
        double y = latUser-latDefib;
        return Math.sqrt(x*x+y*y)*EARTH_RADIUS;
    }

    public static void main(String[] args){
        String LON = "3,879483";
        String LAT = "43,608177";
        int N = 3;
        String[] defibs = {"1;Maison de la Prevention Sante;6 rue Maguelone 340000 Montpellier;;3,87952263361082;43,6071285339217",
                "2;Hotel de Ville;1 place Georges Freche 34267 Montpellier;;3,89652239197876;43,5987299452849",
                "3;Zoo de Lunaret;50 avenue Agropolis 34090 Mtp;;3,87388031141133;43,6395872778854"};
        String nearest = Stream.of(defibs).min((line1,line2) ->{
            String[] data1 = line1.split(";");
            if(data1.length != 6){
                //générer une exception
                throw new RuntimeException(String.format("cette ligne n'a pas 6 données : %s",line1));
            }
            double dist1 = calcDistance(convertDegreeToRadius(convertToDouble(LON)),
                    convertDegreeToRadius(convertToDouble(LAT)),
                    convertDegreeToRadius(findLong(data1)),
                    convertDegreeToRadius(findLat(data1)));

            String[] data2 = line2.split(";");
            if(data2.length != 6){
                //générer une exception
                throw new RuntimeException(String.format("cette ligne n'a pas 6 données : %s",line2));
            }
            double dist2 = calcDistance(convertDegreeToRadius(convertToDouble(LON)),
                    convertDegreeToRadius(convertToDouble(LAT)),
                    convertDegreeToRadius(findLong(data2)),
                    convertDegreeToRadius(findLat(data2)));
            return Double.valueOf(dist1 - dist2) <0 ? -1 :1;
        }).orElse("");
        //réponse attendue : Maison de la Prevention Sante
        System.out.println(nearest.split(";")[1]);
    }
}
