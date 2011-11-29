float angle = 0.0;
boolean stopped = false;

void setup() {
    size(200, 200);
    smooth(); 
    frameRate(30);
}

void draw() {
    if (stopped) {
        return;
    }
    
    background(000);
    translate(100, 100);
    fill(#FFFFFF);
    noStroke();
    ellipse(0, 0, 20, 20);
    
    rotate(angle);
    angle += 0.02;
    noFill();
    stroke();
    strokeWeight(2);
    quad(-40, -40, 40, -40, 40, 40, -40, 40);
    
}

void mousePressed() {
    stopped = !stopped;
}
