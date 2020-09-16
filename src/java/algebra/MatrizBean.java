/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra;

import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author RHUAN
 */

@ManagedBean
@SessionScoped
public class MatrizBean {
    
    private static String QUEBRA_LINHA = System.getProperty("line.separator");
    
    private Matriz matriz;
    private Operacao operacao;
    
    private String mm1; //depois de corrigida
    private String mm2; //depois de corrigida
    
    private String m1; //matriz m1 da tela do usuário
    private String m2; //matriz m2 da tela do usuário
    
    private String output = "";

    
    
    
    
    public MatrizBean() {
        this.operacao = new Operacao();
        this.matriz = new Matriz();
    }


    public void trocaMatriz(){
        String a = this.m1;
        this.m1 = this.m2;
        this.m2 = a;
    }
    

    public void corrigeMatrizes() {
        if (this.m1 != null)
            this.mm1 = corrigeMatriz(this.m1);
        if (this.m2 != null)
            this.mm2 = corrigeMatriz(this.m2);
    }
    
    
    
    
    public void transpostaB() {
        
        String a = "";
        this.mm2 = corrigeMatriz(m2);
        double[][] d = toMatriz(this.mm2);
        
        if (mm2.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        } else {
            a += printDados(matriz.getTransposta(d), "Transposta B");
        }
        setOutput(a);
    }
        
        
        
    public void transpostaA() {
        
        String a = "";
        this.mm1 = corrigeMatriz(m1);
        double[][] d = toMatriz(this.mm1);
        
        if (mm1.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        } else {
            a += printDados(matriz.getTransposta(d), "Transposta A");
        }
        setOutput(a);
    }
    
    
    public void inversaB() {
        String a = "";
        this.mm2 = corrigeMatriz(m2);
        double[][] d = toMatriz(this.mm2);
        
        if (mm2.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        }
        if (quadrada(d)) {
            double det = matriz.getDeterminante(d);
            if (det > 0) {
                a += "Determinante: " + det + "<br />";    
                a += printDados(matriz.getInversa(d, det), "Inversa B");                
            } else {
                a += "Determinante é zero, portanto não há inversa...<br />"; 
            }
        } else {
            setOutput("A matriz 'B' precisa ser quadrada!");
        }
        setOutput(a); 
        
    }
        
        
    
    public void inversaA() {
        String a = "";
        this.mm1 = corrigeMatriz(m1);
        double[][] d = toMatriz(this.mm1);
        
        if (mm1.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        }
        if (quadrada(d)) {
            double det = matriz.getDeterminante(d);
            if (det > 0) {
                a += "Determinante: " + det + "<br />";    
                a += printDados(matriz.getInversa(d, det), "Inversa A");                
            } else {
                a += "Determinante é zero, portanto não há inversa...<br />"; 
            }
        } else {
            setOutput("A matriz 'A' precisa ser quadrada!");
        }
        setOutput(a); 
        
    }
    
    
    public void determinanteA() {
        
        String a = "";
        this.mm1 = corrigeMatriz(m1);
        
        if (mm1.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        }
        if (quadrada(toMatriz(this.mm1))) {
            a = printDados(matriz.getDeterminante(toMatriz(this.mm1)), "Determinante A");
        } else {
            setOutput("A matriz 'A' precisa ser quadrada!");
        }
        setOutput(a);
        
    }
    
    
    public void determinanteB() {
        
        String a = "";
        this.mm2 = corrigeMatriz(m2);
        
        if (mm2.isEmpty()) {
            setOutput("Verifique se a matriz está correta!");
        }
        if (quadrada(toMatriz(this.mm2))) {
            a = printDados(matriz.getDeterminante(toMatriz(this.mm2)), "Determinante B");
        } else {
            setOutput("A matriz 'B' precisa ser quadrada!");
        }
        setOutput(a);
        
    }
        
        
    
