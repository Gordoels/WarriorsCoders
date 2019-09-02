/**
	Abstract for construction commom in user case
*/
stefanini.controller('abstractController', ['$scope', '$state', 
    function($scope, $state) {
      
	  $scope.initial = function(){
	    $state.go('/', {});
	  };
	  $scope.partida = function(){
	    $state.go('partida', {});
	  };
	  $scope.historico = function(){
	    $state.go('historico', {});
	  };
	  
}]).directive('menu', function() {
  return {
  	restrict: 'E',
    templateUrl: 'template/menu.html'
  };
});