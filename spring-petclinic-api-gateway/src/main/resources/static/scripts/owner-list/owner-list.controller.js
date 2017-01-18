'use strict';

angular.module('ownerList')
    .controller('OwnerListController', ['$http', function ($http) {
        var self = this;

        $http.get('api/gateway/owners').then(function (resp) {
            self.failure = resp.data.failure;
            self.owners = resp.data.owners;
        });
    }]);
