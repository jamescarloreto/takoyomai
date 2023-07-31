function fillOrders(data, forView, toFillField) {
    $(toFillField).html("");
    $.each(data.orders, function(index, order) {
        console.log(order)
        $("#btnPrintViewOrder").attr('hidden', !order.paid)
        
        $("<tr></tr>")
            .attr('class', "alert order" + order.menuId)
            .attr('id', order.menuId)
            .attr('role', "alert")
            .appendTo(toFillField)

        $("<td></td>")
            .attr('class', "col-sm-1")
            .text(order.menuName)
            .appendTo("tr.order" + order.menuId)

        $("<td></td>")
            .attr('class', "col-md-2 text-center col2" + order.menuId)
            .appendTo("tr.order" + order.menuId);

        $("<span></span>")
            .text(order.quantity)
            .appendTo("tr.order" + order.menuId + " td.col2" + order.menuId)
        
        $("<td></td>")
            .attr('class', "col-md-2 text-center col4" + order.menuId)
            .appendTo("tr.order" + order.menuId);

        $("<span></span>")
            .text(order.price)
            .appendTo("tr.order" + order.menuId + " td.col4" + order.menuId)
        
        if (!forView) {
            $("<td></td>")
                .attr('class', "col-md-2 align-items-center justify-content-center col3" + order.menuId)
                .appendTo("tr.order" + order.menuId);

            $("<button></button>")
                .attr('type', "button")
                .attr('class', "btnClose")
                .attr('id', order.menuId)
                .attr('data-dismiss', "success")
                .attr('aria-label', "Close")
                .appendTo("tr.order" + order.menuId + " td.col3" + order.menuId)

            $("<span></span>")
                .attr('aria-hidden', true)
                .appendTo("tr.order" + order.menuId + " td.col3" + order.menuId + " button")

            $("<i></i>")
                .attr('class', "ion-ios-close")
                .appendTo("tr.order" + order.menuId + " td.col3" + order.menuId + " button span")
        }
    });

    $("<tr></tr>")
        .attr('role', "alert")
        .attr('id', "forPrice")
        .appendTo(toFillField)

    $("<td></td>")
        .attr('id', "forPrice1st")
        .appendTo(toFillField + " tr#forPrice")

    $("<span></span>")
        .text("Total:")
        .appendTo(toFillField + " tr#forPrice td#forPrice1st")

    $("<td></td>")
        .appendTo(toFillField + " tr#forPrice")

    $("<td></td>")
        .attr('class', "text-center")
        .attr('id', "forPrice3rd")
        .appendTo(toFillField + " tr#forPrice")

    $("<span></span>")
        .text(data.total)
        .appendTo(toFillField + " tr#forPrice td#forPrice3rd")

    if (!forView) {
        $("<td></td>")
            .appendTo(toFillField + " tr#forPrice");
    }
}

function fillViewOrderHistory(data) {
    $("table#tableViewOrderHistory tbody").html("");
    console.log(data)
    $.each(data, function(index, order) {
        $("<tr></tr>")
            .attr('role', "alert")
            .attr('id', order.orderId)
            .appendTo("table#tableViewOrderHistory tbody")

        $("<td></td>")
            .text(index + 1)
            .appendTo("table#tableViewOrderHistory tbody tr#" + order.orderId)

        $("<td></td>")
            .text(order.orderDate)
            .appendTo("table#tableViewOrderHistory tbody tr#" + order.orderId)

        $("<td></td>")
            .text(order.paid ? "Yes" : "No")
            .appendTo("table#tableViewOrderHistory tbody tr#" + order.orderId)
    });
}