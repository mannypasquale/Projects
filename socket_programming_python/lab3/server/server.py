#!/usr/bin/python
import socket
import sys
import os

verbose = False
sock = socket.socket()
port = sys.argv[1]
port = int(port)
if '-v' in sys.argv:
    verbose = True 
sock.bind(("", port ))
sock.listen(1) #only 1 connection
if verbose == True:
    print("Server waiting on port %d" % (port))

conn, address = sock.accept()
ready_check = "READY"
conn.send(ready_check.encode('UTF-8'))
command = conn.recv(1024)
command = command.decode()
file_name = conn.recv(1024)
file_name = file_name.decode()
address2 = str(address)

if verbose == True:
    print("Server connected to client at %s " % (address2))
if verbose == True:
    print("Serve receiving request: %s %s " % (file_name, command))


if os.path.isfile('./' + file_name) and command == "GET":
    print(file_name, command)

    file_exists = "OK"
    conn.send(file_exists.encode('UTF-8'))
    ready_check = conn.recv(1024)
    ready_check = ready_check.decode()

    if ready_check == "READY":
        file_size = os.path.getsize('./' + file_name)

        conn.send(file_size.to_bytes(8, byteorder="big", signed=False))


        ok_check = conn.recv(1024)
        ok_check = ok_check.decode()
        try:
            file = open(file_name, 'rb')
            if ok_check == "OK":
                if verbose == True:
                    print("Server sending %d bytes" % (int(file_size)))
                    
                tosend = file.read(1024)
                while tosend:
                    conn.sendall(tosend)
                    tosend = file.read(1024)
                file.close()
                done = "DONE"
                conn.send(done.encode("utf-8"))
        except IOError:
            errorTry = "ERROR: " + file_name + " file exists but cant open"
            conn.send(errorTry.encode())
            ok_check = 'nahhh'
    conn.close()

if command == "PUT":
    conn.send("OK".encode())
    bytes_left = conn.recv(1024)
    bytes_left = int.from_bytes(bytes_left, byteorder='big', signed=False)

    try:
        file = open(file_name, 'wb')
        conn.send("OK".encode())
        if verbose == True:
            print("Server is receiving %d bytes" % (bytes_left))

        num_packets = (bytes_left / 1024) + 1
        n = 0
        while n < num_packets:
            line = conn.recv(1024)
            file.write(line)
            n = n + 1
        check_done = conn.recv(1024).decode("UTF-8")
        if check_done == "DONE":
            print("COMPLETE")
        else:
            print(check_done)


    except IOError:
        error2 = "ERROR: unable to create your file " + file_name
        #conn.send(error2.encode())
    conn.close()

if command == "DEL":
    conn.send("OK".encode())
    if verbose == True:
        print("Server gone delete %s file" % (file_name))
    if os.path.isfile('./' + file_name) == False:
        error = "ERROR: %s does not exist" % (file_name)
        conn.send(error.encode())
        conn.close()
    if os.path.isfile('./' + file_name):
        try:
            os.remove(file_name)
            conn.send("Server successfully removed file".encode())
            conn.close()
        except IOError:
            error = ("ERROR: unable to remove %s " % (file_name))
            conn.send(error.encode())
            conn.close()