
var reviewsTableDiv = $('#reviews-table-div');

function setTriggers() {
    // TODO
}

function appendReviewsTableHeader(body) {
    body.append(
        '<thead><tr>'
        + '  <td>Рецензии</td>'
        + '  <td>'
        + '    <form id="book-edit-form" class="inline">'
        + '      <button form="book-edit-form" class="link-button-disabled" onclick="return false">Edit</button>'
        + '    </form>'
        + '  </td>'
        + '  <td>'
        + '    <form id="book-delete-form" class="inline">'
        + '      <button form="book-delete-form" class="link-button-disabled" onclick="return false">Delete</button>'
        + '    </form>'
        + '  </td>'
        + '</tr></thead>'
    );
}

function addReviewRow(body, review) {
    body.append(
        '<tr>'
        + '  <td>' + review.review + '</td>'
        + '  <td>'
        + '    <a href="/review-edit?reviewId=' + review.id + '&bookId=' + bookId + '">Edit</a>'
        + '  </td>'
        + '  <td>'
        + '    <form method="post" action="/review-delete" class="inline">'
        + '      <input hidden type="hidden" name="reviewId" value="' + review.id + '"/>'
        + '      <input hidden type="hidden" name="bookId" value="' + bookId + '"/>'
        + '      <button type="submit" name="submit_param" value="submit_value" class="link-button">'
        + 'Delete</button>'
        + '    </form>'
        + '  </td>'
        + '</tr>'
    );
}

function appendReviewsTableFooter(body) {
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

function renderReviewsList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    reviewsTableDiv.empty();
    reviewsTableDiv.append('<table id="reviews-table" class="tg0"></table>');

    var reviewsTable = $('#reviews-table');

    appendReviewsTableHeader(reviewsTable);
    reviewsTable.append('<tbody id="reviews-table-tbody"></tbody>');

    var reviewsTableTbody = $('#reviews-table-tbody');

    $.each(list, function(index, entry) { addReviewRow(reviewsTableTbody, entry) });
    appendReviewsTableFooter(reviewsTableTbody);
    setTriggers()
}

function findAllReviews() {
    console.log('findAllReviews');
    $.ajax({
        type: 'GET',
        url: reviewsURL + '/' + bookId,
        dataType: "json",
        success: renderReviewsList
    })
}

function main() {
    findAllReviews()
}

jQuery(document).ready(main());
