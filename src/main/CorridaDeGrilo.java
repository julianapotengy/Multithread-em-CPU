package main;

import java.util.Random;

public class CorridaDeGrilo 
{

	int minGrilos = 2;
	int maxGrilos = 5;
	
	// Pega um número aleatório entre minGrilos e maxGrilos para determinar a quantidade de participantes
	Random rand = new Random();
	int nGrilos = rand.nextInt((maxGrilos - minGrilos) + 1) + minGrilos;
	
	int time1[] = new int[Math.round((nGrilos + 1) / 2)];
	int time2[] = new int[nGrilos - time1.length];
	
	int maxDistancia = 100;
	int nPosicoes = 0;
	
	public CorridaDeGrilo() 
	{
		
		// Divide os times
		for(int i = 0; i < nGrilos; i++)
		{
			if(i < time1.length)
			{
				time1[i] = i;
				//System.out.println("Time 1: " + time1[i]);
			}
			else 
			{
				time2[i - time1.length] = i;
				//System.out.println("Time 2: " + time2[i - time1.length]);
			}
		}
		
		// Cria as threads
		ThreadProcessor threads[] = new ThreadProcessor[nGrilos];
		
		for(int i = 0; i < nGrilos; i++) {
			String nomeGrilo = "Grilo_0" + (i + 1);
			threads[i] = new ThreadProcessor(nomeGrilo, i, maxDistancia, this);
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
		
		int qntPulosTime = 0;
		int qntDistanciaTime = 0;
		
		for(int i = 0; i < time1.length; i++)
		{
			qntPulosTime += threads[i].getQntPulos();
			qntDistanciaTime += threads[i].getDistanciaAtual();
		}
		
		System.out.println("Time 1: Total de Pulos: " + qntPulosTime + " - Distância Percorrida: " + qntDistanciaTime);
		
		qntPulosTime = 0;
		qntDistanciaTime = 0;
		
		for(int i = 0; i < time2.length; i++)
		{
			qntPulosTime += threads[time2[i]].getQntPulos();
			qntDistanciaTime += threads[time2[i]].getDistanciaAtual();
		}
		
		System.out.println("Time 2: Total de Pulos: " + qntPulosTime + " - Distância Percorrida: " + qntDistanciaTime);
		
		for (int i = 0; i < nGrilos; i++) 
		{
			if(threads[i].posicao == 1) 
			{
				for (int j = 0; j < nGrilos; j++) 
				{
					if (time1[j] == threads[i].id) 
					{
						System.out.println("Time 1 foi o vencedor");
						break;
					} else if(time2[j] == threads[i].id) 
					{
						System.out.println("Time 2 foi o vencedor");
						break;
					}
				}
			}
		}
	}
	
	public int getPosicoes() 
	{
		nPosicoes++;
		return nPosicoes;
	}
	
	
	/*
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
	}*/
}