    private boolean quadrada(double[][] m) {
        int a = m.length;
        int b = m[0].length;
        return a == b;
    }
    
    
    public void subtracao() {
        String a = "Subtração inválida! As matrizes precisam ser de mesma ordem <br />";
        corrigeMatrizes();
        
        if (validaSoma()) { // é a mesma lóica para a subtração
            double[][] q = toMatriz(this.mm1);
            double[][] w = toMatriz(this.mm2);
            
            if (q != null && w != null) {
                a = printDados(operacao.subtracao(q, w), "Subtração");
            }
        }
        setOutput(a);
    }
    
    
    
    public void multiplica() {

        String a = "O número de colunas da matriz da esquerda deve ser igual número de linhas da matriz da direita. <br />";
        corrigeMatrizes();
        if (validaMultiplicacao()) {
            double[][] q = toMatriz(this.mm1);
            double[][] w = toMatriz(this.mm2);
            if (q != null && w != null) {
                a = printDados(operacao.multiplicacao(q, w), "Multiplicação");  
            }
        }
        setOutput(a);
    }
    
    public void soma() {
        
        String a = "Soma inválida! As matrizes precisam ser de mesma ordem <br />";
        corrigeMatrizes();
        if (validaSoma()) {
            double[][] q = toMatriz(this.mm1);
            double[][] w = toMatriz(this.mm2);
            
            if (q != null && w != null) {
                a = printDados(operacao.soma(q, w), "Soma");
            }
        }
        setOutput(a);
    }
    
   

    public String getOutput() {
        return this.output;
    }

    public void setOutput(String output) {
        if (this.output.isEmpty()) {
            this.output = output;
        } else {
            this.output = (output += this.output);
        }
    }
    
    
    
    private boolean validaMultiplicacao() {
        int c1 = getColunas(mm1);
        int l2 = getLinhas(mm2);
        return c1 == l2;
    }
    
    private boolean validaSoma() {
        int l1 = getLinhas(mm1);
        int c1 = getColunas(mm1);
        int l2 = getLinhas(mm2);
        int c2 = getColunas(mm2);
        
        System.out.println(l1);
        System.out.println(c1);
        
        System.out.println(l2);
        System.out.println(c2);
        
        return l1 == l2 && c1 == c2;
    }
    
    
    public static boolean checkDouble(String m) {        
        
        boolean flag = false;
        if (m.contains(".")) {
            
            String[] a = m.split("\n");
            String b = Arrays.toString(a);
            b = b.replace('[', ' ');
            b = b.replace(']', ' ');
            a = b.split(" ");       

            for (int i = 0; i < a.length; i++) {
                try {
                    if (a[i].contains(",")) {
                        Double.parseDouble(a[i].replace(',', ' '));
                        return true;
                    } else {
                        Double.parseDouble(a[i]);                    
                        return true;
                    }
                } catch(NumberFormatException e) {
                    flag = false;
                }
            }
        }
        return flag;
    }
    
    private static String trata(String a, boolean f) {
        if (a.contains("]"))
            a = a.replace(']', ' ');
        if (a.contains("["))
            a = a.replace('[', ' ');
        if (f){
            if (a.contains(","))
                a = a.replace(',', ' ');
        }
        return a;
        
    }
 
    
    
    public static String removeUltimoCaracter(String s) {
        
        int pos = 0;
        char[] a = s.toCharArray();
        boolean f = true;
        for (int i = a.length-1; i > 0 && f; i--) {
            if (a[i] == ',') {
                pos = i;
                f = false;
            }
        }
        
        if (pos > 0) {
            String c = "";
            char b[] = Arrays.copyOf(a, pos-1);
            for (int i = 0; i < b.length; i++) {
                c += a[i];
            }
            return c + "\n";
        }
        return s;
    }
    
    
    
