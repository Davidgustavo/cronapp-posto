app.controller('AfterLoginController', function($scope) {
 $('body').addClass('MinhaClasse');
});

app.controller('AfterHomeController', function($scope) {
 $('body').addClass('MinhaClasse');
});

app.controller('AfterPageController', function($scope) {
 $('body').removeClass('MinhaClasse');
});