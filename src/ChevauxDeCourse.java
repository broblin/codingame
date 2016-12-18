import java.util.*;

/**
 * Created by benoit on 18/12/16.
 * trouver les 2 nombres les plus proches d'une liste
 */
public class ChevauxDeCourse {

    public static void solution1(int[] numbers){
        //TODO : vérifier la longueur du tableau
        int[] numbersSorted = Arrays.stream(numbers).sorted().toArray();

        int minimum = numbersSorted[1] - numbersSorted[0];
        int previous = 1;
        for(int i=2;i<numbersSorted.length;i++){
            int difference = Math.abs(numbersSorted[i]-numbersSorted[previous]);
            if(difference < minimum){
                minimum = difference;
            }
            previous = i;
        }
        System.out.println(minimum);
    }

    public void solution2(int[] numbers){
        //TODO : vérifier la longueur du tableau
        //Arrays.stream(numbers).sorted().collect(int i -> new MinimumDifference(i),);
        List<MinimumDifference> liste = new ArrayList<>();
        Arrays.stream(numbers).sorted().forEach(num -> liste.add(new MinimumDifference(num)));

        MinimumDifference result = liste.stream().reduce(new MinimumDifference(0,Integer.MAX_VALUE),(dif1,dif2) -> {
            int difference = Math.abs(dif1.horseLevel-dif2.horseLevel);
            if(difference < dif1.previousMinimumDifference){
                dif2.previousMinimumDifference = difference;
            }else{
                dif2.previousMinimumDifference = dif1.previousMinimumDifference;
            }
            return dif2;
        });
        System.out.println(result.previousMinimumDifference);
    }

    public static void main(String[] args){
        int[] numbers = new int[5];
        numbers[0] = 3;
        numbers[1] = 5;
        numbers[2] = 8;
        numbers[3] = 2;
        numbers[4] = 9;
        solution1(numbers);
        (new ChevauxDeCourse()).solution2(numbers);
    }

    //sert d'accumulateur : pzs sûr que ce soit plus simple à priori mais on peut plus étendre le code.
    class MinimumDifference{

        public MinimumDifference(int horseLevel){
            this.horseLevel = horseLevel;
        }

        public MinimumDifference(int horseLevel,int previousMinimumDifference){
            this.horseLevel = horseLevel;
            this.previousMinimumDifference = previousMinimumDifference;
        }

        public int horseLevel;
        public int previousMinimumDifference;

    }
}
