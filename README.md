# Rocket-Game-Android-Game-LibGDX
An android game Jets vs Rockets

# 2nd COMMIT
-Got rid of jitter that was causing the spritebatch to go a little bit out of the worldbounds and then return back each time
 the player reached the edge of the world left or right.

-Figured out that this was caused by where I did the batch.draw(...), I initially had the batch.draw(...) at the end of
the update method, this was causing it because draw(..) was not "checking" with the worldbounds() method, meaning that 
it time it made a position change it did so before the worldbounds() method.So since render
is called 60 times a sec draw() was drawing batch in a new position that was not valid, then when worldbounds() method 
checks validity of the position, it was resetting this position previously set by the draw() method, thus causing the jitter

# 3rd COMMIT
-Made a simple change so that the player can move in both all four directions (left, right, up, down). 

# 4th COMMIT
-Added another simple change to the previous commit, made it so that the player can move in all four directions, but can
also move in a specified direction simultaniously, EX: moving left and up means player moves north-east direction...

