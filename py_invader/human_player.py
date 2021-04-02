# This is the player class.  Will take care of the simple things for the player.

class Player:
    """The player will be able to move left and right, fire bullets or missiles (Eventually.)
    For now though, this basic functionality """
    def __init__(self, x_pos, y_pos):
        self.x = x_pos
        self.y = y_pos
        self.lives = 3
        self.bullet_timer_MAX = 5
        self.bullet_timer = 5                                                  # Cooldown for shooting
        self.bullets = []                                                       # Added to list when method called
        
    def fire_bullet(self):
        self.bullets.append([self.x, self.y])
        
    def remove_bullet(self, bullet): # Using this instead of list.remove() for OOP
        self.bullets.remove(bullet)
        
    def move_player(self, player_speed):
        self.x += player_speed
    
    def change_bullet_timer(self):
        if len(self.bullets) == 0 or self.bullet_timer == 0:
            self.bullet_timer = self.bullet_timer_MAX
            
        else:
            self.bullet_timer -= 1
