package vv.spoon;

import vv.spoon.counter.CounterMethod;
import vv.spoon.counter.ShutdownHookLog;
import vv.spoon.processor.CountProcessor;

import java.io.IOException;
import java.util.Arrays;

/**
 * Classe appelé pour lancer les process permettant de compter le nombre d'appel de chaque méthode.

 */
public class MainCountMethod {

    public static void main(String[] args) throws IOException {
        Instru instru = new Instru(args[0], args[1], new CountProcessor());

        //copy the project (args[0]) in the output directory (args[1])
        instru.initOutputDirectory();

        instru.instru("/vv/spoon/counter",Arrays.asList(CounterMethod.class.getSimpleName(),ShutdownHookLog.class.getSimpleName()));
    }

}
