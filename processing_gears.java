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

HashMap addGear(int x, int y, int g1, int g2, color c, int speed, int angle) {
    float d1 = (g1 * 15) / 2;
    float d2 = (g2 * 15) / 2;
    
    float t = 2 * Math.PI * angle / 360;
    x = x + Math.round(d1 * Math.cos(t));
    y = y + Math.round(d1 * Math.sin(t));
    
    if (clockwise) {
        x = Math.round(d2 * Math.cos(t)) + x - ((toothSize + 12) / 2);
        y = Math.round(d2 * Math.sin(t)) + y + ((toothSize + 12) / 2);
    } else {
        x = Math.round(d2 * Math.cos(t)) + x + ((toothSize + 12) / 2);
        y = Math.round(d2 * Math.sin(t)) + y + ((toothSize + 12) / 2)
    }
    
    Gear gear = new Gear(x, y, speed * (g1 / g2), 0, c, g2, clockwise);
    gears.add(gear);
    
    clockwise = !clockwise;
    HashMap map = new HashMap();
    
    map.put('x', x);
    map.put('y', y);
    map.put('speed', speed * (g1 / g2));
    map.put('gear', gear);
    
    return map;
}

void setup() {
    size(800, 900);
    smooth(); 
    
    int speed = 0.02;
    
    int x = width / 3.5;
    int y = height / 5;
    
    Gear redGear = new Gear(x, y, speed, 0, #FF0000, 15, true);
    gears.add(redGear);
    
    HashMap map = addGear(x, y, 15, 9, #0000FF, speed, 25);
    map.get('gear').rot(4);
    
    map = addGear(map.get('x'), map.get('y'), 9, 11, #00FF00, map.get('speed'), 120);
    map = addGear(map.get('x'), map.get('y'), 11, 21, #F00FF0, map.get('speed'), 45);
    map = addGear(map.get('x'), map.get('y'), 21, 5, #FFA500, map.get('speed'), 150);
    map.get('gear').rot(14);
    
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

class Gear {
    int m_x;
    int m_y;
    color m_c;
    float m_speed;
    int m_angle;
    int m_teeth;
    int m_angle;
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
        rot();
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
    
    void rot(int rotations) {
        for (int i = 0; i < rotations; i++) {
            rot();
        }
    }
    
    void rot() {
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
            rotate( angle - 55 );
            quad(-(toothSize / 4), -(toothSize / 2),    // upper left
                 toothSize / 4, -(toothSize / 2),       // upper right
                 toothSize / 2, toothSize / 2,          // bottom right
                 -(toothSize / 2), toothSize / 2);      // bottom left
            angle += increase;
            
            //fill(255, 0, 0);
            //ellipse(0, 0, 10, 10);
            popMatrix();
        }
        popMatrix();
    }
}

void mousePressed() {
    stopped = !stopped;
}
