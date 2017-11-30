/**
 * Created by benoit on 30/11/2017.
 */
public class ChuckNorris {

    public static String convertMessage(String message){
        byte[] byteMessage = message.getBytes();
        StringBuilder binary = new StringBuilder();
        StringBuilder chuckNorrisMessage = new StringBuilder();
        for(byte b : byteMessage) { //Boucle sur ces octets
            int val = b & 0xFF; // conversion vers int, comme si le byte était non signé.
            // => Autrement dit, si le bit de poids fort était à 1, ne pas le propager lors de la conversion

            String s = Integer.toBinaryString(val); // obtient le nombre écrit en binaire
            // => Mais la chaîne n'a pas de zéro devant, par exemple pour le nombre 4, ça donne "100" et non pas "00000100"

            for(int i = 0; i < 7 - s.length(); i++) { // boucle pour écrire les zéros manquants ASCII -> 7 bites
                binary.append('0');
            }

            binary.append(s); // écrire le nombre lui-même
        }


        //boucle pour l'algorithme chuck norris
        char previousBite=' ';
        for(char bite:binary.toString().toCharArray()){
            //nouvelle série
            if(previousBite != bite){
                if(previousBite != ' '){
                    //premier item
                    chuckNorrisMessage.append(" ");
                }
                if(bite == '1'){
                    chuckNorrisMessage.append("0 0");
                } else {
                    chuckNorrisMessage.append("00 0");
                }

            }else{
                chuckNorrisMessage.append("0");
            }
            previousBite = bite;
        }

        return chuckNorrisMessage.toString();
        //return binary.toString();
    }

    public static void main(String args[]) {
        //C majuscule -> 1000011 -> "0 0 00 0000 0 00"
        //bloc d'une valeur binaire
        //s'il s'agit d'un bloc de 1 : 0 sinon 00
        //puis ensuite le nombre d'élément

        System.out.println(convertMessage("%"));

        //autre exemple : CC : 0 0 00 0000 0 000 00 0000 0 00
        // % : 00 0 0 0 00 00 0 0 00 0 0 0
        //Message de Chuck Norris : 0 0 00 0000 0 0000 00 0 0 0 00 000 0 000 00 0 0 0 00 0 0 000 00 000 0 0000 00 0 0 0 00 0 0 00
    }
}
