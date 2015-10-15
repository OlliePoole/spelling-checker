//
// Created by Oliver Poole on 13/10/15.
//

#include "FileLoader.h"

FileLoader::FileLoader(string filePath) {
    // Load file using filepath

    // The command needed to extract plain text from .docx
    string cmd = "unzip -p  word/document.xml | sed -e 's/<[^>]\\{1,\\}>//g; s/[^[:print:]]\\{1,\\}//g'";

    // Insert the filePath into the command
    cmd.insert(9, filePath);

    FILE *fp;
    fp = popen(cmd.c_str(), "r");

    if (fp == nullptr) {
        exit(EXIT_FAILURE);
    }

    char *lineBuffer = nullptr;
    size_t lineSize = 0;
    do {
        ssize_t linlen = getline(&lineBuffer, &lineSize, fp);
        if (linlen<=0) break;
        this->fileContents += string(lineBuffer, linlen);
    } while (!feof(fp));

    free(lineBuffer), lineBuffer =nullptr;

    int excod = pclose(fp);
    if (excod != 0)
        return;
}