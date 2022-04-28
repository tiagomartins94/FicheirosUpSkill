import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Exercicio1Autonomo{
    public static void main(String[] args) throws FileNotFoundException
    {
        String[] nomes = new String[50];
        int[] salarios = new int[50];
        int index = 0;

        Scanner sc = new Scanner(System.in);

        int esc;

        do
        {
            System.out.println("Empresa 0.0.1");
            System.out.println("[1]-Carregar ficheiro");
            System.out.println("[2]-Inserir novo funcionario");
            System.out.println("[3]-Listar todos funcionarios");
            System.out.println("[4]-Atualizar vencimento");
            System.out.println("[5]-Eliminar funcionario");
            System.out.println("[6]-Listar todos funcionarios ord");
            System.out.println("[7]-Guardar ficheiro");
            System.out.println("[8]-Sair");
            line();
            esc = sc.nextInt();

            switch (esc)
            {
                case 1:
                    if (index >= 50)
                    {
                        System.out.println("CAPACIDADE NAO PODE EXCEDER 50.");
                    }
                    else
                    {
                        index = loadFile(nomes, salarios, index);
                    }
                    break;
                case 2:
                    index = newEmployee(nomes,salarios,index);
                    break;
                case 3:
                    listEmployees(nomes,salarios,index,false);
                    break;
                case 4:
                    updateWage(nomes,salarios,index);
                    break;
                case 5:
                    index = deleteEmployee(nomes,salarios,index);
                    break;
                case 6:
                    listEmployeesSorted(nomes,salarios,index);
                    break;
                case 7:
                    saveFile(nomes,salarios,index);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Operaçao nao existe.");
            }

        }while(esc!=8);

    }

    private static int deleteEmployee(String[] n, int[] s, int i)
    {
        listEmployees(n,s,i,true);
        int id = validateID(i);

        for (int j = id; j < i-1; j++)
        {
            n[j] = n[j + 1];
            s[j] = s[j + 1];
        }

        System.out.println("Utilizador apagado com sucesso.");
        anyKey();
        line();
        i--;
        return i;
    }


    private static void updateWage(String[] n, int[] s, int i)
    {
        listEmployees(n,s,i,true);
        int e = validateID(i);
        int v = validateWage();

        s[e] = v;

        System.out.println("Vencimento de "+n[e]+" atualizado para "+v+"€");
        anyKey();
        line();
    }

    private static void listEmployeesSorted(String[] n, int[] s, int i)
    {
        String[] nClone = n.clone();
        int[] sClone = s.clone();

        String x;
        int sal;

        for(int j=0;j<i-1;j++)
        {
            for (int k = j + 1; k < i; k++)
            {
                if (nClone[j].compareTo(nClone[k]) > 0)
                {
                    x = nClone[j];
                    nClone[j] = nClone[k];
                    nClone[k] = x;

                    sal = sClone[j];
                    sClone[j] = sClone[k];
                    sClone[k] = sal;
                }
            }
        }

        listEmployees(nClone,sClone,i,false);
    }

    private static void listEmployees(String[] n, int[] s, int i, boolean printID)
    {
        line();
        if(i == 0)
        {
            System.out.println("Nao existem funcionarios.");
            anyKey();
        }
        else
        {
            for (int x = 0; x < i; x++)
            {
                if (printID)
                {
                    System.out.println("[" + x + "] Func: " + n[x] + " - " + s[x]);
                } else
                {
                    System.out.println("Func: " + n[x] + " - " + s[x]);
                }
            }
        }
        anyKey();
        line();
    }

    private static int newEmployee(String[] n, int[] s, int i)
    {
        line();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do funcionario: ");
        String nome = sc.nextLine();


        int venc;
        do{
            System.out.print("Vencimento: ");
            venc = sc.nextInt();
        }while (venc <= 0);

        n[i] = nome;
        s[i] = venc;
        i++;

        System.out.println("Funcionario adicionado com sucesso.");
        anyKey();
        line();
        return i;
    }

    private static void line()
    {
        System.out.println("---------------------------------------");
    }


    private static void saveFile(String[] n, int[] s, int i) throws FileNotFoundException
    {
        Formatter f = new Formatter("at1.txt");

        for(int j = 0; j < i-1; j++)
        {
            f.format("%s:%d%n",n[j],s[j]);
        }

        f.format("%s:%d",n[i-1],s[i-1]);

        System.out.println("Ficheiro gravado com sucesso.");
        anyKey();
        f.close();
    }

    private static int loadFile(String[] n, int[] s, int i) throws FileNotFoundException
    {
        File f = new File("at1.txt");

        if (!f.exists())
        {
            System.out.println("Dados dos funcionarios nao existem.");
            anyKey();
            return i;
        }

        String[] nrep = new String[n.length];
        int ncount = 0;

        Scanner sc = new Scanner(new File("at1.txt"));

        while(sc.hasNextLine() && i < 50)
        {
            String[] ln = sc.nextLine().split(":");

            if (findEmployee(n,i,ln[0]) == -1)
            {
                n[i] = ln[0];
                s[i] = Integer.parseInt(ln[1]);
                i++;
            }
            else
            {
                nrep[ncount] = ln[0];
                ncount++;
            }
        }

        if (ncount != 0)
        {
            System.out.println("Nao carregou os seguintes funcionarios: ");
            for (int k = 0; k < ncount; k++)
            {
                System.out.println(nrep[k]);
            }
        }
        System.out.println("Dados carregados");
        anyKey();
        return i;
    }

    private static int findEmployee(String[] n, int i, String value)
    {
        for(int j = 0; j < i; j++)
        {
            if (value.equals(n[j])) return j;
        }

        return -1;
    }

    static Scanner val = new Scanner(System.in);
    private static int validateID(int i)
    {
        System.out.print("Escreva o ID do funcionario: ");
        int e;
        do
        {
            e = val.nextInt();
        } while (e < 0 || e >= i);

        return e;
    }

    private static int validateWage()
    {
        int v;
        System.out.print("Escreva o novo vencimento: ");
        do
        {
            v = val.nextInt();
        } while (v <= 0);

        return v;
    }

    private static void anyKey()
    {
        System.out.println("Prima enter para continuar.");
        val.nextLine();
    }
}
