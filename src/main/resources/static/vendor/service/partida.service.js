/**
 * Create service 27/04/2019
 */
stefanini.service('partidaService', ['$http', 
                                function($http){

	this.getHerois = function(uri){
		return $http.get(uri + '/duelo');
	};
	this.novoDuelo = function(uri){
		return $http.get(uri + '/duelo/novo');
	};

	this.getMutante = function(uri){
		return $http.get(uri + '/duelo/mutante');
	};

	this.getVencedores = function(uri){
		return $http.get(uri + '/duelo/vencedores');
	};

}]);