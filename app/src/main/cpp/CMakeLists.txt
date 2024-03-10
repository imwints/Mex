cmake_minimum_required(VERSION 3.22.1)

project("mex" CXX)

add_library(filesystem SHARED filesystem.cpp)

target_compile_options(filesystem PUBLIC -Wall -Wextra -Wpedantic -Wshadow -Wold-style-cast
        -fno-exceptions -fno-rtti)
target_link_libraries(filesystem android log)
