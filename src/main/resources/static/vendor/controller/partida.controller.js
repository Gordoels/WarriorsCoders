/**
 * Controller para add face group
 */
stefanini.controller('partidaController', ['$scope', '$state', '$cookieStore', 'util', '$http', 'partidaService',
	function ($scope, $state, $cookieStore, util, $http, partidaService, $anchorScroll) {

		$scope.messages = "";
		$scope.cssMessage = "";
		$scope.visibleMessage = false;
		$scope.visibleBotaoDuelo = true;
		$scope.visibleBotaoMutante = false;
		$scope.herois = [];
		$scope.qtdDuelos = 0;
		$scope.visibleMutante = false;
		$scope.visibleCollapse = false;
		$scope.mutante = {};
		$scope.vencedores = [];
		$scope.visibleVencedores = false;

		resetar = function () {
			partidaService.novoDuelo(util.getUri());
		}
		resetar();

		$scope.duelar = function () {
			$scope.messages = "Aguarde que estamos processando...";
			$scope.visibleMessage = true;
			$scope.cssMessage = "message-table-correct";
			partidaService.getHerois(util.getUri()).success(function (data, status, headers, config) {
				++$scope.qtdDuelos;
				$scope.herois.push(data);
				console.log($scope.herois);
				$scope.visibleMessage = false;
			}).error(function (data, status, headers, config) {
				$scope.messages = data.message;
				$scope.visibleMessage = true;
				$scope.cssMessage = "message-table-incorret";
			});
			if ($scope.qtdDuelos == 9) {
				$scope.visibleBotaoDuelo = false;
				$scope.visibleBotaoMutante = true;
				$scope.getVencedores();
				return;
			}
		};

		$scope.criarMutante = function () {

			partidaService.getMutante(util.getUri()).success(function (data, status, headers, config) {
				$scope.mutante = data;
				$scope.visibleMessage = false;
				$scope.visibleMutante = true;
				$scope.visibleBotaoMutante = false;
			}).error(function (data, status, headers, config) {
				$scope.messages = data.message;
				$scope.visibleMessage = true;
				$scope.cssMessage = "message-table-incorret";
			});
		};

		$scope.getVencedores = function () {

			partidaService.getVencedores(util.getUri()).success(function (data, status, headers, config) {
				$scope.vencedores = data;
				$scope.visibleVencedores = true;
				$scope.visibleMessage = false;
			}).error(function (data, status, headers, config) {
				$scope.messages = data.message;
				$scope.visibleMessage = true;
				$scope.cssMessage = "message-table-incorret";
			});
		};
	}]);