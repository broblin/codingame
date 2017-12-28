import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Votre virus a créé une backdoor sur le réseau Skynet vous permettant d'envoyer de nouvelles instructions au virus en temps réél.

 Vous décidez de passer à l'attaque active en empêchant Skynet de communiquer sur son propre réseau interne.

 Le réseau Skynet est divisé en sous-réseaux. Sur chaque sous-réseau un agent Skynet a pour tâche de transmettre de l'information en se déplaçant de noeud en noeud le long de liens et d'atteindre une des passerelles qui mène vers un autre sous-réseau.

 Votre mission est de reprogrammer le virus pour qu'il coupe les liens dans le but d'empêcher l'agent Skynet de sortir de son sous-réseau et ainsi d'informer le hub central de la présence de notre virus.
 Règles

 Pour chaque test on vous fournit :
 La carte du sous-réseau.
 L'emplacement des passerelles de sortie.
 L'emplacement de départ de l'agent Skynet.
 >>> Au maximum, un noeud du réseau ne peut être lié qu'à une seule passerelle. <<<

 À chaque tour de jeu :
 Dans un premier temps, vous coupez un des liens du sous-réseau.
 Ensuite l'agent Skynet se déplace vers un noeud du réseau encore accessible.
 * Created by benoit on 27/12/2017.
 */
public class SkynetRevolution1 {

    int skynetAgentPos;

    int[] exitGateways;

    List<int[]> linkIndexes;

    public int[] cutNearestLinkFromSkynetAgent(){

        Predicate<int[]> filterNoExistGatewayLink = linkIndex -> IntStream.of(exitGateways).anyMatch(exitGateway -> exitGateway == linkIndex[0] || exitGateway == linkIndex[1]);

        List<int[]> existGatewayLinks = linkIndexes.stream().filter(filterNoExistGatewayLink).collect(Collectors.toList());

        //si l'agent est suceptible d'atteindre une passerelle, on supprime le noeud en question
        Optional<int[]> nearestGateway = existGatewayLinks.stream().filter(existGatewayLink -> existGatewayLink[0] == skynetAgentPos || existGatewayLink[1] == skynetAgentPos).findFirst();
        if(nearestGateway.isPresent()){
            linkIndexes.remove(nearestGateway.get());
            return nearestGateway.get();
        }else{
            //sinon on supprime le premier noeud lié à une passerelle de la liste
            int[] firstElem = existGatewayLinks.get(0);
            linkIndexes.remove(0);
            return firstElem;
        }
    }

    public static void main(String[] args){

        List<int[]> linkIndexes = new ArrayList<>();
        linkIndexes.add(new int[]{0,1});
        linkIndexes.add(new int[]{0,2});
        linkIndexes.add(new int[]{1,3});
        linkIndexes.add(new int[]{2,3});
        int[] exitGateways = new int[]{3};
        int skynetAgentPos = 0;

        SkynetRevolution1 skynet = new SkynetRevolution1();
        skynet.skynetAgentPos = skynetAgentPos;
        skynet.linkIndexes = linkIndexes;
        skynet.exitGateways = exitGateways;
        int[] result = skynet.cutNearestLinkFromSkynetAgent();

        System.out.println(result[0] + " "+result[1]);

        skynet.skynetAgentPos = 2;
        result = skynet.cutNearestLinkFromSkynetAgent();

        System.out.println(result[0] + " "+result[1]);

    }
}
