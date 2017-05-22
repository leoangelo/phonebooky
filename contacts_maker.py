import random

n = 900000
f = open('contacts.txt', 'w+')
for x in range(1, n):
	rand = random.randint(100000000, 999999999)
	line = '61' + str(rand) + '|' + 'user' + str(rand)
	f.write(line + '\n')
f.close()

