float bx;
float by;
int bs = 20;
boolean clicked = false;

void setup() {
    size(200, 200);
    bx = width/2.0;
    by = height/2.0;
    noStroke();
    fill(#52b755);
    frameRate(10);
}

void draw() { 
    background(#ffffff);
    
    translate(100, 100);
    rect(-40, -40, 80, 80);
}

void mousePressed() {
    
    // Test if the cursor is over the box 
    if (mouseX > bx-bs && mouseX < bx+bs && 
        mouseY > by-bs && mouseY < by+bs) { 
        if (clicked) {
          fill(#52b755);
        } else {
            fill(#f00ff0);
        }
        clicked = !clicked;
    }
}
