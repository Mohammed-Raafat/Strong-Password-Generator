/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Mohammed Raafat
 */
public class JavaApplication4 
{

    /**
     * @param args the command line arguments
     */
    
    static String[] en_Numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},//10 Chars
                    en_Upper_Letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"},//26 Chars
                    en_Lower_Letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"},//26 Chars
                    common_Symbols = {"!", "#", "$", "%", "'", "(", ")", "*", "+", "-", ".", "/", ":", ";", "<", "@", ">", "?", "@", "[", "]", "_", "^", "`", "{", "|", "}", "~", "=", "&", "%", "\"", "×", "÷", ","};//35 Chars
                    //uncommon_Letters = {"À", "Á", "Â", "Ã", "Ä", "Å", "È", "É", "Ê", "Ë", "Ì", "Í", "Î", "Ï", "Ð", "Ò", "Ó", "Ô", "Õ", "Ö", "Ø", "Ù", "Ú", "Û", "Ü", "Ý", "Þ", "ã", "ð", "õ", "ø", "ü", "ý", "þ", "Ç", "ü", "é", "â", "ä", "à", "å", "ç", "ê", "ë", "è", "ï", "î", "ì", "æ", "Æ", "ô", "ö", "ò", "û", "ù", "ÿ", "¢", "£", "¥", "P", "á", "í", "ó", "ú", "ñ", "Ñ"}, //66 Chars
                    //uncommon_Symbols = {"~", "¶", "§", "ª", "º", "¿", "¬", "½", "¼", "¡", "«", "»", "¦", "ß", "µ", "±", "°", "²", "¨", "©", "®", "¯", "³", "´", "¸", "¹", "¾"}; //27 Chars

    static int  en_Numbers_Length,          //10    10%
                en_Upper_Letters_Length,    //26    10%
                en_Lower_Letters_Length,    //26    20%
                common_Symbols_Length;      //35    30%
                //uncommon_Letters_Length,    //66    10%
                //uncommon_Symbols_Length;    //27    20%
    
    static int fullPasswordLength;
    
    public static String[] sub_Password(String[] arr, int length)
    {
        Random random = new Random();
        int indexRandom;
        String[] sub_password = new String[length];
        for (int i = 0; i < length; i++) 
        {
            indexRandom = random.nextInt( arr.length );
            if(!Arrays.asList(sub_password).contains(arr[indexRandom]))
                sub_password[i] = arr[indexRandom];
            
            else
                i--;                       
        }
        
        return sub_password;
    }
    
    public static boolean devide_Password()
    {
        if(fullPasswordLength >=10 && fullPasswordLength <= 100)
        {   
            double fraction_part;
            
            en_Numbers_Length       =   (int)(0.1 * fullPasswordLength);     //10    10%
            en_Upper_Letters_Length =   (int)(0.2 * fullPasswordLength);     //26    20%
            en_Lower_Letters_Length =   (int)(0.2 * fullPasswordLength);     //26    20%
            common_Symbols_Length   =   (int)(0.5 * fullPasswordLength);     //35    50%
            //uncommon_Letters_Length =   (int)(0.1 * fullPasswordLength);     //66    10%
            //uncommon_Symbols_Length =   (int)(0.2 * fullPasswordLength);     //27    20%
            
            fraction_part = (( 1 * ((0.1 * fullPasswordLength)-en_Numbers_Length)
                                       + 2 * ((0.2 * fullPasswordLength)-en_Lower_Letters_Length)
                                       + 1 * ((0.5 * fullPasswordLength)-common_Symbols_Length)));
           
            if(fraction_part != 0)
            {
                if(fraction_part == 3)
                {
                    en_Lower_Letters_Length++;
                    fraction_part--;
                }
                if(fraction_part == 2)
                {
                    common_Symbols_Length++;
                    fraction_part--;
                }
                if(fraction_part == 1)
                {
                    en_Upper_Letters_Length++;
                    fraction_part--;
                }
            }
            
        return true;
        }
        
        else
        {
            System.out.println("Password Length MUST be between 10 and 100");
            return false;
        }
    }
    
    public static String[] combine(String[] a, String[] b, String[] c, String[] d)
    {
        String[] result = new String[fullPasswordLength];
        
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, a.length+b.length, c.length);
        System.arraycopy(d, 0, result, a.length+b.length+c.length, d.length);
        //System.arraycopy(e, 0, result, a.length+b.length+c.length+d.length, e.length);
        //System.arraycopy(f, 0, result, a.length+b.length+c.length+d.length+e.length, f.length);
        
        return result;
    }

    public static void shuffleArray(String[] ar)
    {
      // If running on Java 6 or older, use `new Random()` on RHS here
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        String a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }

    public static String full_Password()
    {
        String[] full_PasswordArray;
   
        full_PasswordArray = combine( sub_Password(en_Numbers,en_Numbers_Length),
                                      sub_Password(en_Upper_Letters,en_Upper_Letters_Length),
                                      sub_Password(en_Lower_Letters,en_Lower_Letters_Length),
                                      sub_Password(common_Symbols,common_Symbols_Length));
                                      //sub_Password(uncommon_Letters,uncommon_Letters_Length),
                                      //sub_Password(uncommon_Symbols,uncommon_Symbols_Length));

        shuffleArray(full_PasswordArray);
        StringBuilder finalFullPassword = new StringBuilder();
        for (int i = 0; i < full_PasswordArray.length; i++) 
           finalFullPassword.append(full_PasswordArray[i]);

        return (finalFullPassword).toString();
    }
    
    public static void main(String[] args)
    {
        // TODO code application logic here

        fullPasswordLength = 20;//Must be between 10 to 100
        //for(int i = 0; i < 10; i++)
        //{
            if(devide_Password())
            System.out.println(full_Password());
        //}
    }
}