/**
 * Controller para add face group
 */
stefanini.controller('dueloController', ['$scope', '$state', '$cookieStore', 'util', '$http', 'dueloService',
    function($scope, $state, $cookieStore, util, $http, dueloService){
	
		$scope.messages = "";
		$scope.cssMessage = "";
		$scope.visibleMessage = false;
		$scope.herois = [];
		
		/**
		 * Method upload file
		 */
		$scope.duelar = function () {
			dueloService.novoDuelo(util.getUri());
			$scope.herois = [];
			$scope.messages = "Aguarde que estamos processando...";
			$scope.visibleMessage = true;
			$scope.cssMessage = "message-table-correct";
			dueloService.getDuelo(util.getUri()).success(function(data, status, headers, config) {
				$scope.herois.push(data);
				$scope.visibleMessage = false;
			}).error(function(data, status, headers, config) {
				$scope.messages = data.message;
				$scope.visibleMessage = true;
				$scope.cssMessage = "message-table-incorret";
			});

		};
		
}]);