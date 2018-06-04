//
// Created by Manny Pasquale on 2018-03-03.
//

#include <exception>
using namespace std;

 class NegativeValueException : public exception {
 public:
         const char* what() const throw()        {
            cout <<  "Number Must be greater than ZERO!!!!!\n";
        }

};



