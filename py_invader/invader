import pygame, sys, random
from pygame.locals import *
pygame.init()

ROUND = 1
LIVES = 3
livesStartAmount = 3
PAUSE = False # Starts as NOT paused.
GREEN = (0, 255, 0)
RED = (255, 0, 0)
RUNNING = True
SNOW = True
SQUARE = False
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
WIDTH = 500
HEIGHT = 400
SIZE = (WIDTH, HEIGHT)
DISPLAYSURF = pygame.display.set_mode(SIZE)
pygame.display.set_caption('GAME')
DONE = False
FPS = 30
CLOCK = pygame.time.Clock()
velocityX = 5
velocityY = 5
SCORE = 0
snowRadius = 2
snowList = []
snowListVelocities = []
snowCount = 30
maxVelocity = 4
minVelocity = 1
snowSize = 1 # looks really decent for simple stars going by
playerImage = pygame.image.load('ship.png')
livesPicture = pygame.transform.scale(playerImage, (25, 20))
playerWidth = ( WIDTH / 2) - 20
playerX = playerWidth
playerHeight = HEIGHT - 50
playerY = playerHeight
livesX = WIDTH / 2.3
livesY = 50
playerSpeed = 4
bulletSizeX = 4
bulletSizeY = 15
bulletOnScreen = False
bulletLocX = 0
bulletLocY = 0
bulletList = []
bulletSpeed = 10
badGuys = []
badGuysSpeed = 20
speedStartAmt = 2
enemyImg = pygame.image.load('enemy.png')
badGuysDropSpeed = 4
collisionAmt = 8
explosionList = []
expl1 = pygame.image.load('expl1.png')
expl2 = pygame.image.load('expl2.png')
expl3 = pygame.image.load('expl3.png')
expl4 = pygame.image.load('expl4.png')
explosionTimes = []
explosionProgression = 3
badGuyStartHeight = 100
badGuyStartLeft = 20
badGuyRow = 8
badGuyColumn = 5
badGuySpacing = 30
badGuysDirection = 'right'
badPoints = 10 # Amount of points gained per enemy shot down
font = pygame.font.Font('freesansbold.ttf', 25)
font2 = pygame.font.Font('freesansbold.ttf', 20)
pausedFont = pygame.font.Font('freesansbold.ttf', 50)
healthFont = pygame.font.Font('freesansbold.ttf', 25)
livesFont = pygame.font.Font('freesansbold.ttf', 25)
gameOverFont = pygame.font.Font('freesansbold.ttf', 50)
scorePosnX = WIDTH - 150
scorePosnY = 20
scoreMessage = "SCORE"
healthMessage = "DAMAGE"
livesMessage = "LIVES"
gameOverMessage = "NEW GAME?"
choice = "Y or N"
gameOverX = WIDTH / 4
shots = 0
livesHeight = 20
livesWidth = WIDTH / 2.3
scoreAmtPosnX = WIDTH - 120
scoreAmtPosnY = 50
endTimer = 0
damage = 100
deathHeight = -50
collisionMultiplier = 1.5 
# the multiplier of the collision to hit a player
# vs the collision to hit an enemy with a bullet.


def drawHealthBar(damage, healthMessage):
    pygame.draw.rect(DISPLAYSURF, BLACK, (50, 50, WIDTH / 4, HEIGHT / 15))
    pygame.draw.rect(DISPLAYSURF, RED, (50, 50, WIDTH / 4, HEIGHT / 15), 3)
    if damage > 0:
        pygame.draw.rect(DISPLAYSURF, RED, (55, 55, (WIDTH / 4.35) * (damage / 100), HEIGHT / 25))
    elif damage <= 0:
        animateDeath()
    damageText = font.render(healthMessage, True, WHITE)
    DISPLAYSURF.blit(damageText, [50, 20])


def drawLives(LIVES, livesMessage):
    spacing = livesPicture.get_width() + 10
    for i in range(LIVES):   
        DISPLAYSURF.blit(livesPicture, (i * spacing + livesX, livesY))
    livesText = font.render(livesMessage, True, WHITE)
    DISPLAYSURF.blit(livesText, [livesWidth, livesHeight])
    if LIVES < 0:
        gameOver()
        
def checkAndRemove():
    # If the baddies go off the bottom of the screen, they're gone
    global badGuys
    for i in range(len(badGuys)):
        if badGuys[i][1] > HEIGHT:
            badGuys.pop(i)
            break
            
def animateDeath():
    global playerX, playerY, LIVES, damage, deathHeight
    explosionList.append([playerX, playerY])
    playerX = -50
    playerY = deathHeight
    damage = 100
    LIVES -= 1
    
