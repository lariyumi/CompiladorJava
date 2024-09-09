package io.compiler.core.ast;

public class ExpressionCommand {
	
	private String expression;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public ExpressionCommand(String expression) {
		super();
		this.expression = expression;
	}

	public ExpressionCommand() {
		super();
	}

}
