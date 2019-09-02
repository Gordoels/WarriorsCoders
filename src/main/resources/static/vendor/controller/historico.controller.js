stefanini.controller('historicoController', ['$scope', '$state', '$cookieStore', 'util', '$http', 'historicoService',
	function ($scope, $state, $cookieStore, util, $http, historicoService) {

		$scope.duelos = [];
		$scope.mensagem = "";
		$scope.visibleMensagem = false;

		historico = function () {

			if ($scope.mensagem.length == 0) {

				$scope.mensagem = "";
				$scope.visibleMensagem = true;
			} else {
				$scope.visibleMensagem = false;
			}
			historicoService.getHistorico(util.getUri()).success(function (data, status, headers, config) {
				$scope.duelos = data;
			}).error(function (data, status, headers, config) {
				$scope.mensagem = data.message;
			});

		};
		historico();


	}]);