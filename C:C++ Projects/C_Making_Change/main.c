/**
 * This program makes change using canadian currency denominations
 *
 * Manny Pasquale
 * Jan. 16 2018
 */

#include <stdio.h>
#include <math.h>

int main() {
    double price;
    double tendered;
    double changeDue;
    double rndChangeDue;
    //ask user for total purchase price
    printf("Enter the amount of the purchase: ");
    //assign variable to amount entered
    scanf("%lf", &price);

    // ask user for amount tendered
    printf("Enter the amount tendered: ");
    //assign tendered
    scanf("%lf", &tendered);

    //calculate change due
    changeDue = tendered - price;
    printf("Change due is $%.2lf\n", changeDue);

    //recalculate change due to nearest nickel
    rndChangeDue = roundf(changeDue * 20)/20;
    printf("Rounded to the nearest nickel $%.2lf\n", rndChangeDue);

    //here we want to start with 20 and move down
    // numOfChangeItems = changeDue / changeItemValue --> how many change items equals the change due
    // divided by how much the change item youre checking is worth
    // use for loop because we know how many tests we are doing 20/10/5/2/1/.25/.10/.5

    // array with all necessary denominations
    double denominations[] = {20, 10, 5, 2, 1, .25, .10, .05};
    // string array with twendy ten etc
    char *strs[] = {"Twenties", "Tens", "Fives", "Toonies", "Loonies", "Quarters", "Dimes", "Nickels"};
    int i;
    double numOfChangeItems;
    for(i = 0; i < 8; i = i + 1){

        if (denominations[i] <= rndChangeDue){
            numOfChangeItems = rndChangeDue / denominations[i];
            rndChangeDue = rndChangeDue - ((int)numOfChangeItems * denominations[i]);
            int num;
            num = (int)numOfChangeItems;
            printf("%d  ", num);
            printf(strs[i]);
            printf("\n");
        } else{
            printf("0  ");
            printf(strs[i]);
            printf("\n");
        }

    }


}