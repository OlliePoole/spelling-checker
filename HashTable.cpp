//
// Created by Oliver Poole on 13/10/15.
//

#include "HashTable.h"

HashTable::HashTable(string filePath) {

    // Load the .txt file
    ifstream dictionaryFile;
    dictionaryFile.open(filePath, ios::in);

    // Fill hash table
    string line = "";

    // If the dictionary file is still open
    if (dictionaryFile.is_open()) {
        // Repeat while there are lines available
        while (getline(dictionaryFile, line)) {

            // convert string to char * for inserting
            char *keyToAdd;
            keyToAdd = (char *)line.c_str();

            // Add vale to hashtable
            addValueToTable(keyToAdd);
        }
        dictionaryFile.close();
    }
}

unsigned HashTable::hash(const char *key, int length) {

    unsigned h = 0;
    int i;

    for (i = 0; i < length; i++) {
        h = 50 * h^key[i];
    }
    return h % this->tableSize;
}

void HashTable::addValueToTable(const char *key) {

    if (this->findValue(key) == false) {

        unsigned index = hash(key, sizeof(key));
        
        node *newNode = (node *)malloc(sizeof *newNode);
        
        if (newNode == NULL)
            return;
        
        newNode->data = (char *)malloc(sizeof(key) + 1);
        
        if(newNode->data==NULL)
            return;
        
        // Assign the data to the new node
        strcpy(newNode->data,key);
        
        // Set the current node at the table[index] to the next value
        newNode->next=table[index];
        
        // Add the node to the table
        table[index]=newNode;
    }
}


int HashTable::findValue(const char * key) {
    unsigned index = this->hash(key, sizeof(key));

    const node *currentNode = this->table[index];

    //Search for the value in the hash table
    while (currentNode != NULL) {
        
        // Does the key match the value of the node?
        if (strcmp(key, currentNode->data) != 0) {
            currentNode = currentNode->next;
        }
        else { // The keys match!
            return true;
        }
    }

    return false;
}