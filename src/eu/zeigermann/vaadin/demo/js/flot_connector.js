window.de_zeigermann_vaadin_js_Flot = function() {
    var element = $(this.getElement());
    
    this.onStateChange = function() {
        $.plot(element, this.getState().series, {grid: {clickable: true}});
    };
    
      // Communicate local events back to server-side component
     element.bind('plotclick', function(event, point, item) {
        if (item) {
        	window.notify(item.seriesIndex, item.dataIndex);
        }
  });
};