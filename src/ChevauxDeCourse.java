import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static void main(String[] args){
        int[] numbers = new int[5];
        numbers[0] = 3;
        numbers[1] = 5;
        numbers[2] = 8;
        numbers[3] = 2;
        numbers[4] = 9;
        solution1(numbers);
    }
}
