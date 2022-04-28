import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) throws FileNotFoundException {
        final int TAMANHO = 3;
        File ficheiro = new File("funcionarios.txt");
        String[] funcionarios = new String[TAMANHO];
        int[] vencimentos = new int[TAMANHO];
        int nElems = TAMANHO;
        int somaSalario=0;
        try {
            Formatter output = new Formatter(new File("funcionarios.txt"));
            Scanner ler = new Scanner(System.in);
            for (int i = 0; i < nElems; i++) {
                System.out.println("Qual o nome?");
                funcionarios[i] = ler.next();
                System.out.println("Qual o vencimento?");
                vencimentos[i] = ler.nextInt();
                String textoOutput = "Funcionário: " + funcionarios[i] + " - Vencimento: " + vencimentos[i] + ";\n";
                output.format(textoOutput);
                somaSalario=somaSalario+vencimentos[i];
            }
            output.close();
        }catch (Exception e){}
        Scanner lerficheiro=new Scanner(ficheiro);
        while (lerficheiro.hasNext()){
            System.out.println(lerficheiro.nextLine());
        }
        int media=somaSalario/nElems;
        System.out.println("\nOs funcionários que recebem acima da média");
        for (int i = 0; i < nElems; i++) {
            if (vencimentos[i]>media){
                System.out.println(funcionarios[i]);
            }
        }

        System.out.println(somaSalario);
    }
}

