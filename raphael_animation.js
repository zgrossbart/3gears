jQuery(document).ready(function() {
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
});
