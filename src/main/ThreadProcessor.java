package main;

public class ThreadProcessor extends Thread {

	String nome;
	int distanciaAtual;
	int maxDistancia;
	
	public ThreadProcessor(String n, int md) {
		nome = n;
		distanciaAtual = 0;
		maxDistancia = md;
	}
	
	@Override
	public void run() {
		
		System.out.println(nome + " " + maxDistancia);
	}
}
