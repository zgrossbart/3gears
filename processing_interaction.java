float bx;
float by;
int bs = 20;
boolean bover = false;
boolean clicked = false;

void setup() {
    size(200, 200);
    bx = width/2.0;
    by = height/2.0;
    frameRate(10);
}

void draw() { 
    background(0);
    
    // Test if the cursor is over the box 
    if (mouseX > bx-bs && mouseX < bx+bs && 
        mouseY > by-bs && mouseY < by+bs) {
        bover = true;  
    } else {
        bover = false;
    }
    
    translate(100, 100);
    rect(-40, -40, 80, 80);
}

void mousePressed() {
    if (bover) { 
        if (clicked) {
          fill(255, 255, 255);
        } else {
          fill(255, 0, 0);
        }
        clicked = !clicked;
    }
}
