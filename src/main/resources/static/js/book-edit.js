
function bookFormToJSON() {
	var bookId = $('#bookId');
    var bookIsbn = $('#bookIsbn');
    var bookTitle = $('#bookTitle');
    var bookEditionNumber = $('#bookEditionNumber');
    var bookCopyright = $('#bookCopyright');
    var bookGenre = $('#bookGenre');

    return JSON.stringify({
        "id": bookId === "" ? "0" : bookId.val(),
        "isbn": bookIsbn.val(),
        "title": bookTitle.val(),
        "editionNumber": bookEditionNumber.val(),
        "copyright": bookCopyright.val(),
        "authors": null,
        "genre": bookGenre.val()
    })
}

function addBook() {
    console.log('addBook');
    // noinspection JSUnusedLocalSymbols
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: booksURL,
        dataType: "json",
        data: bookFormToJSON(),
        statusCode: {
            201: function(data, textStatus, jqXHR) {
                console.log("Book created successfully: " + textStatus);
                window.alert("Book created successfully: " + textStatus);
            },
            406: function(data, textStatus, jqXHR) {
                console.log("create Book with error: " + textStatus);
                window.alert("create Book with error: " + textStatus);
            },
            500: function(data, textStatus, jqXHR) {
                console.log("create Book with server error: " + textStatus);
                window.alert("create Book with server error: " + textStatus);
            }
        }
    })
}

function updateBook() {
    console.log('updateBook');
    // noinspection JSUnusedLocalSymbols
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: booksURL,
        dataType: "json",
        data: bookFormToJSON(),
        success: function(data, textStatus, jqXHR) {
            console.log("Book updated successfully: " + textStatus);
            window.alert("Book updated successfully: " + textStatus);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("update Book with error: " + textStatus);
            window.alert("update Book with error: " + textStatus);
        }
    })
}

function setTriggers() {
    $('#btnSave').click(function() {
        if ($('#bookId').val() === '0')
            addBook();
        else
            updateBook();
        return false
    })
}

function main() {
    setTriggers()
}

jQuery(document).ready(main());
