/**
 * Created by paradise on 17.04.16.
 */

$(document).ready(function () {

    $("table").DataTable();

    $(document).on('click', '.ban', ban);
    $(document).on('click', '.pardon', pardon);
});

function ban() {
    var td = $(this).parent('td');
    var status = td.siblings('.status');
    var email = td.siblings('.email').text();

    $.ajax({
        url: '/users/ban',
        type: 'post',
        data: {
            email: email
        },
        success: function () {
            status.text('banned');
            td.children('button').remove();
            var pardon = $("<button>").text('pardon').addClass('pardon').addClass('btn').addClass('btn-outline')
                .addClass('btn-primary').addClass('btn-xs');
            td.append(pardon);
        }
    });
}

function pardon() {
    var td = $(this).parent('td');
    var status = td.siblings('.status');
    var email = td.siblings('.email').text();

    $.ajax({
        url: '/users/pardon',
        type: 'post',
        data: {
            email: email
        },
        success: function () {
            status.text('active');
            td.children('button').remove();
            var ban = $("<button>").text('ban').addClass('ban').addClass('btn').addClass('btn-outline')
                .addClass('btn-danger').addClass('btn-xs');
            td.append(ban);
        }
    });
}
