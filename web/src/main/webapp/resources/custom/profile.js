/**
 * Created by paradise on 06.05.16.
 */

$(document).ready(function () {

    $(document).on('click', '.assess', function () {
        var user = $('#user').val();
        var rating = $('.rating');
        var assess = $(this);
        var assessment = assess.hasClass('fa-thumbs-o-up') ? 'dislike' : 'like';

        $.ajax({
            url: '/users/' + user + '/assess',
            type: 'post',
            data: {
                assessment: assessment
            },
            success: function (rat) {
                rat = Number(rat);
                if (rat >= 0) {
                    rating.removeClass('fa-thumbs-o-down').addClass('fa-thumbs-o-up');
                } else {
                    rating.removeClass('fa-thumbs-o-up').addClass('fa-thumbs-o-down');
                }
                rating.text(Math.abs(rat));
                if (assessment == 'dislike') {
                    assess.removeClass('fa-thumbs-o-up').addClass('fa-thumbs-o-down');
                } else {
                    assess.removeClass('fa-thumbs-o-down').addClass('fa-thumbs-o-up');
                }
            }
        });
    });

});
