
var booksTableDiv = $('#books-table-div');

function deleteBook(bookId) {
    console.log('deleteBook with id: '+ bookId);
    $.ajax({
        type: 'DELETE',
        url: booksURL + '/' + bookId,
        success: function(data, textStatus, jqXHR) {
            console.log("Book deleted successfully: " + textStatus);
            window.alert("Book deleted successfully: " + textStatus);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("delete Book with error: " + textStatus);
            window.alert("delete Book with error: " + textStatus);
        }
    })
}

function setTriggers() {
    $("button[id^='btnDeleteBook']").each(function (i, el) {
        console.log(el.id);
        $('#' + el.id).click(function() {
            deleteBook(el.name);
            return false
        })
    })
}

function addAuthorLi(body, author) {
    body.append('<li>' + author.firstName + ' ' + author.lastName + '</li>')
}

function renderAuthorsForBookList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    if (list.length < 1) return;

    var bookId = list[0].bookId;
    var authorsListN = $('#authors-list-' + bookId);

    authorsListN.empty();
    $.each(list, function(index, entry) { addAuthorLi(authorsListN, entry) });
}

function findAuthorsForBook(id) {
    console.log('findAuthorsForBook');
    $.ajax({
        type: 'GET',
        url: authorsURL + '/' + id,
        dataType: "json",
        success: renderAuthorsForBookList
    })
}

function appendBooksTableHeader(body) {
    body.append(
        '<thead><tr>'
        + '  <td>ISBN</td>'
        + '  <td>Название книги</td>'
        + '  <td>Издание</td>'
        + '  <td>Год издания</td>'
        + '  <td>Автор(ы)</td>'
        + '  <td>Жанр</td>'
        + '  <td>Рецензии</td>'
        + '  <td></td>'
        + '  <td></td>'
        + '</tr></thead>'
    );
}

function opportunityOfDeleteBook(btnDeleteBookId, book.id) {
    console.log('opportunityOfDeleteBook');
    $.ajax({
        type: 'GET',
        url: reviewsURL + '/count/by-book/' + id,
        dataType: "json",
        success: function (data) {
            console.log('opportunityOfDeleteBook inline function data: ' + data);
            $(btnDeleteBookId).hide()
        }
    })
}

function addBookRow(body, book) {
    btnDeleteBookId = "btnDeleteBook-' + book.id
    body.append(
        '<tr>'
        + '  <td class="tg0-cl">' + book.isbn + '</td>'
        + '  <td>' + book.title + '</td>'
        + '  <td>' + book.editionNumber + '</td>'
        + '  <td>' + book.copyright + '</td>'
        + '  <td class="tg0-cl">'
        + '    <ul id="authors-list-' + book.id + '" class="w3-margin-right">'
        + '    </ul>'
        + '  </td>'
        + '  <td>' + book.genre + '</td>'
        + '  <td>'
        + '      <a href="/reviews-list?bookId=' + book.id + '">Reviews</a>'
        + '  </td>'
        + '  <td>'
        + '      <a href="/book-edit?bookId=' + book.id + '">Edit</a>'
        + '  </td>'
        + '  <td>'
        + '    <form id="book-delete-form" class="inline">'
        + '      <button form="book-delete-form" id="' + btnDeleteBookId + '" name="' + book.id + '" '
        + 'class="link-button">'
        + 'Delete</button>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
    opportunityOfDeleteBook(btnDeleteBookId, book.id);
    findAuthorsForBook(book.id)
}

function appendBooksTableFooter(body) {
    body.append(
        '<tr>'
        + '  <td colspan="9">'
        + '    <form id="button-form" th:action="@{/book-create}" method="get" action="/book-create">'
        + '      <button type="submit" >Create new</button>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
}

function renderBooksList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    booksTableDiv.empty();
    booksTableDiv.append('<table id="books-table" class="tg0"></table>');

    var booksTable = $('#books-table');

    appendBooksTableHeader(booksTable);
    booksTable.append('<tbody id="books-table-tbody"></tbody>');

    var booksTableTbody = $('#books-table-tbody');

    $.each(list, function(index, entry) { addBookRow(booksTableTbody, entry) });
    appendBooksTableFooter(booksTableTbody);
    setTriggers()
}

function findAllBooks() {
    console.log('findAllBooks');
    $.ajax({
        type: 'GET',
        url: booksURL,
        dataType: "json",
        success: renderBooksList
    })
}

function main() {
    findAllBooks()
}

jQuery(document).ready(main());