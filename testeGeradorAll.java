public class testeGeradorAll {

    public static void main(String[] args) {
        //generateAllStrings(8, 64, 8);
        System.out.println("First Test"); 
        char[] set1 = {'-', 'D'}; 
        int k = 64; 
        printAllKLength(set1, k);  

    }

    public static void combinacoes(int elements, int items){
        
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
            /* for(int j = 1 ; j < 8; j++){                   
                char aux2 = stringArray[i - j];
                stringArray[i - j] = stringArray[i - j + 1];
                stringArray[i - j + 1] = aux2;
                System.out.println(new String(stringArray));
                if(j == 7){

                }
            } */
        }
    }

    // The method that prints all  
// possible strings of length k. 
// It is mainly a wrapper over  
// recursive function printAllKLengthRec() 
static void printAllKLength(char[] set, int k) 
{ 
    int n = set.length;  
    printAllKLengthRec(set, "", n, k); 
} 
  
// The main recursive method 
// to print all possible  
// strings of length k 
static void printAllKLengthRec(char[] set,  
                               String prefix,  
                               int n, int k) 
{ 
      
    // Base case: k is 0, 
    // print prefix 
    if (k == 0)  
    { 
        System.out.println(prefix); 
        return; 
    } 
  
    // One by one add all characters  
    // from set and recursively  
    // call for k equals to k-1 
    for (int i = 0; i < n; ++i) 
    { 
  
        // Next character of input added 
        String newPrefix = prefix + set[i];  
          
        // k is decreased, because  
        // we have added a new character 
        printAllKLengthRec(set, newPrefix,  
                                n, k - 1);  
    } 
}}
  
