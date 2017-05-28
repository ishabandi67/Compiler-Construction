import java.util.*;
import java.io.*;

public class Lexical_Analysis {

    public static void main(String args[]) throws IOException {

        Hashtable<String, Integer> keyword = new Hashtable<String, Integer>();
        keyword.put("int", 1);
        keyword.put("float", 2);
        keyword.put("char", 3);
        keyword.put("long", 4);
        keyword.put("if", 5);
        keyword.put("else", 6);
        keyword.put("void", 7);
        keyword.put("while", 8);
        keyword.put("do", 9);
        keyword.put("break", 10);
        keyword.put("for", 11);
        keyword.put("include", 11);

        Hashtable<String, Integer> function = new Hashtable<String, Integer>();
        function.put("main", 1);
        function.put("return", 2);
        function.put("printf", 3);
        function.put("scanf", 4);

        Hashtable<String, Integer> operator = new Hashtable<String, Integer>();
        operator.put("+", 1);
        operator.put("-", 2);
        operator.put("*", 3);
        operator.put("/", 4);
        operator.put("=", 5);
        operator.put("%d", 6);
        operator.put("(", 7);
        operator.put(")", 8);
        operator.put("#", 9);

        Hashtable<String, Integer> special = new Hashtable<String, Integer>();
        special.put(",", 1);
        special.put(";", 2);
        special.put("\"", 3);
        special.put("{", 4);
        special.put("}", 5);
        special.put("<", 6);
        special.put(">", 7);
        special.put("&", 8);

        Hashtable<String, Integer> symbol = new Hashtable<String, Integer>();
        Hashtable<String, Integer> literal = new Hashtable<String, Integer>();
        BufferedReader ip = new BufferedReader(new FileReader("C:\\Users\\Isha\\Documents\\ip1.txt"));
        System.out.println("Keyword Table" + keyword);
        System.out.println("Operator table" + operator);
        System.out.println("Special Symbol table" + special);
        System.out.println("Function table" + function);
        //System.out.println("Enter the statement");
        //Scanner sc= new Scanner(System.in);
        //String input= sc.nextLine();
        long startTime = System.currentTimeMillis();
        int i = 1, j = 1;
        String input = ip.readLine();
        input = input.trim();
        while (input != null) {
            //System.out.println(input);
            input = input.trim();
            StringTokenizer s = new StringTokenizer(input, " ,;=+-*/{}()#<>\n", true);
            String temp;
            
            while (s.hasMoreTokens()) {
                temp = s.nextToken();

                if (temp.equals("\n")) {
                    System.out.println("\n");
                } else if (temp.equals(" ")) {
                    System.out.print(" ");
                } else if (keyword.containsKey(temp)) {
                    System.out.print("keyword#" + keyword.get(temp) + " ");
                } else if (special.containsKey(temp)) {
                    System.out.print("special#" + special.get(temp) + " ");
                } else if (function.containsKey(temp)) {
                    System.out.print("function#" + function.get(temp) + " ");
                } else if (operator.containsKey(temp)) {
                    System.out.print("operator#" + operator.get(temp) + " ");

                } else {
                    if (temp.matches("[0-9]+.[0-9]+") || temp.matches("[0-9]+")) {
                        if (!literal.containsKey(temp)) {
                            literal.put(temp, i);
                        }
                        System.out.print("literal#" + literal.get(temp) + " ");
                        i++;
                    } else {

                        if (!symbol.containsKey(temp)) {
                            symbol.put(temp, j);
                        }
                        System.out.print("symbol#" + symbol.get(temp) + " ");
                        j++;

                    }
                }
            }
           System.out.println();
           input = ip.readLine();
           
        }
        System.out.println("Literal Table" + literal);
        System.out.println("Symbol table" + symbol);
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time" + (endTime - startTime));
    }
}

/*Input: ip1.txt
#include<stdio.h> 
void main() 
{
int a,b,c; 
printf("Enter nos"); 
scanf("%d %d",&a,&b);
c=a*2.17+b*8;
printf("Sum is ",c); 
}
*/

