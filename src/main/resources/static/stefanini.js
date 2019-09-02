/**
 * Created by Paulo on 27/04/2019.
 */
var stefanini = angular.module('stefanini',['ngResource', 'ngRoute', 'ngCookies', 'ui.router',
	'ngAnimate', 'ngProgress']);

stefanini.config(['$stateProvider', '$urlRouterProvider', '$routeProvider', function($stateProvider, $urlRouterProvider, $routeProvider) {
	 
	$stateProvider.state('/', {
	   url: '/',
	   templateUrl : 'pages/heroi/partida.html',
	   controller : 'dueloController',
	}).state('partida', {
	   url: '/partida',
	   templateUrl : 'pages/heroi/partida.html',
	   controller : 'partidaController',
	}).state('historico', {
	   url: '/historico',
	   templateUrl : 'pages/heroi/historico.html',
	   controller : 'historicoController',
	});
	
   $urlRouterProvider.otherwise('/');
}]);