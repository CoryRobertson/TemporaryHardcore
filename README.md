# Temporary Hardcore
This is a plugin for spigot minecraft 1.19, that modifies the game to temporarily kick you from the game when you die, instead of banning you like vanilla hardcore mode.

##### Contributions
Feel free to pullrequest anything that fits the goal of the plugin. :)

```yml
enable-lightning: false # strike lightning at the location a player dies
disable-for-ops: false # don't act upon ops
ban-duration: 15000 # time in milliseconds to ban someone
spawn-enemy-on-death: false # spawns a zombie with the player's name who died on death
change-death-message: false # changes the death message put in the chat
death-message: " died, lmao" # the death message to use
kick-message: "You died lmao..." # the kick message
funny-chance: 5 # chance of funny thing happening :)
```