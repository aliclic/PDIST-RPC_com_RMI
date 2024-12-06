package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        if (args.length == 0) {
            System.out.println("Por favor, forneça o IP do servidor como argumento.");
            System.exit(1);
        }

        String servidorIP = args[0]; // Primeiro argumento é o IP do servidor
        int porta = 1099; // Porta padrão do RMI Registry
        Registry registry = LocateRegistry.getRegistry(servidorIP, porta);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da nova conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    System.out.println(banco.adicionarConta(numero, saldo));
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta para pesquisar:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    if (conta != null) {
                        System.out.println(conta);
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta para remover:");
                    String numero = entrada.next();
                    System.out.println(banco.removerConta(numero));
                    break;
                }
                case 6: {
                    System.out.println("Listando todas as contas:");
                    banco.listarContas().forEach(System.out::println);
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI ===");
        System.out.println("Usuário: ALIC VICTOR SANTOS DE ANDRADE");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("6 - Listar todas as contas");
        System.out.println("9 - Sair");
    }
}
