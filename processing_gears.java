float x = 50;
float y = 100;
float angle1 = 65.0;
float angle2 = 0.0;
float angle3 = 65.0;
float segLength = 150;
boolean stopped = false;

void setup() {
  size(800, 900);
  smooth(); 
  frameRate(30);
  //noStroke();
  //strokeWeight(20.0);
  //stroke(0, 100);
  //
  draw();
}

void draw() {
    if (stopped) {
        return;
    }
  
    background(236);
    pushMatrix();
  
    int speed = 0.02;
  
    angle1 += speed;
    
    if (angle1 > 360) {
        angle1 = 0;
    }
    drawGear(width/3, height/4, 315, angle1, #FF0000);
    
    angle3 += speed;
    
    if (angle3 > 360) {
        angle3 = 0;
    }
    drawGear((width/3) + 20, (height / 4) + (315 / 1.53) + 220, 315, angle3, #00FF00);
    
    angle2 -= speed * 1.62;
    
    if (angle2 > 360) {
        angle2 = 0;
    }
  
    drawGear((width / 3) + (315 / 1.53), (height / 4) + (315 / 1.53), 195, angle2, #0000FF);
    
    popMatrix();
  
}

void drawGear(int x, int y, int d, int angle, color c) {
    pushMatrix();
    translate(x, y);
    
    
    rotate(angle);
    pushMatrix();
    
    //translate(width/2, height/2);
    
    noStroke();
    
    fill(c);
    ellipse(0, 0, d, d);
    
    fill();
    ellipse(0, 0, d / 4, d / 4);
    
    popMatrix();
    
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

void segment(float x, float y, float a) {
  translate(x, y);
  rotate(a);
  line(0, 0, segLength, 0);
}
