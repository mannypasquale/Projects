//
// Created by Manny Pasquale on 2018-03-13.
//

#include "Square.h"

/**
 * The draw() member function
 */
void Square::draw() const {
    std::cout << "Square of size [" << getWidth() << "] drawn at " << getX() << " " << getY() << std::endl;
}

void Square::printMe(std::ostream& os) const {
    os << "Square of size [" << getWidth() << "] at " << getX() << " " << getY();
}
