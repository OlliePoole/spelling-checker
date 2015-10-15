#include <iostream>
#include "FileLoader.h"
#include "HashTable.h"


using namespace std;

int main() {

    //FileLoader *fileLoader;
    //fileLoader = new FileLoader("/Users/oliverpoole/Desktop/DOCFILE.docx");


    HashTable *hashTable;
    hashTable = new HashTable("/Users/oliverpoole/Documents/Oxford Brookes/Algotithms/Coursework/Spelling-Checker/Spelling-Checker/dictionary.txt");

    int searchStatus = hashTable->findValue("aardvark");
    if (searchStatus == 1) {
        cout << "Element found" << endl;
    }
    else {
        cout << "Element not found" << endl;
    }

    return 0;
}