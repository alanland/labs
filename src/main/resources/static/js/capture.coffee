capture = null;
fingers = null
prevCapture = null

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
    prevCapture = capture


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
    image(fingers, w * 2, 0);

drawMotion = ->
    for x in [0...w]
        for y in [0...h]
            loc = x + y * w
            current = capture.pixels[loc]
            previous = prevCapture.pixels[loc]

            return if current == undefined or previous == undefined
            current = color current
            previous = color previous
            r1 = red current
            g1 = green current
            b1 = blue current
            r2 = red previous
            g2 = green previous
            b2 = blue previous
            diff = dist r1, g1, b1, r2, g2, b2

            if diff > 30
                prevCapture.set x, y, color 0
            else
                pixels[loc] = color 255
    prevCapture.updatePixels()
    image prevCapture, 0, h * 2
    prevCapture = capture           


draw = ->
    background(255);
    drawInvert()
    drawPixels()
    drawCanvas()
    drawMotion()