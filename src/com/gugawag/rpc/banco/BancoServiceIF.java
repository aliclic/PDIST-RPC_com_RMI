package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;

    int quantidadeContas() throws RemoteException;

    String adicionarConta(String numero, double saldo) throws RemoteException;

    Conta pesquisarConta(String numero) throws RemoteException;

    String removerConta(String numero) throws RemoteException;

    List<Conta> listarContas() throws RemoteException;
}
