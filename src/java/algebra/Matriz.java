package algebra;

import org.kohsuke.rngom.digested.Main;

public class Matriz {
	

    /***************************** ADJUNTA ********************************/
    /**********************************************************************/
    /** A matriz adjunta � igual a matriz transposta a matriz cofatora ****/
    /**********************************************************************/
    /**********************************************************************/

    public double[][] getAdjunta(double[][] matriz) {
        return getTransposta(getCofatora(matriz));
    }
	
	
	
	
    /***************************** COFATORA *******************************/
    /**********************************************************************/
    /** A matriz cofatora � igual a matriz original eliminando-se uma 
     * linha L, e uma coluna C */
    /**********************************************************************/
    /**********************************************************************/

    public double[][] getCofatora(double[][] matriz) {

            double[][] cofatora = new double[matriz.length -1][matriz.length -1];		
            double[][] resultado = new double[matriz.length][matriz.length];
            double det = 0;
            double x = 0;

            if (matriz.length == 2)
                    return getCofatoraOrdem2(matriz);

            for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz.length; j++) {
                            cofatora = eliminaLinhaEColuna(matriz, i, j);
                            det = getDeterminante(cofatora);
                            x = Math.pow(-1, (i+1) + (j+1)) * det;
                            resultado[i][j] = x;
                    }
            }
            return resultado;
    }


    public double[][] getCofatoraOrdem2(double[][] matriz) {
            //1  2      6  -3
            //3  6     -2   1

            double aux = matriz[0][0];
            matriz[0][0] = matriz[1][1];
            matriz[1][1] = aux;

            aux = matriz[0][1];
            matriz[0][1] = matriz[1][0]*-1;
            matriz[1][0] = aux*-1;
            return matriz;
    }








    /**
     * Retorna a diagonal principal de uma matriz
     * @param matriz
     * @return
     */
    public double[] getDiagonalPricipal(double[][] matriz) {

            double[] diagonal = new double[matriz.length];
            for (int i=0; i<matriz.length; i++) {
                    for (int j=0; j<matriz.length; j++) {
                            if (i == j) {
                                    diagonal[i] = matriz[i][j];
                            }
                    }
            }
            return diagonal;
    }

    /**
     * Retorna a diagonal secund�ria de uma matriz
     * @param matriz
     * @return
     */
    public double[] getDiagonalSecundaria(double[][] matriz) {

            double[] diagonal = new double[matriz.length];
            for (int i=0; i<matriz.length; i++) {
                    for (int j=0; j<matriz.length; j++) {
                            if(i + j == matriz.length -	1) {
                                    diagonal[i] = matriz[i][j];
                            }
                    }
            }	
            return diagonal;
    }


    /**
     * O tra�o de uma matriz � a soma de seus elementos da diagonal principal
     * @param matriz
     * @return
     */
    public double getTraco(double[][] matriz) {
            double [] diagonal = getDiagonalPricipal(matriz);
            double soma = 0;
            for (int i=0; i<diagonal.length; i++) {
                    soma += diagonal[i];
            }
            return soma;
    }


    /**
     * Retorna um elemento x da matriz, dado suas coordenadas i e j
     * @param matriz
     * @param i
     * @param j
     * @return
     */
    public double getElemento(double[][] matriz, int i, int j) {
            double max = matriz.length -1;
            if (i > max || j > max || i < 0 || j < 0) {
                    System.out.println("i e j fora do limite da matriz!");
                    return -1;		
            }
            return matriz[i][j];
    }




    /************************** IDENTIDADE ********************************/
    /**********************************************************************/
    /**********************************************************************/

    /**
     * A matriz identidade tem a diagonal principal
     * igual a 1 e o restante dos elementos igual a 0
     * @param matriz
     * @return boolean
     */
    public boolean isIdentidade(double[][] matriz) {

            for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz.length; j++) {
                            if (i == j) {
                                    if (matriz[i][j] != 1)
                                            return false;					
                            }
                            if (matriz[i][j] != 0)
                                    return false;
                    }
            }
            return true;
    }

    /**
     * Gera uma matriz identidade de ordem n
     * @param ordem
     * @return
     */
    public double[][] getMatrizIdentidade(int ordem) {
            double[][] m = new double[ordem][ordem];
            for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m.length; j++) {
                            if (i == j)
                                    m[i][j] = 1;
                            m[i][j] = 0;
                    }
            }	
            return m;
    }






    /*************************** SIM�TRICA ********************************/
    /**********************************************************************/
    /**********************************************************************/

    /**
     * Se a matriz for igual a sua transposta, elas s�o sim�tricas
     * @param matriz
     * @return boolean
     */
    public boolean isSimetrica(double[][] matriz) {

            double[][] transposta = getTransposta(matriz);
            for (int i = 0; i < transposta.length; i++) {
                    for (int j = 0; j < transposta.length; j++) {
                            if (matriz[i][j] != transposta[i][j]) {
                                    return false;
                            }
                    }
            }
            return true;
    }



    /**************************** INVERSA *********************************/
    /**********************************************************************/
    /** A matriz inversa � igual a:  1 / det(matriz) * matriz             */
    /**********************************************************************/
    /**********************************************************************/

    
    
    public static void main(String[] args) {
        
        double[][] a = new double[3][3];
        a[0][0] = 2;  a[0][1] = 2;  a[0][2] = 4;
        a[1][0] = 6;  a[1][1] = 6;  a[1][2] = 6;
        a[2][0] = 8;  a[2][1] = 6;  a[2][2] = 6;

        Matriz m = new Matriz();
        printMatriz(m.getInversa(a, m.getDeterminante(a)));
        
        
    }
    
    
    
    public double[][] getInversa(double[][] matriz, double determinante) {
            if (matriz.length == 2)
                    return getInversaOrdem2(matriz);

            double[][] cof = getCofatora(matriz);
            cof = getTransposta(cof);
            
            for (int i = 0; i < cof.length; i++) {
                
                for (int j = 0; j < cof.length; j++) {
                    double value = (cof[i][j]/determinante);
                    cof[i][j] = value;
                }
            }            
            return cof;
    }


    private double[][] getInversaOrdem2(double[][] matriz) {

            double[][] m = matriz;
            double[][] a = new double[2][2];

            /****************************************/
            //troca a por b
            double aux = matriz[0][0];
            matriz[0][0] = matriz[1][1];
            matriz[1][1] = aux;

            //inverte o sinal dos demais
            matriz[0][1] = (matriz[0][1] *-1);
            matriz[1][0] = (matriz[1][0] *-1);
            /****************************************/


            //a * d - b * c
            double x = (m[0][0] * m[1][1]) - (m[0][1] * m[1][0]) ;
            for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m.length; j++) {
                            a[i][j] = ((1/x) * matriz[i][j]);
                    }
            }
            return a;		
    }






    /************************** TRANSPOSTA ********************************/
    /**********************************************************************/
    /** Linhas da matriz, passam a ser colunas                            */
    /**********************************************************************/
    /**********************************************************************/
    public double[][] getTransposta(double[][] matriz) {

            double[] linha = new double[matriz.length];
            double[][] transposta = new double[Utils.getQuantidadeColunas(matriz)][Utils.getQuantidadeLinhas(matriz)];

            for (int i = 0; i < matriz.length; i++) {
                    linha = Utils.getLinha(matriz, i);
                    for (int j = 0; j < linha.length; j++) {
                            transposta[j][i] = linha[j];
                    }
            }		
            return transposta;
    }





    /**************************** DETERMINANTES ***************************/
    /**********************************************************************/
    /**********************************************************************/	

    public double getDeterminante(double[][] matriz) {

            if (matriz.length == 1)
                    return matriz[0][0];

            if (matriz.length == 2) {
                    return determinanteOrdem2(matriz);
            }

            //Se a matriz for igual a 3, faz a regra de sarrus
            if (matriz.length == 3) {
                    double [][] sarrus = getMatrizSarrus(matriz);			
                    double somaPrincipal = ((sarrus[0][0] * sarrus[1][1] * sarrus[2][2]) + 
                                                                    (sarrus[0][1] * sarrus[1][2] * sarrus[2][3]) +
                                                                    (sarrus[0][2] * sarrus[1][3] * sarrus[2][4])); 

                    double somaSecundaria = (((sarrus[0][4] * sarrus[1][3] * sarrus[2][2]) * -1) +
                                                                     ((sarrus[0][3] * sarrus[1][2] * sarrus[2][1]) * -1) +
                                                                     ((sarrus[0][2] * sarrus[1][1] * sarrus[2][0]) * -1));

                    return somaPrincipal + somaSecundaria;
            }

            if (matriz.length > 3) {
                    laPlace(matriz);
            }

            return 0;
    }


    /**
     * La Place calcula a determinante das matrizes de
     * ordem maior que 3
     * 
     * @param matriz
     * @return
     */
    public double laPlace(double[][] matriz) {

            double x = 0;
            if (matriz.length > 3) {
                    double []linha = Utils.getLinha(matriz, 0);
                    for (int i = 0; i < linha.length; i++) {
                            if (linha[i] != 0) {
                                    x += linha[i] * ( Math.pow(-1, 1 + (i + 1)) * laPlace(eliminaLinhaEColuna(matriz, 0, i)) );
                            }
                    }
            } else {
                    return getDeterminante(matriz);
            }
            return x;

    }



    /**
     * Elimina a linha e coluna de uma matriz, e
     * retorna a nova matriz com n-1 tamanho
     * 
     * @param matriz
     * @param l
     * @param c
     * @return
     */	
    private static double[][] eliminaLinhaEColuna(double[][] matriz, int l, int c) {
            double[][] m = new double[matriz.length -1][matriz.length -1];
            int a = 0;
            int b = 0;
            int i = 0;

            for (i = 0; i < matriz.length; i++) {
                    if (l < matriz.length -1) {
                            if (i == l) {
                                    i++;

                            }
                    }
                    if (l == i)
                            break;

                    for (int j = 0; j < matriz.length; j++) {

                            if (c < matriz.length -1) {
                                    if (c == j) {
                                            j++;
                                    }
                                    m[b][a] = matriz[i][j];
                                    a++;
                            }

                            if (c == matriz.length -1) {
                                    if (a < matriz.length -1) {
                                            m[b][a] = matriz[i][j];
                                    }
                                    a++;
                            }
                    }
                    b++;
                    a = 0;
            }
            return m;
    }



    /**
     * Duplica as duas primeiras colunas da matriz, e retorna 
     * uma nova matriz de tamanho n+2
     * 
     * @param matriz
     * @return
     */
    public static double[][] getMatrizSarrus(double[][] matriz) {
            double[][] aux = new double[matriz.length][matriz.length + 2];
            double[] col0 = Utils.getColuna(matriz, 0);
            double[] col1 = Utils.getColuna(matriz, 1);
            double cont = 0;
            for (int i = 0; i < aux.length; i++) {
                    for (int j = 0; j < aux[0].length; j++) {  // j -> percorre as colunas
                            if (j < (Utils.getQuantidadeColunas(matriz))) { // -> limite da matriz
                                    aux[i][j] = matriz[i][j];
                            } else { // -> percorre col0 e col1 alternadamente
                                    if (cont == 0) {
                                            aux[i][j] = col0[i];
                                            cont++;
                                    } else {
                                            aux[i][j] = col1[i];
                                            cont = 0;
                                    }
                            }
                    }
            }
            return aux;
    }




    /**
     * Calcula a determinante de uma matriz de ordem 2
     * @param matriz
     * @return
     */
    public static double determinanteOrdem2(double[][] matriz) {
            return (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
    }






    public static void printArray(double[] m) {
            for (double i : m) {
                    System.out.print(i + " ");
            }
    }


    public static void printMatriz(double[][] m) {
            for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[0].length; j++) {
                            System.out.print(m[i][j] + "\t");
                    }
                    System.out.println();
            }
    }

	
	
}
