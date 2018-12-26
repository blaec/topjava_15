const mealAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done( function () {
        updateTableByData;
        successNoty("meal.filtered");
    });
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
    successNoty("meal.filtercleared");
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return data.replace('T', ' ');
                        }
                        return data;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.excess) {
                    $(row).attr("data-mealExcess", false);
                } else {
                    $(row).attr("data-mealExcess", true);
                }
            }
        }),
        updateTable: updateFilteredTable
    });

//  http://xdsoft.net/jqplugins/datetimepicker/
    const startDate = $('#startDate');
    const endDate = $('#endDate');
    const dateFormat = 'Y-m-d';
    const theme = 'dark';
    startDate.datetimepicker({
        timepicker: false,
        format: dateFormat,
        formatDate: dateFormat,
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        },
        theme:theme
    });
    endDate.datetimepicker({
        timepicker: false,
        format: dateFormat,
        formatDate: dateFormat,
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        },
        theme:theme
    });

    const startTime = $('#startTime');
    const endTime = $('#endTime');
    const timeFormat = 'H:i';
    startTime.datetimepicker({
        datepicker: false,
        format: timeFormat,
        onShow: function (ct) {
            this.setOptions({
                maxTime: endTime.val() ? endTime.val() : false
            })
        },
        theme:theme
    });
    endTime.datetimepicker({
        datepicker: false,
        format: timeFormat,
        onShow: function (ct) {
            this.setOptions({
                minTime: startTime.val() ? startTime.val() : false
            })
        },
        theme:theme
    });

    $('#dateTime').datetimepicker({
        format: dateFormat + ' ' + timeFormat,
        theme:theme
    });
});