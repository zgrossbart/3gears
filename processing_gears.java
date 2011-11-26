/******************************************************************************* 
 * 
 * Copyright 2011 Zack Grossbart 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

boolean stopped = false;
boolean clockwise = false;
float angle1 = 0.0;
float angle2 = 0.0;

ArrayList gears = new ArrayList();
int toothSize = 30;

void setup() {
    size(960, 650);
    smooth(); 
    
    int speed = 0.02;
    
    int x = width / 6;
    int y = (height / 3) + 30;
    
    Gear redGear = new Gear(x, y, speed, 0, #ee2a33, 15, true);                                       // red gear
    gears.add(redGear);
    
    HashMap map = addGear(x, y, redGear, 9, #00aeef, speed, 30);                                      // blue gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 11, #52b755, map.get('speed'), 125);   // green gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 38, #d03c3a, map.get('speed'), 25);    // dark red gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 4, #ac6a13, map.get('speed'), 318);    // light brown gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 7, #F00FF0, map.get('speed'), 281);    // light purple gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 8, #f8b724, map.get('speed'), 257);    // yellow gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 16, #e0cb61, map.get('speed'), 188);   // beige gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 4, #f69c9f, map.get('speed'), 201);    // pink gear
    map = addGear(map.get('x'), map.get('y'), map.get('gear'), 7, #825f84, map.get('speed'), 175);    // pink gear
    
    frameRate(30);
    //drawX();
}

void draw() {
    if (stopped) {
        return;
    }
  
    background(236);
    for (int i = 0; i < gears.size(); i++) {
        gears.get(i).drawGear();
    }
}

HashMap addGear(int x, int y, Gear gear1, int g2, color c, int speed, int angle) {
    float r1 = (gear1.getToothCount() * 15) / 2;
    float r2 = (g2 * 15) / 2;
    
    int x2 = x + ((r1 + r2 + (toothSize - 2)) * Math.cos((angle / 180) * Math.PI));
    int y2 = y + ((r1 + r2 + (toothSize - 2)) * Math.sin((angle / 180) * Math.PI));
    
    Gear gear2 = new Gear(x2, y2, speed * (gear1.getToothCount() / g2), 0, c, g2, clockwise);
    gears.add(gear2);
    
    /*
     * Now we need to rotate the gears so they match up
     */
    float wedge = 360 / gear1.getToothCount();
    int tooth = Math.floor((angle - gear1.getRot()) / wedge);
    double t = 2 * Math.PI * tooth / gear1.getToothCount();
    int x = (int) Math.round((r1 + (toothSize / 1.85)) * Math.cos(t));
    int y = (int) Math.round((r1 + (toothSize / 1.85)) * Math.sin(t));
    
    double rad = gear1.getRot() * (Math.PI / 180);
    int x1 = (Math.cos(rad) * x) - (Math.sin(rad) * y);
    int y1 = (Math.cos(rad) * y) + (Math.sin(rad) * x);
    x = x1;
    y = y1;
    
    int pa1x = gear1.getX() + x;
    int pa1y = gear1.getY() + y;
    
    t = 2 * Math.PI * 0.5 / gear2.getToothCount();
    x = Math.round((r2) * Math.cos(t));
    y = Math.round((r2) * Math.sin(t));
    int padx = gear2.getX() + x;
    int pady = gear2.getY() + y;
    
    PVector v1 = new PVector(pa1x - gear2.getX(),
                             pa1y - gear2.getY());
    PVector v2 = new PVector(padx - gear2.getX(),
                             pady - gear2.getY());
    
    gear2.rot(PVector.angleBetween(v1, v2));
    
    clockwise = !clockwise;
    
    HashMap map = new HashMap();
    map.put('x', x2);
    map.put('y', y2);
    map.put('speed', speed * (gear1.getToothCount() / g2));
    map.put('gear', gear2);
    
    return map;
}


class Gear {
    int m_x;
    int m_y;
    color m_c;
    float m_speed;
    int m_angle;
    int m_teeth;
    boolean m_clockwise;
    
    Gear(int x, int y, float speed, int angle, color c, int teeth, boolean clockwise) {
        m_x = x;
        m_y = y;
        m_speed = speed;
        m_angle = angle;
        m_c = c;
        m_teeth = teeth;
        m_clockwise = clockwise;
    }
    
    void drawGear() {
        spin();
        pushMatrix();
        translate(m_x, m_y);
        
        rotate(m_angle);
        noStroke();
        
        int d = m_teeth * 15;
        
        /*
         The gear circle
         */
        fill(m_c);
        ellipse(0, 0, d, d);
        
        /*
         The inner gap
         */
        fill();
        ellipse(0, 0, d / 4, d / 4);
        
        drawTeeth((d / 2) - 5, m_teeth, m_c);
        
        popMatrix();
    }
    
    void rot(int angle) {
        m_angle -= angle;
    }
    
    int getRot() {
        return (180 / Math.PI) * m_angle;
    }
    
    void spin() {
        if (m_clockwise) {
            m_angle += m_speed;
        } else {
            m_angle -= m_speed;
        }
    }
    
    void drawTeeth(int d, int plots) {
        float increase = Math.PI * 2 / plots;
        float angle = 0;
        float x = 0;
        float y = 0;
        
        pushMatrix();
        
        for (var i = 0; i < plots; i++ ) {
            pushMatrix();
            double t = 2 * Math.PI * i / plots;
            int x = (int) Math.round((d + (toothSize / 2)) * Math.cos(t));
            int y = (int) Math.round((d + (toothSize / 2)) * Math.sin(t));
            
            translate(x, y);
            
            fill(m_c);
            rotate(angle - 55);
            
            beginShape();
            vertex(-(toothSize / 4) + 2, -(toothSize / 2));     // upper left
            vertex((toothSize / 4) - 6, -(toothSize / 2));      // upper right
            vertex(toothSize / 4, -(toothSize / 2) + 8);        // upper right bump
            vertex(toothSize / 2, toothSize / 2);               // bottom right
            vertex(-(toothSize / 2), toothSize / 2);            // bottom left
            vertex(-(toothSize / 2) + 4, -(toothSize / 2) + 8); // upper left bump
            endShape();
            angle += increase;
            
            //fill(255, 255, 0);
            //ellipse(0, 0, 10, 10);
            popMatrix();
        }
        popMatrix();
    }
    
    int getX() {
        return m_x;
    }
    
    int getY() {
        return m_y;
    }
    
    int getToothCount() {
        return m_teeth;
    }
}

void mousePressed() {
    stopped = !stopped;
}
