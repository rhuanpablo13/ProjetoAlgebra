package algebra;

public class Utils {

	
	/**
	 * Retorna verdadeiro se a quantidade de linhas de m1 
	 * for igual a quantidade de colunas de m2
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static boolean isValido(double[][] m1, double[][]m2) {
            return getQuantidadeColunas(m1) == getQuantidadeLinhas(m2);
	}
	
	
	/**
	 * Recupera a quantidade de linhas de uma matriz
	 * @param m
	 * @return
	 */
	public static int getQuantidadeLinhas(double [][]m) {
            return m.length;
	}

	
	/**
	 * Recupera a quantidade colunas de uma matriz
	 * @param m
	 * @return
	 */
	public static int getQuantidadeColunas(double [][]m) {
            return m[0].length;
	}
	
	
	
	/**
	 * Recupera uma linha espec�fica de uma matriz
	 * @param m
	 * @param linha
	 * @return
	 */
	public static double[] getLinha(double [][]m, int linha) {
		double [] l = new double [getQuantidadeColunas(m)];
                System.arraycopy(m[linha], 0, l, 0, l.length);		
		return l;
	}

	
	/**
	 * Recupera uma coluna espec�fica de uma matriz
	 * @param m
	 * @param coluna
	 * @return
	 */
	public static double[] getColuna(double [][]m, int coluna) {
		double [] l = new double [getQuantidadeLinhas(m)];
		for (int i = 0; i < l.length; i++) {
			l[i] = m[i][coluna];
		}
		return l;
	}
        
        
        
        
        
        /*********** TRATAMENTO DOS INPUTS NA TELA *******************/
        
        
        public static void toMatriz(String m) {
            
        }
        
        
        /**
         * Verifica se a matriz informada na tela pelo usuário, é uma matriz quadrada
         * @param m
         * @return 
         */
        public static boolean isMatrizQuadrada(String m) {      
            char[] a = m.toCharArray();
            int cont = 0;
            int primeiraLinha = 0;
            int linhaAtual = 0;
            int linhas = 0;


            boolean flag = false;
            for (int i = 0; i < a.length; i++) {

                if (a[i] != '\n' && a[i] != ' ') {                
                    flag = true; // esta lendo uma linha com valores                
                }

                // chegou ao final da linha
                if (a[i] == '\n') {
                    if (flag) {
                        flag = false;
                        linhas++;
                    }
                } else {
                    if (i == a.length -1 && flag) {
                        linhas++;
                    }
                }
            }

            if (linhas == 0) {
                return false;
            }

            flag = false;
            int colunas = 0;
            for (int i = 0; i < a.length; i++) {

                // conta somente os números
                if (a[i] != ' ' && a[i] != '\n') {
                    cont++;
                    colunas++;
                    flag = true;
                } else {
                    if (i == a.length-1 && flag && a[i] != ' ') {
                        colunas++;
                    }
                }


                // chegou ao final da linha
                if (a[i] == '\n') {

                    // se em algum momento uma linha tiver menos ou mais colunas
                    // que o total de linhas, então não é quadrada
                    if (colunas != linhas) {
                        return false;
                    }
                    colunas = 0;


                    //linha que está sendo olhada agora
                    linhaAtual = cont;
                    if (primeiraLinha == 0) {
                        primeiraLinha = linhaAtual;
                    }

                    if (linhaAtual != primeiraLinha) {
                        return false;
                    }


                    linhaAtual = 0;
                    cont = 0;

                } else {
                    if (i == a.length-1 && flag) {
                        if (colunas != linhas) {
                            return false;
                        }                        
                    }
                }           
            }
            return true;
        }
        

}
