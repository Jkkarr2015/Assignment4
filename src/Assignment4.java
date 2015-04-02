/**
 *
 * @author John Karr
 * @date made 3/24/2015 
 */
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class Assignment4 {

    //Attributes
    public static final int TABLE_SIZE = 57;
    
    String symptoms[] = new String[TABLE_SIZE];
    int[][] pages = new int[TABLE_SIZE][TABLE_SIZE];
    
    //This Arraylist will probably change to multiple arrays (2 MAX)
    
    

    /* Begin hash function one */
    //Hashing Code that was given to me
    public int Hash(String str){

        int val = 0;

        if(str.isEmpty())

            return -1;

        for(int i=0; i < str.length(); i++)

        {

            val += (int)(str.charAt(i)* Math.pow(37,i));//Gets unicode for ever letter and takes positon into account

        }

        return val%TABLE_SIZE; // TABLE_SIZE is the length of the hash table // Compresses hashCode

    }/* End hash function one */
    //Hashing code END
    
    
    
    
    /* Begin function to generate filenames */
    //Code given to me
    public static void fileName(){
        
        int i=1; // file number
       
        while (i<=10){ // loop will process 10 files

            StringBuffer fname = new StringBuffer("file"); // file name begins with this string

            fname.append(i).append(".txt"); // append the buffer with file extension

            String filename =  fname.toString();

            System.out.println(filename);

            // open the file using 'filename' for reading
            

            // read the file until end is reached
           
            // testing purposes [your parsing and hashing goes here]
            try{
                 FileInputStream inf = new FileInputStream(new File(filename));
            
                //Parser Start
                char let;
                String str = "";
                 int n = 0;
           
                while((n = inf.read()) != -1){
                    let = (char)n;//converts n into Char type
               
                    if(Character.isLetter(let))
                        str += Character.toLowerCase(let);
               
                    if((Character.isWhitespace(let) || let == '-') && !str.isEmpty()){
                  
                        str = "";//Clears out str for the next word
                        
                    }//end if
                }//while END
            //Parser end
           i++; // next file
            
            }//Try End
            catch(IOException e){
                e.printStackTrace();
            }//catch
        }//While 1 end
        
        
    }/* End function to generate filenames */
    //END Code given to me
    
    
    public void readSymptoms(){
        String word;
        File in = new File("symptoms.txt");
        try{
            Scanner symptom = new Scanner(in);
            while(symptom.hasNext()){
                word = symptom.next();
                int i = Hash(word);
                if(symptoms[i] == null)
                    symptoms[i] = word;
                else{//Linear Probing
                    i++;
                    if(i < TABLE_SIZE){
                        symptoms[i] = word;
                    }//End If
                    if(i > TABLE_SIZE){
                        i = 0;
                        if(symptoms[i] == null){
                            symptoms[i] = word;
                        }//END if
                    }//END IF
                }//End Else
                
                //Insert hashTable container here
                //Insert Linear Probe code here for collision detection 
            }//END While
          
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }//END readSymptom
    
    public static void main(String[] args){
        
    }//END MAIN
    
}//END Class
