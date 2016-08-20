/**
 * Created by puja on 8/16/16.
 */
(function () {
    'use strict';

    angular
        .module('movieflix')
        .directive('compareTo', compareTo);

    function compareTo() {
        var directive = {
            require: "ngModel",
            scope: {
                otherModelValue: "=compareTo"
            },
            link: function (scope, element, attributes, ngModel) {

                ngModel.$validators.compareTo = function (modelValue) {
                    return modelValue == scope.otherModelValue;
                };

                scope.$watch("otherModelValue", function () {
                    ngModel.$validate();
                });
            }
        };

        return directive;
    }


})();
