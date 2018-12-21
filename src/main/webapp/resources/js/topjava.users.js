const ajaxUrl = "ajax/admin/users/";
let datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
    check();
});

function check() {
    $(".checkbox")
        .click(function () {
            let id = $(this).closest("tr").attr("id");
            $.ajax({
                url: ajaxUrl + id,
                type: "PUT"
            }).done(function () {
                // updateTable();
                successNoty("enabled");
            });
            // successNoty("checked: " + $(this).closest("tr").attr("id") + " " + $(this).is(":checked"));
        })
        .on('change', function () {
            $(this).closest('tr').toggleClass('lightgray', $(this).not(':checked'));
        });
}

