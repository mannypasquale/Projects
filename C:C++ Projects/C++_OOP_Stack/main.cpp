/*
 * A main() to demonstrate inheritance, polymorphism, virtual functions,
 * and an abstract class.
 * Uses classes: Shape, Rectangle, and Circle.
 * 
 * File:   main.cpp
 * Author: dale
 *
 * Created on November 7, 2011, 2:27 PM
 */
#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include "Rectangle.h"
#include "Circle.h"
#include "ShapeStack.h"
#include "Square.h"


using namespace std;

int main(int argc, char* argv[]) {

    //const size_t initial_size = 10;
    // creating shape objects
    Circle c1(20.5, 4, 10);
    Rectangle r1(10, 20.7, 8, 8);
    Circle c2(11, 10, 5);
    Square s1(10, 8, 8);

    // creating shapes vecotr
    vector<Shape*> shapeVector;

    cout << "current capacity: " << shapeVector.capacity() << endl;
    cout << "current size: " << shapeVector.size() << endl;
    cout << "current max size of your shapes vector: " << shapeVector.max_size() << endl;

    // pushing shapes into vector
    try{
        shapeVector.push_back(&c1);
        shapeVector.push_back(&r1);
        shapeVector.push_back(&c2);
        shapeVector.push_back(&s1);
        shapeVector.push_back(&s1);
    } catch (exception& e){
        e.what();
    }




    cout << "\ncurrent capcity after 5 push backs: " << shapeVector.capacity() << endl;
    cout << "current size after 5 push backs: " << shapeVector.size() << endl;

    if (!shapeVector.empty()){
        shapeVector.pop_back();
    }

    cout << "\ncurrent capcity after 1 pop back: " << shapeVector.capacity() << endl;
    cout << "current size after 1 pop back: " << shapeVector.size() << endl;

    cout << "\ndrawing the front and back of shapes vector in that order: " << endl;
    if (!shapeVector.empty()){
        shapeVector.front()->draw();
        shapeVector.back()->draw();
    }



    shapeVector.insert(shapeVector.begin(), &s1);
    cout << "Current capacity after insert: " << shapeVector.capacity() << endl;
    cout << "current size after insert: " << shapeVector.size() << endl;


    cout << "\ndrawing the front and back of shapes vector in that order after an insert: " << endl;
    if (!shapeVector.empty()){
        shapeVector.front()->draw();
        shapeVector.back()->draw();
    }



    // replace index 0 with a circle again
    try{
        shapeVector.at(0) = &c1;
    } catch (out_of_range& e){
        cout << "OUT OF BOUNDS" << endl;
    }

    cout << "\ndrawing the front and back of shapes vector in that order after replacing index 0 with circle: " << endl;
    if (!shapeVector.empty()){
        shapeVector.front()->draw();
        shapeVector.back()->draw();
    }


    //print out vector using iterator
    cout << "Printing out the vector: \n" << endl;
    cout << "there are: " << shapeVector.size() << " Items in the vec\n " << endl;
    for(vector<Shape*>::iterator it1 = shapeVector.begin(); it1 != shapeVector.end(); it1++){
        Shape *testShape = *it1;
        (*it1)->draw();
    }


     //trying to access index not in vector
    try{
        shapeVector.at(50);
    } catch (out_of_range& e){
        cout << "out of bounds" << endl;
    }



    return EXIT_SUCCESS;
}
