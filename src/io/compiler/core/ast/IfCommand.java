package io.compiler.core.ast;

import java.util.ArrayList;
import java.util.List;

public class IfCommand extends Command {

	private String expression;
	private List<Command> trueList;
	private List<Command> falseList = new ArrayList<Command>();

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public List<Command> getTrueList() {
		return trueList;
	}

	public void setTrueList(List<Command> trueList) {
		this.trueList = trueList;
	}

	public List<Command> getFalseList() {
		return falseList;
	}

	public void setFalseList(List<Command> falseList) {
		this.falseList = falseList;
	}

	public IfCommand(String expression, List<Command> trueList, List<Command> falseList) {
		super();
		this.expression = expression;
		this.trueList = trueList;
		this.falseList = falseList;
	}

	public IfCommand() {
		super();
	}
	
	@Override
	public String generateTargetJava() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("if (" + expression + ") {\n");
		for (Command cmd: trueList) {
			str.append("			" + cmd.generateTargetJava());
		}
		str.append("		}\n");
		if(!falseList.isEmpty()) {
			str.append("		else {\n");
			for (Command cmd: falseList) {
				str.append("			" + cmd.generateTargetJava());
			}
			str.append("		}\n");
		}
		return str.toString();
	}

	@Override
	public String generateTargetC() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("if (" + expression + ") {\n");
		for (Command cmd: trueList) {
			str.append("			" + cmd.generateTargetC());
		}
		str.append("		}\n");
		if(!falseList.isEmpty()) {
			str.append("		else {\n");
			for (Command cmd: falseList) {
				str.append("			" + cmd.generateTargetC());
			}
			str.append("		}\n");
		}
		return str.toString();
	}

	@Override
	public String generateTargetPython() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("if (" + expression + ") :\n");
		for (Command cmd: trueList) {
			str.append("					" + cmd.generateTargetPython());
		}
		if(!falseList.isEmpty()) {
			str.append("		else :\n");
			for (Command cmd: falseList) {
				str.append("					" + cmd.generateTargetPython());
			}
		}
		return str.toString();
	}
	
}
