ControllerClass: ExampleController

HTTP method	 URI	 					Class.method	 parameters
GET	 		/example	 				ExampleController.list	 
POST	 	/example	 				ExampleController.create	 
POST 		/example/update		 		ExampleController.update	 
DELETE	 	/example/destroy/id	 		ExampleController.destroy	id="1"
GET	 		/example/show/id	 		ExampleController.show	 	id="1"
GET	 		/example/edit/id			ExampleController.edit	 	id="1"
GET	 		/example/edit-new	 		ExampleController.editNew	 