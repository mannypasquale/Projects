#!/bin/bash

python3 client.py localhost 12346 GET test.txt 1 &
python3 client.py localhost 12346 GET test.txt 2 &
python3 client.py localhost 12346 GET test.txt 3 &
python3 client.py localhost 12346 GET test.txt 4 &
python3 client.py localhost 12346 GET test.txt 5 &
python3 client.py localhost 12346 GET test.txt 6 &
python3 client.py localhost 12346 GET test.txt 7 &
python3 client.py localhost 12346 GET test.txt 8 &
python3 client.py localhost 12346 GET test.txt 9 &
python3 client.py localhost 12346 GET test.txt 10 &