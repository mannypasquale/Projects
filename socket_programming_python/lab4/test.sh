#!/bin/bash

python3 client.py localhost 12345 1 GET test.txt &
python3 client.py localhost 12345 2 GET test.txt &
python3 client.py localhost 12345 3 GET test.txt &
python3 client.py localhost 12345 4 GET test.txt &
python3 client.py localhost 12345 5 GET test.txt &
python3 client.py localhost 12345 6 GET test.txt &
python3 client.py localhost 12345 7 GET test.txt &
python3 client.py localhost 12345 8 GET test.txt &
python3 client.py localhost 12345 9 GET test.txt &
python3 client.py localhost 12345 10 GET test.txt &