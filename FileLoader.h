//
// Created by Oliver Poole on 13/10/15.
//

#include <stdio.h>
#include <iostream>

#ifndef SPELLING_CHECKER_FILELOADER_H
#define SPELLING_CHECKER_FILELOADER_H

using namespace std;

// The purpose of this file is to load a .docx file, then convert that file to .txt
class FileLoader {

public:
    string fileContents;
    FileLoader(string);
};


#endif //SPELLING_CHECKER_FILELOADER_H
