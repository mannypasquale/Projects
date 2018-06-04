import sys
import socket
import os
import collections
import time
import threading

class ClientHandler(threading.Thread):
    def __init__(self, client):
        threading.Thread.__init__(self)
        self.client = client

    def run(self):
        if ('-v' in sys.argv):
            print("STARTED")

        if '-v' in sys.argv:
            print("Server connected to client at %s" % (addr[1]))
        
        self.client.send("READY".encode("UTF-8"))
        if '-v' in sys.argv:
            print("sent ready")
        commandFile = self.client.recv(1024).decode("UTF-8")
        commandFile = commandFile.split()
        file = commandFile[1]

        if '-v' in sys.argv:
            print("Server receiving request: %s" % (commandFile))

        if commandFile[0] == "GET":
            try:
                f = open(file, 'rb')
            except IOError:
                error = "ERROR: " + file + " does not exist"
                self.client.send(error.encode("UTF-8"))

            self.client.send("OK".encode("UTF-8"))
            readyCheck = self.client.recv(1024).decode("UTF-8")
            if "READY" in readyCheck:
                fileSize = int(os.stat(file).st_size)
                self.client.send(fileSize.to_bytes(8, byteorder="big"))
                okCheck = self.client.recv(1024).decode("UTF-8")

                if "OK" in okCheck:
                    if '-v' in sys.argv:
                        print("Server sending %d bytes" % (fileSize))
                    toSend = f.read(1024)
                    while toSend:
                        self.client.sendall(toSend)
                        toSend = f.read(1024)
                    f.close()
                    self.client.send("DONE".encode("UTF-8"))

        elif commandFile[0] == "PUT":
            self.client.send("OK".encode("UTF-8"))
            
            
            bytes_remaining = self.client.recv(1024)
            bytes_remaining = int.from_bytes(bytes_remaining, byteorder='big')
            original_size = bytes_remaining

            try:
                f = open(file, 'wb')
            except IOError:
                error = "ERROR: unable to create file " + file
                self.client.send(error.encode("UTF-8"))

            self.client.send("OK".encode("UTF-8"))
            toRecv = self.client.recv(min(bytes_remaining, 1024))
            if '-v' in sys.argv:
                print("Server receiving %d bytes" % (original_size))
            while toRecv:
                #print("%d of %d remaining..." % (bytes_remaining, original_size))
                f.write(toRecv)
                bytes_remaining = bytes_remaining - len(toRecv)
                toRecv = self.client.recv(min(bytes_remaining, 1024))
            f.close()
            self.client.send("DONE".encode("UTF-8"))

        if commandFile[0] == "DEL":
            if '-v' in sys.argv:
                print("Server deleting file %s" % (file))

            try:
                os.remove(file)
            except IOError:
                error = ("ERROR: %s does not exist" % (file))
                self.client.send(error.encode('utf-8'))
            self.client.send("DONE".encode("UTF-8"))
            self.client.close()                # Close the connection



class Manager(threading.Thread):
    def __init__(self, limit):
        threading.Thread.__init__(self)
        self.waiting = collections.deque()
        self.running = set()
        self.limit = limit


    def run(self):
        print("Manager Started")
        while(True):
            kick = []
            for thread in self.running:
                if not thread.is_alive():
                    kick.append(thread)
            for thread in kick:
                self.running.remove(thread)
            if not self.waiting:
                time.sleep(1)
                continue

            ammt = len(self.running)
            if ammt == self.limit:
                # wait to check again
                time.sleep(1)
                continue
            else:
                # add the thread
                thread = self.waiting.popleft()
                thread.start()
                self.running.add(thread)

if __name__ == "__main__":
    port = int(sys.argv[1])
    limit = int(sys.argv[2])
    manager = Manager(limit)
    manager.start()
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serverSocket.bind(("", port))
    serverSocket.listen(0)

    while True:
        client, addr = serverSocket.accept()
        thread = ClientHandler(client)
        manager.waiting.append(thread)
