# spelling-checker
Design, implement and test a spelling checker

![Code Language](https://img.shields.io/badge/language-C%2B%2B-green.svg)

You can perform a spelling check on any of your own written work as follows.

1. Convert a document of your own that is to be checked into plaintext (.txt) format.
2. The attached file dictionary.txt contains a list of English words taken from a dictionary.
3. Write a hashing program to input the words from dictionary.txt into a hash table using a hashing algorithm and collision resolution strategy of your choice.  For information dictionary.txt contains 80368 words, 1 per line.
4. When the dictionary has been loaded to the hash table you can then check your own document by simply checking for the presence of each word in turn.  If the word is found then do nothing, if the word is not present then you can output that the word is unrecognized or incorrectly spelled to the user.
4. Include some analysis for your hash table to measure the load factor and the average number of hops required, separately for successful and unsuccessful searches.
5. Aim to obtain an optimum performance by making changes to the hashing algorithm to minimize the average number of hops per search.
6. You should record the results for your various trials and give an account of your development strategy.
