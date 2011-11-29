shapes = {
    createCircle: function() {
        var paper = Raphael('raphaelCircle', 200, 200);
        var c = paper.ellipse(100, 100, 10, 10);
        c.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        
    },
    
    createAnimation: function() {
        var paper = Raphael('raphaelAnimation', 200, 200);
        
        var back = paper.rect(10, 10, 180, 180);
        back.attr({
            'fill': 'black',
            'stroke': 'black'
        });
        back.click(function() {
            main.raphaelAnimationStopped = !main.raphaelAnimationStopped;
        });
        
        
        var c = paper.ellipse(100, 100, 10, 10);
        c.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        
        var r = paper.rect(60, 60, 80, 80);
        r.attr({
            'stroke-width': 2,
            'stroke': 'white'
        });
        
        setInterval(function() {
            if (!main.raphaelAnimationStopped) {
                r.rotate(6);
            }
        }, 66);
    },
    
    createInteraction: function() {
        var paper = Raphael('raphaelInteraction', 200, 200);
        var r = paper.rect(60, 60, 80, 80);
        r.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        
        var clicked = false;
        
        r.click(function() {
            if (clicked) {
                r.attr({
                    'fill': 'white',
                    'stroke': 'white'
                });
            } else {
                r.attr({
                    'fill': 'red',
                    'stroke': 'red'
                });
            }
            clicked = !clicked;
        });
        
    },
};

window.onload = function () {
    shapes.createCircle();
    shapes.createAnimation();
    shapes.createInteraction();
};
    
