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
    
    ArrayList<Integer[]> hashTable = new ArrayList<>();

    

    /* Begin hash function one */
    //Hashing Code that was given to me
    public int Hash(String str){

        int val = 0;

        if(str.isEmpty())

            return -1;

        for(int i=0; i < str.length(); i++)

        {

            val += (int)(str.charAt(i)* Math.pow(37,i));

        }

        return val%TABLE_SIZE; // TABLE_SIZE is the length of the hash table

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
        int hashCode;
        
        File in = new File("symptoms.txt");
        try{
            Scanner symptom = new Scanner(in);
            while(symptom.hasNext()){
                word = symptom.next();
                hashCode = Hash(word);
               
               
            }//END While
            
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }//END readSymptom
    
    public void isEmpty(){
        //stub
    }//END isEmpty
    
    public void contains(){
        //stub
    }//END Contains
    
    public void isFull(){
        //stub
    }//END isFull
    
    public void get(){
        //stub
    }//END get
    
    public void insert(){
        //stub
    }//END insert
    
    public static void main(String[] args){
        
    }//END MAIN
    
}//END Main
