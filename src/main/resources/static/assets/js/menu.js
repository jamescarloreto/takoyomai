function fillMenusForUsers(menus) {
        
    $.each(menus, function(index, menu) {
        $("<tr></tr>")
            .attr('class', "alert menuIndex" + index)
            .attr('id', "menuId" + menus[index].menuId)
            .attr('role', "alert")
            .appendTo("tbody")

        $("<td></td>")
            .attr('class', "col-sm-1")
            .appendTo("tbody tr.menuIndex" + index)

        $("<div></div>")
            .attr('class', "form-checked")
            .appendTo("tbody tr.menuIndex" + index + " td")

        $("<input></input>")
            .attr('class', "form-check-input")
            .attr('type', "checkbox")
            .attr('id', menu.menuId)
            .attr('checked', menus[index].today)
            .appendTo("tbody tr.menuIndex" + index + " td div")

        $("<td></td>")
            .attr('class', "img")
            .attr('id', "columnPicture"+index)
            .css('background-image', "picture")
            .appendTo("tbody tr.menuIndex" + index)

        $("<a></a>")
            .attr('href', "data:" + menus[index].fileType + ";base64,"+menus[index].fileByte)
            .attr('class', "glightbox")
            .appendTo("tbody tr.menuIndex" + index + " td#columnPicture" + index)

        $("<img></img>")
            .attr('src', "data:" + menus[index].fileType + ";base64,"+menus[index].fileByte)
            .css('border-radius', "0")
            .css('width', "100px")
            .css('height', "100px")
            .attr('class', "menu-img img-fluid")
            .appendTo("tbody tr.menuIndex" + index + " td#columnPicture" + index + " a")

        $("<td></td>")
            .text(menu.name)
            .attr('id', "column3")
            .appendTo("tbody tr.menuIndex" + index)

        $("<td></td>")
            .text(menu.description)
            .attr('id', "column4")
            .appendTo("tbody tr.menuIndex" + index)

        $("<td></td>")
            .text(menu.price)
            .attr('id', "column5")
            .appendTo("tbody tr.menuIndex" + index)

        $("<td></td>")
            .text(menu.type)
            .attr('id', "column6")
            .appendTo("tbody tr.menuIndex" + index)
        
        $("<td></td>")
            .attr('id', "column7")
            .appendTo("tbody tr.menuIndex" + index)

        $("<button></button>")
            .attr('type', "button")
            .attr('class', "close")
            .attr('id', "column8")
            .attr('data-dismiss', "alert")
            .attr('aria-label', "Close")
            .appendTo("tbody tr.menuIndex" + index + " td#column7")

        $("<span></span>")
            .attr('aria-hidden', true)
            .attr('id', "column9")
            .appendTo("tbody tr.menuIndex" + index + " td#column7 button")

        $("<i></i>")
            .attr('class', "ion-ios-create")
            .appendTo("tbody tr.menuIndex" + index + " td#column7 button span")
    }); 

    formTableToCreate();
}

function formTableToCreate() {
    $("<tr></tr>")
        .attr('id', "rowAdd")
        .attr('role', "alert")
        .appendTo("tbody")

    $("<td></td>") //nbsp
        .attr('class', "col-md-1")
        .appendTo("tbody tr#rowAdd");

    $("<td></td>") //nbsp
        .attr('id', "columnPicture")
        .attr('class', "col-md-2")
        .appendTo("tbody tr#rowAdd");

    $("<div></div>")
        
        .appendTo("tbody tr#rowAdd td#columnPicture");

    $("<input></input>")
        .attr('type', "file")
        .attr('name', "file")
        .attr('id', "fileData")
        .attr('class', "form-control")
        .attr('hidden', true)
        .attr('accept', ".jpg, .png, .jpeg")
        .appendTo("tbody tr#rowAdd td#columnPicture div")

    $("<td></td>") //name
        .attr('id', "columnName")
        .attr('class', "col-md-2")
        .appendTo("tbody tr#rowAdd")

    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuName")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("tbody tr#rowAdd td#columnName")

    $("<td></td>") //description
        .attr('id', "columnDesc")
        .attr('class', "col-md-4")
        .appendTo("tbody tr#rowAdd")
    
    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuDesc")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("tbody tr#rowAdd td#columnDesc")

    $("<td></td>") // price
        .attr('id', "columnPrice")
        .appendTo("tbody tr#rowAdd")
    
    $("<input></input>")
        .attr('type', "text")
        .attr('id', "menuPrice")
        .attr('class', "form-control")
        .attr('hidden', true)
        .appendTo("tbody tr#rowAdd td#columnPrice")

    $("<td></td>") // dish
        .attr('id', "columnDish")
        .attr('class', "col-md-1")
        .appendTo("tbody tr#rowAdd")

    $("<input></input>")
        .attr('class', "form-control")
        .attr('list', "datalistOptions")
        .attr('id', "dishDropdown")
        .attr('hidden', true)
        // .attr('readonly', true)
        .appendTo("tbody tr#rowAdd td#columnDish")

    $("<datalist></datalist>")
        .attr('id', "datalistOptions")
        .appendTo("tbody tr#rowAdd td#columnDish input#dishDropdown")

    $("<option></option>")
        .val("Tokoyaki")
        .appendTo("tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<option></option>")
        .val("Side Dish")
        .appendTo("tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<option></option>")
        .val("Beverage")
        .appendTo("tbody tr#rowAdd td#columnDish input#dishDropdown datalist")

    $("<td></td>") //button/nbsp
        .attr('id', "columnButton")
        // .attr('class', "col-md-1")
        .appendTo("tbody tr#rowAdd")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "create")
        .attr('id', "btnShowFieldMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("tbody tr#rowAdd td#columnButton")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("tbody tr#rowAdd td#columnButton button#btnShowFieldMenu")

    $("<i></i>")
        .attr('class', "ion-ios-create")
        .appendTo("tbody tr#rowAdd td#columnButton button#btnShowFieldMenu span")

    $("<div></div>")
        .attr('class', "input-group")
        .attr('hidden', true)
        .appendTo("tbody tr#rowAdd td#columnButton")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "create")
        .attr('id', "btnCreateMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("tbody tr#rowAdd td#columnButton div")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("tbody tr#rowAdd td#columnButton div button#btnCreateMenu")

    $("<i></i>")
        .attr('class', "ion-ios-add")
        .appendTo("tbody tr#rowAdd td#columnButton div button#btnCreateMenu span")

    $("<button></button>")
        .attr('type', "button")
        .attr('class', "btnClose")
        .attr('id', "btnCloseCreateMenu")
        .attr('data-dismiss', "success")
        .attr('aria-label', "Close")
        .appendTo("tbody tr#rowAdd td#columnButton div")

    $("<span></span>")
        .attr('aria-hidden', true)
        .appendTo("tbody tr#rowAdd td#columnButton div button#btnCloseCreateMenu")

    $("<i></i>")
        .attr('class', "ion-ios-close")
        .appendTo("tbody tr#rowAdd td#columnButton div button#btnCloseCreateMenu span")
}

function fillMenusForAnonymous(menus) {

    $.each(menus, function(index, menu) {

        $("<div></div>")
            .attr('class', "col-lg-4 menu-item")
            .appendTo("#anonymousMenu");

        $("<img></img>")
            .attr('src', )

    });
}