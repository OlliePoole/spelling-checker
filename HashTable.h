//
// Created by Oliver Poole on 13/10/15.
//

#include <iostream>
#include <fstream>

#ifndef SPELLING_CHECKER_HASHTABLE_H
#define SPELLING_CHECKER_HASHTABLE_H

using namespace std;

/*
 * Structure for storing the values for the table collisions
 */
typedef struct node {
    char *data;
    struct node *next;
} node;

class HashTable {

    void addValueToTable(const char *);
    void createEmptyTable();

    /*
     * Method to hash a key
     *
     * param: String - the string to hash
     * param: int    - The length of the key to hash
     *
     * returns: The hash value of the key
     */
    unsigned hash(const char *, int);

    const int tableSize = 15000;

    node *table[15000];

public:

    /*
     * param: String - The filepath of the dictionary.txt file
     */
    HashTable(string);

    int findValue(const char *);
};


#endif //SPELLING_CHECKER_HASHTABLE_H
