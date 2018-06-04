'use strict';
const express = require('express');
const router = express.Router();
const departuresController = require('../controllers/departures');
const othersController = require('../controllers/others');


// Flight pages
// GET HOME PAGE

router.get('/', departuresController.departureList);

// Get detailes bout ferries

router.get('/departures/brentwood', departuresController.departureBrentwood);
router.get('/departures/millbay', departuresController.departuremillbay);

// GET Reso page

router.get('/departures/reserve-brentwood', departuresController.reservebrentwood);
router.get('/departures/reserve-millbay', departuresController.reservemillbay);
router.get('/departures/reserve-ferry', departuresController.reserveferry);

router.get('/departures/neverland', departuresController.departureNeverland);
router.get('/departures/fantasia', departuresController.departureFantasia);


// OTHER

// GET ABOUT US

router.get('/about', othersController.about);


module.exports = router;

