#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "statistics.h"

void printStats(FILE *dest, const int count, const double mean, const double ssdev){
    fprintf(dest, "%d Values, Mean = %lf, Sample Standard Deviation = %lf\n", count, mean, ssdev);
}

int main(int argc, char *argv[]) {

    //make sure command line arguments are 2 and only 2
    //if not exit program
    if (argc != 2){
        printf(stderr, "Usage: %s fileName\n",argv[0]);
        return EXIT_FAILURE;
    }

    //char *argument_array[2] = {argv[0], argv[1]};
    char *program_name = argv[0];
    char *file_name = argv[1];
    //count the number of numbers read in from the file
    int count = 0;
    //grab the sum of all the numbers read in from the file
    double sum = 0;
    //grab sum of squares too while youre at it
    double sum_squares = 0;
    //constant prefix for the result
    // open a file and start reading in the numbers and get the count and sum
    // update sum and count as you interate through the file without storing the
    // numbers in an array

    FILE *inFile;


    if((inFile = fopen(file_name, "r+"))== NULL){
        fprintf(stderr, "%s: Unable to open %s\n", program_name, file_name);
        return EXIT_FAILURE;

    }
    //var storing each double in the file
    double x;

    while (fscanf(inFile, "%lf", &x)!=EOF){
        count++;
        sum += x;
        sum_squares = sum_squares + (x * x);

    }
    fclose(inFile);
    // only write to file if you read in at least 2 numbers
    if (count < 2){
        fprintf(stderr, "There were less than 2 values read from the file: %s", file_name);
        return EXIT_FAILURE;
    }

    double meanVal = mean(sum,count);
    double ssdevVal = ssdev(sum, sum_squares, count);

    //printStats(inFile, count, _mean, _ssdev);
    
    const char *prefix = "Result_";

    int len = strlen(prefix) + strlen(argv[1]);
    // Check for sane len?
    char *outName = malloc(len + 1);
    strncpy(outName, prefix, len);
    strncat(outName, argv[1], len - strlen(prefix));

    FILE *outFile;

    if((outFile = fopen(outName, "w+")) == NULL) {
        fprintf(stderr, "Unable to open: %s for writing", outName);
        return EXIT_FAILURE;
    }
    printStats(stdout, count, meanVal, ssdevVal);
    printStats(outFile, count, meanVal, ssdevVal);

    fclose(outFile);

    return EXIT_SUCCESS;

}



