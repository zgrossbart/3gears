jQuery(document).ready(function() {
    var paper = Raphael('raphaelCircle', 200, 200);
    var c = paper.ellipse(100, 100, 10, 10);
    c.attr({
        'fill': '#00aeef',
        'stroke': '#00aeef'
    });
});
