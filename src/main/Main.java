package main;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		int minGrilos = 2;
		int maxGrilos = 5;
		
		// Pega um número aleatório entre minGrilos e maxGrilos para determinar a quantidade de participantes
		Random rand = new Random();
		int nGrilos = rand.nextInt((maxGrilos - minGrilos) + 1) + minGrilos;
		
		int maxDistancia = 100;
		
		// Cria as threads
		ThreadProcessor threads[] = new ThreadProcessor[nGrilos];
		
		for(int i = 0; i < nGrilos; i++) {
			String nomeGrilo = "Grilo_0" + (i + 1);
			threads[i] = new ThreadProcessor(nomeGrilo, maxDistancia);
			threads[i].start();
		}
	}

}
