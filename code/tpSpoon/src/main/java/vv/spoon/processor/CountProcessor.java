package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

/**
 * Processor qui va permettre de compter le nombre d'appels de chaque méthode.
 */
public class CountProcessor extends AbstractProcessor<CtMethod<?>> {

	@Override
	public void process(CtMethod<?> element) {
		// on récupère la première position du block de la méthode
		SourcePosition sp = element.getBody().getStatement(0).getPosition();
		CompilationUnit compileUnit = sp.getCompilationUnit();
		//nom à donner = nom de la classe + signature de la méthode
        CtClass<?> classN = (CtClass<?>)element.getParent();
        String name = classN.getSimpleName()+"."+element.getSignature();
		String snippet = "\n\t\tvv.spoon.counter.CounterMethod.add(\""+name+"\");\n";
		//on ajout le code au programme
		SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()), snippet, 0);
        compileUnit.addSourceCodeFragment(before);
	}
}
