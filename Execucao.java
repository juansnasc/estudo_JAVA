package controleestoquemapa;

import java.util.ArrayList;
import java.util.Scanner;

public class Execucao {

    public static void main(String[] args) {
        int atual;

        ArrayList<Produto> estoque = Execucao.criaEstoque();

        do {
            atual = Execucao.telaPrincipal(estoque);
        } while (atual != 0);
    }

    public static ArrayList<Produto> criaEstoque() {
        ArrayList<Produto> est = new ArrayList<Produto>();
        return est;
    }

    public static void exibeRelatorio(ArrayList<Produto> est) {
        Execucao.menuBase();
        System.out.println("RELATÓRIO DO ESTOQUE");

        if (est.isEmpty()) {
            System.out.println("\nESTOQUE VAZIO!");
        } else {
            for (int i = 0; i < est.size(); i++) {
                System.out.print("\n#" + (i + 1) + est.get(i).toString());
            }
        }
    }

    public static boolean isProdCadastrado(ArrayList<Produto> est, String nome) { //ok
        if (est.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < est.size(); i++) {
                String nm = est.get(i).getNome();
                if (nm.equals(nome)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static int achaPosPorNome(ArrayList<Produto> est, String nome) { //ok
        int i;

        for (i = 0; i < est.size(); i++) {
            String g = est.get(i).getNome();
            if (g.equals(nome)) {
                break;
            }
        }
        return i;
    }

    public static void incluiProduto(ArrayList<Produto> est) {
        char opcao = ' ';
        Scanner s = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("INCLUSÃO DE PRODUTO");
            String naux = validaNome(est);
            float preaux = validaPreco(est);
            int qtdaux = validaQuantidade(est);
            System.out.print("UNIDADE:");
            String unidade = s.nextLine();
            System.out.print("CONFIRMA INCLUSÃO(S/N)? ");
            opcao = s.next().charAt(0);
            if (opcao == 'S' || opcao == 's') {
                Produto novo = new Produto(naux, preaux, qtdaux);
                novo.setUnidade(unidade);
                est.add(novo);
                System.out.println("Produto cadastrado com sucesso!");
                System.out.print("REPETIR OPERAÇÃO(S/N)? ");
                opcao = s.next().charAt(0);
                s.nextLine();
            }
        } while (opcao == 'S' || opcao == 's');
    }

    public static void alteraProduto(ArrayList<Produto> est) {
        char op = 'N';
        int saida = 0;
        String nome = "";
        boolean isIn = false;
        Scanner s = new Scanner(System.in);

        do {
            System.out.print("Nome do produto:");
            nome = s.nextLine();

            isIn = Execucao.verificaProdIn(est, nome);

            if (isIn) {
                saida = Execucao.achaPosPorNome(est, nome);
                String dado = est.get(saida).toString();
                System.out.println(dado);

                System.out.println("ALTERAÇÃO HABILITADA");
                System.out.println("AVISO: Não é possível modificar o campo NOME"
                        + " de um produto,para tal, exclua-o e cadastre novamente");
                System.out.print("Deseja continuar(S/N)? ");
                op = s.nextLine().charAt(0);

                if (op != 'N' && op != 'n') {
                    float preaux = validaPreco(est);
                    int qtdaux = validaQuantidade(est);
                    System.out.print("UNIDADE:");
                    String unidade = s.nextLine();

                    System.out.print("CONFIRMA OPERAÇÃO(S/N)? ");
                    op = s.nextLine().charAt(0);
                    if (op == 'S' || op == 's') {
                        est.get(saida).setPrecoUnit(preaux);
                        est.get(saida).setQtdEstoque(qtdaux);
                        est.get(saida).setUnidade(unidade);
                    }
                }
                System.out.print("REPETIR OPERAÇÃO(S/N)? ");
                op = s.nextLine().charAt(0);
            } else {
                System.out.println("Alteração falhou - Produto não cadastrado "
                        + "no sistema!");
            }
        } while (op != 'N' && op != 'n');
    }

    public static void consultaProduto(ArrayList<Produto> est) {
        int saida;
        String nome;
        char op = 'N';
        boolean isIn = false;
        Scanner s = new Scanner(System.in);

        do {
            System.out.print("\nNome do produto:");
            nome = s.nextLine();
            isIn = Execucao.verificaProdIn(est, nome);

            if (isIn) {
                saida = Execucao.achaPosPorNome(est, nome);
                String dado = est.get(saida).toString();
                System.out.println(dado);
            } else {
                System.out.println("Consulta falhou - Produto não cadastrado "
                        + "no sistema!");
            }
            System.out.print("REPETIR OPERAÇÃO(S/N)? ");
            op = s.nextLine().charAt(0);
        } while (op != 'N' && op != 'n');
    }

    public static void excluiProduto(ArrayList<Produto> est) {
        char op = 'N';
        int saida = 0;
        boolean resp;
        Scanner s = new Scanner(System.in);

        do {
            System.out.print("Nome do produto:");
            String nm = s.nextLine();
            resp = Execucao.verificaProdIn(est, nm);

            if (resp) {
                saida = Execucao.achaPosPorNome(est, nm);
                System.out.print("CONFIRMA EXCLUSÃO(S/N)? ");
                op = s.nextLine().charAt(0);
                if (op == 'S' || op == 's') {
                    est.remove(saida);
                    System.out.println("Produto excluído com sucesso!");
                    System.out.print("REPETIR OPERAÇÃO(S/N)? ");
                    op = s.nextLine().charAt(0);
                }
            } else {
                System.out.println("Exclusão falhou - Produto não consta "
                        + "cadastrado no sistema!");
            }
        } while (op != 'N' && op != 'n');
    }

    public static boolean verificaProdIn(ArrayList<Produto> est, String nome) {
        int saida = 0;
        Scanner s = new Scanner(System.in);

        boolean cadastrado = Execucao.isProdCadastrado(est, nome);
        return cadastrado;
    }

    public static void telaMovProduto(ArrayList<Produto> est) {
        int op;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("MOVIMENTAÇÃO");
            System.out.println("1 - ENTRADA");
            System.out.println("2 - SAÍDA");
            System.out.println("0 - RETORNAR");
            System.out.print("OPCAO:");
            op = scan.nextInt();

            while (op != 0 && op != 1 && op != 2) {
                System.out.println("Favor selecionar uma opção válida");
                System.out.print("OPCAO:");
                op = scan.nextInt();
            }

            switch (op) {
                case 0:
                    break;
                case 1:
                    entradaProduto(est);
                    break;
                case 2:
                    saidaProduto(est);
                    break;
            }
        } while (op != 0);
    }

    public static void reajustaPreco(ArrayList<Produto> est) {
        int posCad;
        char confirmacao = 'N';
        boolean cadastrado = false;
        float valPerc, pAtual, aumento;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("REAJUSTE DE PREÇO");
            System.out.print("PRODUTO:");
            String nome = scan.nextLine();

            cadastrado = isProdCadastrado(est, nome);

            if (cadastrado) {
                valPerc = validaPercAum();
                posCad = achaPosPorNome(est, nome);
                System.out.print("CONFIRMA REAJUSTE(S/N)? ");
                confirmacao = scan.nextLine().charAt(0);
                if (confirmacao == 'S' || confirmacao == 's') {
                    pAtual = est.get(posCad).getPrecoUnit();
                    aumento = (pAtual * valPerc) / 100;
                    est.get(posCad).setPrecoUnit(pAtual + aumento);
                }
            } else {
                System.out.println("Não foi possível reajustar produto, o "
                        + "mesmo não consta cadastrado no sistema");
            }

            System.out.print("REPETIR OPERAÇÃO(S/N)? ");
            confirmacao = scan.nextLine().charAt(0);
        } while (confirmacao != 'N' && confirmacao != 'n');
    }

