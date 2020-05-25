public class testeGeradorAll {

    public static void main(String[] args) {
        
        
        generateAllStrings(64, 8);
        

    }

    public static void combinacoes(int elements, int items){
        
    }

    public static void generateAllStrings(int dimensions, int numberOfQueens){
        // Combinaçoes =  nCr ou C(n,r) = n! / (n - r)!(r!)
        /* If we include the first item, then we need to choose “r – 1″ elements from the remaining “n – 1″ items.
        On the other hand, if we discard the first item, then we need to select “r” elements out of the remaining “n – 1″ items. */
        // Em termos matemáticos : C(n,r) = C(n - 1, r - 1) + C(n - 1, r)
   
        String baseStringQueen = "Q".repeat(numberOfQueens);
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
   