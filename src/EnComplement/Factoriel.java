package EnComplement;

import java.util.function.UnaryOperator;
import java.util.stream.LongStream;

/**
 * Created by benoit on 01/12/2017.
 */
public class Factoriel {

    public long faconJava7(long number){
        generateErrorIfNegative(number);
        long result=number;
        for(long i=number-1;i>0;i--){
            result *=i;
        }
        return result == 0 ? 1 : result;
    }

    public long faconRecursiveJava7(long number){
        generateErrorIfNegative(number);
        return number == 0 ? 1 : number*faconRecursiveJava7(number-1);
    }

    UnaryOperator<Long> factorialUnaryOperator = number -> number == 0 ? 1 : number * this.factorialUnaryOperator.apply(number-1);

    public long faconRecursive(long number){
        generateErrorIfNegative(number);

        return factorialUnaryOperator.apply(number);

    }

    public long faconMapReduce(long number){
        generateErrorIfNegative(number);
        return LongStream.range(1L,number+1).reduce(1,(result,item) -> {return result*item; });

    }

    public void generateErrorIfNegative(long number){
        if(number <0){
            throw new NumberFormatException("une factoriel ne peut être négatif");
        }
    }

    public static void main(String[] args){
        Factoriel factoriel = new Factoriel();
        System.out.println(factoriel.faconJava7(6L));
        System.out.println(factoriel.faconRecursiveJava7(6L));
        System.out.println(factoriel.faconMapReduce(6L));
        System.out.println(factoriel.faconRecursive(6L));

        //équivalent d'un range: iterate c'est en bonus
        long [] longArray = LongStream.iterate(1,n -> n+1).limit(5).toArray();

        System.out.println(longArray[0]);
        System.out.println(longArray[1]);
        System.out.println(longArray[2]);
        System.out.println(longArray[3]);
        System.out.println(longArray[4]);
    }
}
