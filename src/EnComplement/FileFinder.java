package EnComplement;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by benoit on 26/12/2017.
 */
public class FileFinder {

    static Optional<File> findFile(File mainDir){
        if(mainDir.isDirectory()){
            return Stream.of(mainDir.listFiles()).filter(file -> findFile(file).isPresent()).findFirst();
        }else if(mainDir.getName().equals("universe-formula")){
            return Optional.of(mainDir);
        }
        return Optional.empty();
    }

    static String findFullName(String mainDir){
        Optional<File> file = findFile(new File(mainDir));
        if(file.isPresent()){
          return file.get().getAbsolutePath();
        }else{
            return null;
        }
    }

    public static void main(String[] args){
        String dir = ".";

        System.out.println("result : "+findFullName(dir));
    }
}
