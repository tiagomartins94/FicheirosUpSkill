import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> nomes =new ArrayList<>();
        ArrayList<Integer> notas=new ArrayList<>();
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o seu primeiro nome: ");
        String nome = ler.nextLine();
        int nota;
        int nElems=0;
        int somadasnotas=0;
        nomes.add(nome);
        while (!"fim".equalsIgnoreCase(nome)) {
            System.out.println("Insira a nota do aluno: ");
            nota=ler.nextInt();
            nElems++;
            notas.add(nota);
            somadasnotas+=nota;
            System.out.println("Insira o nome do aluno:");
            ler.nextLine();
            nome=ler.nextLine();
            nomes.add(nome);
        }

        try{
        Formatter output=new Formatter(new File("melhores.txt"));
        double media=(double)somadasnotas/nElems;
        String textoOutput = "Média dos alunos: "+media+"\nAlunos com nota acima da média:\n";
        output.format(textoOutput);
            for (int i = 0; i < nElems; i++) {
                if (notas.get(i)>media){
                textoOutput=nomes.get(i)+":"+notas.get(i)+"\n";
                output.format(textoOutput);
                }
            }
        output.close();}
        catch (Exception e){}

        System.out.println(nElems);
    }
}