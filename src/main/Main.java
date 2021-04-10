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
		
		for (int i = 0; i < threads.length; i++) 
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0 ; i < threads.length; i++)
	    {
	        for(int j = i+1 ; j < threads.length; j++)
	        {
	            if(threads[i].qntPulos > threads[j].qntPulos)
	            {
	                int temp = threads[i].qntPulos;
	                threads[i].qntPulos = threads[j].qntPulos;
	                threads[j].qntPulos = temp;
	                String tempNome = threads[i].nome;
	                threads[i].nome = threads[j].nome;
	                threads[j].nome = tempNome;
	            }
	        }
	    }
		
		System.out.println("");
		System.out.println("Placar:");
		System.out.println("");
		
		for (int i = 0; i < threads.length; i++) 
		{
			int placar = i +1;
			System.out.println("O " + threads[i].nome + " ficou em " + placar + "º lugar com " + threads[i].qntPulos + " pulos");
		}
	}

}
