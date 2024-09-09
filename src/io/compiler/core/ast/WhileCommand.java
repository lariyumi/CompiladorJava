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
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (" + expression + ") {\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTarget());
		}
		str.append("		}\n");
		return str.toString();
	}
	
}