def gameOver(gameOverMessage, choice):
    global LIVES, badGuysSpeed, SCORE, damage, playerX, playerY, shots, ROUND
    gameOver = True
    while gameOver:
        DISPLAYSURF.fill(BLACK)
        keys = pygame.key.get_pressed()
        gameOverText = font.render(gameOverMessage, True, WHITE)
        gameOverLabel = font.render("GAME OVER!", True, WHITE)
        choiceText = font.render(choice, True, WHITE)
        scoreText = font.render("TOTAL SCORE: " + str(SCORE), True, WHITE)
        roundText = font.render("ROUNDS LASTED: " + str(ROUND), True, WHITE)
        shotsText = font.render("SHOTS FIRED: " + str(shots), True, WHITE)
        DISPLAYSURF.blit(gameOverLabel, [gameOverX - 30, HEIGHT / 6])
        DISPLAYSURF.blit(gameOverText, [gameOverX, HEIGHT / 4])
        DISPLAYSURF.blit(choiceText, [gameOverX, HEIGHT / 3])
        DISPLAYSURF.blit(scoreText, [gameOverX, HEIGHT / 2])
        DISPLAYSURF.blit(roundText, [gameOverX, HEIGHT / 1.5])
        DISPLAYSURF.blit(shotsText, [gameOverX, HEIGHT / 1.25])
        
        for event in pygame.event.get():
            if event.type == QUIT or keys[pygame.K_n]:
                quit()
                
        if keys[pygame.K_y]:
            LIVES = livesStartAmount
            badGuysSpeed = speedStartAmt
            SCORE = 0
            damage = 100
            playerX = playerWidth
            playerY = playerHeight
            shots = 0
            ROUND = 1
            createBadGuyList(badGuyStartLeft, badGuyStartHeight, badGuySpacing)
            break
        pygame.display.update()
        CLOCK.tick(FPS)
    
            
def paused():
    global PAUSE
    pausedText = font.render("PRESS G TO CONTINUE", True, WHITE)
    DISPLAYSURF.blit(pausedText, [WIDTH / 4, HEIGHT / 2]) 
    while PAUSE:
        lookForG = pygame.key.get_pressed()
        if lookForG[pygame.K_g]:
            PAUSE = False
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()
        pygame.display.update()
        CLOCK.tick(15)
        

# Start the customizable list off with some baddies.
def createBadGuyList(left, height, spacing):
    for i in range(badGuyRow):
        for j in range(badGuyColumn):
            badGuys.append([i * spacing + left, j * spacing + height])
    
# Check to see if the player needs to die.
def detectPlayerCollision():
    global explosionList, explosionTimes, badGuys, playerX, playerY, collisionAmt, damage
    for i in range(len(badGuys)):
        if abs(playerX - badGuys[i][0]) <= collisionAmt * collisionMultiplier and abs(playerY - badGuys[i][1]) <= collisionAmt * collisionMultiplier:
            explosionList.append(badGuys.pop(i))
            explosionTimes.append(0)
            damage -= 10
            break
        
def quit():
    pygame.quit()
    sys.exit()
    
# Does all the score for the game from here.
def doScore(message, colour, wordScoreX, wordScoreY, amount, amountX, amountY):
    global DISPLAYSURF
    scoreText = font.render(message, True, colour)
    scoreItself = font2.render(str(amount), True, colour)
    DISPLAYSURF.blit(scoreText, [wordScoreX, wordScoreY])
    DISPLAYSURF.blit(scoreItself, [amountX, amountY])
    
# You want a bunch of different speeds of the stars so here it is.
def createSnowSpeeds():
    for i in range(snowCount):
        snowListVelocities.append(random.randint(minVelocity, maxVelocity))

# Should be more called ''randomCoordinates''                            
def randomLocation():
    posnX = random.randint(0, WIDTH)
    posnY = random.randint(0, HEIGHT)
    return [posnX, posnY]
    
# Only called when they hit the edge of the screen, and they'll drop down.
def dropBadGuys(BGlist):
    for i in range(len(BGlist)):
        BGlist[i][1] += badGuysDropSpeed
            
