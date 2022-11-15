import pygame, sys, random, player, ball
from pygame.locals import *
pygame.init()
class Main:

	def __init__(self):
		self.WIDTH = 400
		self.HEIGHT = 600
		self.FPS = 60
		self.fpsClock = pygame.time.Clock()
		self.DISPLAYSURF = pygame.display.set_mode((self.WIDTH,self.HEIGHT))
		pygame.display.set_caption("Pong with Python and Pygame")

		self.player = player.Player(150,500)
		self.ball = ball.Ball(random.randrange(0,360),0)

	#Game Loop
	def gameloop(self):
		while True:

			# Execute input and movements
			self.player.controller()
			self.player.move()
			self.ball.move()

			# Collision Detection
			# Game Over
			if self.ball.y > 500:
				self.ball.speed = 0
			# HIT
			if self.ball.getRect().colliderect(self.player.getRect()):
				if self.ball.hit == False:
					
					#Left Side of the Player
					if(self.ball.x + self.ball.r <= self.player.x+10):
						self.ball.xa = -self.ball.speed
					#Right Side of the Player
					if(self.ball.x + self.ball.r >= self.player.x+100 -10):
						self.ball.xa = self.ball.speed
					
					self.ball.ya = -self.ball.speed
					self.ball.hit = True

			# Window close button
			for event in pygame.event.get():
				if event.type == QUIT:
					pygame.quit()
					sys.exit()
			# Render
			self.DISPLAYSURF.fill((0,0,0)) # Clear the screen

			self.player.render(self.DISPLAYSURF) # Render player
			self.ball.render(self.DISPLAYSURF) # Render ball
			self.fpsClock.tick(self.FPS)
			pygame.display.update()
MainObject = Main()
MainObject.gameloop()