#!/usr/bin/python

import sys, socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('', int(sys.argv[1])))
packet, addr = s.recvfrom(12)
hostInteger = int()
first = packet[0]

operator = first >> 4
mask = 2**3 - 1
num_ints = first & mask

#print(operator)
#print(num_ints)





if operator == 1:
	for index in range(1,len(packet)):
		#print(packet[index])
		firstvalue = packet[index] >> 4
		hostInteger = hostInteger + firstvalue
		mask = (2**4) - 1
		secondvalue = packet[index] & mask
		hostInteger = hostInteger + secondvalue

elif operator == 2:
	first = True
	for index in range(1,len(packet)):
		#print(packet[index])
		if first == True:
			firstvalue = packet[index] >> 4
			hostInteger = firstvalue
			mask = 2**4 -1
			secondvalue = packet[index] & mask
			hostInteger = firstvalue - secondvalue
			first = False
		else:
			firstvalue = packet[index] >> 4
			hostInteger = hostInteger - firstvalue
			mask = (2**4) - 1
			secondvalue = packet[index] & mask
			hostInteger = hostInteger - secondvalue

elif operator == 4:
	first = True
	for index in range(1,len(packet)):

		if first == True:
			firstvalue = packet[index] >> 4
			hostInteger = firstvalue
			mask = 2**4 -1
			secondvalue = packet[index] & mask
			hostInteger = firstvalue * secondvalue
			first = False
			print(hostInteger)



		else:
			if index == len(packet)-1:
				firstvalue = packet[index]
				hostInteger = hostInteger * firstvalue
			else:
				firstvalue = packet[index] >> 4
				print(firstvalue)
				hostInteger = hostInteger * firstvalue
				print(hostInteger)
				mask = (2**4) - 1
				secondvalue = packet[index] & mask
				hostInteger = hostInteger * secondvalue

#packet = a.to_bytes(2, byteorder="little", signed=True)

print(hostInteger)
packet = hostInteger.to_bytes(2, byteorder="little", signed=True)
s.sendto(packet,addr)
#print("received message:", data)



s.close()

