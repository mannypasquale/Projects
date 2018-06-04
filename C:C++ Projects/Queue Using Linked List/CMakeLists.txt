cmake_minimum_required(VERSION 3.9)
project(Lab04Off C)

set(CMAKE_C_STANDARD 99)

add_executable(Lab04Off queueMain.c queue.h queue.c)