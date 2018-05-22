jQuery(document).ready(function($){

    var flag = false;
    $("body").on("mousemove",function(event) {
        if (event.pageY < 100) {
            if(!flag) {
                flag = true;
                $('header').fadeIn(500);
            }
        } else {
            flag = false;
            $('header').fadeOut(500);
        }
    });

    //main page slider
    $('.flexslider').flexslider({
        animation: 'fade',
        controlsContainer: '.flexslider'
    });

	var formModal = $('.cd-user-modal'),
		formLogin = formModal.find('#cd-login'),
		formSignup = formModal.find('#cd-signup'),
		formForgotPassword = formModal.find('#cd-reset-password'),
		formModalTab = $('.cd-switcher'),
		tabLogin = formModalTab.children('li').eq(0).children('a'),
		tabSignup = formModalTab.children('li').eq(1).children('a'),
		forgotPasswordLink = formLogin.find('.cd-form-bottom-message a'),
		backToLoginLink = formForgotPassword.find('.cd-form-bottom-message a'),
		mainNav = $('.main-nav');

	//open modal
	mainNav.on('click', function(event){
		$(event.target).is(mainNav) && mainNav.children('ul').toggleClass('is-visible');
	});

	//open sign-up form
	mainNav.on('click', '.cd-signup', signup_selected);
	//open login-form form
	mainNav.on('click', '.cd-signin', login_selected);

    mainNav.on('click', '.cd-signout', signout_selected);

	//close modal
	formModal.on('click', function(event){
		if( $(event.target).is(formModal) || $(event.target).is('.cd-close-form') ) {
			formModal.removeClass('is-visible');
		}	
	});
	//close modal when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		formModal.removeClass('is-visible');
	    }
    });

	//switch from a tab to another
	formModalTab.on('click', function(event) {
		event.preventDefault();
		( $(event.target).is( tabLogin ) ) ? login_selected() : signup_selected();
	});

	//hide or show password
	$('.hide-password').on('click', function(){
		var togglePass= $(this),
			passwordField = togglePass.prev('input');
		
		( 'password' == passwordField.attr('type') ) ? passwordField.attr('type', 'text') : passwordField.attr('type', 'password');
		( 'Hide' == togglePass.text() ) ? togglePass.text('Show') : togglePass.text('Hide');
		//focus and move cursor to the end of input field
		passwordField.putCursorAtEnd();
	});

	//show forgot-password form 
	forgotPasswordLink.on('click', function(event){
		event.preventDefault();
		forgot_password_selected();
	});

	//back to login from the forgot-password form
	backToLoginLink.on('click', function(event){
		event.preventDefault();
		login_selected();
	});

	function login_selected(){
		mainNav.children('ul').removeClass('is-visible');
		formModal.addClass('is-visible');
		formLogin.addClass('is-selected');
		formSignup.removeClass('is-selected');
		formForgotPassword.removeClass('is-selected');
		tabLogin.addClass('selected');
		tabSignup.removeClass('selected');
	}

	function signup_selected(){
		mainNav.children('ul').removeClass('is-visible');
		formModal.addClass('is-visible');
		formLogin.removeClass('is-selected');
		formSignup.addClass('is-selected');
		formForgotPassword.removeClass('is-selected');
		tabLogin.removeClass('selected');
		tabSignup.addClass('selected');
	}

	function signout_selected() {
        window.location.href = "/";
    }

	function forgot_password_selected(){
		formLogin.removeClass('is-selected');
		formSignup.removeClass('is-selected');
		formForgotPassword.addClass('is-selected');
	}

	//LOGIN BUTTON submit
    formLogin.find('input[type="submit"]').on('click', function(event){
        event.preventDefault();

        var emailReg = /^([a-zA-Z0-9\\.]+)@([a-zA-Z0-9\\-\\_\\.]+)\.([a-zA-Z0-9]+)$/i;
        var error = false;

        if(!emailReg.test($("#signin-email").val())) {
            formLogin.find('input[type="email"]').toggleClass('has-error').next('span').html('Wrong email pattern!').toggleClass('is-visible');
            setTimeout(function () {formLogin.find('input[type="email"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        }
        if($('#signin-password').val() === '') {
            formLogin.find('input[type="password"]').toggleClass('has-error').next('a').next('span').html('Field is empty!').toggleClass('is-visible');
            setTimeout(function () {formLogin.find('input[type="password"]').removeClass('has-error').next('a').next('span').removeClass('is-visible');},3000);
            error = true;
        } else if($('#signin-password').val().length < 6) {
            formLogin.find('input[type="password"]').toggleClass('has-error').next('a').next('span').html('Too short password!').toggleClass('is-visible');
            setTimeout(function () {formLogin.find('input[type="password"]').removeClass('has-error').next('a').next('span').removeClass('is-visible');},3000);
            error = true;
        }

        if(!error) {
            var data = { id:0, username:'username', email: $("#signin-email").val(), password: $('#signin-password').val(), salt: ''};

            $.ajax({
                type: 'POST',
                url: '/authorization',
                data: JSON.stringify(data),
                contentType: "application/json",

                success: function (data) {
                    alert(data);
                    setTimeout(function () {
                        formModal.removeClass('is-visible');
                        $('#li-signout').show();
                        $('#li-signup').hide();
                        $('#li-signin').hide();
                        localStorage.setItem('loggedIn', 'true');
                        window.location.href = "maininfo.html";
                    }, 1000);
                },
                error: function (data) {
                    alert(data.responseText);
                }
            });
        }
    });

    //REGISTER BUTTON submit
    formSignup.find('input[type=submit]').on('click', function(event){
        event.preventDefault();
        var emailReg = /^([a-zA-Z0-9\\.]+)@([a-zA-Z0-9\\-\\_\\.]+)\.([a-zA-Z0-9]+)$/i;
        var error = false;

        if($('#signup-username').val() === '') {
            formSignup.find('input[type="text"]').toggleClass('has-error').next('span').html('Field is empty!').toggleClass('is-visible');
            setTimeout(function () {formSignup.find('input[type="text"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        } else if($('#signup-username').val().length < 4) {
            formSignup.find('input[type="text"]').toggleClass('has-error').next('span').html('Too short username!').toggleClass('is-visible');
            setTimeout(function () {formSignup.find('input[type="text"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        }
        if(!emailReg.test($("#signup-email").val())) {
            formSignup.find('input[type="email"]').toggleClass('has-error').next('span').html('Wrong email pattern!').toggleClass('is-visible');
            setTimeout(function () {formSignup.find('input[type="email"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        }
        if($('#signup-password').val() === '') {
            formSignup.find('input[type="password"]').toggleClass('has-error').next('a').next('span').html('Field is empty!').toggleClass('is-visible');
            setTimeout(function () {formSignup.find('input[type="password"]').removeClass('has-error').next('a').next('span').removeClass('is-visible');},3000);
            error = true;
        } else if($('#signup-password').val().length < 6) {
            formSignup.find('input[type="password"]').toggleClass('has-error').next('a').next('span').html('Too short password!').toggleClass('is-visible');
            setTimeout(function () {formSignup.find('input[type="password"]').removeClass('has-error').next('a').next('span').removeClass('is-visible');},3000);
            error = true;
        }

        if(!error) {

            var data = { id: 0, username: $('#signup-username').val(), email: $("#signup-email").val(), password: $('#signup-password').val(), salt: ''};
            // var data = { id: 0, username: 'name', email: 'value', password: 'pas'};

            $.ajax({
                type: 'POST',
                url: '/registration',
                data: JSON.stringify(data),
                contentType: "application/json",
                cache: false,

                success: function (data) {
                    alert(data);
                    setTimeout(function () {
                        formModal.removeClass('is-visible');
                        $('#li-signout').show();
                        $('#li-signup').hide();
                        $('#li-signin').hide();
                        localStorage.setItem('loggedIn', 'true');
                        window.location.href = "maininfo.html"
                    }, 1000);
                },
                error: function (data) {
                    alert(data.responseText);
                }
            });
        }

        // '/api/sum?a=' + a + '&b='+b
    });

    //UNIQUE DEVICE KEY LOGIN BUTTON submit
    formForgotPassword.find('input[type="submit"]').on('click', function(event){
        event.preventDefault();

        var error = false;


        if($('#reset-email').val() === "") {
            formForgotPassword.find('input[type="password"]').toggleClass('has-error').next('span').html('Field is empty!').toggleClass('is-visible');
            setTimeout(function () {formForgotPassword.find('input[type="password"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        } else if($('#reset-email').val().length != 10) {

            formForgotPassword.find('input[type="password"]').toggleClass('has-error').next('span').html('Key length must be equal to 10 characters!').toggleClass('is-visible');
            setTimeout(function () {formForgotPassword.find('input[type="password"]').removeClass('has-error').next('span').removeClass('is-visible');},3000);
            error = true;
        }

        if(!error) {
            var data = { 'device_code': $("#reset-email").val() };

            $.ajax({
                type: 'POST',
                url: '/deviceKeyLogin',
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",

                success: function (data) {
                    alert(data);
                    setTimeout(function () {
                        formModal.removeClass('is-visible');
                        $('#li-signout').show();
                        $('#li-signup').hide();
                        $('#li-signin').hide();
                        localStorage.setItem('loggedIn', 'true');
                        window.location.href = "profile.html";
                    }, 1000);
                },
                error: function (data) {
                    alert(data.responseText);
                }
            });


        }
    });


    //HANDLE IF USER WAS ALREADY LOGGED IN THEN GO TO MAP PAGE
    if(localStorage['loggedIn'] === 'true') {
        window.location.href = 'map.html';
    }

});


//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
	return this.each(function() {
    	// If this function exists...
    	if (this.setSelectionRange) {
      		// ... then use it (Doesn't work in IE)
      		// Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
      		var len = $(this).val().length * 2;
      		this.focus();
      		this.setSelectionRange(len, len);
    	} else {
    		// ... otherwise replace the contents with itself
    		// (Doesn't work in Google Chrome)
      		$(this).val($(this).val());
    	}
	});
};