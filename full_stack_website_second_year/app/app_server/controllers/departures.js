'use strict';
const request = require('request');

const apiOptions = {
  server: 'http://localhost:3000/api/'
};

if (process.env.NODE_ENV === 'production') {
  apiOptions.server = 
    'http://pasquale-ics211-finalproject.deploy.cs.camosun.bc.ca/api/'
};

const renderHomepage = function(req, res, data){
	let errorMsg = null;
	if(!(data instanceof Array)){
		errorMsg = 'API lookup eroor';
		data = [];
	};
	res.render('index', { 
		title: 'Brenthood Express',
			pageHeader: {
				title: 'Brenthood Express',
				tagline: 'Never drive the malahat again......at least most of it!!!!!!'
			},
			errorMsg: errorMsg,
			departures: data,
			// departures: [{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	frequency: 'Several Departures Daily',
			// 	reference: '/departures/brentwood'
			// },{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	frequency: 'Several Departures Daily',
			// 	reference: '/departures/millbay'
			// }],
			sidebar: 'Brenthood Express has many departure times which will change your commute forever! We always aim to please with a fast, easy, and reliable service!'

			
		});
};
const departureNeverland = function(req, res){
	const reqOptions = {
		baseUrl: apiOptions.server,
		url: '/departures/Neverland/departure-times',
		method: 'GET',
		json: {}
	};
	request( reqOptions, function(err, apiRes, apiResBody){
		if (err) {
      console.log(err);
    } else if (apiRes.statusCode === 200) {
      console.log(apiResBody);
    } else {
      console.log(apiRes.statusCode);
    }

		renderdepartureNeverland(req, res, apiResBody);
	});

};
const renderdepartureNeverland = function(req, res, data){
	res.render('departure-neverland', { 
		title: 'To Neverland!',
			pageHeader: {
				title: 'Brentwood Departures',
				tagline: 'One way ticks to Neverland!'
			},
			departures: data

		});
};
const departureFantasia = function(req, res){
	const reqOptions = {
		baseUrl: apiOptions.server,
		url: '/departures/Fantasia/departure-times',
		method: 'GET',
		json: {}
	};
	request( reqOptions, function(err, apiRes, apiResBody){
		if (err) {
      console.log(err);
    } else if (apiRes.statusCode === 200) {
      console.log(apiResBody);
    } else {
      console.log(apiRes.statusCode);
    }

		renderdepartureFantasia(req, res, apiResBody);
	});

};
const renderdepartureFantasia = function(req, res, data){
	res.render('departure-fantasia', { 
		title: 'Fantasia!',
			pageHeader: {
				title: 'Mill Bay Departures',
				tagline: 'Next Stop Fantasia!'
			},
			departures: data

		});
};
const departureList = function(req, res){
	const reqOptions = {
		baseUrl: apiOptions.server,
		url: '/departures',
		method: 'GET',
		json: {}
	};
	request( reqOptions, function(err, apiRes, apiResBody){
		if (err) {
      console.log(err);
    } else if (apiRes.statusCode === 200) {
      console.log(apiResBody);
    } else {
      console.log(apiRes.statusCode);
    }

		renderHomepage(req, res, apiResBody);
	});
	
};
const departureBrentwood = function(req, res){
	const reqOptions = {
		baseUrl: apiOptions.server,
		url: '/departures/MillBay/departure-times',
		method: 'GET',
		json: {}
	};
	request( reqOptions, function(err, apiRes, apiResBody){
		if (err) {
      console.log(err);
    } else if (apiRes.statusCode === 200) {
      console.log(apiResBody);
    } else {
      console.log(apiRes.statusCode);
    }

		renderdepartureBrentwood(req, res, apiResBody);
	});
};

const renderdepartureBrentwood = function(req, res, data){
	res.render('departure-brentwood', { 
		title: 'Brentwood Departures',
			pageHeader: {
				title: 'Brentwood Departures',
				tagline: 'Brentwood Bay to Mill Bay departure times'
			},
			departures: data
			// departures: [{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '8:00am'
			// },{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '10:00am'
			// },{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '12:00pm'
			// },{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '2:00pm'
			// }, {
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '4:00pm'
			// },{
			// 	origin: 'Brentwood Bay',
			// 	destination: 'Mill Bay',
			// 	time: '6:00pm'
			// }]
		});
};
const departuremillbay = function(req, res){
	const reqOptions = {
		baseUrl: apiOptions.server,
		url: '/departures/BrentwoodBay/departure-times',
		method: 'GET',
		json: {}
	};
	request( reqOptions, function(err, apiRes, apiResBody){
		if (err) {
      console.log(err);
    } else if (apiRes.statusCode === 200) {
      console.log(apiResBody);
    } else {
      console.log(apiRes.statusCode);
    }

		renderdeparturemillbay(req, res, apiResBody);
	});
};
const renderdeparturemillbay = function(req, res, data){
	res.render('departure-millbay', { 
		title: 'Mill Bay Departures',
			pageHeader: {
				title: 'Mill Bay Departures',
				tagline: 'Mill Bay to Brentwood Bay departure times'
			},
			departures: data
			// departures: [{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '8:00am'
			// },{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '10:00am'
			// },{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '12:00pm'
			// },{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '2:00pm'
			// }, {
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '4:00pm'
			// },{
			// 	origin: 'Mill Bay',
			// 	destination: 'Brentwood Bay',
			// 	time: '6:00pm'
			// }
			// ]
		});
};
const reservebrentwood = function(req, res){
	res.render('reserve-brentwood', { 
		title: 'Reserve brentwood',
			pageHeader:{
				title: 'Reserve Brentwood',
				tagline: 'Reserve going from Brentwood to Mill Bay'
			}});
};
const reservemillbay = function(req, res){
	res.render('reserve-millbay', { 
		title: 'Reserve Mill Bay',
			pageHeader: {
				title: 'Reserve Mill Bay',
				tagline: ' Reserve going from Mill Bay to Brentwood'
			}});
};
const reserveferry = function(req, res){
	res.render('reserve-ferry', { 
		title: 'Reservations',
			pageHeader: {
				title: 'Reservations',
				tagline: 'Make your Reservation Today!!'
			},
			date: {
				today: function(){
					let d = new Date();
					let dd = d.toDateString();
					return dd;

				}(),
				tomorrow: function(){
					let d = new Date();
					let day = d.getDate();
					d.setDate(day+1);
					return d.toDateString();
				}(),
				dayAfter: function(){
					let d = new Date();
					let day = d.getDate();
					d.setDate(day + 2);
					return d.toDateString();
				}()

			},
			departure: [{
				time: '8:00am'
			},{
				time: '10:00am'
			},{
				time: '12:00pm'
			},{
				time: '2:00pm'
			},{
				time: '4:00pm'
			},{
				time: '6:00pm'
			}
			]
		});
};



module.exports = {
	departureList,
	departureBrentwood,
	departuremillbay,
	reservebrentwood,
	reservemillbay,
	reserveferry,
	departureNeverland,
	departureFantasia
};