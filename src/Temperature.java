import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by benoit on 05/12/16.
 */
public class Temperature {

    public static void main(String[] args){
        String strTemperatures = "4 -1 1 2 -3";
        if(strTemperatures == null){
            System.out.println("tableau null !");
        }
        String[] tabStrTemperatures = strTemperatures.split(" ");

        //vérifier que tout est numérique
        List<String> strTemperaturesList = Arrays.asList(tabStrTemperatures);
        if(strTemperaturesList.stream().allMatch(t -> t.matches("[0-9\\-]+"))){
            System.out.println(Stream.of(tabStrTemperatures).mapToInt(Integer::parseInt).boxed().min((s1, s2) -> Math.abs(s1) == Math.abs(s2) ? -Integer.compare(s1, s2) : Integer.compare(Math.abs(s1), Math.abs(s2))).get());
        }else {
            System.out.println("pas un numérique !");
        }
    }
}
