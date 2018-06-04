#include <iostream>
#include "NegativeValueException.h"
#include "structsHead.h"
#include <iomanip>
#include <cmath>

using namespace std;

#define N_CURRENCY 8

// array holding currency
static const Currency arrayOfDenominations[N_CURRENCY] =
        {{ 20.00, "Twenty", "Twenties"},
         {10.00, "Ten", "Tens"},
         {5.00, "Five", "Fives"},
         {2.00, "Twoonie", "Twoonies"},
         {1.00, "Loonie", "Loonies"},
         {0.25, "Quarter", "Quarters"},
         {0.10, "Dime", "Dimes"},
         {0.05, "Nickel", "Nickels"}};

// function declartion
double getMoneyValue(string prompt) throw(NegativeValueException);

// function declaration
ChangeValue makeChange (double balance, double denominationValue);

int main() {

    string purchasePrice = "TotalPurchasePrice: ";
    string amountTendered = "AmountTendered: ";
    double price;
    double amountPaid;
    bool properInput = false;
    // loop through looking for correct values, repeating if incorrect catching exceptions
    do {
        try {
            price = getMoneyValue(purchasePrice);

        } catch (NegativeValueException &e) {
            e.what();
            continue;
        }
        try {
            amountPaid = getMoneyValue(amountTendered);

        } catch (NegativeValueException &e) {
            e.what();
            continue;
        }
        if (amountPaid < price){
            cout << "NOT ENOUGH MONEY TRY AGAIN" << endl;
            continue;
        }
        if (price > 0 && price <= amountPaid && amountPaid >= 0 && amountPaid >= price){
            properInput = true;
        }
    } while (properInput == false);

    double amountOwed = amountPaid - price;
    cout << "Change due: " << amountOwed << endl;

    amountOwed = roundf(amountOwed * 20) / 20;
    cout << "Change due rounded to the nearest nickel: " << setprecision(2) << fixed << amountOwed  << endl;
    // go through array of denoms calling make chance on each and printing if there is a new value

    for (int i = 0; i < N_CURRENCY; i++){
        double denom = arrayOfDenominations[i].value;

        ChangeValue newValue = makeChange(amountOwed, denom);
        //only print if value is one or more
            // if more than one print plural
            if (newValue.numberReturned > 1){
                cout << newValue.numberReturned << "    " << arrayOfDenominations[i].pluralName << endl;
                amountOwed = newValue.revisedBalance;
            } else if (newValue.numberReturned == 1){
                cout << newValue.numberReturned << "    " << arrayOfDenominations[i].name << endl;
                amountOwed = newValue.revisedBalance;
            }
    }

    return EXIT_SUCCESS;
}

//prompt user for input throw exception if negative
double getMoneyValue(string prompt) throw(NegativeValueException) {
    double x;

    cout << prompt;
    cin >> x;
    if (x <= 0) {
        throw NegativeValueException();
    }
    return x;
}

//check if change is needed on denomination value
ChangeValue makeChange (double balance, double denominationValue) {
    ChangeValue value;
    double newBalance;
    int numOfItem;

    if (denominationValue <= balance){

        numOfItem =  (balance / denominationValue);
        value.numberReturned = numOfItem;
        newBalance = balance - (denominationValue * numOfItem);
        value.revisedBalance = newBalance;
        return value;
    }

    value.revisedBalance = balance;
    value.numberReturned = 0;
    return value;
}







