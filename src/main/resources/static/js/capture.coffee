capture = null;
fingers = null

w = 320
h = 240

setup = ->
    width = document.body.offsetWidth
    height = document.body.offsetHeight
    createCanvas(width, height)
    capture = createCapture(VIDEO)
    capture.size(w, h)
    capture.hide()
    fingers = capture


drawInvert = ->
    image(capture, w, h, w, h);
    filter('INVERT');


drawPixels = ->
    fingers.loadPixels();
    stepSize = round(constrain(mouseX / 8, 4, 32))
    for y in [0..h] by stepSize
        for x in [0..w] by stepSize
            i = y * w + x
            darkness = (255 - fingers.pixels[i * 4]) / 255
            radius = stepSize * darkness
            ellipse x, y, radius, radius


drawCanvas = ->
    image(fingers, w, 0);
    filter('GRAY');
    image(fingers, w*2, 0);


draw = ->
    background(255);
    drawInvert()
    drawPixels()
    drawCanvas()