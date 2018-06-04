#!/usr/bin/python

import socket
import sys
import os
import time
import threading
import collections

if __name__ == '__main__':
# We will impose a limit on how big the “running” set may be,
# so that the clients are limited to how much of the server’s resources 
# they can collectively use.  The server will now take an additional 
# commandline parameter (sys.argv[2]) that says how many clients may actually 
# be actively running concurrently (e.g. 5 -- don’t make it too big!).  
# Note that sys.argv[1] will still be the server’s binding port. 
# You can implement a -v flag if you like, but it’s not required and will 
# not be graded; your server should not have any output as the default option.
    port = sys.argv[1]
    port = int(port)
    limit = sys.argv[2]
    limit = int(limit)

    manager = Manager(limit)
    manager.start()
    s = socket.socket()
    s.bind(('', port))
    s.listen(0)

    while True:
        conn, addr = s.accept()
        t = ClientHandler(conn)
        manager.q.append(t);

class Manager (threading.Thread):
# heck the “running” threads; if any of them have stopped, remove them from the set.
# check the waiting queue:
#   if empty, sleep for 1 second and return to the top of the loop;
#   if it has an item:
#       check the size of the running set:
#           if it is full, sleep for 1 second and return to the top of the loop;
#           if it has space:
#               remove the next client thread from the queue
#               start the thread
#               add the thread to the running set

    def __init__(self, limit):

        threading.Thread.__init__(self)
        self.q = collections.deque()
        self.running = set()
        self.limit = limit

    def run(self):
        #print("Threading manager started before ClientHandler")
        while True:
            kick = []
            for t in self.running:
                if not t.isAlive():
                    kick.append(t)
            for t in kick:
                self.running.remove(t)


            if ((len(self.limit)) == (len(self.q))):
                time.sleep(1)
                continue;
            else:
                popped_thread = self.q.popleft()
                popped_thread.start()
                self.running.add(popped_thread)




class ClientHandler (threading.Thread):


    def __init__(self, conn):

        threading.Thread.__init__(self)
        self.conn = conn


    def run(self):

        self.conn.send("READY".encode())
        #verbose = False
        command = self.conn.recv(1024).decode()
        file_name = self.conn.recv(1024).decode()
        #if '-v' in sys.argv:
            #verbose = True 
        #if verbose == True:
            #print("REQUESSSST RECEIVED: %s ", % (command, file_name))



    if os.path.isfile('./' + file_name) and command == "GET":


        file_exists = "OK"
        self.conn.send(file_exists.encode('UTF-8'))
        ready_check = self.conn.recv(1024)
        ready_check = ready_check.decode()

        if ready_check == "READY":
            file_size = os.path.getsize('./' + file_name)

            self.conn.send(file_size.to_bytes(8, byteorder="big", signed=False))


            ok_check = self.conn.recv(1024)
            ok_check = ok_check.decode()
            try:
                file = open(file_name, 'rb')
                if ok_check == "OK":
                    #if verbose == True:
                        #print("Server sending %d bytes" % (int(file_size)))
                        
                    tosend = file.read(1024)
                    while tosend:
                        self.conn.sendall(tosend)
                        tosend = file.read(1024)
                    file.close()
                    done = "DONE"
                    self.conn.send(done.encode("utf-8"))
            except IOError:
                errorTry = "ERROR: " + file_name + " file exists but cant open"
                self.conn.send(errorTry.encode())
                ok_check = 'nahhh'
        self.conn.close()

    if command == "PUT":
        self.conn.send("OK".encode())
        bytes_left = self.conn.recv(1024)
        bytes_left = int.from_bytes(bytes_left, byteorder='big', signed=False)

        try:
            file = open(file_name, 'wb')
            self.conn.send("OK".encode())
            #if verbose == True:
                #print("Server is receiving %d bytes" % (bytes_left))

            num_packets = (bytes_left / 1024) + 1
            n = 0
            while n < num_packets:
                line = self.conn.recv(1024)
                file.write(line)
                n = n + 1
            check_done = self.conn.recv(1024).decode("UTF-8")
            #if check_done == "DONE":
                #print("COMPLETE")
            #else:
                #print(check_done)


        except IOError:
            error2 = "ERROR: unable to create your file " + file_name
            #conn.send(error2.encode())
        self.conn.close()

    if command == "DEL":
        self.conn.send("OK".encode())
        #if verbose == True:
            #print("Server gone delete %s file" % (file_name))
        if os.path.isfile('./' + file_name) == False:
            error = "ERROR: %s does not exist" % (file_name)
            self.conn.send(error.encode())
            self.conn.close()
        if os.path.isfile('./' + file_name):
            try:
                os.remove(file_name)
                self.conn.send("Server successfully removed file".encode())
                self.conn.close()
            except IOError:
                error = ("ERROR: unable to remove %s " % (file_name))
                self.conn.send(error.encode())
                self.conn.close()


