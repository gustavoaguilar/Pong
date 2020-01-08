import pygame, random
from pygame.locals import *

class Ball:

	def __init__(self, x,y):
		self.x = x
		self.y = y
		self.ya = 0
		self.r = 20
		self.speed = 5
		self.color = (255,255,255) # White
		self.hit = False

		# Even tho the circle is a circle LOL, we are going to use a rectangle
		# to detect the collision
		self.rect = pygame.Rect(self.x,self.y,40,40)

		# Decides if it's going to start moving to left or right
		initDir = random.randrange(0,2)
		
		if initDir == 0:
			self.xa = self.speed
		elif initDir == 1 :
			self.xa = -self.speed

	def render(self, display):
		# Draws a circle
		pygame.draw.circle(display,self.color, (int(self.x+self.r), int(self.y+self.r)), self.r,2)

	def move(self):

		# Moves
		self.x += self.xa
		self.y += self.ya
		
		# Updates the collision rectangle
		self.rect = pygame.Rect(self.x,self.y,40,40)


		# Bounce in the scene
		if self.x <= 0:
			self.xa = self.speed
			self.hit = False
		elif self.x >= 400- 2*self.r:
			self.xa = -self.speed
			self.hit = False
		if self.y <= 0:
			self.ya = self.speed
			self.hit = False
		elif self.y >= 600 - 2*self.r:
			self.ya = -self.speed
			self.xa = 0
			self.hit = False
			print('Fail')

	# Returns the collision rectangle
	def getRect(self):
		return self.rect