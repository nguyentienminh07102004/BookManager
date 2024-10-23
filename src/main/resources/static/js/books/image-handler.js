$(document).ready(function () {
    $("#thumbnail").on("change", function (event) {
        const file = event.target.files[0];
        const maxSize = 1048576;
        $("#error").text("");
        if (file) {
            if (file.size > maxSize) {
                $("#error").text("Upload file dưới 1MB!");
                $("#imagePreview").attr("src", "");
                return;
            }
            const reader = new FileReader();
            reader.onload = function (e) {
                const base64String = e.target.result;
                console.log(base64String); // Đây là chuỗi Base64 của ảnh
                $("#thumbnail-preview").attr("src", base64String); // Hiển thị ảnh
            };
            reader.readAsDataURL(file);
        }
    });
});