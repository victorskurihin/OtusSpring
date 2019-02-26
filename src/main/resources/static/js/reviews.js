
var reviewsTableDiv = $('#reviews-table-div');

function setTriggers() {
    // TODO
}

function tableHeader(body) {
    body.append(
        '<thead><tr>'
        + '<td>Рецензии</td>'
        + ' <td></td>'
        + ' <td></td>'
        + '</tr></thead>'
    );
}

function addRow(body, review) {
    body.append(
        '<tr>'
        + '  <td>' + review.review + '</td>'
        + '  <td>'
        + '    <a href="/review-edit?reviewId=' + review.id + '&bookId=' + bookId + '">Edit</a>'
        + '  </td>'
        + '  <td>'
        + '    <form method="post" action="/review-delete" class="inline">'
        + '         <input hidden type="hidden" name="reviewId" value="' + review.id + '"/>'
        + '         <input hidden type="hidden" name="bookId" value="' + bookId + '"/>'
        + '         <button type="submit" name="submit_param" value="submit_value" class="link-button">'
        + 'Delete</button>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
}

function tableFooter(body) {
    body.append(
        '<tr>'
        + '  <td colspan="3">'
        + '    <form id="button-form" method="get" action="/review-create">'
        + '      <div class="dtb0">'
        + '        <div class="dtb0Body">'
        + '          <div class="dtb0Row">'
        + '            <div class="dtb0Cell">'
        + '              <button type="reset" onclick="history.back()">Go Back</button>'
        + '            </div>'
        + '            <div class="dtb0Cell">'
        + '              <input hidden type="hidden" name="bookId" value="' + bookId + '"/>'
        + '              <button type="submit" >Create new</button>'
        + '            </div>'
        + '          </div>'
        + '        </div>'
        + '      </div>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
}

function renderList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    reviewsTableDiv.empty();
    reviewsTableDiv.append('<table id="reviews-table" class="tg0"></table>');

    var reviewsTable = $('#reviews-table');

    tableHeader(reviewsTable);
    reviewsTable.append('<tbody id="reviews-table-tbody"></tbody>');

    var reviewsTableTbody = $('#reviews-table-tbody');

    $.each(list, function(index, entry) { addRow(reviewsTableTbody, entry) });
    tableFooter(reviewsTableTbody);
    setTriggers()
}

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: reviewsURL + '/' + bookId,
        dataType: "json",
        success: renderList
    })
}

function main() {
    findAll()
}

jQuery(document).ready(main());
