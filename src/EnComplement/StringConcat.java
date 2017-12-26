package EnComplement;

import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by benoit on 26/12/2017.
 */
public class StringConcat {

    static String concatWithJava7(String[] strings){
        if(strings == null){
            return "";
        }
        StringBuilder resultBuilder = new StringBuilder();
        for(int i=0;i<strings.length;i++){
            resultBuilder.append(strings[i]);
        }
        return resultBuilder.toString();
    }

    static String concatWithStringJoin(String[] strings) {
        return String.join("",strings);
       }


    static String concatWithReduce(String[] strings) {
        if(strings == null){
            return "";
        }
        String result="";
        BinaryOperator<String> concatWithStrBuilder = (str1,str2) -> str1+str2;

        return Stream.of(strings).reduce(result,concatWithStrBuilder);
    }

    public static void main(String[] args){
        String[] examples = new String[] {"a","b","c"};
        System.out.println(concatWithJava7(examples));
        System.out.println(concatWithReduce(examples));
        System.out.println(concatWithStringJoin(examples));
    }

}
