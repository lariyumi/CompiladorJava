package io.compiler.core.ast;

import java.util.List;

public class DoWhileCommand extends Command {
	
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

	public DoWhileCommand(String expression, List<Command> list) {
		super();
		this.expression = expression;
		this.list = list;
	}

	public DoWhileCommand() {
		super();
	}

	@Override
	public String generateTargetJava() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("do {\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetJava());
		}
		str.append("		} while (" + expression + ");\n");
		return str.toString();
	}

	@Override
	public String generateTargetC() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("do {\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetC());
		}
		str.append("		} while (" + expression + ");\n");
		return str.toString();
	}

	@Override
	public String generateTargetPython() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (True):\n");
		for (Command cmd: list) {
			str.append("			" + cmd.generateTargetPython());
		}
		str.append("			if (" + expression + "):\n");
		str.append("				break\n");
		return str.toString();
	}

}
