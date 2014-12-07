/*'use strict';

 App Module 

var Dictionary = angular.module('Dictionary', ['ngRoute','DictionaryCtrls','ui.codemirror'
]);

Dictionary.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/home', {
        templateUrl: 'views/home.html'
        //controller: 'homeCtrl'
      }).
      when('/home/user', {
        templateUrl: 'views/upload.html',
        controller: 'uploadCtrl'
      }).
      when('/home/loggedin',{
        templateUrl: 'views/upload.jsp'
      }).
      otherwise({
        redirectTo: '/home'
      });
  }]);
*/