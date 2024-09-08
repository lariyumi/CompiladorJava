package io.compiler.main;

import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;

import io.compiler.core.GrammarLexer;
import io.compiler.core.GrammarParser;
import io.compiler.core.ast.Program;

public class MainClass {
	public static void main(String[] args) {
		try {
			GrammarLexer lexer;
			GrammarParser parser;
			
			//Os três comenados a seguir são linhas que o antlr exige
			//cria o analisador léxico a partir da leitura de um arquivo
			lexer = new GrammarLexer(CharStreams.fromFileName("program.in"));
			
			//a partir do analisador léxico, obtenho um fluxo de tokens
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			//Crio o parser a partir do tokenStream
			parser = new GrammarParser(tokenStream);
			
			System.out.println("Compilador");
			parser.programa();
			System.out.println("Compilado com sucesso");


			//geração do código do programa
			Program program = parser.getProgram();
			
			try {
				File f = new File(program.getName() + ".java");
				FileWriter fr = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fr);
				pr.println(program.generateTarget());
				pr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			System.out.println(program.generateTarget());
		}
		catch(Exception ex) {
			System.err.println("Erro: " + ex.getMessage());
			//ex.printStackTrace();
		}
	}
}
