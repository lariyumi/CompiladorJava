package io.compiler.core.ast;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class CommandAtrib extends Command {

	private String atrib;
	private Var var;
	private String operador;

	public String getAtrib() {
		return atrib;
	}

	public void setAtrib(String atrib) {
		this.atrib = atrib;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public CommandAtrib(String atrib, Var var, String operador) {
		super();
		this.atrib = atrib;
		this.var = var;
		this.operador = operador;
	}

	public CommandAtrib() {
		super();
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		if (var.getType().getValue() == Types.NUMBER.getValue() && this.operador.equals("=")) {
			return this.var.getId() + " " + this.operador + " (float) " + this.atrib + ";\n";
		} else {
			return this.var.getId() + " " + this.operador + " " + this.atrib + ";\n";
		}
	}

}
