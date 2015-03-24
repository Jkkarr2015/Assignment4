/**
 *
 * @author John Karr
 * @date made 3/24/2015 
 */
public class Assignment4 {
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

        String words;

        while (i<=10){ // loop will process 10 files

            StringBuffer fname = new StringBuffer("file"); // file name begins with this string

            fname.append(i).append(".txt"); // append the buffer with file extension

            String filename =  fname.toString();

            System.out.println(filename);

            // open the file using 'filename' for reading

            // read the file until end is reached

            // testing purposes [your parsing and hashing goes here]

            i++; // next file

        }

    }/* End function to generate filenames */
    //END Code given to me
    
    
}//END Main
