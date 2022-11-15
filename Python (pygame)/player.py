import pygame
from pygame.locals import *

class Player:

	def __init__(self, x, y):
		self.x = x
		self.y = y
		self.vel = 10
		self.color = (255,255,255) # White
		self.rect = (self.x, self.y, 100, 20) # Collision rectangle
		self.MovRight, self.MovLeft = False, False # Movement flags

	# Get players input
	def controller(self):
		for event in pygame.event.get():
			pygame.key.set_repeat(1)
			if event.type == pygame.QUIT:
				pygame.quit()

			# Set movement flags based on keyboard input
			# Key Press
			if event.type == KEYDOWN:
				if event.key == pygame.K_LEFT:
					self.MovLeft = True
				if event.key == pygame.K_RIGHT:
					self.MovRight = True
			# Key Release
			if event.type == KEYUP:
				if event.key == pygame.K_LEFT:
					self.MovLeft = False
				if event.key == pygame.K_RIGHT:
					self.MovRight = False

	def render(self, display):
		# Draws a rectangle
		pygame.draw.rect(display,self.color, self.rect, 2)

	def move(self):
		#Move to the left
		if self.x >= 0 + self.vel:
			if self.MovLeft == True :
				self.x += -self.vel
		#Move to the right
		if self.x <= 400 -(100+ self.vel):
			if self. MovRight == True :
				self.x += self.vel

		# Updates the collision rectangle position
		self.rect = (self.x, self.y, 100, 20)

	# Returns the collision rectangle
	def getRect(self):
		return self.rect