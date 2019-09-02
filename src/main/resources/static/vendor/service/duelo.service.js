/**
 * Create service 27/04/2019
 */
stefanini.service('dueloService', ['$http', 
                                function($http){
	/**
	 * carrega os personagens
	 */
	this.getDuelo = function(uri){
		return $http.get(uri + '/duelo');
	};

	this.novoDuelo = function(uri){
		return $http.get(uri + '/duelo/novo');
	};

	this.getMutante = function(uri){
		return $http.get(uri + '/duelo/mutante');
	};
}]);