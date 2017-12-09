import java.util.HashMap;
import java.util.Map;

/**
 * Created by benoit on 09/12/2017.
 */
public class Mime {

    Map tableDeCorrespondance = new HashMap<String,String>();

    private String[] fileNameWithExtension;
    String file;


    public void publishMimeType(String file){
        this.file=file;
        fileNameWithExtension = file.split("\\.");
        if(isNoExtension() || !isKnownExtension()){
            displayUnknown();
        }else{
            displayMimeType();
        }
    }

    private boolean isNoExtension(){
        return file.endsWith(".") || fileNameWithExtension.length <2;
    }

    private boolean isKnownExtension(){
        return tableDeCorrespondance.containsKey(fileNameWithExtension[fileNameWithExtension.length-1].toLowerCase());
    }

    private void displayUnknown(){
        System.out.println("UNKNOWN");
    }

    private void displayMimeType(){
        System.out.println(tableDeCorrespondance.get(fileNameWithExtension[fileNameWithExtension.length-1].toLowerCase()));
    }

    public static void main(String[] args){

        Mime mime = new Mime();
        mime.tableDeCorrespondance.put("html", "text/html");
        mime.tableDeCorrespondance.put("png", "image/png");
        mime.tableDeCorrespondance.put("mp3", "audio/mpeg");

        mime.publishMimeType("test.html");
        mime.publishMimeType("noextension");
        mime.publishMimeType("portrait.png");
        mime.publishMimeType("doc.TXT");
        mime.publishMimeType(".mp3.");

        /**
         *
         *
         wav audio/x-wav
         mp3 audio/mpeg
         pdf application/pdf

         a
         a.wav
         b.wav.tmp
         test.vmp3
         pdf
         .pdf
         mp3
         report..pdf
         defaultwav
         .mp3.
         final.
         */

    }
}
