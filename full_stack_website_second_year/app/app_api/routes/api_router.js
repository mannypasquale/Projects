'use strict';
const express = require('express');
const router = express.Router();

const departuresAPI = require('../controllers/departures-api');

router
    .route('/departures-brentwood/:origin')
    .get(departuresAPI.departureReadOne)
    .post(departuresAPI.departureReadOne);

router
    .route('/departures-millbay/:origin')
    .get(departuresAPI.departureReadOne)
    .post(departuresAPI.departureReadOne);
    
router
	.route('/departures')
	.get(departuresAPI.departureListByOrigin)
	.post(departuresAPI.departureListByOrigin);

router
	.route('/departures/:origin/departure-times/:departuretimeid')
	.get(departuresAPI.departureGetTime)
	.post(departuresAPI.departureGetTime);

router
	.route('/departures/:destination/departure-times')
	.get(departuresAPI.departureGetTimesAll)
	.post(departuresAPI.departureGetTimesAll);


module.exports = router