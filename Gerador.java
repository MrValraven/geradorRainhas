import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerador {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        int numberOfConfigs = 0;
        int numberOfQueens = 0;
        int numberOfSize = 0;
        int dimensions = 0;
        /*
        if(args.length == 0){
            System.out.println("Please choose a mode: 'Random' or 'all' ");
            String mode = scanner.nextLine();
            if(mode.contains("Random")){
                System.out.println("Digite o número de configurações: ");
                numberOfConfigs = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite a dimensão do tabuleiro: ");
                numberOfSize = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o número de rainhas: ");
                numberOfQueens = scanner.nextInt();
                scanner.nextLine();
                random(numberOfSize,numberOfQueens,numberOfConfigs);
            }
            else if(mode.contains("all")){
                int value = 0;
                System.out.println("Value: ");
                value = scanner.nextInt();
                all(value);
            }
            else{
                System.out.println("Invalid mode, please try again");
                mode = scanner.nextLine();
            }
        // Configuração final para aceitar argumentos do terminal
        else if(args[0].equals("random")){
            numberOfConfigs = Integer.parseInt(args[3]);
            numberOfQueens = Integer.parseInt(args[2]);
            numberOfSize = Integer.parseInt(args[1]);
            dimensions = numberOfSize * numberOfSize;

            random(numberOfConfigs, dimensions, numberOfQueens);
        }
        else if(args[0].equals("all")){
            numberOfQueens = Integer.parseInt(args[1]);
            all(numberOfQueens); 
        }
        
        }
        scanner.close();*/
        allValid(8);
    }

    private static int randomIndex(int range){
        int randomNumber =  (int) Math.floor(Math.random() * range);
        return randomNumber;
    }

    private static String dashLine(int length){
         String dash = "-".repeat(length);
         return dash;
    }
    
    private static String addQueensToString(int size, int numberOfQueens) {
        StringBuilder string = new StringBuilder(dashLine(size));
        int queenCounter = 0;

            for(int j = 0; j < string.length(); j++){
                while(queenCounter <= numberOfQueens){
                    string.setCharAt(randomIndex(size), 'D');
                    queenCounter++;
                }
            }
            return string.toString();
    }

    public static List<String> random(int m, int q, int n){ 
        List<String> configList = new ArrayList<String>();
        int dimensions = m * m;
        if(q <= m){
            for(int i = 0; i < n; i++){
                configList.add(addQueensToString(dimensions, q));
            }
        }
        else{
            System.out.println("Argumentos inválidos, o numero de rainhas (Q) é maior que a dimensão (M) permitida");
        }
        return configList;
    }

    
    public static List<String> all(int m){
        List<String> allStrings = new ArrayList<String>();
        int lowestValue = 0;
        long highestValue = 0;
        if(m < 2){
            highestValue = (long)Math.pow(2, m);
        }
        else if(m == 2){
            highestValue = (long)Math.pow(2, m+2);
        }
        else{
            lowestValue = (int)Math.pow(2, m) - 1;
            highestValue = (long)Math.pow(2, m * m);
        }
        int count = 0;
        //Valores de M: 2 = 16 dá 6Combs | 3 = 511 dá 84Combs | 4 = 65535 dá 1820Combs | 5 = 33554431 dá 53130Combs | 6 = 68719476735 dá 1947792Combs 
        String binary;
        String finalString;
        for(int i = lowestValue; i < highestValue; i++){
            binary = Integer.toBinaryString(i);
            char[] binaryChar = binary.toCharArray();
            count = 0;
            for(int j = 0; j < binaryChar.length; j++) {
                if(binaryChar[j] == '1'){
                    count++;
                    }
                }
            
            if(count == m){
                if(binary.length() < m * m){
                    binary = "-".repeat(m * m - binary.length()) + binary;
                }
                finalString = binary.replace('1', 'D');
                finalString = finalString.replace('0', '-');
                allStrings.add(finalString);
            }
        }
        return allStrings;
    }

    private static char[][] criarTabuleiro(String stringConfig){

        int size = (int) (Math.sqrt(stringConfig.length()));
        char[][] board = new char[size][size];
        int aux = 0;

        // Converte a String em uma Matriz
        for (int i = 0; i < size; i++){ 
            for (int j = 0; j < size; j++){ 
                if(aux < stringConfig.length())
                    board[i][j] = stringConfig.charAt(aux);
                aux++; 
            } 
        }
        //Imprime tabuleiro
        for (int i = 0; i < size; i++){ 
            for (int j = 0; j < size; j++){ 
                System.out.print(board[i][j]); 
            } 
            System.out.println(""); 
        }
        return board;
    }

    private static List<String> linhasDoTabuleiro(char[][] board){

        List<String> linhas = new ArrayList<String>();
        char linhaChar;
        String linha = "";
        int size = board.length;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                linhaChar = board[i][j];
                linha += linhaChar;
                if(linha.length() == size){
                    linhas.add(linha);
                    linha = "";
                }
                
            }
        }
        return linhas;

    }

    private static List<String>  colunasDoTabuleiro(char[][] board){

        List<String> colunas = new ArrayList<String>();
        char colunaChar;
        String coluna = "";
        int size = board.length;
        int aux = 0;

        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++){
                colunaChar = board[i][aux];
                coluna += colunaChar;
                if(coluna.length() == size){
                    colunas.add(coluna);
                    coluna = "";
                }
            }
            aux++;
        }
        return colunas;
    }

    private static List<String> diagonaisAscendentesDoTabuleiro(char[][] board){
        List<String> diagonaisAscendentes = new ArrayList<String>();
        String diagonalAscendente = "";
        char diagonalChar;

        //print first half
        int linha = 0;
        int coluna;

        while(linha < board.length){
            coluna = 0;
            int linhaAux = linha;
            while(linhaAux >= 0){
                diagonalChar = board[linhaAux][coluna];
                diagonalAscendente += diagonalChar;
                linhaAux--;
                coluna++;
            }
            diagonaisAscendentes.add(diagonalAscendente);
            diagonalAscendente = "";
            linha++;
        }

        //print second half
        coluna = 1;

        while(coluna < board.length){
            int colunaAux = coluna;
            linha = board.length-1;
            while(colunaAux <= board.length-1){
                diagonalChar = board[linha][colunaAux];
                diagonalAscendente += diagonalChar;
                linha--;
                colunaAux++;
            }
            diagonaisAscendentes.add(diagonalAscendente);
            diagonalAscendente = "";
            coluna++;
        }
        return diagonaisAscendentes;
    }

    private static List<String> diagonaisDescendentesDoTabuleiro(char[][] board){
        List<String> diagonaisDescendentes = new ArrayList<String>();
        return diagonaisDescendentes;
    }

    public static List<String> allValid(int m){
        List<String> linha = new ArrayList<String>();
        List<String> coluna = new ArrayList<String>();
        List<String> diagonalAscendente = new ArrayList<String>();
        List<String> allValid = new ArrayList<String>();
        String stringConfig = "-D---------D---------D---------D--D-----D-------------D-----D---";
        char[][] board = criarTabuleiro(stringConfig);
        linha = linhasDoTabuleiro(board);
        coluna = colunasDoTabuleiro(board);
        diagonalAscendente = diagonaisAscendentesDoTabuleiro(board);
        
        return allValid;

    }
}


