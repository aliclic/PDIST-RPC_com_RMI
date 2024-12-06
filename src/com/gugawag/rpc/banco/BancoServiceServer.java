package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return contas.stream()
                .filter(c -> c.getNumero().equals(conta))
                .findFirst()
                .map(Conta::getSaldo)
                .orElse(0.0);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public String adicionarConta(String numero, double saldo) throws RemoteException {
        if (contas.stream().anyMatch(c -> c.getNumero().equals(numero))) {
            return "Conta já existente!";
        }
        contas.add(new Conta(numero, saldo));
        return "Conta adicionada com sucesso!";
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        return contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String removerConta(String numero) throws RemoteException {
        Conta conta = contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
        if (conta != null) {
            contas.remove(conta);
            return "Conta removida com sucesso!";
        }
        return "Conta não encontrada!";
    }

    @Override
    public List<Conta> listarContas() throws RemoteException {
        return contas;
    }


}
