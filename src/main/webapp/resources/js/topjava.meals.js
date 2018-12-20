const ajaxUrl = "ajax/meals/";
let datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
                "desc"
            ]
        ]
    });
    makeEditable();
});

function clearFilter() {
    // $("#filter").find(":input").reset();
    // $("#filter").find(":input").cleanData;
    $("#filter").find(":input").val("");
}

function filter() {
    let form = $("#filter");
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        data: form.serialize()
    }).done(function (data) {
        datatableApi.clear().rows.add(data).draw();
        successNoty("data filtered");
    });
}