package io.compiler.core.ast;

public class WriteCommand extends Command {
	
	private String content;

	@Override
	public String generateTargetJava() {
		// TODO Auto-generated method stub
		return "System.out.println(" + content + ");\n";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WriteCommand(String content) {
		super();
		this.content = content;
	}

	public WriteCommand() {
		super();
	}

	@Override
	public String generateTargetC() {
		// TODO Auto-generated method stub
		if (content.indexOf('"') == -1) {
			return "printf(\"%f\", &"+ content + ");\n";
		} else {
			return "printf(" + content + ");\n";
		}
	}
	
	public String generateTargetPython() {
		// TODO Auto-generated method stub
		return "print(" + content + ")\n";
	}

}
