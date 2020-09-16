package algebra;

import java.util.Arrays;

public class Operacao {

	
	/************ MULTIPLICA��O ***************/
	
	
	/**
	 * Multiplica��o por n�mero inteiro
	 * @param matriz
	 * @param real
	 * @return
	 */
	public int[][] multiplicacao(int[][] matriz, int inteiro) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = (matriz[i][j] * inteiro);
			}
		}
		return matriz;
	}
	
	
	/**
	 * Multiplica��o por double
	 * @param matriz
	 * @param real
	 * @return
	 */
	public double[][] multiplicacao(int[][] matriz, double real) {
		double[][] aux = new double[matriz.length][matriz.length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				aux[i][j] = (matriz[i][j] * real);
			}
		}
		return aux;
	}
	
	
	/**
	 * Multiplica��o de duas matrizes, por for�a bruta.
	 * @param matriz
	 * @param matriz2
	 * @return
	 */
	public double[][] multiplicacao(double[][] matriz, double[][] matriz2) {
					
		//Para multiplicar as matrizes, pegamos a linha 
		//da matriz 1 e multiplicamos pela coluna da matriz 2		
		double[] linha;   //matriz 1
		double[] coluna;  //matriz 2
		
		double [][] matrizFinal = new double[Utils.getQuantidadeLinhas(matriz)][Utils.getQuantidadeColunas(matriz2)];

		
		//percorre todas as linhas da matriz m1
		for (int j = 0; j < Utils.getQuantidadeLinhas(matriz); j++) {
			
			//recupera a linha n�mero j
			linha = Utils.getLinha(matriz, j);
						
			//percorre todas as colunas da matriz m2
			for (int k = 0; k < Utils.getQuantidadeColunas(matriz2); k++) {
				
				int soma = 0;
				//recupera a coluna n�mero k
				coluna = Utils.getColuna(matriz2, k);
				
				//faz a multiplica��o dos dois arrays (linha * coluna) e faz a soma
				soma += soma(linha, coluna);
				matrizFinal[j][k] = soma;
			}
		}
		return matrizFinal;

	}

		
	
	
	/************ SUBTRA��O ***************/
	
	/**
	 * Subtra��o por n�mero inteiro
	 * @param matriz
	 * @param inteiro
	 * @return
	 */
	public int[][] subtracao(int[][] matriz, int inteiro) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = (matriz[i][j] - inteiro);
			}
		}
		return matriz;
	}


	/**
	 * Subtra��o por n�mero double
	 * @param matriz
	 * @param real
	 * @return
	 */
	public double[][] subtracao(int[][] matriz, double real) {
		double[][] aux = new double[matriz.length][matriz.length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				aux[i][j] = (matriz[i][j] - real);
			}
		}
		return aux;
	}
	
	
	/**
	 * Subtra��o de duas matrizes
	 * @param matriz
	 * @param matriz2
	 * @return
	 */
	public double[][] subtracao(double[][] matriz, double[][] matriz2) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = (matriz[i][j] - matriz2[i][j]);
			}
		}
		return matriz;
	}
	
	
	
	
	/************ SOMA ***************/
	
	/**
	 * Soma de dois arrays de inteiro
	 * @param linha
	 * @param coluna
	 * @return
	 */
	public static int soma(int[] linha, int[] coluna) {
            int soma = 0;
            for (int j = 0; j < coluna.length; j++) {
                soma += (linha[j] * coluna[j]);
            }
            return soma;
	}
	
	/**
	 * Soma de dois arrays de double
	 * @param linha
	 * @param coluna
	 * @return
	 */
	public static double soma(double[] linha, double[] coluna) {
		double soma = 0;
		for (int j = 0; j < coluna.length; j++) {
                    soma += (linha[j] * coluna[j]);
		}
		return soma;
	}
		
	/**
	 * Soma de uma matriz por um valor inteiro
	 * @param matriz
	 * @param inteiro
	 * @return
	 */
	public int[][] soma(int[][] matriz, int inteiro) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                        matriz[i][j] += inteiro;
                }
            }
            return matriz;
	}

	/**
	 * Soma de uma matriz por um valor double
	 * @param matriz
	 * @param real
	 * @return
	 */
	public double[][] soma(int[][] matriz, double real) {
            double[][] aux = new double[matriz.length][matriz.length];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    aux[i][j] = (matriz[i][j] + real);
                }
            }
            return aux;
	}
	
	/**
	 * Soma de duas matrizes de inteiro
	 * @param matriz
	 * @param matriz2
	 * @return
	 */
	public int[][] soma(int[][] matriz, int[][] matriz2) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = (matriz[i][j] + matriz2[i][j]);
                }
            }
            return matriz;
	}
	
	/**
	 * Soma de duas matrizes de double
	 * @param matriz
	 * @param matriz2
	 * @return
	 */
	public double[][] soma(double[][] matriz, double[][] matriz2) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = (matriz[i][j] + matriz2[i][j]);
                }
            }
            return matriz;
	}
	
	
}
