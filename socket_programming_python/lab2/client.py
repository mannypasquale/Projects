#!/usr/bin/python

import sys, socket

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
	packet = bytearray()
	operator = sys.argv[2]
	int_count = 0
	if operator == "+":
		operator = 1
		operator = operator << 4
		for i in range(4, len(sys.argv) + 1):
			int_count += 1
		packet.append((operator | int_count))
	elif operator == "-":
		operator = 2
		operator = operator << 4
		for i in range(4, len(sys.argv) + 1):
			int_count += 1
		packet.append((operator | int_count))
	elif operator == "*":
		operator = 4
		operator = operator << 4
		for i in range(4, len(sys.argv) + 1):
			int_count += 1
		packet.append((operator | int_count))

	if int_count % 2 == 0:
		for i in range(3, len(sys.argv), 2):
			#print(sys.argv[i])

			int1 = int(sys.argv[i])
			int2 = int(sys.argv[i+1])
			to_append = int1 << 4
			to_append = to_append | int2
			packet.append(to_append)
	if int_count % 2 == 1:
		for i in range(3, len(sys.argv), 2):
			if i == len(sys.argv) - 1:
				int1 = int(sys.argv[i])
				packet.append(int1)
			else:
				int1 = int(sys.argv[i])
				int2 = int(sys.argv[i+1])
				to_append = int1 << 4
				to_append = to_append | int2
				packet.append(to_append)

			
			#print(to_append)

	
	s.sendto(packet, ('localhost', int(sys.argv[1])))
	packet = bytearray(2)
	n = s.recv_into(packet)

	hostInteger = int.from_bytes(packet, byteorder="little", signed=True)
	print("the host integer is %d" % hostInteger)
	

	