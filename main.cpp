#include <iostream>
#include <stdio.h>
#include "FileLoader.h"

using namespace std;

int main() {

    string filePath = "";
    cout << "Enter the filepath of a .docx file: ";
    getline(cin, filePath);

    FileLoader *fileLoader;
    fileLoader = new FileLoader(filePath);

    cout << fileLoader->fileContents;

    return 0;
}