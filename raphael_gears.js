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
        
        var outerCircle = g.paper.circle(p.x, p.y, d / 2);
        outerCircle.attr({
            fill: c,
            stroke: c
        });
        outerCircle.click(function() {
            g.stopped = !g.stopped;
        });
        
        var innerCircle = g.paper.circle(p.x, p.y, d / 8);
        innerCircle.attr({
            fill: 'white',
            stroke: 'white'
        });
        innerCircle.click(function() {
            g.stopped = !g.stopped;
        });
        
        var st = g.drawTeeth((d / 2) - 5, d / scale, c, p),
            an = Raphael.animation({transform: "r360," + [p.x, p.y]}, 5000);
        
        var path = "";
        for (var i = 0, ii = st.length; i < ii; i++) {
            path += Raphael.mapPath(st[i].attr("path"), st[i].matrix);
        }
        st.remove();
        st = g.paper.path(path).attr({
            stroke: "none",
            fill: c
        });
        
        st.animate(an.repeat(Infinity));
    },
    
    drawTeeth: function(/*int*/ d, /*int*/ plots, /*color*/ c, /*Point*/ p) {
        var increase = 360 / plots;
        var angle = 0;
        
        var st = g.paper.set();
        
        for (var i = 0; i < plots; i++) {
            var tooth = g.createTooth(c);
            tooth.transform("t" + [p.x, p.y - (d + (toothSize / 2))] + "R" + [angle, p.x, p.y]);
            st.push(tooth);
            angle += increase;
        }
        
        return st;
    },
    
    createTooth: function(/*color*/ c) {
        var t2 = toothSize / 2;
        var path = 'M ' + (-t2 / 2 + 2) + ' ' + (-t2) +   // upper left
            'L ' + (t2 / 2 - 6) + ' ' + (-t2) +           // upper right
            'L ' + (t2 / 2) + ' ' + (-t2 + 8) +             // upper right bump
            'L ' + t2 + ' ' + t2 +                    // bottom right
            'L ' + (-t2) + ' ' + t2 +                 // bottom left
            'L ' + (-t2 + 4) + ' ' + (-t2 + 8);       // upper left bump
        path += ' z';

        return g.paper.path(path).attr({
            fill: c, 
            stroke: "none"
        });
    }
};

window.onload = function () {
    g.paper = Raphael('canvas', 960, 650);
    
    var back = g.paper.rect(0, 0, 960, 650);
    back.attr({
        fill: '#ececec',
        stroke: 'none'
    });

    back.click(function() {
        g.stopped = !g.stopped;
    });
    
    g.create({
        'x': 250,
        'y': 250
    }, 15, '#00aeef', !clockwise);
    
};
