boolean stopped = false;
float angle1 = 0.0;
float angle2 = 0.0;

void setup() {
  size(800, 900);
  smooth(); 
  frameRate(30);
  //drawX();
}

void draw() {
    if (stopped) {
        return;
    }
  
    background(236);

    int speed = 0.02;
  
    /*
     The big red gear
     */
    angle1 += speed;
    if (angle1 > 360) {
        angle1 = 0;
    }
    drawGear(width/3, height/4, 315, angle1, #FF0000);
    
    /*
     The big green gear
     */
    drawGear((width/3) + 40, (height / 4) + (315 / 1.53) + 220, 315, angle1, #00FF00);
    
    /*
     The small blue gear
     */
    angle2 -= speed * 1.62;
    if (angle2 > 360) {
        angle2 = 0;
    }
    drawGear((width / 3) + (315 / 1.47), (height / 4) + (315 / 1.62), 195, angle2, #0000FF);
  
}

void drawGear(int x, int y, int d, int angle, color c) {
    pushMatrix();
    translate(x, y);
    
    rotate(angle);
    noStroke();
    
    /*
     The gear circle
     */
    fill(c);
    ellipse(0, 0, d, d);
    
    /*
     The inner gap
     */
    fill();
    ellipse(0, 0, d / 4, d / 4);
    
    drawTeeth((d / 2) - 5, d / 15, c);
    
    popMatrix();
    
}

void drawTeeth(int d, int plots, color c) {
    float increase = Math.PI * 2 / plots;
    float angle = 0;
    float x = 0;
    float y = 0;
    
    pushMatrix();
    
    int w = 35;
    int h = 35;
    
    for (var i = 0; i < plots; i++ ) {
        pushMatrix();
        double t = 2 * Math.PI * i / plots;
        int x = (int) Math.round((d + (w / 2)) * Math.cos(t));
        int y = (int) Math.round((d + (h / 2)) * Math.sin(t));
        
        translate(x, y);
        
        fill(c);
        rotate( angle - 55 );
        quad(-(w / 4), -(h / 2),    // upper left
             w / 4, -(h / 2),       // upper right
             w / 2, h / 2,          // bottom right
             -(w / 2), h / 2);      // bottom left
        angle += increase;
        
        //fill(255, 0, 0);
        //ellipse(0, 0, 10, 10);
        popMatrix();
    }
    popMatrix();
}

void mousePressed() {
    stopped = !stopped;
}
