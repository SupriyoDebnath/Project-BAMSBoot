(function($) {
	
	//Dynamic Implementations -- Angular

	var avatarName = "NA";
	var app = angular.module('avengersportalUIModule', ['ngResource']);

	app.factory('avengersportalAPIFactory', function($resource) {
		
		//var avengersportalAPIResource = $resource('http://localhost:8090/avengersportal-apifactory/microservices/Agent/:agentId', 
		var avengersportalAPIResource = $resource('http://Riyo14-LB-48091037.ap-south-1.elb.amazonaws.com:81/avengersportal-apifactory/microservices/Agent/:agentId', 
				
				{agentId : '@profileId'}, 
				
				{
					 getProfileCount : {
						method : 'GET'
					 },
				
					 getProfile : {
						method : 'GET'		 
					 },
				
					 saveProfile : {
						 method : 'POST'
					 }
				});

		return avengersportalAPIResource;
		//return null;
	}); 

	app.controller('RegistrationController', function($scope, $window, $resource, avengersportalAPIFactory, jsonFilter) {
		
		//console.log("I'm IN Controller");
		$scope.FACEBOOK = "https://www.facebook.com/Riyo.shameless";
		$scope.LINKEDIN = "https://www.linkedin.com/in/supriyodebnath/";
		$scope.GITHUB = "https://github.com/SupriyoDebnath";
		$scope.SOURCECODE = "https://github.com/SupriyoDebnath/Project-BAMSBoot";

		$scope.agentId = "";
		$scope.agentCountResponse = avengersportalAPIFactory.getProfileCount();
		$scope.agentCountResponse.$promise.then(function(result) {
			$scope.agentCount = result.ProfileCount + " New Agents Have Registered";
		});
		
		$scope.agentCount = "";
		$scope.registration = {};
		
		$scope.saveTNRegistration = function() {
			
			if(avatarName == "NA" || $scope.registration.agentName == null ||  $scope.registration.agentLocation == null ||  $scope.registration.agentSkill == null) {
				
				alert("All Data Must be Provided to Sign Up for the Cause");
			}else {
				
				
				$scope.registration.agentTeamName = "Team Natasha";
				$scope.registration.agentAvatarId = avatarName;
				$scope.profileSaveResponse = avengersportalAPIFactory.saveProfile($scope.registration);
				$scope.profileSaveResponse.$promise.then(function(result) {
					$scope.agentId = result.AgentId;
					$window.document.getElementById("resultTNRegistration").click();
				}); 
				
			};
			
		};
		
		$scope.saveTSRegistration = function() {
			
			if(avatarName == "NA" || $scope.registration.agentName == null ||  $scope.registration.agentLocation == null ||  $scope.registration.agentSkill == null) {
				
				alert("All Data Must be Provided to Sign Up for the Cause");
			}else {
				
				$scope.registration.agentTeamName = "Team Stark";
				$scope.registration.agentAvatarId = avatarName;
				$scope.profileSaveResponse = avengersportalAPIFactory.saveProfile($scope.registration);
				$scope.profileSaveResponse.$promise.then(function(result) {
					$scope.agentId = result.AgentId;
					$window.document.getElementById("resultTSRegistration").click();
				});
			};
			
		};
		
		$scope.agent = {};
		$scope.getProfile = function() {
			
			if($scope.agent.profileId == null) {
				
				alert("Are you nuts?! Who do you think would give the Agaent Id??");
			}else {
				
				$scope.profileGetResponse = avengersportalAPIFactory.getProfile({agentId : $scope.agent.profileId});
				
				$scope.profileGetResponse.$promise.then(function(result) {
					
					$scope.agent = result;
					
					if($scope.agent.agentAvatarId.endsWith(".jpg")) {
						
						$scope.agent.profileAvatarStr = "data:image/jpeg;base64,".concat($scope.agent.agentAvatarBase64Encoded);
					} else if($scope.agent.agentAvatarId.endsWith(".png")) {
						
						$scope.agent.profileAvatarStr = "data:image/png;base64,".concat($scope.agent.agentAvatarBase64Encoded);
					} else if($scope.agent.agentAvatarId.endsWith(".gif")) {
						
						$scope.agent.profileAvatarStr = "data:image/gif;base64,".concat($scope.agent.agentAvatarBase64Encoded);
					} else {
						
						$scope.agent.profileAvatarStr = "";
					};
					
					$window.document.getElementById("redirectProfileModal").click();
				}, function(error) {
					
					alert("We could not find your Profile in system. Please register one more time");
				});
				
			};
		
		};

	});
	
	
	//Static Implementations -- Bootstrap, JQuery
	"use strict"; // Start of use strict

  // Smooth scrolling using jQuery easing
  $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: (target.offset().top - 54)
        }, 1000, "easeInOutExpo");
        return false;
      }
    }
  });

  // Closes responsive menu when a scroll trigger link is clicked
  $('.js-scroll-trigger').click(function() {
    $('.navbar-collapse').collapse('hide');
  });

  // Activate scrollspy to add active class to navbar items on scroll
  $('body').scrollspy({
    target: '#mainNav',
    offset: 56
  });

  // Collapse Navbar
  var navbarCollapse = function() {
    if ($("#mainNav").offset().top > 100) {
      $("#mainNav").addClass("navbar-shrink");
    } else {
      $("#mainNav").removeClass("navbar-shrink");
    }
  };
  // Collapse now if page is not at top
  navbarCollapse();
  // Collapse the navbar when page is scrolled
  $(window).scroll(navbarCollapse);

  // Hide navbar when modals trigger
  $('.registration-modal').on('show.bs.modal', function(e) {
    $(".navbar").addClass("d-none");
  });
  
  $('.registration-modal').on('hidden.bs.modal', function(e) {
    $(".navbar").removeClass("d-none");
  });
  
  
  
  // File Upload Plugin
  $("#avatar-tn").fileinput({
	  	uploadUrl: "/avengersportal-webartifacts/webhook/profileAvatar",
	  	uploadAsync: true,
	  	showBrowse: false,
	  	overwriteInitial: true,
	  	initialPreviewAsData: true,
	    maxFileSize: 2048, // 2 MB
	    browseOnZoneClick: true,
	    uploadLabel: '',
	    uploadIcon: '<i class="fa fa-upload"></i>',
	    uploadTitle: 'Click to Confirm',
	    elErrorContainer: '#avatar-span-error',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="images/default-avatar.png" alt="Your Avatar"><h6 class="text-muted">Click to Select (jpg/png/gif within 2MB size)</h6>',
	    layoutTemplates: {main1: '{preview}\n{upload}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	}).on('fileuploaded', function(event, data, id, index) {
		//console.log(data);
		//console.log(data.response.AvatarName);
		avatarName = data.response.AvatarName;
		//console.log(avatarName);
	});
  
  $("#avatar-ts").fileinput({
	  	uploadUrl: "/avengersportal-webartifacts/webhook/profileAvatar",
	  	uploadAsync: true,
	  	showBrowse: false,
	  	overwriteInitial: true,
	  	initialPreviewAsData: true,
	    maxFileSize: 2048, // 2 MB
	    browseOnZoneClick: true,
	    uploadLabel: '',
	    uploadIcon: '<i class="fa fa-upload"></i>',
	    uploadTitle: 'Click to Confirm',
	    elErrorContainer: '#avatar-span-error',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="images/default-avatar.png" alt="Your Avatar"><h6 class="text-muted">Click to Select (jpg/png/gif within 2MB size)</h6>',
	    layoutTemplates: {main1: '{preview}\n{upload}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	}).on('fileuploaded', function(event, data, id, index) {
		//console.log(data);
		//console.log(data.response.AvatarName);
		avatarName = data.response.AvatarName;
		//console.log(avatarName);
	});

})(jQuery); // End of use strict
