#include <iostream>
#include <cstdlib> 
#include <fstream>
#include <string>
using namespace std;


int main(){
  int i=1; // file number
  string words; 
  // char *itoa(int, char*, int);
  while (i<=10){ // loop will process 10 files 
    char buf[3]; // buffer will hold 3 characters
    itoa(i,buf,10); // convert a decimal number
    string fname = "file"; // file name begins with this string
	fname.append(buf).append(".txt"); // append the buffer with file extention    
  	ifstream inf(fname.c_str()); //open the file for reading
	if (inf.fail())
		cerr<<"file open fail"<<endl;
	inf>>words;
	while(!inf.fail()){ // read the file until end is reached
		cout<<words<<endl; // testing purposes [your parsing and hashing goes here]
		inf>>words;
	}
	i++; // next file	
  }
  return 0;
}
    
// itoa(int, char, int radix) only works in ANSI C++, not all the compilers provide it.
// didn't work in linux c++ compiler
