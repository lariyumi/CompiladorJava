grammar Grammar;

@header {
	import java.util.ArrayList;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
	import java.util.Stack;
	import java.util.List;
	import java.util.Map.Entry;
}


@members {
	private HashMap<String, Var> symbolTable = new HashMap<String, Var>();
	private ArrayList<Var> currentDecl = new ArrayList<Var>();
	private Types currentType;
	private Types leftType = null, rightType = null;
	private Program program = new Program();
	private String strExpr;
	private Stack<ExpressionCommand> expressionStack = new Stack<ExpressionCommand>();
	private Var atribVar;
	private String atribuicao;
	private String op;
	private CommandAtrib comandoAtribuicao;
	
	//Stack para o comando if
	private Stack<IfCommand> ifCommandStack = new Stack<IfCommand>();
	
	//Stack para o comando while
	private Stack<WhileCommand> whileCommandStack = new Stack<WhileCommand>();
	
	//Stack para o comando do while
	private Stack<DoWhileCommand> doWhileCommandStack = new Stack<DoWhileCommand>();
	
	//Foi criada uma pilha de listas de comando
	private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();

	
	public void updateType() { 
		for(Var v: currentDecl){
			v.setType(currentType);
			symbolTable.put(v.getId(), v);
		}
	}
		
	public void exibirVar() {
		for(String id: symbolTable.keySet()){
			System.out.println(symbolTable.get(id));
		}
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public boolean isDeclared(String id) {
		return symbolTable.get(id) != null;
	}
	
	public String calcular(String expr) {
		int countOperations = 0;
		for (int i = 0; i < expr.length(); i++) {
			if((expr.charAt(i) == '+') || (expr.charAt(i) == '-') || (expr.charAt(i) == '/') || (expr.charAt(i) == '*')) {
				countOperations++;
			}
		}
		
		String[] parts = {};
		float value = 0;
		
		for(int i = 0; i < countOperations; i++) {
			if ((expr.indexOf('/') < expr.indexOf('*')) && (expr.indexOf('/') != -1) || ((expr.indexOf('*') == -1 && expr.indexOf('/') != -1))) {
				parts = expr.split("\\/");
				String number1 = "";
				String number2 = "";
				for (int j = 1; j <= parts[0].length(); j++ ) {
					if((parts[0].charAt(parts[0].length() - j) == '+') || (parts[0].charAt(parts[0].length() - j) == '-') || (parts[0].charAt(parts[0].length() - j) == '/') || (parts[0].charAt(parts[0].length() - j) == '*')) {
						break;
					}
					number1 += parts[0].charAt(parts[0].length() - j);
				}
				number1 = new StringBuilder(number1).reverse().toString();
				for (int j = 0; j < parts[1].length(); j++ ) {
					if((parts[1].charAt(j) == '+') || (parts[1].charAt(j) == '-') || (parts[1].charAt(j) == '/') || (parts[1].charAt(j) == '*')) {
						break;
					}
					number2 += parts[1].charAt(j);
				}
				value = Float.valueOf(number1) / Float.valueOf(number2);
				//precisa tirar da expr os números utilizados, substituindo pelo valor dado e o operador;
				expr = expr.replaceFirst(number1, "" + value);
				expr = expr.replaceFirst(number2, "");
				expr = expr.replaceFirst("\\/", "");
			} else if (((expr.indexOf('/') > expr.indexOf('*')) && (expr.indexOf('*') != -1)) || ((expr.indexOf('/') == -1 && expr.indexOf('*') != -1))) {
				parts = expr.split("\\*");
				String number1 = "";
				String number2 = "";
				for (int j = 1; j <= parts[0].length(); j++ ) {
					if((parts[0].charAt(parts[0].length() - j) == '+') || (parts[0].charAt(parts[0].length() - j) == '-') || (parts[0].charAt(parts[0].length() - j) == '/') || (parts[0].charAt(parts[0].length() - j) == '*')) {
						break;
					}
					number1 += parts[0].charAt(parts[0].length() - j);
				}
				number1 = new StringBuilder(number1).reverse().toString();
				for (int j = 0; j < parts[1].length(); j++ ) {
					if((parts[1].charAt(j) == '+') || (parts[1].charAt(j) == '-') || (parts[1].charAt(j) == '/') || (parts[1].charAt(j) == '*')) {
						break;
					}
					number2 += parts[1].charAt(j);
				}
				value = Float.valueOf(number1) * Float.valueOf(number2);
				//precisa tirar da expr os números utilizados, substituindo pelo valor dado e o operador;
				expr = expr.replaceFirst(number1, "" + value);
				expr = expr.replaceFirst(number2, "");
				expr = expr.replaceFirst("\\*", "");
			} else if ((expr.indexOf('+') < expr.indexOf('-')) && (expr.indexOf('+') != -1)  || ((expr.indexOf('-') == -1 && expr.indexOf('+') != -1))) {
				parts = expr.split("\\+");
				String number1 = "";
				String number2 = "";
				for (int j = 1; j <= parts[0].length(); j++ ) {
					if((parts[0].charAt(parts[0].length() - j) == '+') || (parts[0].charAt(parts[0].length() - j) == '-') || (parts[0].charAt(parts[0].length() - j) == '/') || (parts[0].charAt(parts[0].length() - j) == '*')) {
						break;
					}
					number1 += parts[0].charAt(parts[0].length() - j);
				}
				number1 = new StringBuilder(number1).reverse().toString();
				for (int j = 0; j < parts[1].length(); j++ ) {
					if((parts[1].charAt(j) == '+') || (parts[1].charAt(j) == '-') || (parts[1].charAt(j) == '/') || (parts[1].charAt(j) == '*')) {
						break;
					}
					number2 += parts[1].charAt(j);
				}
				value = Float.valueOf(number1) + Float.valueOf(number2);
				//precisa tirar da expr os números utilizados, substituindo pelo valor dado e o operador;
				expr = expr.replaceFirst(number1, "" + value);
				expr = expr.replaceFirst(number2, "");
				expr = expr.replaceFirst("\\+", "");
			} else if ((expr.indexOf('+') > expr.indexOf('-')) && (expr.indexOf('-') != -1) || ((expr.indexOf('+') == -1 && expr.indexOf('-') != -1))) {
				parts = expr.split("\\-");
				String number1 = "";
				String number2 = "";
				for (int j = 1; j <= parts[0].length(); j++ ) {
					if((parts[0].charAt(parts[0].length() - j) == '+') || (parts[0].charAt(parts[0].length() - j) == '-') || (parts[0].charAt(parts[0].length() - j) == '/') || (parts[0].charAt(parts[0].length() - j) == '*')) {
						break;
					}
					number1 += parts[0].charAt(parts[0].length() - j);
				}
				number1 = new StringBuilder(number1).reverse().toString();
				for (int j = 0; j < parts[1].length(); j++ ) {
					if((parts[1].charAt(j) == '+') || (parts[1].charAt(j) == '-') || (parts[1].charAt(j) == '/') || (parts[1].charAt(j) == '*')) {
						break;
					}
					number2 += parts[1].charAt(j);
				}
				value = Float.valueOf(number1) - Float.valueOf(number2);
				//precisa tirar da expr os números utilizados, substituindo pelo valor dado e o operador;
				expr = expr.replaceFirst(number1, "" + value);
				expr = expr.replaceFirst(number2, "");
				if (expr.indexOf('-') == 0){
					expr = expr.replaceFirst("(?s)(.*)" + "-", "$1"+ "");
				} else {
					expr = expr.replaceFirst("-", "");
				}
			}
		}
		return expr;
	}
	
}

programa	:	'programa' ID 	{ 
									program.setName(_input.LT(-1).getText());
									stack.push(new ArrayList<Command>());
								}
				declaravar+
				'inicio'
				comando+
				'fim'
				'fimprog'
				
				{
					try { 
					    for (Entry<String, Var> entry: symbolTable.entrySet()) {
					    	boolean isUsed = entry.getValue().isInitialized();
					    	if (isUsed == false) {
					    		throw new SemanticException("Variável não declarada, mas não utilizada: " + entry.getValue().getId());
					    	}
					    }
					} catch (RuntimeException e) { 
					    System.out.println("Aviso: " + e); 
					} 
					program.setSymbolTable(symbolTable);
					program.setCommandList(stack.pop());
				}
			;
			
declaravar	:	'declare' { currentDecl.clear(); }
				ID { currentDecl.add(new Var(_input.LT(-1).getText())); }
				( VIRG ID { currentDecl.add(new Var(_input.LT(-1).getText())); } )* 
				DP 
				( 'number' { currentType = Types.NUMBER; } 
				| 'text' { currentType = Types.TEXT; } ) 
				{ updateType(); }
				PV
			;
			
comando		:	cmdAtrib
			|	cmdLeitura
			|	cmdEscrita
			|	cmdIf
			|	cmdWhile
			|	cmdDoWhile
			;
			
cmdIf		:	'se'	{ 
							stack.push(new ArrayList<Command>()); 
							ifCommandStack.push(new IfCommand());
							expressionStack.push(new ExpressionCommand());
							strExpr = "";
						}
				AP 
				expr 
				OP_REL { strExpr += _input.LT(-1).getText();  } 
				expr 
				FP 	{ 	
						expressionStack.peek().setExpression(strExpr); 
						strExpr = "";
						ifCommandStack.peek().setExpression(expressionStack.pop().getExpression());
					}
				'entao' 
				comando+	{ 
								ifCommandStack.peek().setTrueList(stack.pop());  
							}
				('senao'	{
								stack.push(new ArrayList<Command>());
							}
				comando+	{
								ifCommandStack.peek().setFalseList(stack.pop());
							}
				)?
				'fimse'	{
							stack.peek().add(ifCommandStack.pop());
						}
			;
			
cmdWhile	:	'enquanto'	{
								stack.push(new ArrayList<Command>());
								whileCommandStack.push(new WhileCommand());
								expressionStack.push(new ExpressionCommand());
								strExpr = "";
							}
				AP 
				expr 
				OP_REL { strExpr += _input.LT(-1).getText(); }
				expr 
				FP	{ 	
						expressionStack.peek().setExpression(strExpr); 
						strExpr = "";
						whileCommandStack.peek().setExpression(expressionStack.pop().getExpression());
					}
				comando+	{ 
								whileCommandStack.peek().setList(stack.pop());  
							}
				'fimwhile'	{
								stack.peek().add(whileCommandStack.pop());
							}
			;
			
cmdDoWhile	:	'faca'	{
							stack.push(new ArrayList<Command>());
							doWhileCommandStack.push(new DoWhileCommand());
							expressionStack.push(new ExpressionCommand());
							strExpr = "";
						}
				comando+	{ 
								doWhileCommandStack.peek().setList(stack.pop());  
							}
				'enquanto'
				AP		{ strExpr = ""; }
				expr
				OP_REL	{ 
							strExpr += _input.LT(-1).getText(); 
						}
				expr	{
							expressionStack.peek().setExpression(strExpr); 
							strExpr = "";
						}
				FP 	{ 	
						doWhileCommandStack.peek().setExpression(expressionStack.pop().getExpression());
						stack.peek().add(doWhileCommandStack.pop());
					}
			;
		
cmdAtrib	:	ID { if (!isDeclared(_input.LT(-1).getText())) {
						throw new SemanticException("Variável não declarada: " + _input.LT(-1).getText());
					} 
					symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
					leftType = symbolTable.get(_input.LT(-1).getText()).getType();
					op = "";
					atribuicao = "";
					atribVar = new Var();
					comandoAtribuicao = new CommandAtrib();
					atribVar.setId(symbolTable.get(_input.LT(-1).getText()).getId());
					atribVar.setType(symbolTable.get(_input.LT(-1).getText()).getType());
					comandoAtribuicao.setVar(atribVar);
				}
				(OP)? 
					{
						if (_input.LT(-1).getText().equals("+") || _input.LT(-1).getText().equals("-") || _input.LT(-1).getText().equals("*") || _input.LT(-1).getText().equals("/")) {
							op += _input.LT(-1).getText();
						}
				 	}
				OP_AT { op += _input.LT(-1).getText(); }
				expr
				PV
				{ 
					//System.out.println("Tipo da expressão do lado esquerdo = " + leftType); 
					//System.out.println("Tipo da expressão do lado direito = " + rightType); 
					if ( leftType.getValue() != rightType.getValue() ) {
						throw new SemanticException("Houve uma incompatibilidade de tipos na atribuição");
					}
					comandoAtribuicao.setOperador(op);
					comandoAtribuicao.setAtrib(atribuicao);;
					stack.peek().add(comandoAtribuicao);
				}
			;
			
cmdLeitura	:	'leia' 
				AP 
				ID { if (!isDeclared(_input.LT(-1).getText())) {
						throw new SemanticException("Variável não declarada: " + _input.LT(-1).getText());
					} 
					symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
					Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
					stack.peek().add(cmdRead);
				}
				FP 
				PV
			;
			
cmdEscrita	:	'escreva' AP 
				( termo  
					{
						Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
						stack.peek().add(cmdWrite);
					} 
				) 
				FP PV	

				{ 
					rightType = null;
					//Temos que zerar o rightType porque senão vai dar errado quando ele entrar no termo 
				}
			;
			
expr		:	termo 	{ 
							strExpr += _input.LT(-1).getText();
							atribuicao += _input.LT(-1).getText();
			 			}
			 	exprl	{
			 				if (atribuicao.indexOf('/') != -1 || atribuicao.indexOf('*') != -1 || atribuicao.indexOf('+') != -1 || atribuicao.indexOf('-') != -1) {
			 					atribuicao = calcular(atribuicao.toString());
			 				}
			 			}
			;
			
termo		:	ID { if (!isDeclared(_input.LT(-1).getText())) {
						throw new SemanticException("Variável não declarada: " + _input.LT(-1).getText());
					} 
					if ( !symbolTable.get(_input.LT(-1).getText()).isInitialized() ) {
						throw new SemanticException("Variável " + _input.LT(-1).getText() + " não possui valor atribuído a ela");
					}
					if (rightType == null) {
						rightType = symbolTable.get(_input.LT(-1).getText()).getType();
						//System.out.println("Encontrei pela 1a vez uma variável = " + rightType);
					}
					else {
						if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() != rightType.getValue()) {
							rightType = symbolTable.get(_input.LT(-1).getText()).getType();
							//System.out.println("Já havia tipo declarado e mudou para = " + rightType);
						}
					}
				}
			|	NUM {	if ( rightType == null) {
							rightType = Types.NUMBER;
							//System.out.println("Encontrei um número pela primeira vez = " + rightType);
						}
						else {
							if ( rightType.getValue() != Types.NUMBER.getValue() ) {
								rightType = Types.NUMBER;
								//System.out.println("Mudei o tipo para NUMBER = " + rightType);
							}
						}
				}
			|	TEXTO {	if ( rightType == null) {
							rightType = Types.TEXT;
							//System.out.println("Encontrei um texto pela primeira vez = " + rightType);
						}
						else {
							if ( rightType.getValue() != Types.TEXT.getValue() ) {
								rightType = Types.TEXT;
								//System.out.println("Mudei o tipo para TEXT = " + rightType);
							}
						}
				}
			;
			
exprl		:	( OP 
					{ 
						strExpr += _input.LT(-1).getText(); 
						atribuicao += _input.LT(-1).getText();
					} 
				termo 
					{ 
						strExpr += _input.LT(-1).getText(); 
						atribuicao += _input.LT(-1).getText();
					}
				)*
			;
			
OP			:	'+'
				| '-'
				| '*'
				| '/'
			;
			
OP_AT		:	'='
			;
		
OP_REL		:	'>' | '<' | '>=' | '<=' | '==' | '!='
			;
			
ID			:	[a-z] ( [a-z] | [A-Z] | [0-9] )*
			;
			
TEXTO		:	'"' ( [a-z] | [A-Z] | [0-9] | ' ' | ',' | '.' | '-' | '!' | '(' | ')' )* '"'
			;
			
NUM			:	[0-9]+ ( '.' [0-9]+ )?
			;

VIRG		:	','
			;
			
PV			:	';'
			;
			
DP			:	':'
			;
			
AP			:	'('
			;
			
FP			:	')'
			;
			
WS			:	( ' ' | '\n' | '\r' | '\t' ) -> skip
			;