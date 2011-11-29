//40 degrees = 0.698131701 radians
float angle = 0.698131701;

void setup() {
    size(200, 200);
    smooth(); 
    frameRate(30);
    main.processingAnimationStopped = false;
    draw();
    main.processingAnimationStopped = true;
}

void draw() {
    if (main.processingAnimationStopped) {
        return;
    }
    
    background(#ffffff);
    translate(100, 100);
    fill(#52b755);
    noStroke();
    ellipse(0, 0, 20, 20);
    
    rotate(angle);
    angle += 0.1;
    noFill();
    stroke(#52b755);
    strokeWeight(2);
    rect(-40, -40, 80, 80);
    
}

void mousePressed() {
    main.processingAnimationStopped = !main.processingAnimationStopped;
}
