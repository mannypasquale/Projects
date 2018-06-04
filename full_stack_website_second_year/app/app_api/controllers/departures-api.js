'use strict';
const mongoose = require('mongoose');
const departureModel = mongoose.model('departure');


const departureReadOne = function(req, res){
	if (req.params && req.params.origin){
	    departureModel
	    	.findById(req.params.origin)
	    	.exec(function(err, departure){
	    		if (!departure){
	    			res
	    				.status(404)
	    				.json({
	    					"message": "departure not found",
	    					"origin": req.params.origin,
	    					err
	    				});
	    			return;	
	    		}
	    		if (err){
	    			res
	    				.status(404)
	    				.json(err);
	    			return;
	    		}
	    		res
	    			.status(200)
	    			.json('here' + departure);
	    	});
	    }else{
	    	res
	    		.status(200)
	    		.json({
	    			"message": "No id in request"
	    		});
	    }
};
const departureListByOrigin = function(req, res) {
	departureModel
		.find( {}, {origin: 1, destination: 1})
		.sort({'origin': -1})
		.exec(function(err, departures){
			if (err){
				res
					.status(404)
					.json(err);
			}else{
				res
					.status(200)
					.json(departures);
			}
		});

};
const departureCreate = function(req, res) {};
const departureGetTimesAll = function(req,res){
	if (req.params && req.params.destination){
	    departureModel
	    	.findOne({'destination': req.params.destination })
	    	.exec(function(err, departure){
	    		if (!departure){
	    			res
	    				.status(404)
	    				.json({
	    					"message": "departure not found",
	    					"origin": req.params.destination,
	    					err
	    				});
	    			return;	
	    		}
	    		if (err){
	    			res
	    				.status(404)
	    				.json({
	    					"message": "hello"
	    				});
	    			return;
	    		}
	    		const departureTimesAll = departure.departureTimes;
	    		res
	    			.status(200)
	    			.json(departureTimesAll);
	    	});
	    }else{
	    	res
	    		.status(200)
	    		.json({
	    			"message": "No id in request"
	    		});
	    }
	};
const departureGetTime = function(req,res){
	if (req.params && req.params.origin){
	    departureModel
	    	.findById(req.params.origin)
	    	.exec(function(err, departure){
	    		if (!departure){
	    			res
	    				.status(404)
	    				.json({
	    					"message": "departure not found",
	    					"origin": req.params.origin,
	    					err
	    				});
	    			return;	
	    		}
	    		if (err){
	    			res
	    				.status(404)
	    				.json(err);
	    			return;
	    		}
	    		const departureTime = departure.departureTimes.id(req.params.departuretimeid);
	    		res
	    			.status(200)
	    			.json(departureTime);
	    	});
	    }else{
	    	res
	    		.status(200)
	    		.json({
	    			"message": "No id in request"
	    		});
	    }
	};

module.exports = {
	departureReadOne,
    departureListByOrigin,
    departureCreate,
    departureGetTimesAll,
    departureGetTime
    
};