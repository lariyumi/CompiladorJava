package io.compiler.core.ast;

import java.util.HashMap;
import java.util.List;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class Program {
	
	private String name;
	private HashMap<String, Var> symbolTable;
	private List<Command> commandList;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public HashMap<String, Var> getSymbolTable() {
		return symbolTable;
	}
	
	public void setSymbolTable(HashMap<String, Var> symbolTable) {
		this.symbolTable = symbolTable;
	}
	
	public List<Command> getCommandList() { 
		return commandList;
	}
	
	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}
	
	public String generateTargetJava() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class " + name + "{ \n");
		str.append("	public static void main(String args[]) { \n");
		str.append("		Scanner sc = new Scanner(System.in);\n");
		for (String varId: symbolTable.keySet()) {
			Var var = symbolTable.get(varId);
			if (var.getType() == Types.NUMBER) {
				str.append("		float ");
			}
			else {
				str.append("		String ");	
			}
			str.append(var.getId() + ";\n");
		}
		
		for(Command cmd: commandList) {
			str.append("		" + cmd.generateTargetJava());
		}
		str.append("	}\n");
		str.append("}");
		
		return str.toString();
	}
	
	public String generateTargetC() {
		StringBuilder str = new StringBuilder();
		str.append("#include <stdio.h>\n");
		str.append("int main() { \n");;
		for (String varId: symbolTable.keySet()) {
			Var var = symbolTable.get(varId);
			if (var.getType() == Types.NUMBER) {
				str.append("		float ");
				str.append(var.getId() + ";\n");
			}
			else {
				str.append("		char ");	
				str.append(var.getId() + "[100];\n");
			}
		}
		for(Command cmd: commandList) {
			str.append("		" + cmd.generateTargetC());
		}
		str.append("	return 0;\n ");
		str.append("}");
		
		return str.toString();
	}
	
	public String generateTargetPython() {
		StringBuilder str = new StringBuilder();
		str.append("#Necessário formatar código corretamente para rodar de forma apropriada\n");
		for (String varId: symbolTable.keySet()) {
			Var var = symbolTable.get(varId);
			str.append(var.getId() + " = None\n");
		}
		for(Command cmd: commandList) {
			str.append("		" + cmd.generateTargetPython());
		}
		
		return str.toString();
	}
	
}