# Super cheesy Explosions!
def animateExplosions(explosionTimes, explosionList):
    for i in range(len(explosionTimes)):
        if explosionTimes[i] < 10:
            DISPLAYSURF.blit(expl1, (explosionList[i][0], explosionList[i][1]))
        elif explosionTimes[i] < 20:
            DISPLAYSURF.blit(expl2, (explosionList[i][0], explosionList[i][1]))
        elif explosionTimes[i] < 30:
            DISPLAYSURF.blit(expl3, (explosionList[i][0], explosionList[i][1]))
        elif explosionTimes[i] < 40:
            DISPLAYSURF.blit(expl4, (explosionList[i][0], explosionList[i][1]))
        elif explosionTimes[i] < 50:
            DISPLAYSURF.blit(expl3, (explosionList[i][0], explosionList[i][1]))
        elif explosionTimes[i] < 60:
            DISPLAYSURF.blit(expl2, (explosionList[i][0], explosionList[i][1]))

def animateStars():
    for i in range(len(snowList)):
        pygame.draw.circle(DISPLAYSURF, WHITE, snowList[i], snowSize)
        snowList[i][1] += snowListVelocities[i]
        #snowListVelocities is one integer per list entry
        if snowList[i][1] > HEIGHT:
            snowList[i] = [random.randint(0, WIDTH), 0]
            
def createSnowList():
    if len(snowList) < 1:
        for i in range(snowCount):
            snowList.append(randomLocation())
    
createSnowList()
createSnowSpeeds()
createBadGuyList(badGuyStartLeft, badGuyStartHeight, badGuySpacing)

while RUNNING:
    DISPLAYSURF.fill(BLACK)
    keys = pygame.key.get_pressed()  
    doScore(scoreMessage, WHITE, scorePosnX, scorePosnY, SCORE, scoreAmtPosnX, scoreAmtPosnY)
    
    if SNOW:
        animateStars()
               
    if keys[pygame.K_LEFT] and playerX >= 10:
        playerX -= playerSpeed
    if keys[pygame.K_RIGHT] and playerX <= WIDTH - 50:
        playerX += playerSpeed
    if keys[pygame.K_SPACE] and not bulletOnScreen:
        shots += 1
        bulletOnScreen = True
        bulletLocX = playerX + 18
        bulletLocY = playerY - 10
            
    if bulletOnScreen and bulletLocY >= 0:
        pygame.draw.rect(DISPLAYSURF, WHITE, [bulletLocX, bulletLocY, bulletSizeX, bulletSizeY])
        bulletLocY -= bulletSpeed  
    elif bulletOnScreen and bulletLocY <= 0:
        bulletOnScreen = False
        
    if len(badGuys) > 0:
        if bulletOnScreen:
            for i in range(len(badGuys)):
                if abs(bulletLocX - badGuys[i][0]) <= collisionAmt and abs(bulletLocY - badGuys[i][1]) <= collisionAmt:
                    explosionList.append(badGuys.pop(i))
                    explosionTimes.append(0)
                    bulletOnScreen = False
                    SCORE += badPoints # Score increased per baddy destroyed
                    break    
                
        for i in range(len(badGuys)):
            if badGuys[i][0] < 10:
                badGuysDirection = 'right'
                dropBadGuys(badGuys)   
            elif badGuys[i][0] > WIDTH - 30:
                badGuysDirection = 'left'
                dropBadGuys(badGuys)
                
        for i in range(len(badGuys)):
            if playerY == deathHeight:
                badGuys[i][1] += badGuysSpeed
            elif badGuysDirection == 'right':
                badGuys[i][0] += badGuysSpeed
            elif badGuysDirection =='left':
                    badGuys[i][0] -= badGuysSpeed
            DISPLAYSURF.blit(enemyImg, (badGuys[i][0], badGuys[i][1]))
            
    elif len(badGuys) == 0:
        endTimerOn = True
        if endTimer >= 100:
            createBadGuyList(badGuyStartLeft, badGuyStartHeight, badGuySpacing)
            badGuysSpeed += 1
            ROUND += 1
            explosionTimes = []
            explosionList = []
            endTimer = 0
            if playerY == deathHeight:
                playerX = WIDTH / 2
                playerY = playerHeight
        while endTimer < 100:
            endTimer += 1
        
    if len(explosionTimes) > 0:
        for i in range(len(explosionTimes)):
            explosionTimes[i] += explosionProgression #basically a timer
        animateExplosions(explosionTimes, explosionList)
    
    DISPLAYSURF.blit(playerImage, (playerX, playerY))
    drawLives(LIVES, livesMessage)
    detectPlayerCollision()
    drawHealthBar(damage, healthMessage)
    checkAndRemove()
    if LIVES < 0:
        badGuys = []
        explosionList = []
        explosionTimes = []
        gameOver(gameOverMessage, choice)
        
    for event in pygame.event.get():
        if event.type == QUIT:
            quit()
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_p:
                PAUSE = True
                paused()
                       
    CLOCK.tick(FPS)
    pygame.display.update()
