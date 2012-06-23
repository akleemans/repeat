# Repeat #

A Java program for executing very simple repeated tasks, mainly clicking on a certain position on the screen. With the Java **Robot** class, clicks and mouse movements are simulated, so one can program a sequence of clicks and waiting.

It can be used for tasks which are exactly repeated each time, and I use it for putting emails in our order system at work (I'm lazy).


## Commands ##

Scripts are saved in separate text files, and you can name them as you want. I name mine with the ending *.do*, simply for implying that the file contains some action commands.

At the moment, there are three basic commands: `click`, `doubleclick` and `wait`. The first two expect positions on the screen, and the last takes a double value which represents the seconds to wait

    click 450, 525
    doubleclick 575, 275
    wait 7.0


## Usage ##
Just call 

    java -jar script.do 5

to execute script.do 5 times. It should run on both Windows and Linux (tested: Ubuntu 12).

## Features to add ##
 * virtual clipboard for copying and pasting text
 * if-then control structure (for making decisions based on clipboard text)
