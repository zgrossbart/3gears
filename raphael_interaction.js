jQuery(document).ready(function() {
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
});
