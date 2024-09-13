package io.compiler.core.ast;

import java.util.List;

public class WhileCommand extends Command {

	private String expression;
	private List<Command> list;
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public List<Command> getList() {
		return list;
	}

	public void setList(List<Command> list) {
		this.list = list;
	}

	public WhileCommand(String expression, List<Command> list) {
		super();
		this.expression = expression;
		this.list = list;
	}

	public WhileCommand() {
		super();
	}

	@Override
	public String generateTargetJava() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String expressionJava = expression.replace("e", " && ");
		expressionJava = expressionJava.replace("ou", " || ");
		str.append("while (" + expressionJava + ") {\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetJava());
		}
		str.append("		}\n");
		return str.toString();
	}

	@Override
	public String generateTargetC() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String expressionC = expression.replace("e", " && ");
		expressionC = expressionC.replace("ou", " || ");
		str.append("while (" + expressionC + ") {\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetC());
		}
		str.append("		}\n");
		return str.toString();
	}
	
	@Override
	public String generateTargetPython() {
		StringBuilder str = new StringBuilder();
		String expressionPython = expression.replace("e", " and ");
		expressionPython = expressionPython.replace("ou", " or ");
		str.append("while (" + expressionPython + "):\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetPython());
		}
		return str.toString();
	}
	
}
