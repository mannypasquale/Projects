#include "Shape.h"

std::ostream & operator<<(std::ostream &out, Shape &shape) {
    shape.printMe(out);

    return out;
}
