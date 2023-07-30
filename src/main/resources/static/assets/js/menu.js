function fillMenusForUsers(menus) {
    $.each(menus, function(index, menu) {

        $("<tr></tr>")
            .attr('class', "alert")
            .attr('id', menu.menuId)
            .attr('role', "alert")
            .appendTo("table#tableMenu tbody")

        $("<td></td>")
            .attr('class', "col-sm-1")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<div></div>")
            .attr('class', "form-checked")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td")

        $("<input></input>")
            .attr('class', "form-check-input")
            .attr('type', "checkbox")
            .attr('id', menu.menuId)
            .attr('checked', menu.today)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td div")

        $("<td></td>")
            .attr('class', "img")
            .attr('id', "columnPicture" + menu.menuId)
            .css('background-image', "picture")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<a></a>")
            .attr('href', "data:" + menu.fileType + ";base64,"+menu.fileByte)
            .attr('class', "glightbox")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td#columnPicture" + menu.menuId)

        $("<img></img>")
            .attr('src', "data:" + menu.fileType + ";base64,"+menu.fileByte)
            .css('border-radius', "0")
            .css('width', "100px")
            .css('height', "100px")
            .attr('class', "menu-img img-fluid")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td#columnPicture" + menu.menuId + " a")

        $("<td></td>")
            .text(menu.name)
            .attr('id', "column3" + menu.menuId)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<td></td>")
            .text(menu.description)
            .attr('id', "column4" + menu.menuId)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<td></td>")
            .text(menu.price)
            .attr('id', "column5" + menu.menuId)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<td></td>")
            .text(menu.type)
            .attr('id', "column6" + menu.menuId)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)
        
        $("<td></td>")
            .attr('id', "column7" + menu.menuId)
            .appendTo("table#tableMenu tbody tr#" + menu.menuId)

        $("<button></button>")
            .attr('type', "button")
            .attr('class', "close")
            .attr('data-dismiss', "alert")
            .attr('aria-label', "Close")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td#column7" + menu.menuId)

        $("<span></span>")
            .attr('aria-hidden', true)
            .attr('id', "column9")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td#column7" + menu.menuId + " button")

        $("<i></i>")
            .attr('class', "ion-ios-create")
            .appendTo("table#tableMenu tbody tr#" + menu.menuId + " td#column7" + menu.menuId+ " button span")
    }); 

    formTableToCreate();
}

function formTableToCreate() {
    $("<tr></tr>")
        .attr('id', "rowAdd")
        .attr('role', "alert")
        .appendTo("table#tableMenu tbody")

    $("<td></td>") //nbsp
        .attr('class', "col-md-1")
        .appendTo("table#tableMenu tbody tr#rowAdd");

    $("<td></td>") //nbsp
        .attr('id', "columnPicture")
        .attr('class', "col-md-2")
        .appendTo("table#tableMenu tbody tr#rowAdd");

    $("<div></div>")
        
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnPicture");

    $("<input></input>")
        .attr('type', "file")
        .attr('name', "file")
        .attr('id', "fileData")
        .attr('class', "form-control")
        .attr('hidden', true)
        .attr('accept', ".jpg, .png, .jpeg")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnPicture div")

    $("<td></td>") //name
        .attr('id', "columnName")
        .attr('class', "col-md-2")
        .appendTo("table#tableMenu tbody tr#rowAdd")

    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuName")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnName")

    $("<td></td>") //description
        .attr('id', "columnDesc")
        .attr('class', "col-md-4")
        .appendTo("table#tableMenu tbody tr#rowAdd")
    
    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuDesc")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDesc")

    $("<td></td>") // price
        .attr('id', "columnPrice")
        .appendTo("table#tableMenu tbody tr#rowAdd")
    
    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuPrice")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnPrice")

    $("<td></td>") // dish
        .attr('id', "columnDish")
        .attr('class', "col-md-1")
        .appendTo("table#tableMenu tbody tr#rowAdd")

    $("<input></input>")
        .attr('class', "form-control")
        .attr('list', "datalistOptions")
        .attr('id', "dishDropdown")
        .attr('hidden', true)
        // .attr('readonly', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDish")

    $("<datalist></datalist>")
        .attr('id', "datalistOptions")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDish input#dishDropdown")

    $("<option></option>")
        .val("Takoyaki")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<option></option>")
        .val("Side Dish")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<option></option>")
        .val("Beverage")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<td></td>") //button/nbsp
        .attr('id', "columnButton")
        // .attr('class', "col-md-1")
        .appendTo("table#tableMenu tbody tr#rowAdd")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "create")
        .attr('id', "btnShowFieldMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton button#btnShowFieldMenu")

    $("<i></i>")
        .attr('class', "ion-ios-create")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton button#btnShowFieldMenu span")

    $("<div></div>")
        .attr('class', "input-group")
        .attr('hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "create")
        .attr('id', "btnCreateMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div button#btnCreateMenu")

    $("<i></i>")
        .attr('class', "ion-ios-add")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div button#btnCreateMenu span")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "btnClose")
        .attr('id', "btnCloseCreateMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div button#btnCloseCreateMenu")

    $("<i></i>")
        .attr('class', "ion-ios-close")
        .appendTo("table#tableMenu tbody tr#rowAdd td#columnButton div button#btnCloseCreateMenu span")
}

function fillMenusForAnonymous(menus, appendIn) {
    $.each(menus, function(index, menu) {
        $("<div></div>")
            .attr('class', "col-lg-4 menu-item")
            .attr('id', menu.menuId)
            .appendTo(appendIn);

        $("<img></img>")
            .attr('src', "data:" + menu.fileType + ";base64,"+menu.fileByte)
            .attr('class', "menu-img img-fluid")
            .appendTo(appendIn + " div#" + menu.menuId);

        $("<h4></h4>")
            .text(menu.name)
            .appendTo(appendIn + " div#" + menu.menuId);

        $("<p></p>")
            .attr('class', "ingredients")
            .text(menu.description)
            .appendTo(appendIn + " div#" + menu.menuId);

        $("<p></p>")
            .attr('class', "price")
            .text("₱ " + menu.price)
            .appendTo(appendIn + " div#" + menu.menuId);
    });
}