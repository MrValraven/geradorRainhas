import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerador {
    
    private static Scanner scanner = new Scanner(System.in);
    private static int modeValue = 0;

    public static void main(String[] args) {
        int numberOfConfigs = 0;
        int numberOfQueens = 0;
        int numberOfSize = 0;
        int dimensions = 0;
        
        if(args.length == 0){
            System.out.println("Please choose a mode: 'random' , 'all' , 'allValid' or 'validador'.");
            String mode = scanner.nextLine();
            while(mode != "random" || mode != "allValid" || mode != "all" || mode != "validador"){
                if(mode.contains("random")){
                    System.out.println("Digite o numero de configuracoes: ");
                    numberOfConfigs = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite a dimensao do tabuleiro: ");
                    numberOfSize = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o numero de rainhas: ");
                    numberOfQueens = scanner.nextInt();
                    scanner.nextLine();
                    random(numberOfSize,numberOfQueens,numberOfConfigs);
                    break;
                }
                else if(mode.contains("allValid")){
                    int value = 0;
                    System.out.println("Value: ");
                    value = scanner.nextInt();
                    allValid(value);
                    break;
                }
                else if(mode.contains("all")){
                    modeValue = 1;
                    int value = 0;
                    System.out.println("Value: ");
                    value = scanner.nextInt();
                    all(value);
                    break;
                }
                else if(mode.contains("validador")){
                    System.out.println("Insira a configuracao: ");
                    String configuracao = scanner.nextLine();
                    validadorIndividual(configuracao);
                    break;
                }
                else{
                    System.out.println("Invalid mode, please try again");
                    mode = scanner.nextLine();
                }
            }
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
            modeValue = 1;
            numberOfQueens = Integer.parseInt(args[1]);
            all(numberOfQueens); 
        }
        scanner.close();
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
        int index = 0;

            for(int j = 0; j < string.length(); j++){
                while(queenCounter < numberOfQueens){
                    index = randomIndex(size);
                    if(string.charAt(index) == 'D'){
                        index = randomIndex(size);
                    }
                    else if(string.charAt(index) == '-'){
                        string.setCharAt(index, 'D');
                        queenCounter++;
                    }
                }
            }
            return string.toString();
    }

    public static List<String> random(int m, int q, int n){ 
        List<String> randomList = new ArrayList<String>();
        String randomString;
        int dimensions = m * m;

        if(q <= m){
            for(int i = 0; i < n; i++){
                randomString = addQueensToString(dimensions, q);
                randomList.add(randomString);
                System.out.println(randomString);
            }
        }
        else{
            System.out.println("Argumentos inválidos, o numero de rainhas (Q) é maior que a dimensão (M) permitida");
        }
        return randomList;
    }

    public static List<String> all(int m){
        List<String> allStrings = new ArrayList<String>();
        String binary;
        String finalString;
        long lowestValue = 0;
        long highestValue = 0;
        int count = 0;

        if(m < 2){
            highestValue = (long)Math.pow(2, m);
        }
        else if(m == 2){
            highestValue = (long)Math.pow(2, m+2);
        }
        else{
            lowestValue = (long)Math.pow(2, m) - 1;
            highestValue = (long)Math.pow(2, m * m);
        }

        for(long i = lowestValue; i < highestValue; i++){
            binary = Long.toBinaryString(i);
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
                if(modeValue == 1){
                    System.out.println(finalString);
                }
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
        int linha = 0;
        int coluna;

        //Primeira metade
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

        //Segunda metade
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
        String diagonalDescendente = "";
        char diagonalChar;
        int linha = 0;
        int coluna;

        //Primeira metade
        while(linha < board.length){
            coluna = board.length - 1;
            int linhaAux = linha;
            while(linhaAux >= 0){
                diagonalChar = board[linhaAux][coluna];
                diagonalDescendente += diagonalChar;
                linhaAux--;
                coluna--;
            }
            diagonaisDescendentes.add(diagonalDescendente);
            diagonalDescendente = "";
            linha++;
        }

        //Segunda metade
        coluna = board.length - 2;

        while(coluna >= 0){
            int colunaAux = coluna;
            linha = board.length - 1;
            while(colunaAux >= 0){
                diagonalChar = board[linha][colunaAux];
                diagonalDescendente += diagonalChar;
                linha--;
                colunaAux--;
            }
            diagonaisDescendentes.add(diagonalDescendente);
            diagonalDescendente = "";
            coluna--;
        }
        return diagonaisDescendentes;
    }

    private static boolean isListValid(List<String> conjunto){
        String verificador;
        int count;

        for(int i = 0; i < conjunto.size(); i++){
            verificador = conjunto.get(i);
            count = 0;
            if(verificador.contains("D")){
                for(int j = 0; j < verificador.length(); j++){
                    if(verificador.charAt(j) == 'D'){
                        count++;
                    }
                }
                if(count >= 2){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(String stringConfig){
        List<String> linha = new ArrayList<String>();
        List<String> coluna = new ArrayList<String>();
        List<String> diagonalAscendente = new ArrayList<String>();
        List<String> diagonalDescendente = new ArrayList<String>();
        char[][] board = criarTabuleiro(stringConfig);
        linha = linhasDoTabuleiro(board);
        coluna = colunasDoTabuleiro(board);
        diagonalAscendente = diagonaisAscendentesDoTabuleiro(board);
        diagonalDescendente = diagonaisDescendentesDoTabuleiro(board);

        if(isListValid(linha) && isListValid(coluna) && isListValid(diagonalAscendente) && isListValid(diagonalDescendente)){
            return true;
        }
        else{
            return false;
        }
    }

    public static void validadorIndividual(String stringConfig){

        if(isValid(stringConfig) == true){
            System.out.println("VALIDA"); 
        }
        else{
            System.out.println("INVALIDA");
        }
    }

    public static String filtro(String stringConfig){

        if(isValid(stringConfig) == true){
            return "VALIDA";
        }
        else{
            return "INVALIDA";
        }
    }

    public static List<String> allValid(int m){
        List<String> allValid = new ArrayList<String>();
        String newConfig;
        List<String> allConfigs = all(m);
        
        for(int i = 0; i < allConfigs.size(); i++){
            newConfig = allConfigs.get(i);
            filtro(newConfig);
            if(filtro(newConfig) == "VALIDA"){
                System.out.println(newConfig);
                allValid.add(newConfig);
            }
        } 
        return allValid;
    }
}