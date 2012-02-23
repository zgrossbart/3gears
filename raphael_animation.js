Raphael('raphaelAnimation', 200, 200, function () {
    var paper = this,
        anim;
    
    var c = paper.circle(100, 100, 10);
    c.attr({
        fill: '#00aeef',
        stroke: 'none'
    });
    
    var r = paper.rect(60, 60, 80, 80);
    r.attr({
        'stroke-width': 2,
        stroke: '#00aeef',
        fill: "#000",
        "fill-opacity": 0
    });
    r.click(function () {
        if (anim) {
            r.stop();
        } else {
            r.animate(a);
        }
        anim = !anim;
    });
    
    r.rotate(60);
    
    var a = Raphael.animation({transform: "...r360"}, 3000).repeat(Infinity);
    
});
