var DictionaryCtrls = angular.module('DictionaryCtrls', []);

Dictionary.controller('uploadCtrl', function ($scope, $http) {
 /* $http.get('../data/upload.json').success(function(data) {
    $scope.files = data;
  });
*/
  $scope.editor= false;

  $scope.editorOptions = {
        lineWrapping : true,
        lineNumbers: true,
        theme:'night',
        //readOnly: 'nocursor',
        mode: 'xml',
    };

    $scope.isSomething=true;

    $scope.editor= function(){
      var code = $scope.code;
      console.log(code);
      $scope.editor=false;
    }



    $scope.new_item= function(){
      var folder_append="<label>+ new_Folder </label>"
      //var input="<input>"
     var element = document.getElementsByClassName("add-folder");
     //folder_append.className=folder_append+ "add-folder";
     angular.element(element).append(folder_append);
      console.log(element);
    }


    $scope.new_file= function(){
      var file_append="new_file<br>";

      var element = document.getElementById("add-file");

      angular.element(element).append(file_append);
    }


 
});

/* 
Dictionary.controller('homeCtrl', function ($scope, $http){

 $scope.new_user= function(){
    console.log($scope.user);
  var data= $scope.user;
  var hello= JSON.stringify(data);
  console.log(hello)
  $http.post('/login/web/register_process.jsp', hello).success(function(){
    console.log("bb");
  });

  }
});

*/
Dictionary.directive('homeheady', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/header.html'
    };
  });

Dictionary.directive('heady', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/header.html'
    };
  });



Dictionary.directive('files', function(){
	return{
		restrict: 'E',
		templateUrl: 'views/fileView.html'
	};
});