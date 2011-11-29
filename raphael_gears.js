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
 
var speed = 0.75;
var scale = 15;
var toothSize = 28;
var clockwise = false;

g = {
    stopped: false,
    create: function(/*Point*/ p, /*int*/ teeth, /*string*/ c, /*int*/ speed, /*boolean*/ clockwise) {
        
        g.speed = speed;
        g.clockwise = clockwise;
        
        var d = teeth * scale;
        
        var outerCircle = g.paper.ellipse(p.x, p.y, d / 2, d / 2);
        outerCircle.attr({
            'fill': c,
            'stroke': c
        });
        outerCircle.click(function() {
            g.stopped = !g.stopped;
        });
        
        var innerCircle = g.paper.ellipse(p.x, p.y, d / 8, d / 8);
        innerCircle.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        innerCircle.click(function() {
            g.stopped = !g.stopped;
        });
        
        var st = g.drawTeeth((d / 2) - 5, d / scale, c, p);
        
        setInterval(function() {
            if (!g.stopped) {
                st.rotate(6, 100, 100);
            }
        }, 66);
    },
    
    drawTeeth: function(/*int*/ d, /*int*/ plots, /*color*/ c, /*Point*/ p) {
        var increase = Math.PI * 2 / plots;
        var angle = 0;
        
        var st = g.paper.set();
        
        for (var i = 0; i < plots; i++) {
            var t = 2 * Math.PI * i / plots;
            var x = Math.round((d + (toothSize / 2)) * Math.cos(t));
            var y = Math.round((d + (toothSize / 2)) * Math.sin(t));
            
            var tooth = g.createTooth(c);
            tooth.translate(p.x + x, p.y + y);
            tooth.rotate(((180 / Math.PI) * angle) + 90);
            st.push(tooth);
            angle += increase;
        }
        
        return st;
    },
    
    createTooth: function(/*color*/ c) {
         
        var path = 'M ' + (-(toothSize / 4) + 2) + ' ' + (-(toothSize / 2)) +   // upper left
            'L ' + ((toothSize / 4) - 6) + ' ' + (-(toothSize / 2)) +           // upper right
            'L ' + (toothSize / 4) + ' ' + (-(toothSize / 2) + 8) +             // upper right bump
            'L ' + (toothSize / 2) + ' ' + (toothSize / 2) +                    // bottom right
            'L ' + (-(toothSize / 2)) + ' ' + (toothSize / 2) +                 // bottom left
            'L ' + (-(toothSize / 2) + 4) + ' ' + (-(toothSize / 2) + 8);       // upper left bump
        path += ' z';

        return g.paper.path(path).attr({
            fill: c, 
            'stroke': c, 
            'stroke-width': 1});
    }
};

window.onload = function () {
    g.paper = Raphael('canvas', 960, 650);
    
    var back = g.paper.rect(0, 0, 960, 650);
    back.attr({
        'fill': '#ececec',
        'stroke': '#ececec'
    });

    back.click(function() {
        g.stopped = !g.stopped;
    });
    
    g.create({
        'x': 250,
        'y': 250
    }, 15, '#ee2a33', !clockwise);
    
};
