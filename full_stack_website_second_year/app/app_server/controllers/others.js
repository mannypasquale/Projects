'use strict';
const request = require('request');

const apiOptions = {
  server: 'http://localhost:3000/api/'
};

if (process.env.NODE_ENV === 'production') {
  apiOptions.server = 
    'http://pasquale-ics211-finalproject.deploy.cs.camosun.bc.ca/api/'
};
const about = function(req, res) {
	res.render('about', { 
		title: 'About Brenthood Express',
			aboutUs: 'THIS WILL BE A LOT BIGGER UBT FOR NOW WE BE KILLING THE GAME AND MAKING YOUR COMMUTE BETTER SINCE 1927'
		});
};

module.exports = {
	about
};