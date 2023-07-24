$(document).ready(function() {
    retrieveMenuForUsers();

    function retrieveMenuForUsers() {
        $.ajax({
            url: takoyomai + '/menu/retrieve',
            contentType: 'application/json',
            type: GET,
            success: function(data) {
                console.log(data)
                if (data.status === "success") {
                    if (data.object != null) {
                        fillMenusForUsers(data.object)
                    }
                }
            },
            error: function(jq,status,message) {
                console.log(message)
            },
        });
    }

    function fillMenusForUsers(menus) {

        $.each(menus, function(index, menu) {
            $("<tr></tr>")
                .attr('class', "alert menuIndex" + index)
                .attr('role', "alert")
                .appendTo("tbody")

            $("<td></td>")
                .attr('class', "menuId" + menu.id)
                .attr('id', "column1")
                .appendTo("tbody tr")

            $("<td></td>")
                .attr('class', "img")
                .attr('id', "column2")
                .css('background-image', "picture")
                .appendTo("tbody tr")

            $("<td></td>")
                .text(menu.name)
                .attr('id', "column3")
                .appendTo("tbody tr")

            $("<td></td>")
                .text(menu.description)
                .attr('id', "column4")
                .appendTo("tbody tr")

            $("<td></td>")
                .text(menu.price)
                .attr('id', "column5")
                .appendTo("tbody tr")

            $("<td></td>")
                .text(menu.type)
                .attr('id', "column6")
                .appendTo("tbody tr")
            
            $("<td></td>")
                .attr('id', "column7")
                .appendTo("tbody tr")

            $("<button></button>")
                .attr('type', "button")
                .attr('class', "close")
                .attr('id', "column8")
                .attr('data-dismiss', "alert")
                .attr('aria-label', "Close")
                .appendTo("#column7")

            $("<span></span>")
                .attr('aria-hidden', true)
                .attr('id', "column9")
                .appendTo("#column8")

            $("<i></i>")
                .attr('class', "ion-ios-create")
                .appendTo("#column9")
        }); 

        $("<tr></tr>")
            .attr('role', "alert")
            .appendTo("tbody")

        $("<td></td>") //nbsp
            .attr('id', "column1")
            .appendTo("tbody tr")

        $("<td></td>") //nbsp
            .attr('id', "columnPicture")
            .appendTo("tbody tr");

        $("<input></input>")
            .attr('type', "file")
            .attr('id', "fileData")
            .attr('class', "form-control")
            .attr('hidden', true)
            .attr('accept', ".jpg, .png, .jpeg")
            .appendTo("tbody tr td#columnPicture")

        $("<td></td>") //name
            .attr('id', "columnName")
            .appendTo("tbody tr")

        $("<input></input>")
            .attr('type', "text")
            .attr('id', "menuName")
            .attr('class', "form-control")
            .attr('hidden', true)
            .appendTo("tbody tr td#columnName")

        $("<td></td>") //description
            .attr('id', "columnDesc")
            .appendTo("tbody tr")
        
        $("<input></input>")
            .attr('type', "text")
            .attr('id', "menuDesc")
            .attr('class', "form-control")
            .attr('hidden', true)
            .appendTo("tbody tr td#columnDesc")

        $("<td></td>") // price
            .attr('id', "columnPrice")
            .appendTo("tbody tr")
        
        $("<input></input>")
            .attr('type', "text")
            .attr('id', "menuPrice")
            .attr('class', "form-control")
            .attr('hidden', true)
            .appendTo("tbody tr td#columnPrice")

        $("<td></td>") // dish
            .attr('id', "columnDish")
            .appendTo("tbody tr")

        $("<div></div>")
            .attr('class', "dropdown")
            .attr('hidden', true)
            .appendTo("tbody tr td#columnDish")
        
        $("<button></button>")
            .attr('class', "btn btn-danger dropdown-toggle")
            .attr('type', "button")
            .attr('id', "dropdownMenuButton")
            .attr('data-bs-toggle', "dropdown")
            // .attr('aria-haspopup', true)
            .attr('aria-expanded', false)
            .text("Dish Type")
            .appendTo("tbody tr td#columnDish div")

        $("<ul></ul>")
            .attr('class', "dropdown-menu")
            .attr('aria-labelledby', "dropdownMenuButton")
            .appendTo("tbody tr td#columnDish div")

        $("<li></li>")
        .attr('class', "list1")
        .appendTo("tbody tr td#columnDish div ul.dropdown-menu")

        $("<a></a>")
            .attr('class', "dropdown-item")
            .attr('href', "#")
            .text("Tokoyaki")
            .appendTo("tbody tr td#columnDish div ul.dropdown-menu li.list1")

        $("<li></li>")
            .attr('class', "list2")
            .appendTo("tbody tr td#columnDish div ul.dropdown-menu")
    
        $("<a></a>")
            .attr('class', "dropdown-item")
            .attr('href', "#")
            .text("Side Dish")
            .appendTo("tbody tr td#columnDish div ul.dropdown-menu li.list2")

        $("<li></li>")
            .attr('class', "list3")
            .appendTo("tbody tr td#columnDish div ul.dropdown-menu")

        $("<a></a>")
            .attr('class', "dropdown-item")
            .attr('href', "#")
            .text("Beverage")
            .appendTo("tbody tr td#columnDish div ul.dropdown-menu li.list3")
        
        $("<td></td>") //button/nbsp
            .attr('id', "columnButton")
            .appendTo("tbody tr")

        $("<button></button>")
            .attr('type', "button")
            .attr('class', "create")
            .attr('id', "btnShowFieldMenu")
            .attr('data-dismiss', "success")
            .attr('aria-label', "Close")
            .appendTo("tbody tr td#columnButton")

        $("<span></span>")
            .attr('aria-hidden', true)
            .appendTo("tbody tr td#columnButton button#btnShowFieldMenu")

        $("<i></i>")
            .attr('class', "ion-ios-create")
            .appendTo("tbody tr td#columnButton button#btnShowFieldMenu span")

        $("<div></div>")
            .attr('class', "input-group")
            .attr('hidden', true)
            .appendTo("tbody tr td#columnButton")

        $("<button></button>")
            .attr('type', "button")
            .attr('class', "create")
            .attr('id', "btnCreateMenu")
            .attr('data-dismiss', "success")
            .attr('aria-label', "Close")
            .appendTo("tbody tr td#columnButton div")

        $("<span></span>")
            .attr('aria-hidden', true)
            .appendTo("tbody tr td#columnButton div button#btnCreateMenu")

        $("<i></i>")
            .attr('class', "ion-ios-add")
            .appendTo("tbody tr td#columnButton div button#btnCreateMenu span")

        $("<button></button>")
            .attr('type', "button")
            .attr('class', "btnClose")
            .attr('id', "btnCloseCreateMenu")
            .attr('data-dismiss', "success")
            .attr('aria-label', "Close")
            .appendTo("tbody tr td#columnButton div")

        $("<span></span>")
            .attr('aria-hidden', true)
            .appendTo("tbody tr td#columnButton div button#btnCloseCreateMenu")

        $("<i></i>")
            .attr('class', "ion-ios-close")
            .appendTo("tbody tr td#columnButton div button#btnCloseCreateMenu span")
    }
});