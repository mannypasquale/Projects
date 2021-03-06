//
// Created by Manny Pasquale on 2018-03-13.
//

/*
 * A Rectangle that is derived from Shape
 *
 * File:   Rectangle.h
 * Author: dale
 *
 * Created on October 31, 2011, 2:10 PM
 */

#ifndef SQUARE_H
#define	SQUARE_H
#include <iostream>
#include "Rectangle.h"

class Square : public Rectangle {

private:
//    double length, width;
protected:
    void printMe(std::ostream& os) const;
public:
    /**
     * This constructor passes the x,y location to the base class constructor
     * in Shape and also initializes the Rectangle's size.
     *
     * We choose to make this an inline function.
     *
     * @param l Length
     * @param l Width
     * @param xLoc Initial x coordinate
     * @param yLoc Initial y coordinate
     */

    inline Square(double side, int x, int y)
            : Rectangle(side, side, x, y) {
      }
//
//    inline double getLength(void) const {
//        return length;
//    }
//
//    inline double getWidth(void) const {
//        return width;
//    }

    /**
     * The Rectangle "draws" itself at its current location
     */
    void draw() const;
};


#endif	/* SQUARE_H */
