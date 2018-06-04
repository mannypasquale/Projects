#!/usr/bin/python

import socket
import sys
import os
import time
import math

host = sys.argv[1]
port = sys.argv[2]
port = int(port)
command = sys.argv[4]
file_name = sys.argv[5]
t_id = sys.argv[3]
#CONNECT TO SERVER
s = socket.socket()
s.connect((host, port))
print("client connected   ", s)


#RECEIVE SERVER READY MESSAGE
handshake = s.recv(1024)
handshake = handshake.decode()
#print(handshake)
time.sleep(random.randint(2, 6))
if handshake == 'READY':
    print("GOT READYYYYYYYY")

    
   

    if command == 'GET':
        
        s.send(command.encode('UTF-8'))
        s.send(file_name.encode())
        handshake_ok = s.recv(1024).decode('UTF-8')
        if handshake_ok == 'OK':
            try:
                file = open(file_name, 'wb')
                s.send(handshake.encode('UTF-8'))
                file_size_in_bytes = s.recv(1024)
                file_size_in_bytes = int.from_bytes(file_size_in_bytes, byteorder='big', signed=False)
                num_packets = (file_size_in_bytes/1024)
                n = 0
                s.send(handshake_ok.encode())
                while n < num_packets:
                	line = s.recv(1024)
                	file.write(line)
                	n = n + 1
                
                check_done = s.recv(1024).decode("UTF-8")


                if check_done == "DONE":
                	print("FINISHED")
                	s.close()
                elif "ERROR" in check_done:
                	print("server error getting file %s " % (file_name))
                	s.close()
            except IOError:
                print("Unable to create file " + file_name)
                s.close()
                sys.exit()
    if command == "PUT":
    	s.send(command.encode("UTF-8"))
    	s.send(file_name.encode())
    	handshake_ok = s.recv(1024).decode("UTF-8")
    	if handshake_ok == "OK":
    		file_size = os.path.getsize('./' + file_name)
    		s.send(file_size.to_bytes(8, byteorder="big", signed=False))
    		try:
    			file = open(file_name, 'rb')
    			tosend = file.read(1024)
    			while tosend:
    				s.sendall(tosend)
    				tosend = file.read(1024)
    			file.close()
    			s.send("DONE".encode("UTF-8"))
    			s.close()
    		except IOError:
    			error = "ERROR: " + file_name + " cant open file name"
    			s.send(error.encode())
    			s.close()
    if command == "DEL":
    	s.send(command.encode("UTF-8"))
    	s.send(file_name.encode())
    	handshake_ok = s.recv(1024).decode("UTF-8")
    	check = s.recv(1024)
    	if "ERROR" in check.decode():
    		print(check.decode())
    		s.close()
    	else:
    		#print(check.decode())
    		s.close()

