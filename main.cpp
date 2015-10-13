#include <iostream>

using namespace std;

int addNumbers(int a, int b) {
    int total = a + b;

    return  total;
}

int main() {
    cout << "Hello, World!" << endl;
    cout << "My name is Ollie!" << endl;

    int total = addNumbers(2, 10);

    cout << "The total is " << total;
    return 0;
}