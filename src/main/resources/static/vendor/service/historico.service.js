stefanini.service('historicoService', ['$http',
    function ($http) {
        /**
         * carrega os personagens
         */

        this.getHistorico = function (uri) {
            return $http.get(uri + '/duelo/historico');
        };
    }]);