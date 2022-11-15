#!/bin/bash
g++ -c pong.cpp -std=c++11
g++ pong.o -o Pong -lsfml-graphics -lsfml-window -lsfml-system
