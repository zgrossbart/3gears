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
    int y = (height / 3) - 15;
    
    Gear redGear = new Gear(x, y, speed, 0, #ee2a33, 15, true);                         // red gear
    gears.add(redGear);
    
    HashMap map = addGear(x, y, 15, 9, #00aeef, speed, 35);                             // blue gear
    map.get('gear').rot(14);
    
    map = addGear(map.get('x'), map.get('y'), 9, 11, #d7df21, map.get('speed'), 110);   // green yellow gear
    map.get('gear').rot(16);
    
    map = addGear(map.get('x'), map.get('y'), 11, 6, #1ab374, map.get('speed'), 25);    // green gear
    map.get('gear').rot(2);
    
    map = addGear(map.get('x'), map.get('y'), 6, 25, #F00FF0, map.get('speed'), 330);   // light purple gear
    map.get('gear').rot(11);
    
    map = addGear(map.get('x'), map.get('y'), 25, 8, #fec01e, map.get('speed'), 305);   // yellow gear
    map.get('gear').rot(2);
    
    map = addGear(map.get('x'), map.get('y'), 8, 6, #e0cb61, map.get('speed'), 225);    // beige gear
    map.get('gear').rot(7);
    
    map = addGear(map.get('x'), map.get('y'), 6, 10, #f69c9f, map.get('speed'), 180);   // pink gear
    map.get('gear').rot(18);
    
    frameRate(30);
    //draw();
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

HashMap addGear(int x, int y, int g1, int g2, color c, int speed, int angle) {
    float r1 = (g1 * 15) / 2;
    float r2 = (g2 * 15) / 2;
    
    int x2 = x + ((r1 + r2 + (toothSize - 2)) * Math.cos((angle / 180) * Math.PI));
    int y2 = y + ((r1 + r2 + (toothSize - 2)) * Math.sin((angle / 180) * Math.PI));
    
    Gear gear = new Gear(x2, y2, speed * (g1 / g2), 0, c, g2, clockwise);
    gears.add(gear);
    
    clockwise = !clockwise;
    HashMap map = new HashMap();
    
    map.put('x', x2);
    map.put('y', y2);
    map.put('speed', speed * (g1 / g2));
    map.put('gear', gear);
    
    return map;
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
    
    void rot(int angle) {
        if (m_clockwise) {
            m_angle += angle * m_speed;
        } else {
            m_angle -= angle * m_speed;
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
