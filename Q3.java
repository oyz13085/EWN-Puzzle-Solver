


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author Nicholas
 */
public class Q3 {
               
    public static void main(String[]args){
          
           Scanner scanner = new Scanner(System.in);
           String line1 =scanner.nextLine();
           int midpoint= line1.length()/2;
           
           if (line1.length()%2==1){
               System.out.print("Invalid");
              
               return;}
           
           int sum1 = 0;
           int sum2 = 0;
         
       
           for (int i=0; i<midpoint;i++){
                char c = line1.charAt(i);
                if ((!Character.isDigit(c))== true) { 
                    System.out.print("Invalid!");
                    return;}
    
                int digit1= c - 0;
                sum1+= digit1;
                
            }
           for(int i=midpoint;i<line1.length();i++){
                char z = line1.charAt(i);
                if ((!Character.isDigit(z))== true) { 
                   
                        System.out.print("Invalid!");
                    return;}
    
                int digit2= z-0;
                sum2+= digit2;
               
                
            }
            if (sum1 == sum2){
                   System.out.print("Lucky");}
                else{
                  System.out.print("Invalid");}    
    }
}
    