    public static void entradaProduto(ArrayList<Produto> est) {
        char confirmacao = 'N';
        int posCad, vEntrada = 1;
        boolean cadastrado = false;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("MOVIMENTAÇÃO");
            System.out.println("ENTRADA DE PRODUTO");
            System.out.print("PRODUTO:");
            String nome = scan.nextLine();

            cadastrado = isProdCadastrado(est, nome);

            if (cadastrado) {
                posCad = achaPosPorNome(est, nome);
                System.out.println("QTD ATUAL:" + est.get(posCad).getQtdEstoque());

                do {
                    if (vEntrada <= 0) {
                        System.out.println("ERRO:Valor negativo ou zero "
                                + "encontrado! Tente novamente");
                    }
                    System.out.print("QTD ENTRADA:");
                    vEntrada = scan.nextInt();
                    scan.nextLine();
                } while (vEntrada <= 0);

                System.out.println("QTDE FINAL:"
                        + (est.get(posCad).getQtdEstoque() + vEntrada));
                System.out.print("CONFIRMA ENTRADA(S/N)? ");
                confirmacao = scan.nextLine().charAt(0);

                if (confirmacao == 'S' || confirmacao == 's') {
                    est.get(posCad).setAdicionarQuantidade(vEntrada);
                }
            } else {
                System.out.println("Não foi possível manipular produto, o "
                        + "mesmo não consta cadastrado no sistema");
            }
            System.out.print("REPETIR OPERAÇÃO? ");
            confirmacao = scan.nextLine().charAt(0);
        } while (confirmacao != 'N' && confirmacao != 'n');
    }

    public static void saidaProduto(ArrayList<Produto> est) {
        char confirmacao = 'N';
        int posCad, vSaida = 0;
        boolean cadastrado = false;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("MOVIMENTAÇÃO");
            System.out.println("SAÍDA DE PRODUTO");
            System.out.print("PRODUTO:");
            String nome = scan.nextLine();

            cadastrado = isProdCadastrado(est, nome);

            if (cadastrado) {
                posCad = achaPosPorNome(est, nome);
                System.out.println("QTD ATUAL:" + est.get(posCad).getQtdEstoque());

                do {
                    if(vSaida < 0 || vSaida > est.get(posCad).getQtdEstoque()){
                        System.out.println("ERRO - Valor recebido é negativo ou "
                                + "superior a quantidade disponível em estoque."
                                + "Tente novamente");
                    }
                    System.out.print("QTD SAÍDA:");
                    vSaida = scan.nextInt();
                    scan.nextLine();
                } while (vSaida < 0 || vSaida > est.get(posCad).getQtdEstoque());

                System.out.println("QTDE FINAL:"
                        + (est.get(posCad).getQtdEstoque() - vSaida));
                System.out.print("CONFIRMA SAÍDA(S/N)? ");
                confirmacao = scan.nextLine().charAt(0);

                if (confirmacao == 'S' || confirmacao == 's') {
                    est.get(posCad).setDiminuirQuantidade(vSaida);
                }
            } else {
                System.out.println("Não foi possível manipular produto, o "
                        + "mesmo não consta cadastrado no sistema");
            }
            System.out.print("REPETIR OPERAÇÃO(S/N)? ");
            confirmacao = scan.nextLine().charAt(0);
        } while (confirmacao != 'N' && confirmacao != 'n');
    }

    public static float validaPreco(ArrayList<Produto> est) {
        Scanner s = new Scanner(System.in);
        System.out.print("PREÇO:");
        float preco = s.nextFloat();

        while (preco <= 0) {
            System.out.println("Preço inválido. Tente novamente");
            System.out.print("PREÇO:");
            preco = s.nextFloat();
        }
        return preco;
    }

    public static int validaQuantidade(ArrayList<Produto> est) {
        Scanner s = new Scanner(System.in);
        System.out.print("QUANTIDADE:");
        int qtd = s.nextInt();

        while (qtd < 0) {
            System.out.println("Valor negativo inserido! Tente novamente");
            System.out.print("QUANTIDADE:");
            qtd = s.nextInt();
        }
        return qtd;
    }

    public static String validaNome(ArrayList<Produto> est) {
        Scanner s = new Scanner(System.in);
        System.out.print("NOME:");
        String nomeP = s.nextLine();

        while (Execucao.isProdCadastrado(est, nomeP)) {
            System.out.println("Produto já consta cadastrado no sistema! "
                    + "Insira um produto com nome diferente");
            System.out.print("NOME:");
            nomeP = s.nextLine();
        }
        return nomeP;
    }

    public static float validaPercAum() {
        Scanner s = new Scanner(System.in);
        float valPerc;
        System.out.println("Percentual de aumento:");
        valPerc = s.nextFloat();

        while (valPerc <= 0) {
            System.out.println("Valor inválido! Tente novamente");
            System.out.print("Percentual de aumento:");
            valPerc = s.nextFloat();
        }
        return valPerc;
    }

    public static void menuBase() {
        System.out.println("\n\nEMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA. "
                + "SISTEMA DE CONTROLE DE ESTOQUE");
    }

    public static int telaPrincipal(ArrayList<Produto> est) {
        int op = -1;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - CADASTRO DE PRODUTOS");
            System.out.println("2 - MOVIMENTAÇÃO");
            System.out.println("3 - REAJUSTE DE PREÇOS");
            System.out.println("4 - RELATÓRIOS");
            System.out.println("0 - FINALIZAR");
            System.out.print("OPCAO:");
            op = scan.nextInt();

            while (op != 0 && op != 1 && op != 2 && op != 3 && op != 4) {
                System.out.println("Favor selecionar uma opção válida");
                System.out.print("OPCAO:");
                op = scan.nextInt();
            }

            switch (op) {
                case 0:
                    break;
                case 1:
                    telaCadProd(est);
                    break;
                case 2:
                    telaMovProduto(est);
                    break;
                case 3:
                    reajustaPreco(est);
                    break;
                case 4:
                    exibeRelatorio(est);
                    break;
            }
        } while (op != 0);
        return op;
    }

    public static void telaCadProd(ArrayList<Produto> est) {
        int op;
        Scanner scan = new Scanner(System.in);

        do {
            Execucao.menuBase();
            System.out.println("CADASTRO DE PRODUTOS");
            System.out.println("1 - INCLUSÃO");
            System.out.println("2 - ALTERAÇÃO");
            System.out.println("3 - CONSULTA");
            System.out.println("4 - EXCLUSÃO");
            System.out.println("0 - RETORNAR");
            System.out.print("OPCAO:");
            op = scan.nextInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    Execucao.incluiProduto(est);
                    break;
                case 2:
                    Execucao.alteraProduto(est);
                    break;
                case 3:
                    Execucao.consultaProduto(est);
                    break;
                case 4:
                    Execucao.excluiProduto(est);
                    break;
            }
            System.out.println("\n");
        } while (op != 0);
    }
}
