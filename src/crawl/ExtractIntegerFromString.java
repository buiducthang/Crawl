/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

/**
 *
 * @author ducth_000
 */
public class ExtractIntegerFromString {
    public static int ExtractIntegerFromString(String str)
    {
        //System.out.println("str: " + str);
        if(str.equals(null) || str.equals("")) return 0;
        
        int count = 0;
        int banchung = 0;
        //String str="Do Tien Dat";
        
        if(str.matches(".*\\d+.*")==true){
        for(int index =0; index<str.length();index++)
        {
        
            String numberOnly= str.replaceAll("[^0-9]", "");
            //System.out.println(str.charAt(index));
            if(str.charAt(index) == 'K' && Character.isDigit(str.charAt(index-1)))
            {
                
                
                if(str.charAt(index-2) == ',')
                {                    
                    count = Integer.parseInt(numberOnly)*100;
                    break;
                }
                else
                {
                    count = Integer.parseInt(numberOnly)*1000;
                    break;
                }
                
            }
            else if(str.charAt(index) == 'M' && Character.isDigit(str.charAt(index-1)))
            {
                if(str.charAt(index-2) == ',')
                {                    
                    count = Integer.parseInt(numberOnly)*100000;
                    break;
                }
                else
                {
                    count = Integer.parseInt(numberOnly)*1000000;
                    break;
                }
            }
            else 
            {
                count = Integer.parseInt(numberOnly);
            }
            
        }
        }

        for(int index1 = 0; index1 < str.length(); index1++)
        { 
            if(str.matches(".*\\d+.*")==false) banchung++;
            if(str.charAt(index1) == ',') banchung++;
            if(str.charAt(index1) == 'v' && str.charAt(index1+1) == 'à') {
                banchung++;
                break;
            }
        }

        return (count+banchung);
        
        //System.out.println(str.matches(".*\\d+.*"));
    }
}
  

