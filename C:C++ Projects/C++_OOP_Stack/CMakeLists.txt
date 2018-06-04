cmake_minimum_required(VERSION 3.9)
project(lab07off)

set(CMAKE_CXX_STANDARD 11)

add_executable(lab07off main.cpp Rectangle.cpp Rectangle.h Circle.cpp Circle.h Shape.cpp Shape.h Square.cpp Square.h StackException.h ShapeStack.cpp ShapeStack.h)