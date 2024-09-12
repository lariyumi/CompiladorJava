package io.compiler.core.ast;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class ReadCommand extends Command {

	private Var var;
	
	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	public ReadCommand(Var var) {
		super();
		this.var = var;
	}

	public ReadCommand() {
		super();
	}

	@Override
	public String generateTargetJava() {
		// TODO Auto-generated method stub
		return var.getId() +  " = " + ((var.getType() == Types.NUMBER) ? "sc.nextFloat();" : "sc.nextLine();") + "\n";
	}

	@Override
	public String generateTargetC() {
		// TODO Auto-generated method stub
		return "scanf(" + ((var.getType() == Types.NUMBER) ? ("\"%f\", &" + var.getId()) : ("\"%s\", &" + var.getId())) + ");\n";
	}

	@Override
	public String generateTargetPython() {
		// TODO Auto-generated method stub
		return var.getId() + " = input()\n";
	}
	
}
