import java.util.Scanner;

public class Gerador {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        int numberOfConfigs = 0;
        int numberOfQueens = 0;
        int numberOfSize = 0;
        int dimensions = 0;
        /*************************************************************************************************************************************** 
         CONFIGURAÇÕES PARA VERSÃO FINAL - Para obter input diretamente da linha de comandos
        if(args[0].equals("random")){
            numberOfConfigs = Integer.parseInt(args[3]);
            numberOfQueens = Integer.parseInt(args[2]);
            numberOfSize = Integer.parseInt(args[1]);
            dimensions = numberOfSize * numberOfSize;

            generateStrings(numberOfConfigs, dimensions, numberOfQueens);
        }
        else if(args[0].equals("all")){
            int allConfigs = Integer.parseInt(args[1]);
            numberOfQueens = allConfigs;
            dimensions = allConfigs * allConfigs;

            generateAllStrings(allConfigs, dimensions, numberOfQueens); 
        }
        *****************************************************************************************************************************************/

        //generateAllStrings(1, 64, 8);
        /* System.out.println("Digite o número de configurações: ");
        numberOfConfigs = scanner.nextInt();
        scanner.nextLine();
        generateStrings(numberOfConfigs,64,8);   

        scanner.close(); */
    }

    private static int randomIndex(int range){
        int randomNumber =  (int) Math.floor(Math.random() * range);
        return randomNumber;
    }

    private static String dashLine(int length){
         String dash = "-".repeat(length);
         return dash;
    }
    
    private static StringBuilder createStringWithRainhas(int size, int numberOfQueens){
        StringBuilder string = new StringBuilder(dashLine(size));
        int queenCounter = 0;

            for(int j = 0; j < string.length(); j++){
                while(queenCounter <= numberOfQueens){
                    string.setCharAt(randomIndex(size), 'D');
                    queenCounter++;
                }
            }
            System.out.println(string);
            return string;
    }

    public static void generateStrings(int numberOfConfigs, int dimensions, int numberOfQueens){ 

        for(int i = 0; i < numberOfConfigs; i++){
            createStringWithRainhas(dimensions , numberOfQueens);
        }
    }

    public static void generateAllStrings(int numberOfConfigs, int dimensions, int numberOfQueens){
        // Combinaçoes =  nCr ou C(n,r) = n! / (n - r)!(r!)
        /* If we include the first item, then we need to choose “r – 1″ elements from the remaining “n – 1″ items.
        On the other hand, if we discard the first item, then we need to select “r” elements out of the remaining “n – 1″ items. */
        // Em termos matemáticos : C(n,r) = C(n - 1, r - 1) + C(n - 1, r)
   
        int count = 0;
        String baseStringQueen = "D".repeat(numberOfQueens);
        String baseStringNothing = "-".repeat(dimensions - numberOfQueens);
        String stringFinal = baseStringQueen + baseStringNothing;
        System.out.println(stringFinal);
        System.out.println(stringFinal.length());
        char[] stringArray = stringFinal.toCharArray();
        
        
        for(int i = (numberOfQueens - 1); i < dimensions - 1 ; i++){
            char aux1 = stringArray[i];
            stringArray[i] = stringArray[i + 1];
            stringArray[i + 1] = aux1;
            System.out.println(new String(stringArray));   // Aqui DDDDDD-D
            for(int j = 1 ; j < 8; j++){                   
                char aux2 = stringArray[i - j];
                stringArray[i - j] = stringArray[i - j + 1];
                stringArray[i - j + 1] = aux2;
                System.out.println(new String(stringArray));
            }
        }

    }











}