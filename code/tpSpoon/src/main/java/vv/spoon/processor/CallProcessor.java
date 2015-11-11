package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtReturn;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

/**
 * Processor qui va parcourir l'arbre et ajouter des instructions au début et à la fin de chaque méthode 
 * pour permettre de construire l'arbre d'appels des méthodes.
 */
public class CallProcessor extends AbstractProcessor<CtMethod<?>> {

	@Override
	public void process(CtMethod<?> element) {
		// on récupère la première position du block de la méthode
		SourcePosition sp = element.getBody().getStatement(0).getPosition();
		CompilationUnit compileUnit = sp.getCompilationUnit();
		//nom à donner = nom de la classe + signature de la méthode
		CtClass<?> classN = (CtClass<?>) element.getParent();
		String name = classN.getSimpleName() + "." + element.getSignature();
		//ligne à ajouter au début de la méthode pour indiquer qu'on est entré dans une méthode, 
		// on ajoute un noeud à l'arbre
		String snippet = "\n\t\tvv.spoon.call.TreeCall.add(new vv.spoon.call.Node(\"" + name + "\"));\n";
		//ajout du code
		SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()), snippet, 0);
		compileUnit.addSourceCodeFragment(before);
		
		// on récupère la dernière position où l'on peut ajouter une instruction
		if (element.getBody().getLastStatement() instanceof CtReturn) {
			sp = element.getBody().getStatement(element.getBody().getStatements().size() - 2).getPosition();
		} else {
			sp = element.getBody().getLastStatement().getPosition();
		}

		compileUnit = sp.getCompilationUnit();
		//ligne à ajouter à la fin de la méthode pour indiquer qu'on est sorti de la méthode, 
		// on change le noeud courant de l'arbre, on remonte dans l'arbre
		snippet = "\n\t\tvv.spoon.call.TreeCall.up();\n";
		//ajout du code
		SourceCodeFragment after = new SourceCodeFragment(compileUnit.nextLineIndex(sp.getSourceEnd()), snippet, 0);
		compileUnit.addSourceCodeFragment(after);
	}
}
