#!/usr/bin/python

from sys import argv
import sys
import socket


 #this will be the input from the terminal have to have a variable to grab the 
# entry of the actual program client.py gets sent as an argument
 # how we join a list into a string
 # This will be the host after the https:// but before the resource

 #this will be the /resource, if the argv doesnt have one resource shall be set to "/"
# Here i need to create a function maybe??? or just read the url in and assign the proper substrings to
# The proper variables. for loop maybe?
# in it we need to check to see that https:// was given
# try using the split to split it up by the https and the / after for resource

"""
Here i assigned protocol to the https:// since we assume it always starts with that
I then remove the https:// from the url passed through the console.
from here i need to split the host from the resource--- did this by converted the host list into a string
and splitting the string on the first / which separates the host from the resource then i grab the proper
index and assign the index to the corresponding variable and add '/' for resource since it 

These if statements are there to check if its https:// protocol or just http:// and assign
the variables accordingly
"""
program_name, weburl = argv
weburl_string = ''.join(weburl)

if 'https://' in weburl_string:
	protocol = "https://" #in lab we assume this is what they entered
	host = weburl_string.split('https://',1) #split on https:// which gives us the rest of the url
	# but we need to now split again on the first forward slash or give it one if not there
	host = ''.join(host)#makes the lsit back into string after split on protocol
	#host = host.split('/', 1)
	if '/' in host:
		host = host.split('/',1)
		resource = '/' + host[1]
		host = host[0]
	else:
		resource = '/'

if 'http://' in weburl_string:
	
	protocol = "http://" #in lab we assume this is what they entered
	host = weburl_string.split('http://',1) #split on https:// which gives us the rest of the url
	# but we need to now split again on the first forward slash or give it one if not there
	host = ''.join(host)#makes the lsit back into string after split on protocol
	# host = host.split('/', 1)
	if '/' in host:
		host = host.split('/', 1)
		resource = '/' + host[1]
		host =  host[0]
	else:
		resource = '/'






"""
Using the socket library i am creating two http connections to both the webserver and the html2text one
"""
html2textHost = 'rtvm.cs.camosun.bc.ca'
html2textHostPort = 10010

s1 = socket.socket()
s2 = socket.socket()
s1.connect((host, 80))
s2.connect((html2textHost, html2textHostPort))
s2.recv(1024)	

state = 1

while state != 4:
	
	if state == 1:
		#print('state 1')
		request = (("GET /" + resource + " HTTP/1.1\nHost: ") + host + "\n\n")
		s1.send(request.encode())
		current_Block = s1.recv(1024)
		current_Block = current_Block.decode()
		if '<HTML>' in current_Block.upper():
			state = 2

	if state == 2:

		if '<HTML>' and '</HTML>' in current_Block.upper():
			toSend = current_Block.split()

			for index, elem in enumerate(toSend):
				if '<HTML>' in elem.upper():
					indexofhtmltag = index
				if '</HTML>' in elem.upper():
					indexofendtag = index
			toSend = " ".join(toSend[indexofhtmltag:indexofendtag + 1])
			s2.send(toSend.encode())
			state = 3
		
		if '<HTML>' and not '</HTML>' in current_Block.upper():
			
			toSendCurrentBlock = current_Block.split()
			for index, elem in enumerate(toSendCurrentBlock):
				if '<HTML>' in elem.upper():
					#print('checking for html in elem.upper \n')
					indexofhtmltag = index
				if '</HTML>' in elem.upper():
					indexofendtag = index
					toSendCurrentBlock = current_Block.split()
					toSendCurrentBlock = " ".join(toSendCurrentBlock[indexofhtmltag:indexofendtag+1])
					s2.send(toSendCurrentBlock.encode())
					state = 3
					done = True

			toSendCurrentBlock = current_Block.split()
			toSendCurrentBlock = " ".join(toSendCurrentBlock[indexofhtmltag:])
			s2.send(toSendCurrentBlock.encode())
			done = False
			while done == False:
				#print('enetered while loop')
				toSendFutureBlock = s1.recv(1024)
				toSendFutureBlock = toSendFutureBlock.decode()
				toSendFutureBlock = toSendFutureBlock.split()
				
				for index, elem in enumerate(toSendFutureBlock):
					#print(index,elem)
					if '</HTML>' in elem.upper():

						indexofendtag = index
						done = True
						toSendFutureBlock = " ".join(toSendFutureBlock[:indexofendtag + 1])
						state = 3
						s2.send(toSendFutureBlock.encode())

				if done != True:
					toSendFutureBlock = " ".join(toSendFutureBlock)
					
					s2.send(toSendFutureBlock.encode())

		#nextBlock = s1.recv(1024)
		#nextBlock = nextBlock.decode()
		#if '<HTML>' in current_Block.upper() + nextBlock.upper():
			#print('im in that one that adds the two together lol')
			#break


			
			

	if state == 3:

		
		done = False
		
		while (done == False):
			receivedBlock = s2.recv(1024)
			receivedString = receivedBlock.decode()
			

			if 'ICS 200 HTML CONVERT COMPLETE' in receivedString.upper():
				lastBlock = receivedString.split('ICS 200 HTML CONVERT COMPLETE')
				lastBlock = " ".join(lastBlock)
				print(lastBlock, end="")
				state = 4
				done = True
			else:
				print(receivedString, end="")



s1.close()
s2.close()
