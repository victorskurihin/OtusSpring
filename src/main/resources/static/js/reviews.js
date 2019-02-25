
var reviewsTableDiv = $('#reviews-table-div');


function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: reviewsURL,
        dataType: "json",
        success: renderList
    })
}

function main() {
    findAll()
}

jQuery(document).ready(main());
