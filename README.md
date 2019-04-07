# Introduction

The commonly used Braille input method requires two-hand input, which limits the scenario in which the blind person uses the mobile phone to input text. We tried to create a Braille input method that one can input Braille efficiently and intuitively with only one thumb to fill a long-term blank in this field.

It is developed on top of Android platform. People who know Braille can get started quickly with nearly no additional memory burden. No additional hardward needed.

*NOTE:* This is a hackathon project, it has basic functions but NOT recommended to use directly in the actual scene. The Braille system has been simplified.

# Tutorial

## Prerequisite skills

+ Braille
+ Swipe gesture
+ Screen reader

## Enable IME

After installing it, you will need to enable this app as a keyboard by navigating to *Settings > Languages & Input > Virtual Keyboard > Manage Keyboards*. This keyboard can then be accessed by pressing the virtual keyboard icon in the lower-right hand corner of the display while in a text field.

## Input

Decompose one 6 dot Braille into 2 parts, dot 1-3 and dot 4-6. Input each part with one stroke from up to down in order .
For each stroke, the whole screen is divided into left and right two parts. 
Left part represents there has a dot and right part represents there is not a dot. And 3 dots can be encoded into True/False values. If place for dot 1 (or 4) has a dot (True), then press down at the left part, otherwise (False) the right part. Then if the 2nd place has a different value, than move thumb down right or down left cross the middle border, otherwise move directly down. Input the 3rd value in the same way. As a result, there are 8 different strokes.

+ start left - down - down
+ start left - down - down right
+ start left - down right - down
+ start left - down right - down left
+ start right - down - down
+ start right - down - down left
+ start right - down right - down
+ start right - down right - down left

After two strokes are drawn, the braille will be added into the text box and will be read out for you to confirm.

And also, double click any place to hide the IME, and swipe up to reset first stroke (if the 2nd stroke has not been drawn) or do backspace.

There are two settings that you can change. The first setting is "strong hand", it will change the proportion of left and right division. The second setting is "true side", you can set which side represents True.


