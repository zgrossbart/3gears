shapes = {
    createCircle: function() {
        var paper = Raphael('raphaelCircle', 200, 200);
        var c = paper.ellipse(100, 100, 10, 10);
        c.attr({
            'fill': '#00aeef',
            'stroke': '#00aeef'
        });
        
    },
    
    createAnimation: function() {
        var paper = Raphael('raphaelAnimation', 200, 200);
        
        var back = paper.rect(10, 10, 180, 180);
        back.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        back.click(function() {
            main.raphaelAnimationStopped = !main.raphaelAnimationStopped;
        });
        
        
        var c = paper.ellipse(100, 100, 10, 10);
        c.attr({
            'fill': '#00aeef',
            'stroke': '#00aeef'
        });
        
        var r = paper.rect(60, 60, 80, 80);
        r.attr({
            'stroke-width': 2,
            'stroke': '#00aeef'
        });
        
        r.rotate(60);
        
        setInterval(function() {
            if (!main.raphaelAnimationStopped) {
                r.rotate(6);
            }
        }, 33);
    },
    
    createInteraction: function() {
        var paper = Raphael('raphaelInteraction', 200, 200);
        var r = paper.rect(60, 60, 80, 80);
        r.attr({
            'fill': '#00aeef',
            'stroke': '#00aeef'
        });
        
        var clicked = false;
        
        r.click(function() {
            if (clicked) {
                r.attr({
                    'fill': '#00aeef',
                    'stroke': '#00aeef'
                });
            } else {
                r.attr({
                    'fill': '#f00ff0',
                    'stroke': '#f00ff0'
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
    
