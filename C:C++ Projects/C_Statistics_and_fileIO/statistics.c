//
// Created by Manny Pasquale on 2018-01-30.
//

//#include "statistics.h"

#include <math.h>
#include "statistics.h"

double mean(const double sum, const int count){
    double SUM = sum;
    int COUNT = count;
    double result_mean = SUM/COUNT;
    return result_mean;
}

double ssdev(const double sum, const double sumsq, const int count){
    double result = 0;
    //result = sqrt((count*sumsq)-pow(sum,2)/(count*(count-1.0)));
    double first_OP = count * sumsq;
    double second_OP = pow(sum, 2);
    double third_op = first_OP - second_OP;
    double fourth_op = third_op/(count*(count-1));
    double final_calc = sqrt(fourth_op);

    return final_calc;

}
