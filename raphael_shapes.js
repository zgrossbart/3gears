shapes = {
    createCircle: function() {
        var paper = Raphael('raphaelCircle', 200, 200);
        var circle = paper.ellipse(100, 100, 10, 10);
        circle.attr({
            'fill': 'white',
            'stroke': 'white'
        });
        
    }
};

window.onload = function () {
    shapes.createCircle();
};
    
