# 0 3 6
# 1 4 7 
# 2 5 8

import sys, string
from numpy import matrix

alpha = 0.5

def main(inpDim, outDim):
	global alpha

	# Validate matrix dimensions
	if not validDims(inpDim, outDim):
		print 'Invalid matrix dimensions'
		sys.exit(1)

	with open('uniqueBoardStates.txt') as boardInp:
		for line in boardInp:
			parts = line.split('[')
			statesStr = parts[0].splitlines()
			playsStr = parts[1].splitlines()
			states = []
			plays = []

			# Parse states
			for state in statesStr:
				states.append(state)

			# Parse plays
			for play in playsStr:
				print play

	exit()

	# Initialize matrix/control data
	# 0: empty	1: X    2: O
	inp = matrix('2,0,0,0,1,0,0,0,0;  \
				  1,0,0,2,1,1,2,1,0')
	out = matrix('7; 2')
	weights = matrix('0.5; 0.5; 0.5; 0.5; 0.5; 0.5; 0.5; 0.5; 0.5')
	numRows = inpDim[0]
	done = False
	noErrCount = 0
	passes = 0

	while not done:
		for i in range(0, numRows):
			passes += 1
			if passes % 100000 == 0:
				alpha += 0.005
				print 'passes: ' + str(passes)
				print weights		

			error = getError(inp[i], weights, out.item(i))
			if error == 0:
				noErrCount += 1
				if noErrCount == numRows:
					done = True
					break
			else:
				noErrCount = 0
				weights = modWeights(weights, inp[i], error)

	print weights

def parseBoard(state):
	board = []
	for char in state:
		if char.upper() == '_':
			board.append(0)
		elif char.upper() == 'X':
			board.append(1)
		elif char.upper() == 'O':
			board.append(2)
		else:
			print 'Invalid state character: ' + char.upper()

	return matrix(board)

def modWeights(weights, inp, error):
	return weights + (alpha * inp * error).T

def getError(inp, weights, targ):
	p = inp * weights
	#print str(inp) + ' *\n' + str(weights) + ' =\n' + str(p)
	y = nonLinFunc(p.item(0))
	return targ - y

def nonLinFunc(p):
	# Floor non-linear func
	if p < -1000:
		return 0
	elif p < -100:
		return 1
	elif p < -10:
		return 2
	elif p < 0:
		return 3
	elif p < 10:
		return 4
	elif p < 100:
		return 5
	elif p < 1000:
		return 6
	elif p < 10000:
		return 7
	else:
		return 8

def validDims(inpDim, outDim):
	# Equal number of input and output rows
	if inpDim[0] == outDim[1] or inpDim[1] == outDim[0]:
		return True
	# Output columns > 1 NOT implemented yet
	elif outDim[1] > 1:
		return False
	else:
		return False

if __name__ == "__main__":
	if len(sys.argv) == 3:
		inpDim = sys.argv[1].split('x')
		outDim = sys.argv[2].split('x')
	else:
		inpDim = [2,9]
		outDim = [9,1]

	main(inpDim, outDim)
