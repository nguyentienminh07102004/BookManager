$(document).ready(function () {
    // search authors
    $('#authorSearch').on('input', function () {
        const query = $(this).val();

        // search ajax
        if (query.length > 2) {
            $.ajax({
                url: '/admin/authors/search',
                method: 'GET',
                data: { name: query },
                success: function (response) {
                    if (response.response.length > 0) {
                        $('#authors-list ul').empty();
                        response.response.forEach(author => {
                            $('#authors-list ul').append(`
                                <li class="list-group-item list-group-item-action" data-id="${author.id}">${author.name}</li>
                            `);
                        });
                        $('#authors-list').show();
                        $('#add-author').html('');
                    } else {
                        $('#authors-list').hide();
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error occurred:", status, error);
                }
            });
        }
    });
// Ẩn kết quả tìm kiếm
    $(document).on('click', function(event) {
        const target = $(event.target);
        if (!target.closest('#authors-list input').length && !target.closest('#authors-list').length) {
            $('#authors-list').hide();
            $('#authorSearch').val('');
        }
    });

// Choose author
    $('#authors-list').on('click', 'li', function() {
        const authorId = $(this).data('id');
        const authorName = $(this).text();
        refeshAuthorData(authorId, authorName);
        $('#authorSearch').val('');
    });

// new author
    $('#new-author-btn').on('click', function() {
        const query = $('#authorSearch').val();
        if (query.length < 3) return;
        $.ajax({
            url: '/admin/authors',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: query }),
            success: function (response) {
                refeshAuthorData(response.response.id, response.response.name);
            },
            error: function (xhr, status, error) {
                console.error("Error occurred:", status, error);
            }
        });
    });
    function refeshAuthorData(authorId, authorName) {
        let authors = $('#selected-authors').text();
        if ($(`#checkbox-${authorId}`).length > 0) {
            console.log("Author already selected.");
        } else {
            // Thêm checkbox ẩn vào container
            $("#hidden-checkbox").append(`
            <input type="checkbox" name="authorIds" id="checkbox-${authorId}" value="${authorId}" checked>
            `);
            authors += authorName + "\t";
            $('#selected-authors').text(authors);
        }
        $('#selected-authors').text(authors);
        $('#authors-list').hide();
    }
// submit ajax
    $("#addBookForm").on('submit', function (e) {
        e.preventDefault();

        let selectedAuthors = [];

        $("input[name='authorIds']:checked").each(function () {
            selectedAuthors.push($(this).val());
        });

        const formData = {
            name: $('#name').val(),
            description: $('#description').val(),
            price: $('#price').val(),
            authorIds: selectedAuthors
        };

        $.ajax({
            url: '/admin/books/',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                alert(response.message);
                console.log(response);
            },
            error: function (xhr, status, error) {
                alert(error);
                console.error(error);
            }
        });
    });
});