# This is the player themself and will take care of the simple things for the playable spaceship.

class Player:
    """The player will be able to move left and right, fire bullets or missiles (Eventually.)
    For now though, this basic functionality """
    def __init__(self, x_pos, y_pos):
        self.x = x_pos
        self.y = y_pos
        self.lives = 3
        self.bullets = [] # Added to list when method called
        
    def fire_bullet(self):
        self.bullets.append([self.x, self.y])
        
    def remove_bullet(self, bullet): # Using this instead of list.remove() for OOP
        self.bullets.remove(bullet)
        
    def move_player(self, player_speed):
        self.x += player_speed
