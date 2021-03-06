package main;

import java.util.Random;

public class ThreadProcessor extends Thread {

	CorridaDeGrilo corrida;
	
	String nome;
	int distanciaAtual;
	int maxDistancia;
	int qntPulos;
	int posicao;
	int id;
	
	int minDistPulo = 5;
	int maxDistPulo = 20;
	
	public ThreadProcessor(String n, int id, int md, CorridaDeGrilo c) {
		nome = n;
		this.id = id;
		distanciaAtual = 0;
		maxDistancia = md;
		qntPulos = 0;
		corrida = c;
	}
	
	@Override
	public void run() {
		
		while(distanciaAtual < maxDistancia)
		{
			// Pega um n?mero aleat?rio entre minDistPulo e maxDistPulo e soma a distancia atual
			Random rand = new Random();
			int puloAleatorio = rand.nextInt((maxDistPulo - minDistPulo) + 1) + minDistPulo;
			distanciaAtual += puloAleatorio;
			
			qntPulos++;
			
			// Checa se chegou na linha de chegada para enviar os textos
			if(distanciaAtual >= maxDistancia)
			{
				posicao = corrida.getPosicoes();
				System.out.println(nome + " foi o " + posicao + "o colocado com " + qntPulos + " pulos");
			}
			else System.out.println("O " + nome + " pulou " + puloAleatorio + " cm e j? percorreu "+ distanciaAtual + " cm");
		}
	}
	
	public int getQntPulos() 
	{
		return qntPulos;
	}
	
	public int getDistanciaAtual() 
	{
		return distanciaAtual;
	}
}