    public static String corrigeMatriz(String m) {
        
        String s = "";
        
        if (checkDouble(m)) {
            return corrigeMatrizComDouble(m);
        }
        
        if (!isEmpty(m) || m != null)  {
            
            char[] a = m.toCharArray();
            for (int i = 0; i < a.length; i++) {            
                
                if (isDigitValid(Character.toString(a[i]), 10)) {                
                    s += a[i];
                }
                else {
                    if (a[i] == '\n') {
                        if (! s.isEmpty()) {
                            char[] c = s.toCharArray();
                            if (s.toCharArray().length > 1) {
                                if (isDigitValid(Character.toString(c[c.length-1]), 10)) {
                                    s += "\n";
                                }                            
                            } else {
                                if (isDigitValid(Character.toString(c[0]), 10)) {
                                    s += "\n";
                                }
                            }
                        }
                    }
                }
            }
            char[] d = s.toCharArray();
            if (d[d.length-1] != '\n') {
                return s += "\n";
            }
        }
        return s;
    }
    
    
    public static boolean isEmpty(String string) {
        return (string.trim().isEmpty());
    }

    
    //OK
    public int getLinhas(String m) {
        
        char[] a = m.toCharArray();
        int linhas = 0;
        
        for (int i = 0; i < a.length; i++) {            
            if (a[i] == '\n')
                linhas++;
        }
        return linhas;
    }
    
   public static String corrigeMatrizComDouble(String m) {
        
        if (!isEmpty(m) || m != null)  {
                        
            String s = "";
            String[] a = m.split("\n");
            
            
            for (int i = 0; i < a.length; i++) {
                
                
                a[i] = trata(a[i], false);
                if (isDigitValid(a[i], 10)) {
                    s += a[i];
                }
                               
                if (a[i].contains(" ")) {
                    s += "\n";
                }
//                    System.out.println("contem");
//                    if (! s.isEmpty()) {
//                        String c = a[i].replace(',', ' ');
//                        
//                        if (s.toCharArray().length > 1) {
//                            if (isDigitValid((c), 10)) {
//                                s += "\n";
//                            }
//                        } else {
//                            if (isDigitValid(c, 10)) {
//                                s += "\n";
//                            }
//                        }
//                    }
//                }
            }

            char[] c = s.toCharArray();
            if (c[c.length-1] != '\n' ) {
                return s += "\n";
            }            
            return removeUltimoCaracter(s);
        }
        return m;
    }
   
    private String tiraUltimosEspacos(String m) {
        char[] a = m.toCharArray();
        int pos = a.length;
        for (int i = a.length-1; i > 0; i--) {
            if (a[i] == ' ' || a[i] == '\n') {
                pos--;
            }      
            if (isDigitValid(Character.toString(a[i]), 10)) {
                char b[] = Arrays.copyOf(a, pos);
                String v = "";
                for (int j = 0; j < b.length; j++) {
                    v+=b[j];
                }
                return v;
            }
        }
        return m;                
    }
            
    
    public int getColunasDouble(String m) {
        m = tiraUltimosEspacos(m); 
        int cols = 0;
        int colunas = 0;
        String[] x = m.split(" ");
        String z = "";
        
        for (String q : x) {
            if (q.contains("\n")) {
                q = q.replace("\n", " , ");
            }
            z += q + " ";
        }
        
        z = tiraUltimosEspacos(z);
        z += ",";
        
        String[] y = z.split(" ");
        for (String g : y) {
            
            if (isDigitValid(g, 10)) {
                cols++;
            } 
            if (g.contains(",")) {
                if (cols > colunas) {
                    colunas = cols;
                }
                cols  = 0;
            }
        }
        return colunas;
    }
    
