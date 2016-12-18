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
        //TODO : vérifier la nullité
        String[] tabStrTemperatures = strTemperatures.split(" ");

        //vérifier que tout est numérique
        List<String> strTemperaturesList = Arrays.asList(tabStrTemperatures);
        if(strTemperaturesList.stream().allMatch(t -> t.matches("[0-9\\-]+"))){
            int[] temperatures = Stream.of(tabStrTemperatures).mapToInt(Integer::parseInt).toArray();
            //int[] temperatures = new int[]{4,-1,1,2,-3};
            List<Integer> temperaturesList = Arrays.stream(temperatures).boxed().collect(Collectors.toList());
            System.out.println(temperaturesList.stream().min((s1, s2) -> Math.abs(s1) == Math.abs(s2) ? -Integer.compare(s1, s2) : Integer.compare(Math.abs(s1), Math.abs(s2))).get());
        }else {
            System.out.println("pas un numérique !");
        }
    }
}
