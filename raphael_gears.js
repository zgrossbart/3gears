var speed = 0.75;
var scale = 15;
var toothSize = 28;
var clockwise = false;

g = {
    create: function(/*Point*/ p, /*int*/ teeth, /*string*/ c, /*int*/ speed, /*boolean*/ clockwise) {
        
        var st = g.paper.set();
        g.speed = speed;
        g.clockwise = clockwise;
        
        var d = teeth * scale;
        
        var outerCircle = g.paper.ellipse(p.x, p.y, d / 2, d / 2);
        outerCircle.attr({
            'fill': c,
            'stroke': c
        });
        st.push(outerCircle);
        
        var innerCircle = g.paper.ellipse(p.x, p.y, d / 8, d / 8);
        innerCircle.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        st.push(innerCircle);
        
        //this.group.addChild(this.drawTeeth((d / 2) - 5, d / scale, c, p));
        g.drawTeeth((d / 2) - 5, d / scale, c, p);
    },
    
    drawTeeth: function(/*int*/ d, /*int*/ plots, /*color*/ c, /*Point*/ p) {
        var increase = Math.PI * 2 / plots;
        var angle = 0;
        
        //var teeth = new Group();
        this.pos = p;
        
        //var symbol = new Symbol(this.createTooth(c));
        
        for (var i = 0; i < plots; i++) {
            var t = 2 * Math.PI * i / plots;
            var x = Math.round((d + (toothSize / 2)) * Math.cos(t));
            var y = Math.round((d + (toothSize / 2)) * Math.sin(t));
            
            var tooth = g.createTooth(c);
            tooth.translate(p.x + x, p.y + y);
            tooth.rotate(((180 / Math.PI) * angle) + 90);
            
            /*var c = g.paper.ellipse(p.x + x, p.y + y, 5, 5);
            c.attr({
                'fill': 'red',
                'stroke': 'red'
            });*/
            //var placed = symbol.place(new Point(p.x + x, p.y + y));
            
            // 1 radian = 57.2957795 degrees
            //placed.rotate(((180 / Math.PI) * angle) + 90);
            //teeth.addChild(placed);
            angle += increase;
        }
        
        this.teethCount = plots;
        
        return;
    },
    
    createTooth: function(/*color*/ c) {
         
        var path = 'M ' + (-(toothSize / 4) + 2) + ' ' + (-(toothSize / 2)) +   // upper left
            'L ' + ((toothSize / 4) - 6) + ' ' + (-(toothSize / 2)) +           // upper right
            'L ' + (toothSize / 4) + ' ' + (-(toothSize / 2) + 8) +             // upper right bump
            'L ' + (toothSize / 2) + ' ' + (toothSize / 2) +                    // bottom right
            'L ' + (-(toothSize / 2)) + ' ' + (toothSize / 2) +                 // bottom left
            'L ' + (-(toothSize / 2) + 4) + ' ' + (-(toothSize / 2) + 8);       // upper left bump
        
        path += ' z';
        
        console.log('path: ' + path);
            
        return g.paper.path(path).attr({
            fill: c, 
            'stroke': c, 
            'stroke-width': 1});
    }
};

window.onload = function () {
    g.paper = Raphael('canvas', 960, 650);
    
    g.create({
        'x': 250,
        'y': 250
    }, 15, '#52b755', !clockwise);
    
};