    // OK
    public int getColunas(String m) {
        m += ",";
        int cols = 0;
        int colunas = 0;
        String[] s = m.split("\n");
        for (int i = 0; i < s.length; i++) {
            if (isDigitValid(s[i], 10)) {
                colunas++;
            }
            
            if (s[i].equals("\n") || s[i].equals(",")) {
                if (colunas > cols) {
                    cols = colunas;
                }
                colunas = 0;
            }
        }
        
//        char [] a = m.toCharArray();
//        int colunas = 0;
//        int cols = 0;
//                
//        for (int j = 0; j < a.length; j++) {
//            if (isDigitValid(Character.toString(a[j]), 10)) {
//                colunas++;
//            }
//            if (a[j] == '\n') {
//                if (colunas > cols) {
//                    cols = colunas;                    
//                }
//                colunas = 0;
//            }
//        }
        return cols;
    }
        
   
    /**
     * Verificar se o input do usuário só contem números
     * @param m
     * @return 
     */
    public static boolean isInputOnlyDigit(String m) {
        char[] a = m.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (! Character.isDigit(a[i])) {
                return false;
            }
        }
        return true;
    }
    
    
    public static boolean isContainsMoreOneNumber(String[] a) {
        int cont = 0;
        for (int i = 0; i < a.length; i++) {
            if (isDigitValid(a[i], 10)) {
                cont++;
            }
        }
        return cont > 1;
    }
    
    public static boolean isDigitValid(String s, int radix) {
        if(s.isEmpty()) {
            return false;
        }
        
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {

            for(int i = 0; i < s.length(); i++) {
                if(i == 0 && s.charAt(i) == '-') {
                    if(s.length() == 1) {
                        return false;
                    }
                    else continue;
                }
//                return (Character.digit(s.charAt(i), radix) < 0);
                return (Character.isDigit(s.charAt(i)));
            }
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public static void print(String[] a) {
        System.out.println(Arrays.toString(a));
    }
    
    
    public double[][] toMatriz(String m) {
        
        double[][] matriz = null;
        char[] a = m.toCharArray();
         
        if (Utils.isMatrizQuadrada(m) && isInputOnlyDigit(m)) {

            int ordem = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != '\n' && a[i] != ' ') {
                    ordem++;
                }
                if (a[i] == '\n') {
                    matriz = new double[ordem][ordem];
                }
                if (matriz != null) {
                    matriz = montarMatriz(m, ordem);
                    break;
                }
            }
        } else {
            int linhas = getLinhas(m);
            int colunas = getColunas(m);
            matriz = montarMatriz(m, linhas, colunas);
        }
        return matriz;
    }

    private double[][] montarMatriz(String a, int ordem) {
            
        String[] b = a.split(" ");
        double [][] m = new double[ordem][ordem];
        int i = 0;
        int j = 0;
            
            for (int k = 0; k < b.length; k++) {
                if (isDigitValid(b[k], 10)) {
                    m[i][j] = Double.parseDouble(b[k]);
                    j++;
                }
                
                if (b[k].equals(QUEBRA_LINHA)) {
                    i++;
                    j = 0;
                }
            }
            return m;
    }
    
    
    private double[][] montarMatriz(String a, int linhas, int colunas) {

        System.out.println("montar matriz " + linhas + " " + colunas);
        
        
        double [][] m = new double[linhas][colunas];
        char[] b = a.toCharArray();
        int i = 0;
        int j = 0;

        System.out.println(Arrays.toString(b));
        
        for (int k = 0; k < b.length; k++) {
                        
            if (isDigitValid(Character.toString(b[k]), 10)) {
                m[i][j] = Double.parseDouble(Character.toString(b[k]));
                j++;
            }

            if (b[k] == '\n') {
                i++;
                j = 0;
            }
        }
        return m;
    }
    
    
    
    public void printMatriz(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println("");
        }
    }


    public String printDados(String msg) {                
        msg += "<br />";
        msg += "------------------------------------------------------------------------<br />";
        return msg += "<br />";
    }    
    
    public String printDados(double m, String msg) {                
        msg += "<br />";
        msg += "------------------------------------------------------------------------<br />";
        msg += m;
        return msg += "<br />";
    }
    
    
    
    public String printDados(double[][] m, String msg) {
        String s = "";
        String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        msg += "<br /><table>";
        msg += "<tr>------------------------------------------------------------------------</tr>";
        
        for (int i = 0; i < m.length; i++) {
            msg += "<tr>";
            for (int j = 0; j < m[0].length; j++) {
                s += ("<td>" + m[i][j]) + tab + "</td>";
            }
            s += "</tr>";
        }
        return msg += s += "</table><br />";
    }
    
    public static void print(char[] a) {
        for (char c : a) {
            System.out.println(c + " ");
        }
    }
            
    

    
    
    
    
    
    
    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public void setMm1(String mm1) {
        this.mm1 = mm1;
    }

    public String getMm1() {
        return mm1;
    }

    public void setMm2(String mm2) {
        this.mm2 = mm2;
    }

    public String getMm2() {
        return mm2;
    }


    
}
