$(document).ready(function () {

//tag handler
    const input = $("#authorSearch")[0];
    const tagify = new Tagify(input, {
        whitelist: [],
        maxTags: 5,
    });
    tagify.on('input', function(e){
        const value = e.detail.value;

        $.ajax({
            url: '/admin/authors/search',
            type: 'GET',
            data: { name: value },
            success: function(response) {
                const authors = response.response.map(function (author) {
                    return author.name;
                });

                tagify.settings.whitelist = authors;
                tagify.dropdown.show(value);
            },
            error: function(xhr, status, error) {
                console.log("Error occurred: " + error);
            }
        });
    });

// submit handler
    $("#addBookForm").on('submit', function (e) {
        e.preventDefault();
        let selectedAuthors = tagify.value.map(tag => tag.value);

        const formData = {
            name: $('#name').val(),
            description: $('#description').val(),
            price: $('#price').val(),
            quantity: $('#quantity').val(),
            authors: selectedAuthors,
            thumbnail: $("#thumbnail-preview").attr("src")
        };

        $.ajax({
            url: '/admin/books/',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                alert(response.message);
                $("#addBookForm")[0].reset();
                $("#thumbnail-preview").attr("src", "");
            },
            error: function (xhr, status, error) {
                alert(error);
                console.error(error);
            }
        });
    });
});
