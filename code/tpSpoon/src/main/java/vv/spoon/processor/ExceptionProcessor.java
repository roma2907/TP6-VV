package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

/**
 * Ce processor permet de faire remonté le noeud courant lorsque l'on passe dans
 * un exception.
 *
 */
public class ExceptionProcessor extends AbstractProcessor<CtCatch> {

	@Override
	public void process(CtCatch element) {
		// on récupère la première position du block du catch
		SourcePosition sp = element.getBody().getStatement(0).getPosition();
		CompilationUnit compileUnit = sp.getCompilationUnit();
		CtElement elem = element;
		//on récupère le nom de la méthode et le nom de la classe
		while(!(elem instanceof CtMethod)){
			elem = elem.getParent();
		}

		// nom à donner = nom de la classe + signature de la méthode
		CtClass<?> classN = (CtClass<?>) elem.getParent();
		String name = classN.getSimpleName() + "." + elem.getSignature();
		String snippet = "\n\t\tvv.spoon.call.TreeCall.up(\""+name+"\");\n";
		// ajout du code au programme
		SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()), snippet, 0);
		compileUnit.addSourceCodeFragment(before);
	}

}
