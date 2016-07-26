capture = null;

setup = ->
    createCanvas(320, 240);
    capture = createCapture(VIDEO);
    capture.size(320, 240);
#capture.hide();

drawInvert = ->
    background(255);
    image(capture, 0, 0, 320, 240);
    filter('INVERT');

drawPixels = ->
    background(255);
    fingers = capture
    fingers.loadPixels();
    stepSize = round(constrain(mouseX / 8, 4, 32));
    for y in [0..height] by stepSize
        for x in [0..width] by stepSize
            i = y * width + x
            darkness = (255 - fingers.pixels[i * 4]) / 255
            radius = stepSize * darkness
            ellipse x, y, radius, radius

draw = ->
    drawPixels()