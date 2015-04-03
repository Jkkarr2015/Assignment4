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
    public long Hash(String str){

        long val = 0;

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
    public void fileName(String word){
        int j = 0;
        int i=1; // file number
       
        while (i<=50){ // loop will process 10 files

            StringBuffer fname = new StringBuffer("file"); // file name begins with this string

            fname.append(i).append(".txt"); // append the buffer with file extension

            String filename =  fname.toString();

            //System.out.println(filename);

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
                        if(str.equals(word)){
                            long k = Hash(word);
                            int index = (int) k;
                            pages[j][index] = i;//Columms are HashCode specific
                            j++;
                            i++;
                        }
                       
                        str = "";//Clears out str for the next word
                        
                    }//end if
                    
                }//while END
                
            //Parser end
           
            i++;
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
                long i = Hash(word);
                //For Loop Linear Probe
                for(int j =(int)i; j<TABLE_SIZE;j++){
                    
                    if(symptoms[j] != null){
                        if(j < TABLE_SIZE - 1)
                           j++;
                    }//ENd if
                    if(symptoms[j] == null){
                        symptoms[j] = word;
                        j = 60;//Breaks out of loop if it sets word in an index
                    }//End If
                        else{
                                if(j >= TABLE_SIZE - 1){
                                    j = 0;//Comes back around if all lower indexes are full
                                }
                            }//End Else
                }//END For
            }//END While
          }//END Try
        catch(FileNotFoundException e){
            e.printStackTrace();
        }//END Catch
    }//END readSymptom
    
   public void printAnd2(String symptom1, String symptom2){
       long i = Hash(symptom1);
       int index = (int) i;
       long j = Hash(symptom2);
       int index2 = (int) j;
       int M = 0;
       
       System.out.print("Pages : ");
       while(M < TABLE_SIZE){
            for(int k = 0; k < TABLE_SIZE; k++){
                if(pages[k][index2] == pages[M][index]){
                    System.out.print(pages[k][index2] + " , ");
                }//end if
                if(pages[k][index2] == 0){
                    
                    k = 60;
                }
            }//end for
            if(pages[M][index] == 0 )
                M = 60;
            M++;//Next page     
        }//end while
   }//END PRINT AND2
   
    public void printOr2(String symptom1, String symptom2){
       long i = Hash(symptom1);
       int index = (int) i;
       long j = Hash(symptom2);
       int index2 = (int) j;
       int M = 0;
       
       System.out.print("Pages : ");
       while(M < TABLE_SIZE){
            for(int k = 0; k < TABLE_SIZE; k++){
                System.out.print(pages[k][index] + pages[k][index2] + " , ");
                if(pages[k][index] == 0){
                    System.out.print(pages[k][index2] + " , ");
                }
                else{
                    if(pages[k][index2] == 0){
                        System.out.print(pages[k][index] + " , ");
                    }
                    else{
                        k = 60;
                        M = 60;
                    }
                }
            }//end for
           
            M++;//Next page     
        }//end while
   }//END PRINT Or2
    
    public void printPages(){
        for(int i = 0; i < TABLE_SIZE; i++){
            for(int j =0; j < TABLE_SIZE; j++){
                System.out.print(pages[i][j] + " , ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Assignment4 obj = new Assignment4();
        obj.readSymptoms();
        Scanner input = new Scanner (System.in);
        System.out.println("How many symptoms?(Enter 2 through 4: ");
        int sN = input.nextInt();
        
        String symptom1;
        String con1;
        String symptom2;
        String con2;
        String symptom3;
        String con3;
        String symptom4;
        
        String and = "and";
        String or = "or";
        
        
        switch (sN){
            case 2: symptom1 = input.next();
                    con1 = input.next();
                    symptom2 = input.next();
                    if(con1.equalsIgnoreCase(and)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        //obj.printPages();
                        obj.printAnd2(symptom1, symptom2);
                    }//END AND IF
                    
                    if(con1.equalsIgnoreCase(or)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        obj.printOr2(symptom1, symptom2);
                    }//OR If
                    break;
            case 3: symptom1 = input.next();
                    con1 = input.next();
                    symptom2 = input.next();
                    con2 = input.next();
                    symptom3 = input.next();
                    if(con1.equalsIgnoreCase(and)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        //obj.printPages();
                        obj.printAnd2(symptom1, symptom2);
                    }//END AND IF
                    
                    if(con1.equalsIgnoreCase(or)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        obj.printOr2(symptom1, symptom2);
                    }//OR If
                    
                    if(con2.equalsIgnoreCase(and)){
                        obj.fileName(symptom2);
                        obj.fileName(symptom3);
                        //obj.printPages();
                        obj.printAnd2(symptom2, symptom3);
                    }//END AND IF
                    
                    if(con2.equalsIgnoreCase(or)){
                        obj.fileName(symptom2);
                        obj.fileName(symptom3);
                        obj.printOr2(symptom2, symptom3);
                    }//OR If
                    break;
            case 4: symptom1 = input.next();
                    con1 = input.next();
                    symptom2 = input.next();
                    con2 = input.next();
                    symptom3 = input.next();
                    con3 = input.next();
                    symptom4 = input.next();
                    if(con1.equalsIgnoreCase(and)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        //obj.printPages();
                        obj.printAnd2(symptom1, symptom2);
                    }//END AND IF
                    
                    if(con1.equalsIgnoreCase(or)){
                        obj.fileName(symptom1);
                        obj.fileName(symptom2);
                        obj.printOr2(symptom1, symptom2);
                    }//OR If
                    if(con2.equalsIgnoreCase(and)){
                        obj.fileName(symptom2);
                        obj.fileName(symptom3);
                        //obj.printPages();
                        obj.printAnd2(symptom2, symptom3);
                    }//END AND IF
                    
                    if(con2.equalsIgnoreCase(or)){
                        obj.fileName(symptom2);
                        obj.fileName(symptom3);
                        obj.printOr2(symptom2, symptom3);
                    }//OR If
                    if(con3.equalsIgnoreCase(and)){
                        obj.fileName(symptom3);
                        obj.fileName(symptom4);
                        //obj.printPages();
                        obj.printAnd2(symptom3, symptom4);
                    }//END AND IF
                    
                    if(con3.equalsIgnoreCase(or)){
                        obj.fileName(symptom3);
                        obj.fileName(symptom4);
                        obj.printOr2(symptom3, symptom4);
                    }//OR If
                    break;
        }//END Switch
        
    
        
        
    }//END MAIN
    
}//END Class


/*
run:
How many symptoms?(Enter 2 through 4: 
4
fatigue
or
fever
or
flu
or
rubella
Pages : 18 , Pages : 21 , Pages : 31 , BUILD SUCCESSFUL (total time: 28 seconds)

run:
How many symptoms?(Enter 2 through 4: 
3
hypothermia
and
frostbite
and
hemtoma
Pages : 21 , 22 , 0 , Pages : 0 , BUILD SUCCESSFUL (total time: 45 seconds)

run:
How many symptoms?(Enter 2 through 4: 
3
hemorrhage
and
paralysis
not
asphyxia
Pages : 0 , BUILD SUCCESSFUL (total time: 1 minute 1 second)

run:
How many symptoms?(Enter 2 through 4: 
3
hemorrhage
or
poisoning
not
dengue
Pages : 37 , BUILD SUCCESSFUL (total time: 42 seconds)

run:
How many symptoms?(Enter 2 through 4: 
4
flu
and
cough
or
fever
not
dengue
Pages : 19 , 20 , 21 , 0 , Pages : 4 , BUILD SUCCESSFUL (total time: 28 seconds)



*/