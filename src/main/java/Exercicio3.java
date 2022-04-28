import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;

public class Exercicio3 {

    public static void main(String[] args) throws FileNotFoundException  {
        //Definir o tamanho da palavra usando um Scanner
        System.out.println("Quantas letras deve ter pelo menos cada palavra?");
        Scanner ler=new Scanner(System.in);
        int numerosPalavras=ler.nextInt();

        //criar um Scanner de Ficheiro (ficheiroEntrada)
        Scanner ficheiroEntrada = new Scanner(new File("Texto.txt"));

        //criar um Formatter de Ficheiro (ficheiroSaída)
        Formatter ficheiroSaída = new Formatter(new File("Novo.txt"));

        //Texto a ser apresentado no ficheiro OUTPUT
        //Neste caso coloquei um título "Palavras com mais de (número de letras)"
        ficheiroSaída.format("Palavras com mais de " + numerosPalavras + " letras:  \n");


        //Enquanto o ficheiroEntrada tiver uma palavra seguinte adiciona para a String[] linha as palavras separadas por um espaco
        while(ficheiroEntrada.hasNextLine()){
            String [] linha = ficheiroEntrada.nextLine().split(" ");
            //Utilizei um for loop para passar as palavras para o ficheiro "Novo.txt"
            //Faz uma leitura da String [] linha e vai adicionando as palavras ao ficheiro "Novo.txt" até chegar à última palavra
            for (int i = 0; i < linha.length; i++) {
                //Faz uma condição para adicionar palavras com um determinado número de letras para o ficheiro "Novo.txt"
                if (linha[i].length()>=numerosPalavras){
                    ficheiroSaída.format(linha[i]+ "\n");
                }

            }
        }
        //Termina o Formatter
        ficheiroSaída.close();
        //Termina o Scanner
        ficheiroEntrada.close();
    }
}
