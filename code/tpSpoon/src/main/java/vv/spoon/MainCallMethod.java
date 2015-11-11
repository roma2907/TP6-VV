package vv.spoon;

import vv.spoon.call.Node;
import vv.spoon.call.TreeCall;
import vv.spoon.call.ShutdownHookLog;
import vv.spoon.processor.CallProcessor;
import vv.spoon.processor.ExceptionProcessor;

import java.io.IOException;
import java.util.Arrays;

/**
 * Classe appelé pour lancer les process permettant de mettre en place l'arbre d'appel des méthodes. 
 */
public class MainCallMethod {

    public static void main(String[] args) throws IOException {
        Instru instru = new Instru(args[0], args[1], Arrays.asList(new CallProcessor(),new ExceptionProcessor()));

        //copy the project (args[0]) in the output directory (args[1])
        instru.initOutputDirectory();
       
        instru.instru("/vv/spoon/call",Arrays.asList(Node.class.getSimpleName(),TreeCall.class.getSimpleName(),ShutdownHookLog.class.getSimpleName()));
    }
}
