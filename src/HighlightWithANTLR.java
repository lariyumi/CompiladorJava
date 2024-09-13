import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import io.compiler.core.GrammarLexer;
import io.compiler.core.GrammarParser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HighlightWithANTLR {
    public static final String RESET = "\033[0m";
    public static final String MAGENTA = "\033[1;35m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String LIGHTBLUE = "\033[1;34m";
    
    public static void main(String[] args) throws Exception {
        // Carregar o arquivo com o código de entrada "programa.in"
        String inputFile = "program.in";
        String inputText = new String(Files.readAllBytes(Paths.get(inputFile)));
        
        // Criação do CharStream a partir do arquivo
        CharStream input = CharStreams.fromString(inputText);
        GrammarLexer lexer = new GrammarLexer(input);

        // Tokenize o código
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        parser.programa(); // parseando o programa principal

        // Iterar sobre os tokens e aplicar o destaque conforme o tipo
        for (Token token : tokens.getTokens()) {
            String text = token.getText();
            int type = token.getType();
            
            // Dependendo do tipo de token, aplicar uma cor diferente
            switch (type) {
                case GrammarLexer.ID:
                    System.out.print(YELLOW + text + RESET);
                    break;
                case GrammarLexer.TEXTO:
                    System.out.print(GREEN + text + RESET);
                    break;
                case GrammarLexer.NUM:
                    System.out.print(MAGENTA + text + RESET);
                    break;
                case GrammarLexer.AP:
                case GrammarLexer.FP:
                    System.out.print(RED + text + RESET);
                    break;
                case GrammarLexer.OP:
                case GrammarLexer.OP_REL:
                case GrammarLexer.OP_AT:
                    System.out.print(LIGHTBLUE + text + RESET);
                    break;
                default:
                	System.out.print(RESET + text + RESET);
                	if(text.equals("entao") || text.equals("enquanto") || text.equals("senao") || text.equals("declare") || text.equals("programa"))
                    	System.out.print(" ");
            }
            

            switch (text) {
                case ";":
                case "fimse":
                case "meu programa":
                case "fim":
                case "fimwhile":
                    System.out.println();
                    break;
            }
        }
    }
}