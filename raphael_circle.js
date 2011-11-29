jQuery(document).ready(function() {
    var paper = Raphael('raphaelCircle', 200, 200);
    console.log('paper: ' + paper);
    var c = paper.ellipse(100, 100, 10, 10);
    c.attr({
        'fill': '#00aeef',
        'stroke': '#00aeef'
    });
});
