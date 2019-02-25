
var booksTableDiv = $('#books-table-div');

function setTriggers() {
    // TODO
}

function addLi(body, author) {
    body.append('<li>' + author.firstName + ' ' + author.lastName + '</li>')
}

function renderAuthorsForBookList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    if (list.length < 1) return;

    var bookId = list[0].bookId;
    var authorsListN = $('#authors-list-' + bookId);

    authorsListN.empty();
    $.each(list, function(index, entry) { addLi(authorsListN, entry) });
}

function findAuthorsForBook(id) {
    console.log('findAuthorsForBook');
    $.ajax({
        type: 'GET',
        url: authorsURL + '/' + id,
        dataType: "json",
        success: renderAuthorsForBookList
    });
}

function tableHeader(body) {
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

function addRow(body, book) {
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
        + '      <a href="reviews-list?boodId=' + book.id + '">Reviews</a>'
        + '  </td>'
        + '  <td>'
        + '      <a href="book-edit?bookId=' + book.id + '">Edit</a>'
        + '  </td>'
        + '  <td>'
        + '    <form method="post" action="book-delete" class="inline">'
        + '      <input hidden type="hidden" name="bookId" value="' + book.id + '"/>'
        + '      <button type="submit" name="submit_param" value="submit_value" class="link-button">'
        + 'Delete'
        + '      </button>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
    findAuthorsForBook(book.id)
}

function renderList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    booksTableDiv.empty();
    booksTableDiv.append('<table id="books-table" class="tg0"></table>');

    var booksTable = $('#books-table');

    tableHeader(booksTable);
    booksTable.append('<tbody id ="books-table-tbody"></tbody>');
    $.each(list, function(index, entry) { addRow($('#books-table-tbody'), entry) });
    setTriggers()
}

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: booksURL,
        dataType: "json",
        success: renderList
    })
}

function main() {
    findAll()
}

jQuery(document).ready(main());