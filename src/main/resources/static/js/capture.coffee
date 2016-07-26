capture = null;
fingers = null


setup = ->
    width = document.body.offsetWidth
    height = document.body.offsetHeight
    createCanvas(width, height)
    capture = createCapture(VIDEO)
    capture.size(320, 240)
    capture.hide()
    fingers = capture


drawInvert = ->
    image(capture, 320, 240, 320, 240);
    filter('INVERT');


drawPixels = ->
    fingers.loadPixels();
    stepSize = round(constrain(mouseX / 8, 4, 32))
    for y in [0..height] by stepSize
        for x in [0..width] by stepSize
            i = y * width + x
            darkness = (255 - fingers.pixels[i * 4]) / 255
            radius = stepSize * darkness
            ellipse x, y, radius, radius


drawCanvas = ->
    image(fingers, 10, 10);
    filter('GRAY');
    image(fingers, 150, 150);


draw = ->
    background(255);
    drawInvert()
    drawPixels()
    drawCanvas()